package org.teamapps.documentation.example.fields.comboboxfields;

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

public class ComboBoxExample implements Example {

    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<Animal> comboBox = new ComboBox<>();
        comboBox.setModel(query -> List.of(
                new Animal(EmojiIcon.ELEPHANT, query + "Benjamin", AnimalSpecies.ELEPHANT, 300, null, "Large grey herbivorous mammal", Color.GREY),
                new Animal(EmojiIcon.RABBIT, query + "Judy" , AnimalSpecies.RABBIT, 20,  null, "Small furry mammal with long ears", Color.BEIGE),
                new Animal(EmojiIcon.LION, query + "Simba", AnimalSpecies.LION, 120, null, "Large carnivorous cat", Color.YELLOW)));
        comboBox.setRecordToStringFunction(animal -> animal.getHeightCentimeters() >= 100 ? animal.getSpecies() + " has height of " + animal.getHeightCentimeters() : animal.getSpecies() + " is tiny." );
        sessionContext.addRootComponent(comboBox);
    }
}
