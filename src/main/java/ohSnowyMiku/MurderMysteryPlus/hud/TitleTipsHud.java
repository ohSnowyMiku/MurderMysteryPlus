package ohSnowyMiku.MurderMysteryPlus.hud;

import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.hud.BasicHud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import cc.polyfrost.oneconfig.renderer.TextRenderer;
import ohSnowyMiku.MurderMysteryPlus.event.hud.title.TitleTips;

import static ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig.TitleTipsHudPage.titleColor;

public class TitleTipsHud extends BasicHud {

    public TitleTipsHud() {
        super(
                false,
                161,
                73,
                2.0f,
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
        //String text = example ? "Bow Dropped" : "Bow Dropped!";
        // 当前透明度（0.0~1.0）
        float alpha = example ? 1.0f : TitleTips.getCurrentAlpha();
        if (!example && alpha <= 0f) return;

        net.minecraft.client.gui.FontRenderer fr = net.minecraft.client.Minecraft.getMinecraft().fontRendererObj;

        String EXAMPLE_TEXT = "A player has picked up a Bow!";
        int textWidth = (int) (fr.getStringWidth(TitleTips.active ? TitleTips.titleText : EXAMPLE_TEXT) * scale);
        int textHeight = (int) (fr.FONT_HEIGHT * scale);

        float centerX = x + getWidth(scale, example) / 2f;
        float centerY = y + getHeight(scale, example) / 2f;

        int drawX = (int) (centerX - textWidth / 2f);
        int drawY = (int) (centerY - textHeight / 2f);


        // 合成颜色（在原始颜色上应用 alpha）
        int baseColor = titleColor.getRGB(); // 你已有的颜色
        int colorWithAlpha = (((int) (alpha * 255)) << 24) | (baseColor & 0xFFFFFF);

        if (example) {
            //System.out.println(1);

            TextRenderer.drawScaledString(
                    TitleTips.active ? TitleTips.titleText : EXAMPLE_TEXT,
                    drawX,
                    drawY,
                    titleColor.getRGB(),
                    TextRenderer.TextType.FULL,
                    scale
            );
            //System.out.println(2);
        }

        //if (MurderMysteryPlusConfig.showTitleTipsHudSwitch) {
        //System.out.println("准备");
        if (!TitleTips.active) return;
        TextRenderer.drawScaledString(
                TitleTips.titleText,
                drawX,
                drawY,
                colorWithAlpha,
                TextRenderer.TextType.SHADOW,
                scale
        );
        //System.out.println("完成");
    }

    @Override
    protected float getWidth(float scale, boolean example) {
        return 300f;
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        return 10f;
    }
    /*@Override
    protected float getWidth(float scale, boolean example) {
        String text = TitleTips.active ? TitleTips.titleText : EXAMPLE_TEXT;
        if (text == null) return 0f;

        net.minecraft.client.gui.FontRenderer fr = net.minecraft.client.Minecraft.getMinecraft().fontRendererObj;
        return fr.getStringWidth(text) * scale;
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        net.minecraft.client.gui.FontRenderer fr = net.minecraft.client.Minecraft.getMinecraft().fontRendererObj;
        return fr.FONT_HEIGHT * scale;
    }*/


    @Override
    protected boolean shouldDrawBackground() {
        return false;
    }
}
