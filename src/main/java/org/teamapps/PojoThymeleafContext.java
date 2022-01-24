package org.teamapps;

import org.apache.commons.lang3.StringUtils;
import org.teamapps.util.ReflectionUtil;
import org.thymeleaf.context.IContext;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class PojoThymeleafContext<T> implements IContext {

	private final T pojo;
	private final Locale locale;
	private Set<String> propertyNames;

	public PojoThymeleafContext(T pojo, Locale locale) {
		this.pojo = pojo;
		this.locale = locale;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public boolean containsVariable(final String name) {
		return getPropertyNames().contains(name);
	}

	@Override
	public Set<String> getVariableNames() {
		return getPropertyNames();
	}

	private Set<String> getPropertyNames() {
		if (propertyNames == null) {
			propertyNames = ReflectionUtil.findMethods(pojo.getClass(), method -> Modifier.isPublic(method.getModifiers())
					&& (method.getName().startsWith("is") && method.getReturnType() == boolean.class
					|| method.getName().startsWith("get") && method.getReturnType() != void.class))
					.stream()
					.map(PojoThymeleafContext::getPropertyName)
					.collect(Collectors.toSet());
		}
		return propertyNames;
	}

	@Override
	public Object getVariable(final String name) {
		return ReflectionUtil.getPropertyValue(pojo, name);
	}

	public static String getPropertyName(Method method) {
		String methodName = method.getName();
		String rawPropertyName = getSubstringIfPrefixMatches(methodName, "get");
		if (rawPropertyName == null) {
			rawPropertyName = getSubstringIfPrefixMatches(methodName, "is");
		}
		return StringUtils.uncapitalize(rawPropertyName);
	}

	private static String getSubstringIfPrefixMatches(String wholeString, String prefix) {
		return wholeString.startsWith(prefix) ? wholeString.substring(prefix.length()) : null;
	}

}
