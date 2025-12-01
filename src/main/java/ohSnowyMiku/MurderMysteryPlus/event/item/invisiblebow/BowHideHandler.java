package ohSnowyMiku.MurderMysteryPlus.event.item.invisiblebow;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;

public class BowHideHandler {

    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        if (!MurderMysteryPlusConfig.hiddenPullingBowAnimationSwitch) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null && mc.thePlayer.isUsingItem()) {
            ItemStack using = mc.thePlayer.getItemInUse();
            if (using != null && using.getItem() instanceof ItemBow) {
                // 取消渲染手部（弓）
                event.setCanceled(true);
            }
        }
    }
}