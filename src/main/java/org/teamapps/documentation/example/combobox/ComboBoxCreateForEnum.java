package org.teamapps.documentation.example.combobox;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.ux.component.field.combobox.ComboBox;

import java.util.List;

public class ComboBoxCreateForEnum implements Example {

    @Override
    public void onSessionStart(SessionContext sessionContext) {
        ComboBox<AnimalSpecies> comboBox = ComboBox.createForEnum(AnimalSpecies.class);
        sessionContext.addRootComponent(comboBox);
    }
}
