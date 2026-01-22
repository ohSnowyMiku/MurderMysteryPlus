package ohSnowyMiku.MurderMysteryPlus.event.chat;

import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoShowShotDistanceInChat {

    // 正则：匹配中英文版本，只捕获数字（小数）
    private static final Pattern DISTANCE_PATTERN = Pattern.compile(
            "§r§e(?:你射到了距离你|You shot someone from )(\\d+\\.\\d+)(?:格方块的玩家！| blocks away!)"
    );

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        //ChatUtil.printLastChat("你只能");
        //ChatUtil.printLastChat("may");

        if (!MurderMysteryPlusConfig.AutoSendBowKillDistanceSwitch) return;

        if (ChatUtil.containsInLastFormatted("§r§e你射到了距离你")
                || ChatUtil.containsInLastFormatted("§r§eYou shot someone from")) {


            String message = event.message.getFormattedText();

            Matcher matcher = DISTANCE_PATTERN.matcher(message);

            if (matcher.find()) {

                String number = matcher.group(1);
                double distance = Double.parseDouble(number);
                sendMessage(distance);

            }
        }
    }
    private void sendMessage(double distance) {
        Minecraft mc = Minecraft.getMinecraft();
        String msg;

        if (MurderMysteryPlusConfig.bowDistanceLanguageValue == 0) {

            msg = "[MurderMysteryPlus] You shot someone from " + distance + " blocks away!";
        } else {

            msg = "[MurderMysteryPlus] 你射到了距离你" + distance + "格方块的玩家！";
        }

        // 根据配置发送到不同频道
        sendIfEnabled(mc, MurderMysteryPlusConfig.sendToPublic, "/ac " + msg);
        sendIfEnabled(mc, MurderMysteryPlusConfig.sendToParty, "/pc " + msg);
        sendIfEnabled(mc, MurderMysteryPlusConfig.sendToGuild, "/gc " + msg);

    }

    private void sendIfEnabled(Minecraft mc, boolean enabled, String message) {
        if (enabled) {
            LocrawInfo locraw = LocrawUtil.INSTANCE.getLocrawInfo();

            if (locraw != null) {
                String mapName = locraw.getMapName();
                mc.thePlayer.sendChatMessage(message + " (" + mapName + ")");
            }

        }
    }
}


//[20:44:24] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r §r§r§r§6§kMN§r§r§r §r§e你射到了距离你50.1格方块的玩家！ §r§r§r§6§kMN§r§r§r §r
//[20:44:24] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText: MN 你射到了距离你50.1格方块的玩家！ MN
//[20:44:24] [main/INFO] (Minecraft) [CHAT]  MN 你射到了距离你50.1格方块的玩家！ MN

//[20:45:29] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r §r§r§r§6§kMN§r§r§r §r§eYou shot someone from 44.8 blocks away! §r§r§r§6§kMN§r§r§r §r
//[20:45:29] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText: MN You shot someone from 44.8 blocks away! MN
//[20:45:29] [main/INFO] (Minecraft) [CHAT]  MN You shot someone from 44.8 blocks away! MN