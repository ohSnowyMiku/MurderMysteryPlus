package ohSnowyMiku.MurderMysteryHelper.event.events;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;

public class HiddenKaliSpamEvent {
    @SubscribeEvent
    public void hiddenKaliSpam(ClientChatReceivedEvent event) {
        if (!MurderMysteryHelperConfig.hiddenKaliSpamMessage) {
            return;
        }
        if (event.message.getFormattedText().contains("§r§e你在诅咒下被刷屏了§r")
                || event.message.getFormattedText().contains("§r§eYOU HAVE BEEN STRUCK WITH THE CURSE OF SPAM§r")) {
            event.setCanceled(true);
                /*System.out.println("已移除刷屏");
                System.out.println(event.message.getFormattedText());*/
        }
    }
}
