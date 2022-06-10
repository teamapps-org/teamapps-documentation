package org.teamapps.documentation.example.templates;

import org.teamapps.common.format.Color;
import org.teamapps.data.extract.PropertyExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.data.Genus;
import org.teamapps.documentation.example.data.Species;
import org.teamapps.icon.antu.AntuIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;
import org.teamapps.ux.component.template.htmltemplate.MustacheTemplate;

import java.util.List;

import static org.teamapps.ux.component.template.BaseTemplate.*;

public class MustacheTemplateExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Species> speciesList = List.of(
                new Species(Genus.ANIMAL, AntuIcon.APP_FIRESTORM_ICON_48, "???", 20, "Small furry mammal with long ears"),
                new Species(Genus.ANIMAL, AntuIcon.APP_NMAP_ICON_48, "???", 300, "Large grey herbivorous mammal"));
        ComboBox<Species> animalComboBox = ComboBox.createForList(speciesList);
        animalComboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        animalComboBox.setPropertyExtractor(new PropertyExtractor<Species>() {
            @Override
            public Object getValue(Species species, String propertyName) {
                return switch (propertyName) {
                    case PROPERTY_ICON -> species.getIcon();
                    case PROPERTY_DESCRIPTION -> species.getDescription();
                    case PROPERTY_TITLE -> species.getName();
                    case PROPERTY_CAPTION -> species.getName();
                    case "color" -> Color.RED;
                    case "iconSize" -> species.getMaxSizeCentimeters();
                    default -> null;
                };
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
