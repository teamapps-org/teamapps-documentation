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
import org.teamapps.ux.component.template.htmltemplate.MustacheTemplate;

// TODO: fix that HTML string throws error (and is not diplayed properly)
public class ComboBoxMustacheTemplate implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Animal_2> animalList = List.of(
                new Animal_2(AntuIcon.APP_FIRESTORM_ICON_48, "Judy", AnimalSpecies.RABBIT, 20,
                        null, "Small furry mammal with long ears", Color.BEIGE),
                new Animal_2(AntuIcon.APP_NMAP_ICON_48, "Benjamin", AnimalSpecies.ELEPHANT, 300,
                        null, "Large grey herbivorous mammal", Color.GREY));
        ComboBox<Animal_2> animalComboBox = ComboBox.createForList(animalList);
        animalComboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        animalComboBox.setPropertyExtractor(new PropertyExtractor<Animal_2>() {
            @Override
            public Object getValue(Animal_2 animal, String propertyName) {
                switch (propertyName) {
                    case PROPERTY_ICON: return animal.getIcon();
                    case PROPERTY_DESCRIPTION: return animal.getDescription();
                    case PROPERTY_TITLE: return animal.getName();
                    case PROPERTY_CAPTION: return animal.getSpecies().toString();
                    case "color": return animal.getColor().toHtmlColorString();
                    case "iconSize": return animal.getHeightCentimeters();
                    default: return null;
                }
            }
        });
        MustacheTemplate template = new MustacheTemplate("" +
                "<div style='color: #000000; background-color: {{color}}'>\n" +
                "   <img style='width: {{iconSize}}px; height auto;' src='{{icon}}'>\n" +
                "      {{caption}}\n" +
                " </div>"
        );
        animalComboBox.setTemplate(template);
        sessionContext.addRootComponent(animalComboBox);
    }
}
