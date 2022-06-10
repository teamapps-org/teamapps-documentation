package org.teamapps.documentation.example.combobox;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.data.Species;
import org.teamapps.icon.antu.AntuIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;

import java.util.List;
import java.util.Objects;

import static org.teamapps.documentation.example.data.Genus.ANIMAL;

public class ComboBoxTemplateDecider implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Species> speciesList = List.of(
                new Species(ANIMAL, AntuIcon.APP_NMAP_ICON_48, "Elephant", 300, "Large grey herbivorous mammal"),
                new Species(ANIMAL, AntuIcon.APP_FIRESTORM_ICON_48, "Rabbit", 20, "Small furry mammal with long ears"),
                new Species(ANIMAL, AntuIcon.APP_WINUSBGUI_ICON_48, "Lion", 120, "Large carnivorous cat"),
                new Species(ANIMAL, AntuIcon.APP_BZR_ICON_64_48, "Bear", 150, "Carnivorous mammal"));
        ComboBox<Species> animalComboBox = ComboBox.createForList(speciesList);
        animalComboBox.setDropdownTemplateDecider(animal -> animal.getMaxSizeCentimeters() > 100? BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES:
                BaseTemplate.ITEM_VIEW_ITEM);
        animalComboBox.setSelectedEntryTemplateDecider(animal -> Objects.equals(animal.getName(), "Sonya") ? BaseTemplate.FILE_ITEM_FLOATING
                : BaseTemplate.TOOL_BUTTON);
        sessionContext.addRootComponent(animalComboBox);
    }
}
