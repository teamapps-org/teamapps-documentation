package org.teamapps.documentation.example.templates;

import org.teamapps.icons.Icon;

public class Item {
    private Icon icon;
    private String caption;
    private String description;

    public Item(Icon icon, String caption, String description) {
        this.icon = icon;
        this.caption = caption;
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public Icon getIcon() {
        return icon;
    }
}
