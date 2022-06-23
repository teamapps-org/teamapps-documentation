package org.teamapps.documentation.example.sessioncontext;

import org.teamapps.common.format.Color;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.ux.component.field.Button;

public class SessionContextExample2 implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        Button<?> button = Button.create("Click me!");
        button.onClicked.addListener(() -> {
            new Thread(() -> {
                sessionContext.runWithContext(() -> {
                    button.setColor(Color.RED);
                });
            }).start();
        });
        sessionContext.addRootComponent(button);
    }
}
