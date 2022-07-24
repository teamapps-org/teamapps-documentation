package org.teamapps.documentation.example.sessioncontext;

import org.teamapps.config.TeamAppsConfiguration;
import org.teamapps.documentation.example.Example;
import org.teamapps.documentation.example.SessionContext;

public class SessionContextEndExample1 implements Example {
    @Override
    public void onSessionStart(SessionContext sessionContext) {
        TeamAppsConfiguration config = new TeamAppsConfiguration();
        config.setUiSessionTimeoutMillis(5_000);
        config.setKeepaliveMessageIntervalMillis(10_000);

    }
}
