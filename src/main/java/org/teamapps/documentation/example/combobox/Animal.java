package org.teamapps.documentation.example.combobox;

import org.teamapps.common.format.Color;
import org.teamapps.icons.Icon;

public class Animal {
    private final Icon icon;
    private final String name;
    private final AnimalSpecies species;
    private final int heightCentimeters;
    private final Animal parent;
    private final String description;
    private final Color color;

    public Animal(Icon icon, String name, AnimalSpecies species, int heightCentimeters, Animal parent,
                  String description, Color color) {
        this.icon = icon;
        this.name = name;
        this.species = species;
        this.heightCentimeters = heightCentimeters;
        this.parent = parent;
        this.description = description;
        this.color = color;
    }
    public Animal(Icon icon, String name, AnimalSpecies species, int heightCentimeters) {
        this(icon, name, species, heightCentimeters,null, null, null);
    }
    public Animal(Icon icon, String name, AnimalSpecies species, int heightCentimeters, Animal parent) {
        this(icon, name, species, heightCentimeters,parent, null, null);
    }

    public Color getColor() {
        return color;
    }

    public Animal getParent() {
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
