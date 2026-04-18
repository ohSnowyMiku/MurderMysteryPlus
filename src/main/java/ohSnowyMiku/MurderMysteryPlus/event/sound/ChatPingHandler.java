package ohSnowyMiku.MurderMysteryPlus.event.sound;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;

public class ChatPingHandler {
    public static boolean shouldPlaySound;
    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {

        /*ChatUtil.printLastChat("弓");
        ChatUtil.printLastChat("Bow");
        ChatUtil.printLastChat("侦探");
        ChatUtil.printLastChat("Detective");
        ChatUtil.printLastChat("地图");
        ChatUtil.printLastChat("Map");
        ChatUtil.printLastChat("幸存者");
        ChatUtil.printLastChat("Survivor");
        ChatUtil.printLastChat("暴露");
        ChatUtil.printLastChat("Exposure");
        ChatUtil.printLastChat("现形");
        ChatUtil.printLastChat("Appear");
        ChatUtil.printLastChat("肾上腺素");
        ChatUtil.printLastChat("You");
        ChatUtil.printLastChat("alpha");*/

        if (!MurderMysteryPlusConfig.enableSoundsSwitch) return;

        String message = event.message.getFormattedText();
        String matchedMessage = matchMessage(message);

        if (matchedMessage == null) return;
        if (mc.thePlayer == null) return;
        switch (matchedMessage) {
            case "弓掉落":
                playSound(MurderMysteryPlusConfig.bowDroppedSoundValue);
                break;
            case "弓拿起":
                playSound(MurderMysteryPlusConfig.bowPickedSoundValue);
                break;
            case "地图启用":
                if (!shouldPlaySound) return;
                playSound(MurderMysteryPlusConfig.mapEnabledSoundValue);
                shouldPlaySound = false;
                break;
            case "最后幸存者":
                playSound(MurderMysteryPlusConfig.lastSurvivorSoundValue);
                break;
            case "母体现形":
                playSound(MurderMysteryPlusConfig.alphaAppearsSoundValue);
                break;
            /*case "揭露幸存者":
                playSound(MurderMysteryPlusConfig.survivorRevealSoundValue);
                break;
            case "母体感染者暴露":
                playSound(MurderMysteryPlusConfig.infectedExposedSoundValue);
                break;
            */
        }

        /*if (message.contains("弓已掉落！")) {
            if (mc.thePlayer != null) {
                playSound(MurderMysteryPlusConfig.bowDroppedSoundValue);
                System.out.println("完成");
            }
        }
        if (message.contains("侦探已被杀死！")) {
            if (mc.thePlayer != null) {
                playSound(MurderMysteryPlusConfig.bowDroppedSoundValue);
                System.out.println("完成");
            }
        }
        if (message.contains("一位玩家拿起了弓！")) {
            if (mc.thePlayer != null) {
                playSound(MurderMysteryPlusConfig.bowPickedSoundValue);
                System.out.println("完成");
            }
        }
        if (message.contains("肾上腺素") || message.contains("幸存者位置已显示")) {
            if (mc.thePlayer != null)
                playSound(MurderMysteryPlusConfig.lastSurvivorSoundValue);
        }*/
        //TODO:你被暴露在小地图上！ 母体被暴露在小地图上
        //母体steve因alex而现形！
        //你的地图现在可以显示所有玩家！
        //你拿起了弓！

    }

