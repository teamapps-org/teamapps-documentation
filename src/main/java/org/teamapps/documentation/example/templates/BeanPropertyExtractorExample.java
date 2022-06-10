package org.teamapps.documentation.example.templates;

import org.teamapps.data.extract.BeanPropertyExtractor;
import org.teamapps.data.extract.ValueExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.data.Genus;
import org.teamapps.documentation.example.data.Species;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.TemplateField;
import org.teamapps.ux.component.template.BaseTemplate;

public class BeanPropertyExtractorExample implements Example {
	@Override
	public void onSessionStart(SessionContext sessionContext) {
		Species rabbitRecord = new Species(Genus.ANIMAL, EmojiIcon.RABBIT, "Rabbit", 20, "Small furry mammal with long ears");
		TemplateField<Species> templateField = new TemplateField<>(BaseTemplate.FILE_ITEM_FLOATING, rabbitRecord);

		ValueExtractor<Species, String> captionValueExtractor = new ValueExtractor<Species, String>() {
			@Override
			public String extract(Species animal) {
				return animal.getName();
			}
		};
		BeanPropertyExtractor<Species> propertyExtractor = new BeanPropertyExtractor<>();
		propertyExtractor.addProperty(BaseTemplate.PROPERTY_CAPTION, captionValueExtractor);
		propertyExtractor.addProperty(BaseTemplate.PROPERTY_BADGE, Species::getName);
		templateField.setPropertyProvider(propertyExtractor);
		sessionContext.addRootComponent(templateField);
	}
}
