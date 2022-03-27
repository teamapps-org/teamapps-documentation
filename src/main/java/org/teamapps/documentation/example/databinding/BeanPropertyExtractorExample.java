package org.teamapps.documentation.example.databinding;

import org.teamapps.common.format.Color;
import org.teamapps.data.extract.BeanPropertyExtractor;
import org.teamapps.data.extract.PropertyProvider;
import org.teamapps.data.extract.ValueExtractor;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.combobox.Animal;
import org.teamapps.documentation.example.combobox.AnimalSpecies;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.template.BaseTemplate;

import java.util.List;

public class BeanPropertyExtractorExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Animal> animalList = List.of(
                new Animal(EmojiIcon.ELEPHANT, "Benjamin", AnimalSpecies.ELEPHANT, 300,null, "Large grey herbivorous mammal", Color.GREY),
                new Animal(EmojiIcon.RABBIT, "Judy", AnimalSpecies.RABBIT, 20, null, "Small furry mammal with long ears", Color.BEIGE),
                new Animal(EmojiIcon.LION, "Simba", AnimalSpecies.LION, 120, null, "Large carnivorous cat", Color.YELLOW),
                new Animal(EmojiIcon.BEAR, "Sonya", AnimalSpecies.BEAR, 150, null, "Carnivorous mammal", Color.BROWN));
        ComboBox<Animal> animalComboBox = ComboBox.createForList(animalList);
        // this template has the propertyNames: [icon, image, caption, description, badge, ariaLabel, title]
        animalComboBox.setTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_TWO_LINES);
        ValueExtractor<Animal, String> captionValueExtractor = new ValueExtractor<Animal, String>() {
            @Override
            public String extract(Animal animal) {
                return animal.getSpecies().toString();
            }
        };
        BeanPropertyExtractor<Animal> propertyExtractor = new BeanPropertyExtractor<>();
        propertyExtractor.addProperty(BaseTemplate.PROPERTY_CAPTION, captionValueExtractor);
        // this image now covers the icon
        propertyExtractor.addProperty(BaseTemplate.PROPERTY_IMAGE, animal -> EmojiIcon.SUN);
        propertyExtractor.addProperty(BaseTemplate.PROPERTY_BADGE, animal -> animal.getName());
        animalComboBox.setPropertyProvider(propertyExtractor);
        sessionContext.addRootComponent(animalComboBox);
    }
}
