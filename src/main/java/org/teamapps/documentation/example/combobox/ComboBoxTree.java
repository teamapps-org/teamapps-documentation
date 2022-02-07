package org.teamapps.documentation.example.combobox;

import com.vdurmont.emoji.EmojiParser;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.tree.TreeNodeInfo;
import org.teamapps.ux.component.tree.TreeNodeInfoImpl;
import org.teamapps.ux.model.ComboBoxModel;

import java.util.List;

public class ComboBoxTree implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<Animal> animalComboBox = new ComboBox<>();
        animalComboBox.setModel(new ComboBoxModel<>() {
            @Override
            public List<Animal> getRecords(String query) {
                Animal sarabi = new Animal(EmojiParser.parseToUnicode(":lion:"), "Sarabi", AnimalSpecies.LION, 120);
                Animal simba = new Animal(EmojiParser.parseToUnicode(":lion:"), "Simba", AnimalSpecies.LION, 120, sarabi);
                return List.of(sarabi, simba,
                        new Animal(EmojiParser.parseToUnicode(":lion:"), "Kopa", AnimalSpecies.LION, 50, simba),
                        new Animal(EmojiParser.parseToUnicode(":lion:"), "Kiara", AnimalSpecies.LION, 60, simba),
                        new Animal(EmojiParser.parseToUnicode(":lion:"), "Kion", AnimalSpecies.LION, 70, simba));
            }
            @Override
            public TreeNodeInfo getTreeNodeInfo(Animal animal) {
                Animal parent = animal.getParent();
                boolean expanded = false;
                boolean selectable = true;
                boolean lazyChildren = true;
                return new TreeNodeInfoImpl<>(parent, expanded, selectable, lazyChildren);
            }
        });
        animalComboBox.setShowExpanders(true);
        sessionContext.addRootComponent(animalComboBox);

    }
}
