package ohSnowyMiku.MurderMysteryPlus.event.item.gestureprotector;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;

public class ArmorStandHandlerEvent {

    @SubscribeEvent
    public void onRightClickArmorStand(PlayerInteractEvent event) {
        if (!MurderMysteryPlusConfig.disableGesturesArmorStandSwitch) {
            return;
        }
        if (!(event.entityPlayer instanceof EntityPlayerSP)) {
            return;
        }


        if (!checkDisableAction(event)) {
            return;
        }

        /*EntityPlayerSP player = (EntityPlayerSP) event.entityPlayer;
        ItemStack held = player.getHeldItem();

        if (player.isSneaking()
                || held == null
                || held.getItem() != Items.armor_stand
                || !held.hasDisplayName()) {
            return;
        }
        String name = held.getDisplayName();
        if (!(name.contains("Gestures") && name.contains("(Right Click)"))) {

            if (checkDisableAction(event)) {
                event.setCanceled(true);
            }
        }*/
        if (checkArmorStandItem(event)) {
            event.setCanceled(true);
        }

    }


    private boolean checkDisableAction(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkArmorStandItem(PlayerInteractEvent event) {
        EntityPlayerSP player = (EntityPlayerSP) event.entityPlayer;
        ItemStack held = player.getHeldItem();

        if (player.isSneaking()
                || held == null
                || held.getItem() != Items.armor_stand
                || !held.hasDisplayName()) {
            return false;
        }
        String name = held.getDisplayName();
        if (name.contains("Gestures") && name.contains("(Right Click)")) {
            return checkDisableAction(event);
        }
        return false;
    }

}
//Gestures waraw (Right Click)
//(Right Click)