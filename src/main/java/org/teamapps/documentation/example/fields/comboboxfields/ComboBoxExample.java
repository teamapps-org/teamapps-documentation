package org.teamapps.documentation.example.fields.comboboxfields;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.data.Species;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;

import java.util.List;

import static org.teamapps.documentation.example.data.Genus.ANIMAL;

public class ComboBoxExample implements Example {

    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<Species> comboBox = new ComboBox<>();
        comboBox.setModel(query -> List.of(
                new Species(ANIMAL, EmojiIcon.ELEPHANT, query + "Benjamin", 300, "Large grey herbivorous mammal"),
                new Species(ANIMAL, EmojiIcon.RABBIT, query + "Judy" , 20, "Small furry mammal with long ears"),
                new Species(ANIMAL, EmojiIcon.LION, query + "Simba", 120, "Large carnivorous cat")));
        comboBox.setRecordToStringFunction(animal -> animal.getMaxSizeCentimeters() >= 100 ? animal.getName() + " has max height of " + animal.getMaxSizeCentimeters() : animal.getName() + " is tiny." );
        sessionContext.addRootComponent(comboBox);
    }
}
