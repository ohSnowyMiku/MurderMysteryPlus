package ohSnowyMiku.MurderMysteryPlus.mixin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.utils.CheckPlayerGameTypeUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemNameTag.class)
public class MixinItemNameTag {
    /**
     * 禁止命名牌对实体生效
     * - 不会给实体设置名字
     * - 不会减少物品数量
     * - 返回 false，相当于“没用上”
     */
    @Inject(method = "itemInteractionForEntity", at = @At("HEAD"), cancellable = true)
    private void disableNameTag(ItemStack stack, EntityPlayer player, EntityLivingBase target,
                                CallbackInfoReturnable<Boolean> cir) {

        if (!MurderMysteryPlusConfig.knifeProtectorSwitch) return;
        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) return;

        cir.setReturnValue(false);
    }
}
