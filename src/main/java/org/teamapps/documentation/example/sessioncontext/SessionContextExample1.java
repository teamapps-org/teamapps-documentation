package org.teamapps.documentation.example.sessioncontext;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.Button;

public class SessionContextExample1 implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        Button<?> button = Button.create("Click me!");
        button.onClicked.addListener(() -> {
            sessionContext.showNotification(EmojiIcon.WAVING_HAND, "Hello world!");
        });
        sessionContext.addRootComponent(button);
    }
}
