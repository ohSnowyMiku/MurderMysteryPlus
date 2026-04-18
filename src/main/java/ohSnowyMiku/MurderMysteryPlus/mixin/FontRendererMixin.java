package ohSnowyMiku.MurderMysteryPlus.mixin;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FontRenderer.class)
public abstract class FontRendererMixin {

    @ModifyVariable(
            method = "renderString",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    public String fixChineseBrackets(String text) {
        if (text == null) return text;
        return text.replace('（', '(').replace('）', ')');
    }
}