package ohSnowyMiku.MurderMysteryHelper.event.events;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MessageEvent {

    @SubscribeEvent
    public void messageReceived(ClientChatReceivedEvent event) {
        String formattedText = event.message.getFormattedText();
        String unformattedText = event.message.getUnformattedText();

        if (formattedText.contains("侦探")
                || unformattedText.contains("侦探")
                || formattedText.contains("You see that")
                || unformattedText.contains("You see that")) {

            System.out.println("接收消息getFormattedText:" + formattedText);
            System.out.println("接收消息getUnformattedText:" + unformattedText);
        }
        //可以接收到状态栏信息
    }
}