package ohSnowyMiku.MurderMysteryHelper.event.events.rolesender;

import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.util.IChatComponent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;
import ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameTypeUtil;
import ohSnowyMiku.MurderMysteryHelper.utils.ScoreBarPlayerCountUtil;
import ohSnowyMiku.MurderMysteryHelper.utils.TickDelayUtil;

import static ohSnowyMiku.MurderMysteryHelper.event.events.chatchannelswap.ChatChannelSwap.inParty;

public class RoleSender {

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static boolean shouldCheckTitle;

    @Subscribe
    public void onPacketReceive(ReceivePacketEvent event) {
        if (!shouldCheckTitle) return;

        if (!MurderMysteryHelperConfig.autoSendMyRoleInPartyChatSwitch) return;

        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) return;

        if (!(event.packet instanceof S45PacketTitle)) return;

        S45PacketTitle packet = (S45PacketTitle) event.packet;


        if (packet.getType() != S45PacketTitle.Type.TITLE) return;


        IChatComponent comp = packet.getMessage();
        String title = comp != null ? comp.getUnformattedText() : "";


        //sendMessage(title);

        // 延迟执行，避免数据还没准备好
        //System.out.println("当前title:" + title);
        TickDelayUtil.runLater(10, () ->
                sendMessage(title));
        }

    private void sendMessage(String title) {
        //System.out.println("开始");
        //if (!inParty) return;
        //System.out.println("在party");
        //System.out.println(inParty);

        String gameMode = checkPlayerGameMode();
        int playerCount = ScoreBarPlayerCountUtil.getPlayerCount();

        // 数据未就绪时直接跳过
        if (gameMode == null || playerCount < 0) return;
        //System.out.println("模式人数没有问题");

        if (is1v1Or2v2(gameMode, playerCount)) return;
        //System.out.println("当前不为12");

        String detectiveMsg, murdererMsg;
        if (MurderMysteryHelperConfig.autoSendMyRoleInPartyChatLanguageValue == 0) {
            //System.out.println("英文");
            detectiveMsg = "/pc [MurderMysteryHelper] My Role: Detective";
            murdererMsg = "/pc [MurderMysteryHelper] My Role: Murderer";
        } else {
            //System.out.println("中文");
            detectiveMsg = "/pc [MurderMysteryHelper] 我的身份：侦探";
            murdererMsg = "/pc [MurderMysteryHelper] 我的身份：杀手";
        }


        if (title.contains("侦探") || title.contains("DETECTIVE")) {
            //System.out.println("匹配标题成功");
            mc.thePlayer.sendChatMessage(detectiveMsg);
            shouldCheckTitle = false;
        /*int playerCount = ScoreBarPlayerCountUtil.getPlayerCount();
        if (playerCount != -1) {
            System.out.println("剩余平民数量为:" + playerCount);
        }*/
        }

        if ((title.contains("杀手") && title.contains("身份"))
                || (title.contains("MURDERER") && title.contains("ROLE"))) {
            //System.out.println("匹配标题成功");
            mc.thePlayer.sendChatMessage(murdererMsg);
            shouldCheckTitle = false;
        }
        //System.out.println("结束");
    }

    private String checkPlayerGameMode() {
        LocrawInfo locraw = LocrawUtil.INSTANCE.getLocrawInfo();
        return locraw != null ? locraw.getGameMode() : null;

    }

    private boolean is1v1Or2v2(String gameMode, int playerCount) {

        if ("MURDER_CLASSIC".equals(gameMode) && playerCount == 1) {
            //System.out.println("当前11");
            return true;
        } else if ("MURDER_DOUBLE_UP".equals(gameMode) && playerCount == 2) {
            //System.out.println("当前22");
            return true;
        }

        return false;
    }

}
//§a1🌠
