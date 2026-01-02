package ohSnowyMiku.MurderMysteryPlus.event.hud.title;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.event.sound.ChatPingHandler;

public class TitleTips {
    /*public static boolean showTitleTips = false;
    @SubscribeEvent
    public void onChatI(ClientChatReceivedEvent event) {
        String formattedText = event.message.getFormattedText();
        if (formattedText.contains("123")) {
            showTitleTips = true;
        }
    }*/
    // 原版 title 风格参数（单位：tick）
    private static final int FADE_IN = 10;   // 渐入
    private static final int STAY = 60;      // 停留
    private static final int FADE_OUT = 20;  // 渐出

    public static boolean active = false;
    private static int tickCounter = 0;
    public static String titleText;

    public static String bowPickedTitleText;
    public static String mapEnableTitleText;
    public static String lastSurvivorTitleText;
    public static String alphaAppearTitleText;
    public static String infectedExposedTitleText;
    public static String survivorRevealTitleText;

    // 触发条件：聊天包含特定文字
    @SubscribeEvent
    public void onChatI(ClientChatReceivedEvent event) {
        if (!MurderMysteryPlusConfig.showTitleTipsHudSwitch) return;
        String text = event.message.getFormattedText();
        String matchedMessage = ChatPingHandler.matchMessage(text);
        if (matchedMessage == null) return;
        switch (matchedMessage) {
            case "弓拿起":
                if (!MurderMysteryPlusConfig.bowPickedTitleSwitch) return;
                checkTitleText();
                titleText = bowPickedTitleText;
                TitleTips.start();
                break;
            case "地图启用":
                if (!MurderMysteryPlusConfig.mapEnabledTitleSwitch) return;
                checkTitleText();
                titleText = mapEnableTitleText;
                TitleTips.start();
                break;
            case "最后幸存者":
                if (!MurderMysteryPlusConfig.lastSurvivorTitleSwitch) return;
                checkTitleText();
                titleText = lastSurvivorTitleText;
                TitleTips.start();
                break;
            /*case "母体现形":
                if (!MurderMysteryPlusConfig.alphaAppearsTitleSwitch) return;
                checkTitleText();
                titleText = alphaAppearTitleText;
                TitleTips.start();
                break;
            case "母体感染者暴露":
                if (!MurderMysteryPlusConfig.infectedExposedTitleSwitch) return;
                checkTitleText();
                titleText = infectedExposedTitleText;
                TitleTips.start();
                break;
            case "揭露幸存者":
                if (!MurderMysteryPlusConfig.survivorRevealTitleSwitch) return;
                checkTitleText();
                titleText = survivorRevealTitleText;
                TitleTips.start();
                break;*/
        }
    }

    // 用 client tick 推进动画
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!active) return;

        tickCounter++;
        int total = FADE_IN + STAY + FADE_OUT;
        if (tickCounter > total) {
            // 自动结束
            active = false;
            tickCounter = 0;
        }
    }

    public static void start() {
        active = true;
        tickCounter = 0;
    }

    public static float getCurrentAlpha() {
        if (!active) return 0f;

        if (tickCounter <= FADE_IN) {
            // 渐入：0 -> 1
            return (float) tickCounter / FADE_IN;
        } else if (tickCounter <= FADE_IN + STAY) {
            // 停留：1
            return 1f;
        } else {
            // 渐出：1 -> 0
            int t = tickCounter - FADE_IN - STAY;
            return Math.max(0f, 1f - (float) t / FADE_OUT);
        }
    }
    public static void checkTitleText() {

        if (MurderMysteryPlusConfig.titleTipsLanguageValue == 0) {
            bowPickedTitleText = "You picked up a bow!";
            mapEnableTitleText = "The map is now enabled!";
            lastSurvivorTitleText = "You are the last survivor!";
            alphaAppearTitleText = "Alpha is now appearing!";
            infectedExposedTitleText = "The infected has been exposed!";
            survivorRevealTitleText = "The survivors have been revealed!";
        } else {
            bowPickedTitleText = "一位玩家拿起了弓！";
            mapEnableTitleText = "你的地图现在可以显示所有玩家！";
            //lastSurvivorTitleText = "你感到肾上腺素在你的体内流淌";
            lastSurvivorTitleText = "最后一人！";
            alphaAppearTitleText = "母体已现形";
            infectedExposedTitleText = "感染者的位置暴露了！";
            survivorRevealTitleText = "幸存者的位置已显示！";

        }
    }
}
