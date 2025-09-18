package ohSnowyMiku.MurderMysteryHelper.event.events.chatchannelswap;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;
import ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil;

public class ChatChannelSwap {

    private boolean inParty = false;

    private static final Minecraft mc = Minecraft.getMinecraft();


    @SubscribeEvent
    public void checkPartyMessage(ClientChatReceivedEvent event) {

        if (!MurderMysteryHelperConfig.chatChannelSwapSwitch) return;

        //ChatUtil.printLastChat("组队");
        //ChatUtil.printLastChat("party");

        onJoinParty();
        onLeaveParty();


    }

    private void onJoinParty() {
        if (mc.thePlayer == null || mc.thePlayer.getName() == null) return;
        String self = mc.thePlayer.getName();

        if (inParty) return;
        if ((ChatUtil.containsInLastFormatted("§e你加入了")
                && ChatUtil.containsInLastFormatted("§e的组队！"))
                || (ChatUtil.containsInLastFormatted("§eYou have joined")
                && ChatUtil.containsInLastFormatted("§eparty!"))
                || (ChatUtil.containsInLastFormatted(self + "§r§e已邀请")
                && ChatUtil.containsInLastFormatted("§e到组队中！"))
                || (ChatUtil.containsInLastFormatted(self + " §r§einvited")
                && ChatUtil.containsInLastFormatted("§eto the party!"))
        ) {
            mc.thePlayer.sendChatMessage("/chat p");
            inParty = true;
        }
    }

    private void onLeaveParty() {

        if (!inParty) return;
        if (ChatUtil.containsInLastFormatted("§c因组队中没有成员，")
                || ChatUtil.containsInLastFormatted("§e你离开了组队。")
                || ChatUtil.containsInLastFormatted("§cThe party was disbanded")
                || ChatUtil.containsInLastFormatted("§eYou left the party.")
                || ChatUtil.containsInLastFormatted("§ehas disbanded the party!")
                || ChatUtil.containsInLastFormatted("§e解散了组队"))
        {
            mc.thePlayer.sendChatMessage("/chat a");
            inParty = false;
        }

        if (ChatUtil.containsInLastFormatted("§cYou are not in a party")
                || ChatUtil.containsInLastFormatted("§c你不在任何组队中，")) {

            inParty = false;
        }
    }
}

//[03:09:50] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[03:09:50] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§b[MVP§r§0+§r§b] HowUGetTheGirl§r§e已邀请§r§a[VIP] Mayo1uta§r§e到组队中！他们有§r§c60§r§e秒时间接受邀请。§r
//[03:09:50] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:[MVP+] HowUGetTheGirl已邀请[VIP] Mayo1uta到组队中！他们有60秒时间接受邀请。
//[03:09:50] [main/INFO] (Minecraft) [CHAT] [MVP+] HowUGetTheGirl已邀请[VIP] Mayo1uta到组队中！他们有60秒时间接受邀请。
//[03:09:50] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[03:11:53] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[03:11:53] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§c因组队中没有成员， 且所有邀请均已过期， 组队已被解散。§r
//[03:11:53] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:因组队中没有成员， 且所有邀请均已过期， 组队已被解散。
//[03:11:53] [main/INFO] (Minecraft) [CHAT] 因组队中没有成员， 且所有邀请均已过期， 组队已被解散。
//[03:11:53] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[03:13:54] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[03:13:54] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§e你加入了§r§a[VIP] Mayo1uta§r§e的组队！§r
//[03:13:54] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:你加入了[VIP] Mayo1uta的组队！
//[03:13:54] [main/INFO] (Minecraft) [CHAT] 你加入了[VIP] Mayo1uta的组队！
//[03:13:54] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[03:16:57] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[03:16:57] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§e你离开了组队。§r
//[03:16:57] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:你离开了组队。
//[03:16:57] [main/INFO] (Minecraft) [CHAT] 你离开了组队。
//[03:16:57] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------


//[03:19:44] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[03:19:44] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§b[MVP§r§0+§r§b] HowUGetTheGirl §r§einvited §r§a[VIP] Mayo1uta §r§eto the party! They have §r§c60 §r§eseconds to accept.§r
//[03:19:44] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:[MVP+] HowUGetTheGirl invited [VIP] Mayo1uta to the party! They have 60 seconds to accept.
//[03:19:44] [main/INFO] (Minecraft) [CHAT] [MVP+] HowUGetTheGirl invited [VIP] Mayo1uta to the party! They have 60 seconds to accept.
//[03:19:44] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[03:20:44] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[03:20:44] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§cThe party was disbanded because all invites expired and the party was empty.§r
//[03:20:44] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:The party was disbanded because all invites expired and the party was empty.
//[03:20:44] [main/INFO] (Minecraft) [CHAT] The party was disbanded because all invites expired and the party was empty.
//[03:20:44] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[03:21:28] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[03:21:28] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§eYou have joined §r§a[VIP] Mayo1uta's §r§eparty!§r
//[03:21:28] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:You have joined [VIP] Mayo1uta's party!
//[03:21:28] [main/INFO] (Minecraft) [CHAT] You have joined [VIP] Mayo1uta's party!
//[03:21:28] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[03:22:38] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[03:22:38] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§eYou left the party.§r
//[03:22:38] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:You left the party.
//[03:22:38] [main/INFO] (Minecraft) [CHAT] You left the party.
//[03:22:38] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[04:10:47] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[04:10:47] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§b[MVP§r§0+§r§b] HowUGetTheGirl §r§ehas disbanded the party!§r
//[04:10:47] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:[MVP+] HowUGetTheGirl has disbanded the party!
//[04:10:47] [main/INFO] (Minecraft) [CHAT] [MVP+] HowUGetTheGirl has disbanded the party!
//[04:10:47] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[04:12:00] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[04:12:00] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§b[MVP§r§0+§r§b] HowUGetTheGirl§r§e解散了组队！§r
//[04:12:00] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:[MVP+] HowUGetTheGirl解散了组队！
//[04:12:00] [main/INFO] (Minecraft) [CHAT] [MVP+] HowUGetTheGirl解散了组队！
//[04:12:00] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[18:05:24] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[18:05:24] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§c你不在任何组队中，故被移至公共频道。§r
//[18:05:24] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:你不在任何组队中，故被移至公共频道。
//[18:05:24] [main/INFO] (Minecraft) [CHAT] 你不在任何组队中，故被移至公共频道。
//[18:05:24] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

//[18:07:57] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------
//[18:07:57] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§cYou are not in a party and were moved to the ALL channel.§r
//[18:07:57] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryHelper.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:You are not in a party and were moved to the ALL channel.
//[18:07:57] [main/INFO] (Minecraft) [CHAT] You are not in a party and were moved to the ALL channel.
//[18:07:57] [main/INFO] (Minecraft) [CHAT] -----------------------------------------------------

