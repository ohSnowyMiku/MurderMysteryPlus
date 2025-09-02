package ohSnowyMiku.MurderMysteryHelper.event.events.weaponcooldownhud;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;

import static ohSnowyMiku.MurderMysteryHelper.enums.ActionEnum.*;

public class ChatHandler {
    public static final ChatHandler INSTANCE = new ChatHandler();

    private ChatHandler() {
    }

    public static String playerRole = null;

    @SubscribeEvent
    public void onClientChat(ClientChatReceivedEvent event) {
        //System.out.println("[ChatHandler] 当前身份：" + playerRole);
        if (playerRole == null || !MurderMysteryHelperConfig.showWeaponCooldownHudSwitch) {
            return;
        }

        String msg = event.message.getFormattedText();

        /*switch (playerRole) {
            case "杀手":
            case "刺客":
                //System.out.println("状态栏匹配为杀手");
                //System.out.println("状态栏匹配为刺客");
                handleThrowing(msg);
                handleStopCharging(msg);
                handleCharging(msg);

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

                if ((msg.contains("§6蓄力") && msg.contains("14.9s§r"))
                        || (msg.contains("§6CHARGING") && msg.contains("14.9s§r"))) {

                    CountDownManager.start(15 * 20);
                }
                break;

            case "侦探":
                //System.out.println("状态栏匹配为侦探");
                handleCharging(msg);
                break;

            case "幸存者":
                //System.out.println("状态栏匹配为幸存者");

                if ((msg.contains("§6蓄力") && msg.contains("1.4s§r"))
                        || (msg.contains("§6CHARGING") && msg.contains("1.4s§r"))) {
                    CountDownManager.start((int) (1.5f * 20));
                }
                break;
            default:
                break;
        }*/
        handleRoleMessage(msg);


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


}


//TODO飞刀攻击队友cd
//TODO 身份可以匹配到123456