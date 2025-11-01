package ohSnowyMiku.MurderMysteryHelper.sound;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;

public class ChatPingHandler {
    /*private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!MurderMysteryHelperConfig.chatPingSwitch) return;

        String message = event.message.getFormattedText();

        if (message.contains("hello")) {
            if (mc.thePlayer != null) {
                mc.thePlayer.playSound("note.pling", 1.0F, 1.0F);
                System.out.println("完成");
            }
        }
    }*/
}
