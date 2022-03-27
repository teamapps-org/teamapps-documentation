package org.teamapps.documentation.example.combobox;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;

import java.util.List;

public class ComboBoxSetModel implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<Animal> comboBox = new ComboBox<>();
        comboBox.setModel(query -> List.of(
                new Animal(EmojiIcon.ELEPHANT, query + "Benjamin", AnimalSpecies.ELEPHANT, 300),
                new Animal(EmojiIcon.RABBIT, query + "Judy" , AnimalSpecies.RABBIT, 20),
                new Animal(EmojiIcon.LION, query + "Simba", AnimalSpecies.LION, 120)));
        sessionContext.addRootComponent(comboBox);
    }
}
