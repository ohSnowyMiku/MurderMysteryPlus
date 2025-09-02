package ohSnowyMiku.MurderMysteryHelper.event.events;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;

public class BowHideHandler {

    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        if (!MurderMysteryHelperConfig.hiddenPullingBowAnimationSwitch) {
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