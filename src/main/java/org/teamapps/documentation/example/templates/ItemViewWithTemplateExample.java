package org.teamapps.documentation.example.templates;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.icon.material.MaterialIcon;
import org.teamapps.ux.component.itemview.ItemGroup;
import org.teamapps.ux.component.itemview.ItemView;

import java.util.List;

import static org.teamapps.ux.component.template.BaseTemplate.PROPERTY_CAPTION;
import static org.teamapps.ux.component.template.BaseTemplate.PROPERTY_ICON;

public class ItemViewWithTemplateExample implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        List<Item> items = List.of(
                new Item(EmojiIcon.BANANA, "Banana", "Sweet yellow long fruit"),
                new Item(EmojiIcon.GLASS_OF_MILK, "Milk", "Dairy product"),
                new Item(EmojiIcon.TOMATO, "Tomato", "Red round vegetable"),
                new Item(EmojiIcon.BREAD, "Bread", "Bakery product")
        );
        ItemGroup<String, Item> itemGroup1 = new ItemGroup<String, Item>("Shopping list", null, items);
        ItemView<String, Item> itemView = new ItemView<String, Item>(List.of(itemGroup1));
        itemView.setHeaderPropertyExtractor((record, propertyName) -> {
            switch (propertyName) {
                case PROPERTY_ICON: return MaterialIcon.LIST;
                case PROPERTY_CAPTION: return record;
                default: return null;
            }});
        sessionContext.addRootComponent(itemView);
    }
}
