package ru.strateg.eventbridge.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 * Created by Alex on 05.03.2016.
 */
public class BridgeHandler {

    @SubscribeEvent
    public void myInteract(PlayerInteractEvent event) {
        if(event.entityPlayer instanceof FakePlayer){
            try {
                BlockFace face = ConvertUtils.toBukkitFace(event.face);
                Player player = ConvertUtils.toBukkitEntity(event.entityPlayer);
                boolean result = EventUtils.cantInteract(event.entityPlayer, player.getItemInHand(), event.x, event.y, event.z, event.face);
                event.setCanceled(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}
