package ohSnowyMiku.MurderMysteryPlus.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.utils.CheckPlayerGameTypeUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBoat.class)
public class MixinItemBoat {

    /**
     * 拦截并短路船的 onItemRightClick：始终返回原始物品，不执行放置与递减。
     * 效果：
     * - 不会产生放置动画或本地“隐形”
     * - 不会生成船实体
     * - 不会减少物品数量
     * - 服务器仍收到“使用物品”的交互（数据包在客户端交互时照常发送），Hypixel 的飞刀蓄力正常触发
     */
    @Inject(method = "onItemRightClick", at = @At("HEAD"), cancellable = true)
    private void noboat_place(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
                              CallbackInfoReturnable<ItemStack> cir) {

        if (!MurderMysteryPlusConfig.protectMurdererKnifeSwitch) return;
        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) return;

        cir.setReturnValue(itemStackIn);
    }
}
