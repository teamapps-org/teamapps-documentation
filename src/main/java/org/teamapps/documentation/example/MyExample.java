package org.teamapps.documentation.example;

import org.teamapps.icon.material.MaterialIcon;
import org.teamapps.ux.component.field.Button;

public class MyExample implements Example {

	private boolean someField;

	@Override
	public void onSessionStart(SessionContext sessionContext) {
		Button<?> button = Button.create("Hello");
		button.onClicked.addListener(() -> {
			sessionContext.showNotification(MaterialIcon.ALARM_ON, "Hello world!");
		});
		sessionContext.addRootComponent(null, button);
	}
}
