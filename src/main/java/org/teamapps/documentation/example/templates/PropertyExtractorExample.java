package org.teamapps.documentation.example.templates;

import org.teamapps.data.extract.PropertyExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.data.Genus;
import org.teamapps.documentation.example.data.Species;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;

import java.util.List;

import static org.teamapps.ux.component.template.BaseTemplate.*;

public class PropertyExtractorExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Species> speciesList = List.of(
                new Species(Genus.ANIMAL, EmojiIcon.ELEPHANT, "Benjamin", 300, "Large grey herbivorous mammal"),
                new Species(Genus.ANIMAL, EmojiIcon.RABBIT, "Judy", 20, "Small furry mammal with long ears"));
        ComboBox<Species> animalComboBox = ComboBox.createForList(speciesList);
        animalComboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        animalComboBox.setPropertyExtractor(new PropertyExtractor<>() {
            @Override
            public Object getValue(Species species, String propertyName) {
                return switch (propertyName) {
                    case PROPERTY_ICON -> species.getIcon();
                    case PROPERTY_DESCRIPTION -> species.getDescription();
                    case PROPERTY_TITLE -> species.getName();
                    case PROPERTY_CAPTION -> species.getName();
                    default -> null;
                };
            }
        });
        sessionContext.addRootComponent(animalComboBox);
    }
}
