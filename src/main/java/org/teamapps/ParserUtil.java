/*-
 * ========================LICENSE_START=================================
 * TeamApps
 * ---
 * Copyright (C) 2014 - 2021 TeamApps.org
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package org.teamapps;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.teamapps.documentation.antlr.java.JavaLexer;
import org.teamapps.documentation.antlr.java.JavaParser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

public class ParserUtil {

	public static List<File> getFilesInDirectory(File sourceDir) {
		File[] files = sourceDir.listFiles();
		if (files == null) {
			throw new IllegalArgumentException("Directory does not exist: " + sourceDir);
		}
		return Arrays.asList(files);
	}

	public static JavaParser createJavaParser(Reader reader) throws IOException {
		JavaLexer lexer = new JavaLexer(CharStreams.fromReader(reader));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JavaParser parser = new JavaParser(tokens);
		parser.addErrorListener(new ThrowingErrorListener());
		parser.setBuildParseTree(true);
		return parser;
	}

	public static class ThrowingErrorListener extends BaseErrorListener {
		@Override
		public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
				throws ParseCancellationException {
			throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
		}
	}
}
