package ohSnowyMiku.MurderMysteryPlus.mixin;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.utils.CheckPlayerGameTypeUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * An example mixin using SpongePowered's Mixin library
 *
 * @see Inject
 * @see Mixin
 */
@Mixin(RenderEntityItem.class)
public class RenderEntityItemMixin_GoldIngotOverlay {
    @Inject(method = "func_177077_a", at = @At("TAIL"))
    public void renderGoldIngotOverlay(EntityItem entity, double x, double y, double z, float yaw, IBakedModel model, CallbackInfoReturnable<Integer> cir) {
        Item item = entity.getEntityItem().getItem();

        if (!(item != null && item.getRegistryName().equals("minecraft:gold_ingot") && MurderMysteryPlusConfig.goldIngotOverlaySwitch)) {
            return;
        }
        boolean doScale = false;

        if (MurderMysteryPlusConfig.onlyWorkInMurderMysterySwitch) {

            if (CheckPlayerGameTypeUtil.checkPlayerGameType()) {
                doScale = true;
            }
        } else {
            doScale = true;
        }
        if (doScale) {
            float multiplier = MurderMysteryPlusConfig.goldIngotOverlayMultiplier;
            GlStateManager.scale(multiplier, multiplier, multiplier);
            GlStateManager.translate(0, 0.1, 0);
        }
    }
}

//TODO: replay
