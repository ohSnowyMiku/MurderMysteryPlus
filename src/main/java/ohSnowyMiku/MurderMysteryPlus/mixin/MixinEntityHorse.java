package ohSnowyMiku.MurderMysteryPlus.mixin;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.utils.CheckPlayerGameTypeUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityHorse.class)
public abstract class MixinEntityHorse {
    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void disableGoldenCarrotUse(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        ItemStack held = player.getHeldItem();
        if (held != null && held.getItem() == Items.golden_carrot) {

            if (!MurderMysteryPlusConfig.knifeProtectorSwitch) return;
            if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) return;

            // 阻止金胡萝卜对马生效
            cir.setReturnValue(false);
        }
    }
}
