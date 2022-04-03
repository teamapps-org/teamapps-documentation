package org.teamapps.documentation.example.templates;

import org.teamapps.common.format.Color;
import org.teamapps.data.extract.PropertyExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.combobox.Animal;
import org.teamapps.documentation.example.combobox.AnimalSpecies;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.TemplateField;
import org.teamapps.ux.component.template.BaseTemplate;

import static org.teamapps.ux.component.template.BaseTemplate.*;
import static org.teamapps.ux.component.template.BaseTemplate.PROPERTY_CAPTION;

public class TemplateFieldExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        Animal bearRecord = new Animal(EmojiIcon.BEAR, "Sonya", AnimalSpecies.BEAR, 150, null, "Carnivorous mammal", Color.BROWN);
        TemplateField<Animal> templateField = new TemplateField(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES, bearRecord);
        templateField.setPropertyExtractor(new PropertyExtractor<Animal>() {
            @Override
            public Object getValue(Animal animal, String propertyName) {
                switch (propertyName) {
                    case PROPERTY_ICON: return animal.getIcon();
                    case PROPERTY_DESCRIPTION: return animal.getDescription();
                    case PROPERTY_TITLE: return animal.getName();
                    case PROPERTY_CAPTION: return animal.getSpecies().toString();
                    default: return null;
                }
            }
        });
        sessionContext.addRootComponent(templateField);
    }
}
