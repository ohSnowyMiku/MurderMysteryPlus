package ohSnowyMiku.MurderMysteryHelper.event.events;

import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.util.IChatComponent;


public class SecretPassageTitleEvent {
    @Subscribe
    public void onPacketReceive(ReceivePacketEvent event) {
        if (!(event.packet instanceof S45PacketTitle)) {
            return;
        }
        S45PacketTitle packet = (S45PacketTitle) event.packet;

        /*if (packet.getType() != S45PacketTitle.Type.TITLE) {
            return;
        }*/
        if (packet.getType() != S45PacketTitle.Type.SUBTITLE) {
            return;
        }

        IChatComponent comp = packet.getMessage();
        String title = comp != null ? comp.getUnformattedText() : "";

        //System.out.println("检测到标题:" + title);


        /*if (title.contains("hello")
                || title.contains("A secret passage has opened")
                || title.contains("一个秘密通道打开了"))*/
        if (title.contains("A secret passage has opened")
                || title.contains("一个秘密通道打开了")) {

            SecretPassageHudEvent.openStatus = true;
            SecretPassageHudEvent.textColor = -16711936;
        }
    }
}




