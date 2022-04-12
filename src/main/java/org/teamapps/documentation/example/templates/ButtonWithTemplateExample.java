package org.teamapps.documentation.example.templates;

import org.teamapps.common.format.Color;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.combobox.Animal;
import org.teamapps.documentation.example.combobox.AnimalSpecies;
import org.teamapps.icon.antu.AntuIcon;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.icon.material.MaterialIcon;
import org.teamapps.ux.component.field.Button;
import org.teamapps.ux.component.linkbutton.LinkButton;
import org.teamapps.ux.component.template.BaseTemplateRecord;

import static org.teamapps.ux.component.template.BaseTemplate.*;

public class ButtonWithTemplateExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        LinkButton link = new LinkButton("Quote", "https://i.redd.it/b0xof6rb13861.png");
        Button<BaseTemplateRecord> button1 = Button.create(MaterialIcon.MENU, "MENU", link).setColor(Color.PEACH_PUFF);
        sessionContext.addRootComponent(button1);
        Animal record =  new Animal(EmojiIcon.LION, "Simba", AnimalSpecies.LION, 120, null, "Large carnivorous cat", Color.YELLOW);
        Button<Animal> button2 = new Button<>(TOOLBAR_BUTTON, record);
        button2.setPropertyExtractor(((animal, propertyName) -> {
            switch (propertyName) {
                case PROPERTY_ICON: return animal.getIcon();
                case PROPERTY_DESCRIPTION: return animal.getDescription() + " size: " + animal.getHeightCentimeters() + " cm, color: yellow";
                case PROPERTY_TITLE: return animal.getName();
                case PROPERTY_CAPTION: return animal.getSpecies().toString();
                default: return null;
        }}));
        sessionContext.addRootComponent(button2);
    }
}
