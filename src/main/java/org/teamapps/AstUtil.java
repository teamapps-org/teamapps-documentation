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

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.teamapps.documentation.antlr.java9.Java9Parser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AstUtil {

	public static String getStringAnnotationValue(Java9Parser.NormalAnnotationContext annotation, String attributeName) {
		Java9Parser.ElementValueContext annotationValue = getAnnotationValue(annotation, attributeName);
		if (annotationValue != null) {
			String title = annotationValue.getText();
			return title.substring(1, title.length() - 1);
		} else {
			return null;
		}
	}

	public static List<String> getStringArrayAnnotationValue(Java9Parser.NormalAnnotationContext annotation, String attributeName) {
		Java9Parser.ElementValueContext annotationValue = getAnnotationValue(annotation, attributeName);
		if (annotationValue != null) {
			if (annotationValue.elementValueArrayInitializer() != null
					&& annotationValue.elementValueArrayInitializer().elementValueList() != null) {
				return annotationValue.elementValueArrayInitializer().elementValueList().elementValue().stream()
						.map(ev -> ev.getText().substring(1, ev.getText().length() - 1))
						.collect(Collectors.toList());
			} else {
				return Collections.singletonList(getStringAnnotationValue(annotation, attributeName));
			}
		} else {
			return Collections.emptyList();
		}
	}

	public static Boolean getBooleanAnnotationValue(Java9Parser.NormalAnnotationContext annotation, String attributeName, boolean defaultValue) {
		Java9Parser.ElementValueContext value = getAnnotationValue(annotation, attributeName);
		if (value != null) {
			return Boolean.parseBoolean(value.getText());
		} else {
			return defaultValue;
		}
	}

	public static Java9Parser.ElementValueContext getAnnotationValue(Java9Parser.NormalAnnotationContext annotation, String attributeName) {
		return annotation.elementValuePairList().elementValuePair().stream()
				.filter(elementValuePairContext -> elementValuePairContext.identifier().getText().equals(attributeName))
				.map(elementValuePairContext -> elementValuePairContext.elementValue())
				.findFirst().orElse(null);
	}

	public static String getFullText(ParserRuleContext context) {
		if (context.getStart() == null || context.getStop() == null || context.getStart().getStartIndex() < 0 || context.getStop().getStopIndex() < 0) {
			return context.getText(); // Fallback
		}
		return context.getStart().getInputStream().getText(Interval.of(context.getStart().getStartIndex(), context.getStop().getStopIndex()));
	}

}
