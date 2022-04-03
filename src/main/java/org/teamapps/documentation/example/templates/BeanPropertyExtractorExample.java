package org.teamapps.documentation.example.templates;

import org.teamapps.common.format.Color;
import org.teamapps.data.extract.BeanPropertyExtractor;
import org.teamapps.data.extract.PropertyProvider;
import org.teamapps.data.extract.ValueExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.combobox.Animal;
import org.teamapps.documentation.example.combobox.AnimalSpecies;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.TemplateField;
import org.teamapps.ux.component.template.BaseTemplate;

public class BeanPropertyExtractorExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        Animal rabbitRecord = new Animal(EmojiIcon.RABBIT, "Judy", AnimalSpecies.RABBIT, 20, null, "Small furry mammal with long ears", Color.BEIGE);
        TemplateField<Animal> templateField = new TemplateField(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES, rabbitRecord);

        ValueExtractor<Animal, String> captionValueExtractor = new ValueExtractor<Animal, String>() {
            @Override
            public String extract(Animal animal) {
                return animal.getSpecies().toString();
            }
        };
        BeanPropertyExtractor<Animal> propertyExtractor = new BeanPropertyExtractor<>();
        propertyExtractor.addProperty(BaseTemplate.PROPERTY_CAPTION, captionValueExtractor);
        propertyExtractor.addProperty(BaseTemplate.PROPERTY_BADGE, animal -> animal.getName());
        templateField.setPropertyProvider(propertyExtractor);
        sessionContext.addRootComponent(templateField);
    }
}
