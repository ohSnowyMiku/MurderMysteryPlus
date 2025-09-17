package ohSnowyMiku.MurderMysteryHelper.utils;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatUtil {
    private static String lastFormattedText = "";
    private static String lastUnformattedText = "";

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        lastFormattedText = event.message.getFormattedText();
        lastUnformattedText = event.message.getUnformattedText();

    }

    public static String getLastFormattedText() {
        return lastUnformattedText;
    }

    public static String getLastUnformattedText() {
        return lastUnformattedText;
    }

    public static boolean containsInLastFormatted(String keyword) {
        if (lastFormattedText == null || keyword == null) {
            return false;
        }
        return lastFormattedText.contains(keyword);
    }

    public static void printLastChat(String keyword) {
        if (containsInLastFormatted(keyword)) {
            System.out.println("接收消息getFormattedText:" + lastFormattedText);
            System.out.println("接收消息getUnformattedText:" + lastUnformattedText);
        }
    }
}
