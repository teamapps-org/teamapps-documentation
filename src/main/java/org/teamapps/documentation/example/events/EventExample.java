package org.teamapps.documentation.example.events;

import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;
import org.teamapps.event.Event;
import org.teamapps.icon.emoji.EmojiIcon;
import org.teamapps.ux.component.field.Button;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EventExample implements Example {

    @Override
    public void onSessionStart(SessionContext sessionContext) {
        TwitterFeed twitterFeed = new TwitterFeed();
        twitterFeed.onMessageTweeted.addListener(message -> {
            sessionContext.showNotification(EmojiIcon.BIRD, message);
        });
    }

    private static class TwitterFeed {
        public final Event<String> onMessageTweeted = new Event<>();

        public TwitterFeed() {
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                onMessageTweeted.fire("What a wonderful Event!");
            }, 10, 60, TimeUnit.SECONDS);

        }
    }
}
