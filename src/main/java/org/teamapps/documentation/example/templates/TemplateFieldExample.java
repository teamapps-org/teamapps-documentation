package org.teamapps.documentation.example.templates;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.data.Genus;
import org.teamapps.documentation.example.data.Species;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.TemplateField;
import org.teamapps.ux.component.template.BaseTemplate;

import java.util.Map;


public class TemplateFieldExample implements Example {
	@Override
	public void onSessionStart(SessionContext sessionContext) {
		Species bearRecord = new Species(Genus.ANIMAL, EmojiIcon.BEAR, "Bear");
		TemplateField<Species> templateField = new TemplateField<>(BaseTemplate.TOOLBAR_BUTTON, bearRecord);
		templateField.setPropertyProvider((species, propertyNames) -> Map.ofEntries(
				Map.entry("icon", species.getIcon()),
				Map.entry("caption", species.getName()),
				Map.entry("description", species.getGenus())
		));
		sessionContext.addRootComponent(templateField);
	}
}
