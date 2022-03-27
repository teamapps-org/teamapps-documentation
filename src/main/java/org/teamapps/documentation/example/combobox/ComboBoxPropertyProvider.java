package org.teamapps.documentation.example.combobox;

import org.teamapps.common.format.Color;
import org.teamapps.data.extract.PropertyExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.icon.antu.AntuIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;

import java.util.List;

import static org.teamapps.ux.component.template.BaseTemplate.*;
import static org.teamapps.ux.component.template.BaseTemplate.PROPERTY_CAPTION;

public class ComboBoxPropertyProvider implements Example {

    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Animal> animalList = List.of(
                new Animal(AntuIcon.APP_NMAP_ICON_48, "Benjamin", AnimalSpecies.ELEPHANT, 300,null, "Large grey herbivorous mammal", Color.GREY),
                new Animal(AntuIcon.APP_FIRESTORM_ICON_48, "Judy", AnimalSpecies.RABBIT, 20, null, "Small furry mammal with long ears", Color.BEIGE));
        ComboBox<Animal> animalComboBox = ComboBox.createForList(animalList);
        animalComboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        animalComboBox.setPropertyExtractor(new PropertyExtractor<>() {
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
        sessionContext.addRootComponent(animalComboBox);
    }
}
