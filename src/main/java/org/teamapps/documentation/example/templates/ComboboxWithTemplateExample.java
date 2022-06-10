package org.teamapps.documentation.example.templates;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.data.Genus;
import org.teamapps.documentation.example.data.Species;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;

import java.util.List;

import static org.teamapps.ux.component.template.BaseTemplate.*;
import static org.teamapps.ux.component.template.BaseTemplate.PROPERTY_CAPTION;

public class ComboboxWithTemplateExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<Species> comboBox = ComboBox.createForList(List.of(
                new Species(Genus.ANIMAL, EmojiIcon.ELEPHANT, "Elephant", 300, "Large grey herbivorous mammal"),
                new Species(Genus.ANIMAL, EmojiIcon.RABBIT, "Rabbit" , 20, "Small furry mammal with long ears"),
                new Species(Genus.ANIMAL, EmojiIcon.LION, "Lion", 120, "Large carnivorous cat")));
        comboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        comboBox.setPropertyExtractor((Species species, String propertyName) ->  {
            switch (propertyName) {
                case PROPERTY_ICON: return species.getIcon();
                case PROPERTY_DESCRIPTION: return species.getDescription();
                case PROPERTY_TITLE: return species.getName();
                case PROPERTY_CAPTION: return species.getName();
                default: return null;
            }
        });
        sessionContext.addRootComponent(comboBox);
    }
}