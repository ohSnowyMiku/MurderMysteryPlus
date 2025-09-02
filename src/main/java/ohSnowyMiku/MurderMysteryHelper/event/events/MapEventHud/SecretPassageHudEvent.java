package ohSnowyMiku.MurderMysteryHelper.event.events.MapEventHud;

import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.util.IChatComponent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;
import ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameTypeUtil;

public class SecretPassageHudEvent {

    public static boolean openStatus = false;
    public static int textColor = -65536;
    public static boolean shouldRenderInRightMap = false;


    @Subscribe
    public void onPacketReceive(ReceivePacketEvent event) {

        if (!MurderMysteryHelperConfig.secretPassageOpenStatusSwitch) {
            return;
        }

        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) {
            return;
        }

        if (!(event.packet instanceof S45PacketTitle)) {
            return;
        }
        S45PacketTitle packet = (S45PacketTitle) event.packet;


        if (packet.getType() != S45PacketTitle.Type.SUBTITLE) {
            return;
        }

        IChatComponent comp = packet.getMessage();
        String title = comp != null ? comp.getUnformattedText() : "";


        if (title.contains("A secret passage has opened")
                || title.contains("一个秘密通道打开了")) {

            openStatus = true;
            textColor = -16711936;
        }


    }

    }

