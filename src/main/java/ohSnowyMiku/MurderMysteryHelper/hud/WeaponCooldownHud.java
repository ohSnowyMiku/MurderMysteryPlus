package ohSnowyMiku.MurderMysteryHelper.hud;

import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.hud.BasicHud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import cc.polyfrost.oneconfig.renderer.TextRenderer;
import ohSnowyMiku.MurderMysteryHelper.event.events.weaponcooldownhud.CountDownManager;

import static ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig.color;

public class WeaponCooldownHud extends BasicHud {

    public WeaponCooldownHud() {
        super(
                false,
                310,
                150,
                1.0f,
                false,
                false,
                2f,
                5f,
                5f,
                new OneColor(0,0,0,120),
                false,
                1f,
                new OneColor(255,255,255));
    }


    @Override
    protected void draw(UMatrixStack matrices, float x, float y, float scale, boolean example) {

        if (example) {
            String hint = "5.0";
            TextRenderer.drawScaledString(
                    hint,
                    (int) x,
                    (int) y,
                    color.getRGB(),
                    TextRenderer.TextType.SHADOW,
                    scale
            );
        }

        if (!CountDownManager.isActive()) {
            //System.out.println("active未开启");
            return;
        }

        String text = String.format("%.1f", CountDownManager.getSecondsLeft());
        //System.out.println("尝试绘制" + text);

        TextRenderer.drawScaledString(
                text,
                (int) x,
                (int) y,
                color.getRGB(),
                TextRenderer.TextType.SHADOW,
                scale
        );
    }

    @Override
    protected float getWidth(float scale, boolean example) {
        return 0;
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        return 0;
    }

}



