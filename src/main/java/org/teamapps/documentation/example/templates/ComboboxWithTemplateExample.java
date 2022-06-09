package org.teamapps.documentation.example.templates;

import org.teamapps.common.format.Color;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.combobox.Animal;
import org.teamapps.documentation.example.combobox.AnimalSpecies;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;

import java.util.List;

import static org.teamapps.ux.component.template.BaseTemplate.*;
import static org.teamapps.ux.component.template.BaseTemplate.PROPERTY_CAPTION;

public class ComboboxWithTemplateExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<Animal> comboBox = ComboBox.createForList(List.of(
                new Animal(EmojiIcon.ELEPHANT, "Benjamin", AnimalSpecies.ELEPHANT, 300, null, "Large grey herbivorous mammal", Color.GREY),
                new Animal(EmojiIcon.RABBIT, "Judy" , AnimalSpecies.RABBIT, 20,  null, "Small furry mammal with long ears", Color.BEIGE),
                new Animal(EmojiIcon.LION, "Simba", AnimalSpecies.LION, 120, null, "Large carnivorous cat", Color.YELLOW)));
        comboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        comboBox.setPropertyExtractor((Animal animal, String propertyName) ->  {
            switch (propertyName) {
                case PROPERTY_ICON: return animal.getIcon();
                case PROPERTY_DESCRIPTION: return animal.getDescription();
                case PROPERTY_TITLE: return animal.getName();
                case PROPERTY_CAPTION: return animal.getSpecies().toString();
                default: return null;
            }
        });
        sessionContext.addRootComponent(comboBox);
    }
}