package org.teamapps.documentation.example.combobox;

import com.vdurmont.emoji.EmojiParser;
import org.teamapps.common.format.Color;
import org.teamapps.data.extract.PropertyExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.icon.antu.AntuIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;
import org.teamapps.ux.component.template.BaseTemplate.*;

import java.util.List;

import static org.teamapps.ux.component.template.BaseTemplate.*;

public class ComboBoxBaseTemplate implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Animal_2> animalList = List.of(
                new Animal_2(AntuIcon.APP_NMAP_ICON_48, "Benjamin", AnimalSpecies.ELEPHANT, 300,null, "Large grey herbivorous mammal", Color.GREY),
                new Animal_2(AntuIcon.APP_FIRESTORM_ICON_48, "Judy", AnimalSpecies.RABBIT, 20, null, "Small furry mammal with long ears", Color.BEIGE),
                new Animal_2(AntuIcon.APP_WINUSBGUI_ICON_48, "Simba", AnimalSpecies.LION, 120, null, "Large carnivorous cat", Color.YELLOW),
                new Animal_2(AntuIcon.APP_BZR_ICON_64_48, "Sonya", AnimalSpecies.BEAR, 150, null, "Carnivorous mammal", Color.BROWN));
        ComboBox<Animal_2> animalComboBox = ComboBox.createForList(animalList);
        animalComboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        sessionContext.addRootComponent(animalComboBox);
    }
}
