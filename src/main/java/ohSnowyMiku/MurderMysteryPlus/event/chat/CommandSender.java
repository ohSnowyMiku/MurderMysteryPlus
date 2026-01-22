package ohSnowyMiku.MurderMysteryPlus.event.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil;
import ohSnowyMiku.MurderMysteryPlus.utils.SimpleDelayedChatUtil;

public class CommandSender {

    @SubscribeEvent
    public void sendClickableMessage(ClientChatReceivedEvent event) {

        if (!MurderMysteryPlusConfig.showPartyButtonsSwitch) return;

        //ChatUtil.printLastChat("party");
        //ChatUtil.printLastChat("Party");
        //ChatUtil.printLastChat("组队");

        ChatComponentText message = new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "[MurderMysteryPlus]: ");
        //ChatComponentText kickOfflineMessage = new ChatComponentText(EnumChatFormatting.RED + "[Kick Offline]");

        //Private指令
        ChatComponentText privateMessage = new ChatComponentText(EnumChatFormatting.GREEN + "[Private]");
        ChatStyle privateStyle = new ChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p private"));

        privateMessage.setChatStyle(privateStyle);

        //All Invite指令
        ChatComponentText allInviteMessage = new ChatComponentText(EnumChatFormatting.YELLOW + "[All Invite]");
        ChatStyle allInviteStyle = new ChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p settings allinvite"));

        allInviteMessage.setChatStyle(allInviteStyle);

        //Stream指令
        ChatComponentText streamMessage = new ChatComponentText(EnumChatFormatting.BLUE + "[Stream]");
        ChatStyle streamStyle = new ChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Stream"));

        streamMessage.setChatStyle(streamStyle);

        //Kick offline指令
        ChatComponentText kickOfflineMessage = new ChatComponentText(EnumChatFormatting.RED + "[Kick Offline]");
        ChatStyle kickOfflineStyle = new ChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p kickoffline"));

        kickOfflineMessage.setChatStyle(kickOfflineStyle);

        //Party warp指令
        ChatComponentText partyWarpMessage = new ChatComponentText(EnumChatFormatting.AQUA + "[Warp]");
        ChatStyle partyWarpStyle = new ChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p warp"));

        partyWarpMessage.setChatStyle(partyWarpStyle);

        //Transfer指令
        ChatComponentText transferMessage = new ChatComponentText(EnumChatFormatting.GOLD + "[Transfer]");
        ChatStyle transferStyle = new ChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/p transfer "));

        transferMessage.setChatStyle(transferStyle);

        //Promote指令
        ChatComponentText promoteMessage = new ChatComponentText(EnumChatFormatting.DARK_GREEN + "[Promote]");
        ChatStyle promoteStyle = new ChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/p promote "));

        promoteMessage.setChatStyle(promoteStyle);


        message.appendSibling(partyWarpMessage);
        message.appendText(" ");
        message.appendSibling(privateMessage);
        message.appendText(" ");
        message.appendSibling(allInviteMessage);
        message.appendText(" ");
        message.appendSibling(kickOfflineMessage);
        message.appendText(" ");
        message.appendSibling(streamMessage);
        message.appendText(" ");
        message.appendSibling(transferMessage);
        message.appendText(" ");
        message.appendSibling(promoteMessage);


        String formattedText = event.message.getFormattedText();

        //准备尝试判读是否为组队队长,自动/pl
        String name = Minecraft.getMinecraft().thePlayer.getName();
        if ( formattedText.contains("若§r§c5§r§e分钟内未重新连接则移出组队。")
                || formattedText.contains("they have §r§c5 §r§eminutes to rejoin before they are removed from the party.")
                || formattedText.contains("§r§e加入了组队。")
                || formattedText.contains("§r§ejoined the party.")
                || formattedText.contains("§r§aParty Commands:")
                || (formattedText.contains("§eThe party was transferred to") && formattedText.contains(name))
                || (formattedText.contains("§r§e提拔为组队") && formattedText.contains(name))
                || (formattedText.contains("§r§e将组队移交给了") && formattedText.contains(name))
                || (formattedText.contains("§r§e has promoted") && formattedText.contains(name)))
                {
                        //Minecraft.getMinecraft().thePlayer.addChatMessage(kickOfflineMessage);
                        SimpleDelayedChatUtil.scheduleMessage(message);
        }

    }
}
//[21:22:15] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§a[VIP] Mayo1uta§r§e已断开连接，若§r§c5§r§e分钟内未重新连接则移出组队。§r
//[21:22:15] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:[VIP] Mayo1uta已断开连接，若5分钟内未重新连接则移出组队。
//[21:23:54] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§a[VIP] Mayo1uta §r§ehas disconnected, they have §r§c5 §r§eminutes to rejoin before they are removed from the party.§r
//[21:23:54] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:[VIP] Mayo1uta has disconnected, they have 5 minutes to rejoin before they are removed from the party.

