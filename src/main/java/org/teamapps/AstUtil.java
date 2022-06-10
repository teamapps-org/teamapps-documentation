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

public class AstUtil {

	public static String getFullText(ParserRuleContext context) {
		if (context.getStart() == null || context.getStop() == null || context.getStart().getStartIndex() < 0 || context.getStop().getStopIndex() < 0) {
			return context.getText(); // Fallback
		}
		return context.getStart().getInputStream().getText(Interval.of(context.getStart().getStartIndex(), context.getStop().getStopIndex()));
	}

}
