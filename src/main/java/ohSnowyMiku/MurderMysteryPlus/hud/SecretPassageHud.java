package ohSnowyMiku.MurderMysteryPlus.hud;

import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.hud.BasicHud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import cc.polyfrost.oneconfig.renderer.TextRenderer;
import ohSnowyMiku.MurderMysteryPlus.event.hud.mapeventhud.SecretPassageHudEvent;

public class SecretPassageHud extends BasicHud {
    public SecretPassageHud() {
        super(
                false,
                500,
                10,
                1.0f,
                false,
                false,
                2f,
                5f,
                5f,
                new OneColor(0, 0, 0, 120),
                false,
                1f,
                new OneColor(255, 255, 255));
    }

    @Override
    protected void draw(UMatrixStack matrices, float x, float y, float scale, boolean example) {


        if (example) {
            String exampleText = "Secret Passage : Closed";
            TextRenderer.drawScaledString(
                    exampleText,
                    (int) x,
                    (int) y,
                    -65536,
                    TextRenderer.TextType.FULL,
                    scale
            );
        }

        if (SecretPassageHudEvent.shouldRenderInRightMap) {
            String text = (SecretPassageHudEvent.openStatus) ? "Secret Passage : Opened" : "Secret Passage : Closed";
            TextRenderer.drawScaledString(
                    text,
                    (int) x,
                    (int) y,
                    SecretPassageHudEvent.textColor,
                    TextRenderer.TextType.SHADOW,
                    scale
            );
        }
    }

    @Override
    protected float getWidth(float scale, boolean example) {
        return 122f;
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        return 6f;
    }

    @Override
    protected boolean shouldDrawBackground() {
        return false;
    }
}


