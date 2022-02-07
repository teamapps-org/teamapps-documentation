package org.teamapps.documentation.example;

import com.ibm.icu.util.ULocale;
import org.teamapps.common.format.Color;
import org.teamapps.dto.UiCommand;
import org.teamapps.dto.UiEvent;
import org.teamapps.event.Event;
import org.teamapps.icons.Icon;
import org.teamapps.icons.SessionIconProvider;
import org.teamapps.uisession.ClientBackPressureInfo;
import org.teamapps.uisession.QualifiedUiSessionId;
import org.teamapps.uisession.UiSessionListener;
import org.teamapps.ux.component.ClientObject;
import org.teamapps.ux.component.Component;
import org.teamapps.ux.component.animation.EntranceAnimation;
import org.teamapps.ux.component.animation.ExitAnimation;
import org.teamapps.ux.component.notification.Notification;
import org.teamapps.ux.component.notification.NotificationPosition;
import org.teamapps.ux.component.popup.Popup;
import org.teamapps.ux.component.rootpanel.RootPanel;
import org.teamapps.ux.component.rootpanel.WakeLock;
import org.teamapps.ux.component.template.Template;
import org.teamapps.ux.component.template.TemplateReference;
import org.teamapps.ux.component.window.Window;
import org.teamapps.ux.i18n.TranslationProvider;
import org.teamapps.ux.icon.IconBundle;
import org.teamapps.ux.resource.Resource;
import org.teamapps.ux.session.ClientInfo;
import org.teamapps.ux.session.ExecutionDecorator;
import org.teamapps.ux.session.SessionConfiguration;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SessionContext {

	private final org.teamapps.ux.session.SessionContext sessionContext;
	private final String rootComponentContainerSelector;

	public SessionContext(org.teamapps.ux.session.SessionContext sessionContext, String rootComponentContainerSelector) {
		this.sessionContext = sessionContext;
		this.rootComponentContainerSelector = rootComponentContainerSelector;
	}

	public void addRootComponent(Component rootPanel) {
		addRootPanel(null, rootPanel);
	}

	public void addRootComponent(String containerElementId, Component rootPanel) {
		addRootPanel(containerElementId, rootPanel);
	}

	public void addRootPanel(String containerSelector, Component component) {
		sessionContext.addRootPanel(rootComponentContainerSelector, component);
	}

	public RootPanel addRootPanel(String containerElementId) {
		RootPanel rootPanel = new RootPanel();
		addRootPanel(containerElementId, rootPanel);
		return rootPanel;
	}

	public RootPanel addRootPanel() {
		return addRootPanel(null);
	}

	// ========= normal delegate methods: =========

	public void setTranslationProvider(TranslationProvider translationProvider) {
		sessionContext.setTranslationProvider(translationProvider);
	}

	public TranslationProvider getTranslationProvider() {
		return sessionContext.getTranslationProvider();
	}

	public void addIconBundle(IconBundle iconBundle) {
		sessionContext.addIconBundle(iconBundle);
	}

	public Icon<?, ?> getIcon(String key) {
		return sessionContext.getIcon(key);
	}

	public ULocale getULocale() {
		return sessionContext.getULocale();
	}

	public Locale getLocale() {
		return sessionContext.getLocale();
	}

	public void setLocale(Locale locale) {
		sessionContext.setLocale(locale);
	}

	public void setULocale(ULocale locale) {
		sessionContext.setULocale(locale);
	}

	public String getLocalized(String key, Object... parameters) {
		return sessionContext.getLocalized(key, parameters);
	}

	public boolean isActive() {
		return sessionContext.isActive();
	}

	public boolean isDestroyed() {
		return sessionContext.isDestroyed();
	}


	public void setName(String name) {
		sessionContext.setName(name);
	}

	public String getName() {
		return sessionContext.getName();
	}

	public void destroy() {
		sessionContext.destroy();
	}

	public Event<Void> onDestroyed() {
		return sessionContext.onDestroyed();
	}

	public <RESULT> void queueCommand(UiCommand<RESULT> command, Consumer<RESULT> resultCallback) {
		sessionContext.queueCommand(command, resultCallback);
	}

	public void queueCommand(UiCommand<?> command) {
		sessionContext.queueCommand(command);
	}

	public ClientInfo getClientInfo() {
		return sessionContext.getClientInfo();
	}

	public HttpSession getHttpSession() {
		return sessionContext.getHttpSession();
	}

	public ClientBackPressureInfo getClientBackPressureInfo() {
		return sessionContext.getClientBackPressureInfo();
	}

	public String createFileLink(File file) {
		return sessionContext.createFileLink(file);
	}

	public String createResourceLink(Resource resource, String uniqueIdentifier) {
		return sessionContext.createResourceLink(resource, uniqueIdentifier);
	}

	public Resource getBinaryResource(int resourceId) {
		return sessionContext.getBinaryResource(resourceId);
	}

	public File getUploadedFileByUuid(String uuid) {
		return sessionContext.getUploadedFileByUuid(uuid);
	}

	public TemplateReference registerTemplate(String id, Template template) {
		return sessionContext.registerTemplate(id, template);
	}

	public void registerTemplates(Map<String, Template> templates) {
		sessionContext.registerTemplates(templates);
	}

	public Template getTemplate(String id) {
		return sessionContext.getTemplate(id);
	}

	public CompletableFuture<Void> runWithContext(Runnable runnable) {
		return sessionContext.runWithContext(runnable);
	}

	public CompletableFuture<Void> runWithContext(Runnable runnable, boolean forceEnqueue) {
		return sessionContext.runWithContext(runnable, forceEnqueue);
	}

	public <R> CompletableFuture<R> runWithContext(Callable<R> runnable) {
		return sessionContext.runWithContext(runnable);
	}

	public <R> CompletableFuture<R> runWithContext(Callable<R> runnable, boolean forceEnqueue) {
		return sessionContext.runWithContext(runnable, forceEnqueue);
	}

	public void addExecutionDecorator(ExecutionDecorator decorator, boolean outer) {
		sessionContext.addExecutionDecorator(decorator, outer);
	}

	public SessionConfiguration getConfiguration() {
		return sessionContext.getConfiguration();
	}

	public void setConfiguration(SessionConfiguration config) {
		sessionContext.setConfiguration(config);
	}

	public void showPopupAtCurrentMousePosition(Popup popup) {
		sessionContext.showPopupAtCurrentMousePosition(popup);
	}

	public void showPopup(Popup popup) {
		sessionContext.showPopup(popup);
	}

	public ZoneId getTimeZone() {
		return sessionContext.getTimeZone();
	}

	public SessionIconProvider getIconProvider() {
		return sessionContext.getIconProvider();
	}

	public <I extends Icon<I, S>, S> void setDefaultStyleForIconClass(Class<I> iconClass, S defaultStyle) {
		sessionContext.setDefaultStyleForIconClass(iconClass, defaultStyle);
	}

	public String resolveIcon(Icon icon) {
		return sessionContext.resolveIcon(icon);
	}

	public void registerClientObject(ClientObject clientObject) {
		sessionContext.registerClientObject(clientObject);
	}

	public void unregisterClientObject(ClientObject clientObject) {
		sessionContext.unregisterClientObject(clientObject);
	}

	public ClientObject getClientObject(String clientObjectId) {
		return sessionContext.getClientObject(clientObjectId);
	}

	public String createResourceLink(Resource resource) {
		return sessionContext.createResourceLink(resource);
	}

	public void showWindow(Window window, int animationDuration) {
		sessionContext.showWindow(window, animationDuration);
	}

	public void download(String fileUrl, String downloadFileName) {
		sessionContext.download(fileUrl, downloadFileName);
	}

	public void download(File file, String downloadFileName) {
		sessionContext.download(file, downloadFileName);
	}

	public void download(Resource resource, String downloadFileName) {
		sessionContext.download(resource, downloadFileName);
	}

	public void registerBackgroundImage(String id, String image, String blurredImage) {
		sessionContext.registerBackgroundImage(id, image, blurredImage);
	}

	public void setBackgroundImage(String id, int animationDuration) {
		sessionContext.setBackgroundImage(id, animationDuration);
	}

	public void showDefaultBackground(int animationDuration) {
		sessionContext.showDefaultBackground(animationDuration);
	}

	public void setBackgroundColor(Color color, int animationDuration) {
		sessionContext.setBackgroundColor(color, animationDuration);
	}

	public void exitFullScreen() {
		sessionContext.exitFullScreen();
	}

	public void addClientToken(String token) {
		sessionContext.addClientToken(token);
	}

	public void removeClientToken(String token) {
		sessionContext.removeClientToken(token);
	}

	public void clearClientTokens() {
		sessionContext.clearClientTokens();
	}

	public void showNotification(Notification notification, NotificationPosition position, EntranceAnimation entranceAnimation, ExitAnimation exitAnimation) {
		sessionContext.showNotification(notification, position, entranceAnimation, exitAnimation);
	}

	public void showNotification(Notification notification, NotificationPosition position) {
		sessionContext.showNotification(notification, position);
	}

	public void showNotification(Icon icon, String caption) {
		sessionContext.showNotification(icon, caption);
	}

	public void showNotification(Icon icon, String caption, String description) {
		sessionContext.showNotification(icon, caption, description);
	}

	public void showNotification(Icon icon, String caption, String description, boolean dismissable, int displayTimeInMillis, boolean showProgress) {
		sessionContext.showNotification(icon, caption, description, dismissable, displayTimeInMillis, showProgress);
	}

	public void setSessionExpiredWindow(Window sessionExpiredWindow) {
		sessionContext.setSessionExpiredWindow(sessionExpiredWindow);
	}

	public void setSessionErrorWindow(Window sessionErrorWindow) {
		sessionContext.setSessionErrorWindow(sessionErrorWindow);
	}

	public void setSessionTerminatedWindow(Window sessionTerminatedWindow) {
		sessionContext.setSessionTerminatedWindow(sessionTerminatedWindow);
	}

	public CompletableFuture<WakeLock> requestWakeLock() {
		return sessionContext.requestWakeLock();
	}

	public void goToUrl(String url, boolean blankPage) {
		sessionContext.goToUrl(url, blankPage);
	}

	public void setGlobalKeyEventsEnabled(boolean unmodified, boolean modifiedWithAltKey, boolean modifiedWithCtrlKey, boolean modifiedWithMetaKey, boolean includeRepeats, boolean keyDown, boolean keyUp) {
		sessionContext.setGlobalKeyEventsEnabled(unmodified, modifiedWithAltKey, modifiedWithCtrlKey, modifiedWithMetaKey, includeRepeats, keyDown, keyUp);
	}

	public QualifiedUiSessionId getSessionId() {
		return sessionContext.getSessionId();
	}

	public void handleStaticEvent(UiEvent event) {
		sessionContext.handleStaticEvent(event);
	}
}
