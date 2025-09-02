package ohSnowyMiku.MurderMysteryHelper.event.events;

import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent;
import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.util.IChatComponent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;
import ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameTypeUtil;

public class HiddenMurderTitleTipEvent {

   public static boolean shouldCancel = false;

    @Subscribe
    public void hiddenMurderTitleTip(ReceivePacketEvent event) {

        if (!MurderMysteryHelperConfig.hiddenMurderTitleTipSwitch) {
            return;
        }

        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) {
            return;
        }


        if (!(event.packet instanceof S45PacketTitle)) {
            return;
        }
        S45PacketTitle packet = (S45PacketTitle) event.packet;

        S45PacketTitle.Type type = packet.getType();

        IChatComponent comp = packet.getMessage();
        String title = comp != null ? comp.getUnformattedText() : "";

        if (checkTitleText(title)) {
            if (doEventCancel(type)) {
                event.isCancelled = true;
            }
        }

        //System.out.println("检测到标题:" + title);
    }


    private boolean checkTitleText(String title) {
        if (shouldCancel) {
            if (title.contains("记住")
                    || title.contains("你是§c杀手")
                    || title.contains("You are the §cMURDERER")
                    || title.contains("Reminder")
                    || title.contains("all players")
                    || title.contains("让街道上尸横遍野")) {
                return true;
            }
        }
        return false;
    }


    private boolean doEventCancel(S45PacketTitle.Type type) {
        return type == S45PacketTitle.Type.TITLE || type == S45PacketTitle.Type.SUBTITLE;
    }

    @Subscribe
    private void checkMurdererMessage(ChatReceiveEvent event) {
        if (event.message.getFormattedText().contains("§e你拿到了剑")) {
            shouldCancel = true;
        }
        if (event.message.getFormattedText().contains("§eYou have received your sword")) {
            shouldCancel = true;
        }
    }
}


//TODO 中文副标题屏蔽未生效