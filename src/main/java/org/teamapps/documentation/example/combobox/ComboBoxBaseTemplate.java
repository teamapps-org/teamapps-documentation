package org.teamapps.documentation.example.combobox;

import org.teamapps.common.format.Color;
import org.teamapps.data.extract.PropertyExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;

import java.util.List;

public class ComboBoxBaseTemplate implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Animal> animalList = List.of(
                new Animal(EmojiIcon.ELEPHANT, "Benjamin", AnimalSpecies.ELEPHANT, 300,null, "Large grey herbivorous mammal", Color.GREY),
                new Animal(EmojiIcon.RABBIT, "Judy", AnimalSpecies.RABBIT, 20, null, "Small furry mammal with long ears", Color.BEIGE),
                new Animal(EmojiIcon.LION, "Simba", AnimalSpecies.LION, 120, null, "Large carnivorous cat", Color.YELLOW),
                new Animal(EmojiIcon.BEAR, "Sonya", AnimalSpecies.BEAR, 150, null, "Carnivorous mammal", Color.BROWN));
        ComboBox<Animal> animalComboBox = ComboBox.createForList(animalList);
        animalComboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        sessionContext.addRootComponent(animalComboBox);
    }
}
