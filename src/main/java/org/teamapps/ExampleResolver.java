package org.teamapps;

import org.apache.commons.lang3.StringUtils;
import org.teamapps.documentation.antlr.java9.Java9Parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.teamapps.AstUtil.getFullText;
import static org.teamapps.StringUtil.normalizeIndentation;

public class ExampleResolver {

	public String getSourceCode(String exampleClassName) {
		try {
			Java9Parser parser = ParserUtil.createJava9arser(
					new InputStreamReader(getClass().getResourceAsStream("/" + exampleClassName.replace('.', '/') + ".java"), StandardCharsets.UTF_8));
			Java9Parser.OrdinaryCompilationContext compilationContext = parser.ordinaryCompilation();
			Java9Parser.NormalClassDeclarationContext classDecl = compilationContext.typeDeclaration().stream()
					.filter(this::isPublicClass)
					.findFirst()
					.map(td -> td.classDeclaration().normalClassDeclaration()).orElse(null);

			String bodyText = getFullText(classDecl.classBody());
			bodyText = bodyText.substring(1, bodyText.length() - 1);
			return StringUtils.trim(normalizeIndentation(bodyText));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isPublicClass(Java9Parser.TypeDeclarationContext td) {
		return td.classDeclaration() != null
				&& td.classDeclaration().normalClassDeclaration() != null
				&& td.classDeclaration().normalClassDeclaration().classModifier().stream().anyMatch(m -> m.PUBLIC() != null);
	}

	public String escapeClassName(String exampleClassName) {
		return exampleClassName.replace('.', '-');
	}
}
