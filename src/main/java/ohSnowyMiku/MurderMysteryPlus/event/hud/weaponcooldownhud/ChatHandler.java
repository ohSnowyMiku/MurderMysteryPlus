package ohSnowyMiku.MurderMysteryPlus.event.hud.weaponcooldownhud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;

import static ohSnowyMiku.MurderMysteryPlus.enums.ActionEnum.*;

public class ChatHandler {
    public static final ChatHandler INSTANCE = new ChatHandler();

    private ChatHandler() {
    }

    public static String playerRole = null;

    @SubscribeEvent
    public void onClientChat(ClientChatReceivedEvent event) {
        //System.out.println("[ChatHandler] 当前身份：" + playerRole);
        if (playerRole == null || !MurderMysteryPlusConfig.showWeaponCooldownHudSwitch) {
            return;
        }

        String msg = event.message.getFormattedText();

        handleRoleMessage(msg);
        sendMurdererChannelMessage(msg);


    }

    private void handleRoleMessage(String msg) {
        switch (playerRole) {
            case "杀手":
            case "刺客":
                //System.out.println("状态栏匹配为杀手");
                //System.out.println("状态栏匹配为刺客");
                handleThrowing(msg);
                handleStopCharging(msg);
                handleCharging(msg);

                //当在双倍/刺客模式里面击中队友/非目标玩家时 显示5s的禁用cd
                handleWrongHitCharging(msg);

                break;

            case "感染者":
                //System.out.println("状态栏匹配为感染者");
                handleThrowing(msg);
                handleStopCharging(msg);

                break;

            case "母体":
                //System.out.println("状态栏匹配为母体");
                handleThrowing(msg);
                handleStopCharging(msg);

                if ((msg.contains(ALPHACHARGING.chineseWithColorCode) && msg.contains(ALPHACHARGING.coolDownMessage))
                        || (msg.contains(ALPHACHARGING.englishWithColorCode) && msg.contains(ALPHACHARGING.coolDownMessage))) {

                    CountDownManager.start(15 * 20);
                }
                break;

            case "侦探":
                //System.out.println("状态栏匹配为侦探");
                handleCharging(msg);
                break;

            case "幸存者":
                //System.out.println("状态栏匹配为幸存者");

                if ((msg.contains(SURVIVORCHARGING.chineseWithColorCode) && msg.contains(SURVIVORCHARGING.coolDownMessage))
                        || (msg.contains(SURVIVORCHARGING.englishWithColorCode) && msg.contains(SURVIVORCHARGING.coolDownMessage))) {
                    CountDownManager.start((int) (1.5f * 20));
                }
                break;
            default:
                break;
        }
    }


    private void handleThrowing(String msg) {
        if ((msg.contains(THROWING.chineseWithColorCode) && msg.contains(THROWING.coolDownMessage))
                || (msg.contains(THROWING.englishWithColorCode) && msg.contains(THROWING.coolDownMessage))) {
            CountDownManager.start((int) (0.5f * 20));
        }
    }

    private void handleStopCharging(String msg) {
        if (msg.contains(STOPCHARGING.chineseWithColorCode)
                || msg.contains(STOPCHARGING.englishWithColorCode)) {
            CountDownManager.stop();
        }
    }

    private void handleCharging(String msg) {
        //System.out.println("准备检测匹配蓄力的状态栏");
        if ((msg.contains(CHARGING.chineseWithColorCode) && msg.contains(CHARGING.coolDownMessage))
                || (msg.contains(CHARGING.englishWithColorCode) && msg.contains(CHARGING.coolDownMessage))) {
            CountDownManager.start(5 * 20);
        }
    }

    private void handleWrongHitCharging(String msg) {
        //当击中时，立即重置cd的显示并开始计时5s
        if (msg.contains("§r§c你被击晕了，持续5秒！") || msg.contains("§r§cYou have been stunned for 5 seconds!")) {
            CountDownManager.forceStart(5 * 20);
        }
    }

    private void sendMurdererChannelMessage(String msg) {
        //当作为杀手击中队友时，在杀手聊天频道发送一条消息告诉队友
        Minecraft mc = Minecraft.getMinecraft();
        if (msg.contains("§r§c你只能攻击平民和侦探！") || msg.contains("§r§cYou may only hit innocents and detectives!")) {
            mc.thePlayer.sendChatMessage("/m [MurderMysteryPlus] You have been stunned for 5 seconds!");
        }
    }

}


//[16:43:12] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§cYou have been stunned for 5 seconds!§r
//[16:43:12] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:You have been stunned for 5 seconds!
//[16:42:56] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§c你被击晕了，持续5秒！§r
//[16:42:56] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:你被击晕了，持续5秒！

//[02:55:44] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§c你只能攻击平民和侦探！§r
//[02:55:44] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:你只能攻击平民和侦探！

//[02:59:05] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§cYou may only hit innocents and detectives!§r
//[02:59:05] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:You may only hit innocents and detectives!