package org.teamapps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teamapps.common.util.ExceptionUtil;
import org.teamapps.config.TeamAppsConfiguration;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.server.jetty.embedded.TeamAppsJettyEmbeddedServer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresource.ClassLoaderTemplateResource;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class DocumentationServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final Pattern FILE_SUFFIX_PATTERN = Pattern.compile(".*\\..*");

	public static void main(String[] args) throws Exception {
		TeamAppsConfiguration teamAppsConfiguration = new TeamAppsConfiguration();
		Path webappDirectory = Files.createDirectories(Path.of("webapp"));
		TeamAppsJettyEmbeddedServer server = new TeamAppsJettyEmbeddedServer(sessionContext -> {
			System.out.println(sessionContext.getClientInfo().getClientParameters());
			String exampleClasses = (String) sessionContext.getClientInfo().getClientParameters().get("exampleClasses");
			List<String> exampleClassNames = ExceptionUtil.softenExceptions(() -> new ObjectMapper().readValue(exampleClasses, new TypeReference<List<String>>() {
			}));
			exampleClassNames.forEach(className -> ExceptionUtil.softenExceptions(() -> {
				Example example = (Example) DocumentationServer.class.getClassLoader().loadClass(className).getConstructor().newInstance();
				example.onSessionStart(new SessionContext(sessionContext, "#" + className.replace('.', '-')));
			}));
			System.out.println(exampleClasses);
//			DocumentationServer.class.getClassLoader().loadClass()
		}, webappDirectory.toFile(), teamAppsConfiguration, 8087);

		server.addServletContextListener(new MyServletContextListener());

		server.start();
	}

	private static class MyServletContextListener implements ServletContextListener {

		private final TemplateEngine templateEngine;

		public MyServletContextListener() {
			TemplateEngine templateEngine = new TemplateEngine();
			ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
			resolver.setCharacterEncoding("UTF-8");
			resolver.setTemplateMode(TemplateMode.HTML);
			resolver.setPrefix("templates/");
			resolver.setSuffix(".html");
			resolver.setUseDecoupledLogic(true);
			templateEngine.setTemplateResolver(resolver);
			templateEngine.setDecoupledTemplateLogicResolver((configuration, ownerTemplate, template, templateSelectors, resource, templateMode) -> {
				LOGGER.info("Adding decoupled template logic to {}", template);
				return new ClassLoaderTemplateResource(null, "templates/template-main.th.html", "UTF-8");
			});
			this.templateEngine = templateEngine;
		}

		@Override
		public void contextInitialized(ServletContextEvent servletContextEvent) {
			ServletContext servletContext = servletContextEvent.getServletContext();
			ServletRegistration.Dynamic dynamic = servletContext.addServlet("pages", new HttpServlet() {
				@Override
				protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// all resources that have file suffixes are static resources and are therefore delegated to the default servlet
					if (!FILE_SUFFIX_PATTERN.matcher(req.getPathInfo()).matches()) { // all resources without file suffix are thymeleaf templates
						String template = req.getPathInfo().startsWith("/") ? req.getPathInfo().substring(1) : req.getPathInfo();
						if (template.length() == 0) {
							template = "index";
						}
						Context context = new Context();
						context.setVariable("exampleResolver", new ExampleResolver());
						templateEngine.process(template, context, resp.getWriter());
					} else if (req.getPathInfo().contains("/res/")) {
						String resourceName = "templates" + req.getPathInfo();
						System.out.println(resourceName);
						InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resourceName);
						new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8).transferTo(resp.getWriter());
					} else {
						getServletContext().getNamedDispatcher("default").forward(req, resp);
					}
				}
			});
			dynamic.addMapping("/*");
		}

		@Override
		public void contextDestroyed(ServletContextEvent servletContextEvent) {
		}
	}
}
