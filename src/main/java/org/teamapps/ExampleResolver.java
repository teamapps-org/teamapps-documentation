package org.teamapps;

import org.apache.commons.lang3.StringUtils;
import org.teamapps.documentation.antlr.java.JavaParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.teamapps.AstUtil.getFullText;
import static org.teamapps.StringUtil.normalizeIndentation;

public class ExampleResolver {

	public String getSourceCode(String exampleClassName) {
		try {
			JavaParser parser = ParserUtil.createJavaParser(
					new InputStreamReader(getClass().getResourceAsStream("/" + exampleClassName.replace('.', '/') + ".java"), StandardCharsets.UTF_8));
			List<JavaParser.TypeDeclarationContext> typeDeclarations = parser.compilationUnit().typeDeclaration();
			JavaParser.ClassBodyContext classBody = typeDeclarations.stream()
					.filter(this::isPublicClass)
					.findFirst()
					.map(td -> td.classDeclaration().classBody()).orElse(null);

			String bodyText = getFullText(classBody);
			bodyText = bodyText.substring(1, bodyText.length() - 1);
			return StringUtils.trim(normalizeIndentation(bodyText));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isPublicClass(JavaParser.TypeDeclarationContext td) {
		if (td.classDeclaration() == null) {
			return false;
		}
		return td.classOrInterfaceModifier().stream().anyMatch(m -> m.PUBLIC() != null);
	}

	public String escapeClassName(String exampleClassName) {
		return exampleClassName.replace('.', '-');
	}
}
