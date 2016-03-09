package ru.strateg.eventbridge;

import java.util.logging.Logger;

import ru.strateg.eventbridge.util.BridgeHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
@Mod(modid = "EventBridge", name = "EventBridge", version = "@VERSION@", acceptableRemoteVersions = "*")
public final class EventHelper
{
	public static String craftPackage = "org/bukkit/craftbukkit/v1_7_R4";
	public static boolean debug = false;
	public static Logger log = Logger.getLogger("Minecraft");;

	@EventHandler
	public final void serverStarted(FMLServerStartedEvent event)
	{
		log.info("EventHelper loading complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new BridgeHandler());
	}

	public static final void callEvent(Event event)
	{
		Bukkit.getPluginManager().callEvent(event);
	}

}