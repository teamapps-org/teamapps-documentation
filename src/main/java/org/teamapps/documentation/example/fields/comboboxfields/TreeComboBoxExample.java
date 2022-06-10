package org.teamapps.documentation.example.fields.comboboxfields;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.documentation.example.data.File;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.tree.TreeNodeInfo;
import org.teamapps.ux.component.tree.TreeNodeInfoImpl;
import org.teamapps.ux.model.ComboBoxModel;

import java.util.List;

import static org.teamapps.documentation.example.data.FileType.DIR;
import static org.teamapps.documentation.example.data.FileType.FILE;

public class TreeComboBoxExample implements Example {
	@Override
	public void onSessionStart(SessionContext sessionContext) {
		ComboBox<File> animalComboBox = new ComboBox<>();
		animalComboBox.setModel(new ComboBoxModel<>() {
			@Override
			public List<File> getRecords(String query) {
				File documentsDir = new File("Documents", DIR, 0);
				File musicDir = new File("Music", DIR, 0);
				File picturesDir = new File("Pictures", DIR, 0);
				return List.of(
						documentsDir,
						musicDir,
						picturesDir,
						new File("letter.docx", FILE, 1023, documentsDir),
						new File("cv.docx", FILE, 1023, documentsDir),
						new File("list.xlsx", FILE, 1023, documentsDir),
						new File("MyWay.mp3", FILE, 1023, musicDir),
						new File("WildThing.mp3", FILE, 1023, musicDir),
						new File("Me.jpg", FILE, 1023, picturesDir),
						new File("Mom.jpg", FILE, 1023, picturesDir)
				);
			}

			@Override
			public TreeNodeInfo getTreeNodeInfo(File File) {
				File parent = File.getParent();
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
