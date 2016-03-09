package ru.strateg.eventbridge.util;

import ru.strateg.eventbridge.EventHelper;

public final class CraftUtils
{
	public static final Class<?> getCraftClass(String name) throws ClassNotFoundException
	{
		return Class.forName((EventHelper.craftPackage + '/' + name).replace("//", ".").replace('/', '.'));
	}
}