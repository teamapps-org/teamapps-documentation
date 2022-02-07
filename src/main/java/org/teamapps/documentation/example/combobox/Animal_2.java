package org.teamapps.documentation.example.combobox;

import org.teamapps.icons.Icon;
import org.teamapps.common.format.Color;

/**
 * TODO:
 * This class is preliminary. It was needed because the template could not parse the (html) String icon.
 * Will be merged with other Animal class once a solution for the icons is found.
 */
public class Animal_2 {
    private final Icon icon;
    private final String name;
    private final AnimalSpecies species;
    private final int heightCentimeters;
    private final Animal_2 parent;
    private final String description;
    private final Color color;

    public Animal_2(Icon icon, String name, AnimalSpecies species, int heightCentimeters, Animal_2 parent,
                    String description, Color color) {
        this.icon = icon;
        this.name = name;
        this.species = species;
        this.heightCentimeters = heightCentimeters;
        this.parent = parent;
        this.description = description;
        this.color = color;
    }
    public Animal_2(Icon icon, String name, AnimalSpecies species, int heightCentimeters) {
        this(icon, name, species, heightCentimeters,null, null, null);
    }

    public Color getColor() {
        return color;
    }

    public Animal_2 getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AnimalSpecies getSpecies() {
        return species;
    }

    public int getHeightCentimeters() {
        return heightCentimeters;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return name + " the " + species;
    }
}