    //[ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§6侦探已被杀死！§r
    //[14:57:11] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:侦探已被杀死！
    //[14:59:02] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§6The Detective has been killed!§r
    //[14:59:02] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:The Detective has been killed!
    //[15:01:04] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§6侦探退出了游戏！ §r§e找到弓，并抓住机会杀死杀手。§r
    //[15:01:04] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:侦探退出了游戏！ 找到弓，并抓住机会杀死杀手。
    //[15:02:25] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§e一位玩家拿起了弓！§r
    //[15:02:25] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:一位玩家拿起了弓！
    // [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§6The Detective has left! §r§eFind the Bow for a chance to kill the Murderer.§r
    //[15:05:30] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:The Detective has left! Find the Bow for a chance to kill the Murderer.
    //[15:05:36] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§eA player has picked up the Bow!§r
    //[15:05:36] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:A player has picked up the Bow!
    //[15:10:35] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§eYour Map now shows all players! §r§eYou can use it to locate remaining innocents.§r
    //[15:10:35] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:Your Map now shows all players! You can use it to locate remaining innocents.
    //[15:16:11] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§e你的地图现在可以显示所有玩家！ §r§e以此来定位剩余平民！§r
    //[15:16:11] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:你的地图现在可以显示所有玩家！ 以此来定位剩余平民！
    //[15:17:46] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§e§l母体§r§aDre4m_Bim§r§e§l因§r§a_n1ghtm4r3§r§e§l而现形！§r
    //[15:17:46] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:母体Dre4m_Bim因_n1ghtm4r3而现形！
    //[15:18:00] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§a§l幸存者位置§r§e已显示！§r
    //[15:18:00] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:幸存者位置已显示！
    //[15:22:55] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:34]: 接收消息getFormattedText:§r§a§lSurvivors §r§ehave been revealed!§r
    //[15:22:55] [main/INFO] (STDOUT) [ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil:printLastChat:35]: 接收消息getUnformattedText:Survivors have been revealed!
    //[CHAT] You feel a rush of adrenaline, you can shoot arrows faster now!
    //[CHAT] The alpha is now exposed on the minimap until they infect a survivor!
    //[CHAT] The alpha revealed their knife! They are no longer invincible.

    @SuppressWarnings("t")
    public static String matchMessage(String message) {
        if (message.contains("§r§6弓已掉落！") || message.contains("§r§6侦探已被杀死！")
                || message.contains("§r§6The Detective has been killed!")
                || message.contains("§r§6A Bow has been dropped!")
                || message.contains("§r§6The Bow has been dropped!")
                || message.contains("§r§6A Detective has been killed!")
                || message.contains("§r§6一把弓已掉落！")
                || message.contains("§r§6The Detective has left!")
                || message.contains("§r§6侦探退出了游戏！")) {
            return "弓掉落";
        }
        if (message.contains("一位玩家拿起了弓！") || message.contains("§r§eA player has picked up the Bow!")
        || message.contains("§r§eA player has picked up a Bow!")) {
            return "弓拿起";
        }
        if (message.contains("§r§e你的地图现在可以显示所有玩家！")
                || message.contains("§r§c小地图上已显示全部玩家")
                || message.contains("§r§eYour Map now shows all players!")
                || message.contains("§r§cAll players are now shown on the minimap")
        ) {
            //shouldPlaySound = false;
            return "地图启用";
        }
        if (message.contains("§r§a你感受到肾上腺素在你体内燃烧")
                || message.contains("§r§aYou feel a rush of adrenaline,")) {
            return "最后幸存者";
        }
        /*if (message.contains("§r§a§l幸存者位置§r§e已显示！") || message.contains("§r§a§lSurvivors §r§ehave been revealed!")) {
            return "揭露幸存者";
        }
        if (message.contains("母体被暴露在小地图上")
                || message.contains("§r§aThe alpha is now exposed on the minimap until they infect a survivor!")) {
            return "母体感染者暴露";
        }*/
        if (message.contains("§r§e§l而现形！§r") || message.contains("§r§e§l, has been revealed by")) {
            return "母体现形";
        }
        return null;
    }

    private void playSound(int value) {
        switch (value) {
            case 0:
                break;
            case 1:
                mc.thePlayer.playSound("note.pling", 1.0F, 1.0F);
                break;
            case 2:
                mc.thePlayer.playSound("random.orb", 1.0F, 1.0F);
                break;
            case 3:
                mc.thePlayer.playSound("random.levelup", 1.0F, 1.0F);
                break;
            case 4:
                //meow1-4
                mc.thePlayer.playSound("mob.cat.meow", 1.0F, 1.0F);
                break;
            case 5:
                mc.thePlayer.playSound("note.harp", 1.0F, 1.0F);
                break;
            case 6:
                mc.thePlayer.playSound("random.anvil_break", 1.0F, 1.0F);
                break;
            case 7:
                //hit1-4
                mc.thePlayer.playSound("mob.villager.hit", 1.0F, 1.0F);
        }
    }
}
