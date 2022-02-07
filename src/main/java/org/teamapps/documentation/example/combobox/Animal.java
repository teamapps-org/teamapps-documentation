package org.teamapps.documentation.example.combobox;

public class Animal {
    private final String icon;
    private final String name;
    private final AnimalSpecies species;
    private final int heightCentimeters;
    private final Animal parent;

    public Animal(String icon, String name, AnimalSpecies species, int heightCentimeters, Animal parent) {
        this.icon = icon;
        this.name = name;
        this.species = species;
        this.heightCentimeters = heightCentimeters;
        this.parent = parent;
    }
    public Animal(String icon, String name, AnimalSpecies species, int heightCentimeters) {
        this(icon, name, species, heightCentimeters,null);
    }

    public Animal getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public AnimalSpecies getSpecies() {
        return species;
    }

    public int getHeightCentimeters() {
        return heightCentimeters;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return name + " the " + species;
    }
}
