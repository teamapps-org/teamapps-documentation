package org.teamapps.documentation.example.data;

public class File {

	private final String name;
	private final FileType fileType;
	private final int size;

	private final File parent;

	public File(String name, FileType fileType, int size) {
		this(name, fileType, size, null);
	}

	public File(String name, FileType fileType, int size, File parent) {
		this.name = name;
		this.fileType = fileType;
		this.size = size;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public FileType getFileType() {
		return fileType;
	}

	public int getSize() {
		return size;
	}

	public File getParent() {
		return parent;
	}
}
