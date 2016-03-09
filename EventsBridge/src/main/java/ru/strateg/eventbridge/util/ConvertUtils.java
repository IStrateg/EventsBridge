package ru.strateg.eventbridge.util;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class ConvertUtils
{
	private static final Method getBukkitEntity;
	private static final Method asCraftMirror;

	public static final org.bukkit.entity.Entity toBukkitEntity(Entity entity) throws Exception
	{
		return (org.bukkit.entity.Entity) getBukkitEntity.invoke(entity);
	}

	public static final Player toBukkitEntity(EntityPlayer player) throws Exception
	{
		return (Player) getBukkitEntity.invoke(player);
	}

	public static final org.bukkit.World toBukkitWorld(World world)
	{
		return Bukkit.getWorld(world.getWorldInfo().getWorldName());
	}

	public static final org.bukkit.inventory.ItemStack toBukkitItemStackMirror(ItemStack stack) throws Exception
	{
		return (org.bukkit.inventory.ItemStack) asCraftMirror.invoke(null, stack);
	}

	public static final BlockFace toBukkitFace(int direction)
	{
		switch (direction)
		{
			case 1:
				return BlockFace.DOWN;
			case 0:
				return BlockFace.UP;
			case 3:
				return BlockFace.NORTH;
			case 2:
				return BlockFace.SOUTH;
			case 5:
				return BlockFace.WEST;
			case 4:
				return BlockFace.EAST;
			case 6:
				return BlockFace.SELF;
			default:
				return BlockFace.SELF;
		}
	}

	static
	{
		try
		{
			getBukkitEntity = Entity.class.getDeclaredMethod("getBukkitEntity");
			getBukkitEntity.setAccessible(true);

			asCraftMirror = CraftUtils.getCraftClass("inventory/CraftItemStack").getDeclaredMethod("asCraftMirror", ItemStack.class);
			asCraftMirror.setAccessible(true);
		}
		catch (Throwable throwable)
		{
			throw new RuntimeException("Failed hooking CraftBukkit methods!", throwable);
		}
	}
}