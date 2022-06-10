package org.teamapps.documentation.example.data;

import org.teamapps.icons.Icon;

public class Species {
	private final Genus genus;
	private final Icon icon;
	private final String name;
	private final int maxSizeCentimeters;
	private final String description;

	public Species(Genus genus, Icon icon, String name, int maxSizeCentimeters,
				   String description) {
		this.genus = genus;
		this.icon = icon;
		this.name = name;
		this.maxSizeCentimeters = maxSizeCentimeters;
		this.description = description;
	}

	public Species(Genus genus, Icon icon, String name) {
		this(genus, icon, name, -1, null);
	}

	public Genus getGenus() {
		return genus;
	}

	public Icon getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public int getMaxSizeCentimeters() {
		return maxSizeCentimeters;
	}

	public String getDescription() {
		return description;
	}
}
