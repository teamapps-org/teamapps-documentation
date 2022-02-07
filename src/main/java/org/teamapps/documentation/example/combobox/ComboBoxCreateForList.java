package org.teamapps.documentation.example.combobox;

import com.vdurmont.emoji.EmojiParser;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.ux.component.field.combobox.ComboBox;

import java.util.List;

public class ComboBoxCreateForList implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<Animal> animalComboBox = ComboBox.createForList(List.of(
                new Animal(EmojiParser.parseToUnicode(":elephant:"), "Benjamin", AnimalSpecies.ELEPHANT, 300),
                new Animal(EmojiParser.parseToUnicode(":rabbit:"), "Judy", AnimalSpecies.RABBIT, 20),
                new Animal(EmojiParser.parseToUnicode(":lion:"), "Simba", AnimalSpecies.LION, 120)));
        sessionContext.addRootComponent(animalComboBox);
    }
}
