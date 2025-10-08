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

import static ohSnowyMiku.MurderMysteryHelper.event.events.chatchannelswap.ChatChannelSwap.inParty;

public class RoleSender {

    private static final Minecraft mc = Minecraft.getMinecraft();

    @Subscribe
    public void onPacketReceive(ReceivePacketEvent event) {
        if (!MurderMysteryHelperConfig.autoSendMyRoleInPartyChatSwitch) {
            return;
        }

        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) {
            return;
        }

        if (!(event.packet instanceof S45PacketTitle)) {
            return;
        }
        S45PacketTitle packet = (S45PacketTitle) event.packet;


        if (packet.getType() != S45PacketTitle.Type.TITLE) {
            return;
        }

        IChatComponent comp = packet.getMessage();
        String title = comp != null ? comp.getUnformattedText() : "";

        sendMessage(title);

    }

    private void sendMessage(String title) {
        if (is1v1Or2v2()) {
            return;
        }

        if (!inParty) {
            return;
        }
        //System.out.println("当前非1v1");
        String detectiveMsg = "";
        String murdererMsg = "";
        if (MurderMysteryHelperConfig.autoSendMyRoleInPartyChatLanguageValue == 0) {
            detectiveMsg = "/pc [MurderMysteryHelper] My Role: Detective";
            murdererMsg = "/pc [MurderMysteryHelper] My Role: Murderer";
        }

        if (MurderMysteryHelperConfig.autoSendMyRoleInPartyChatLanguageValue == 1) {
            detectiveMsg = "/pc [MurderMysteryHelper] 我的身份：侦探";
            murdererMsg = "/pc [MurderMysteryHelper] 我的身份：杀手";
        }


        if (title.contains("侦探") || title.contains("DETECTIVE")) {
            mc.thePlayer.sendChatMessage(detectiveMsg);
        /*int playerCount = ScoreBarPlayerCountUtil.getPlayerCount();
        if (playerCount != -1) {
            System.out.println("剩余平民数量为:" + playerCount);
        }*/
        }

        if ((title.contains("杀手") && title.contains("身份"))
                || (title.contains("MURDERER")) && title.contains("Role")) {
            mc.thePlayer.sendChatMessage(murdererMsg);
        }
    }

    private String checkPlayerGameMode() {
        LocrawInfo locraw = LocrawUtil.INSTANCE.getLocrawInfo();
        if (locraw == null) {
            return null;
        }

        return locraw.getGameMode();

    }

    private boolean is1v1Or2v2() {
        String gameMode = checkPlayerGameMode();
        int playerCount = ScoreBarPlayerCountUtil.getPlayerCount();

        if (gameMode != null
                && gameMode.equals("MURDER_CLASSIC")){
            if (playerCount == 1) {
                return true;
            }
        } else if (gameMode != null
                && gameMode.equals("MURDER_DOUBLE_UP")) {
                if (playerCount == 2) {
                    return true;
            }
        }
        return false;
    }

}
//§a1🌠
