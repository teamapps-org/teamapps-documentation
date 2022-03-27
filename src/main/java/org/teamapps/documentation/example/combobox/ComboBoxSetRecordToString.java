package org.teamapps.documentation.example.combobox;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;

import java.util.List;

public class ComboBoxSetRecordToString implements Example {

    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<Animal> animalComboBox = ComboBox.createForList(List.of(
                new Animal(EmojiIcon.ELEPHANT, "Benjamin", AnimalSpecies.ELEPHANT, 300),
                new Animal(EmojiIcon.RABBIT, "Judy", AnimalSpecies.RABBIT, 20),
                new Animal(EmojiIcon.LION, "Simba", AnimalSpecies.LION, 120),
                new Animal(EmojiIcon.BEAR, "Sonya", AnimalSpecies.BEAR, 150)));

        animalComboBox.setRecordToStringFunction(animal -> animal.getHeightCentimeters() >= 100 ? animal.getSpecies()
                + " has height of " + animal.getHeightCentimeters() : animal.getSpecies() + " is tiny." );
        sessionContext.addRootComponent(animalComboBox);
    }
}
