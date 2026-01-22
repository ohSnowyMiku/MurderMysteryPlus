package ohSnowyMiku.MurderMysteryPlus.mixin;

import net.minecraft.client.particle.*;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.event.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EffectRenderer.class)
public class MixinEffectRenderer {
    @Inject(method = "addEffect", at = @At("HEAD"), cancellable = true)
    private void onAddEffect(EntityFX effect, CallbackInfo ci) {
        if (!MurderMysteryPlusConfig.hideParticlesSwitch) return;
        if (ParticleManager.isParticlesEnabled()) return;

        if (effect instanceof EntityCrit2FX
                || effect instanceof EntityFlameFX
                || effect instanceof EntitySmokeFX
                || effect instanceof EntitySpellParticleFX
                || effect instanceof EntityReddustFX
                || effect instanceof EntityHeartFX
                || effect instanceof EntityCloudFX
                || effect instanceof EntityAuraFX
                || effect instanceof EntityExplodeFX
                || effect instanceof EntityLargeExplodeFX
                || effect instanceof EntityHugeExplodeFX
                || effect instanceof EntityFirework.SparkFX
                || effect instanceof EntityFirework.StarterFX
                || effect instanceof EntityFirework.OverlayFX
                || effect instanceof MobAppearance
                || effect instanceof EntityDiggingFX
                || effect instanceof EntityNoteFX
                || effect instanceof EntityLavaFX
                || effect instanceof EntityPortalFX) {
            ci.cancel();
        }
    }
}
