package ohSnowyMiku.MurderMysteryHelper.hud;

import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.hud.BasicHud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import cc.polyfrost.oneconfig.renderer.TextRenderer;
import ohSnowyMiku.MurderMysteryHelper.event.events.MapEventHud.NewSecretPassageHudEvent;
import ohSnowyMiku.MurderMysteryHelper.event.events.SecretPassageHudEvent;
import ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameTypeUtil;

import static ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameMapNameUtil.checkPlayerGameMapName;

public class NewSecretPassageHud extends BasicHud {
    public NewSecretPassageHud() {
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

        /*if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) return;
        if (!checkPlayerGameMapName("Spooky Mansion") && !checkPlayerGameMapName("Archives")) return;*/
        if (NewSecretPassageHudEvent.shouldRenderInRightMap) {
            String text = (NewSecretPassageHudEvent.openStatus) ? "Secret Passage : Opened" : "Secret Passage : Closed";
            TextRenderer.drawScaledString(
                    text,
                    (int) x,
                    (int) y,
                    NewSecretPassageHudEvent.textColor,
                    TextRenderer.TextType.SHADOW,
                    scale
            );
        }
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        return 0;
    }

    @Override
    protected float getWidth(float scale, boolean example) {
        return 0;
    }
}


