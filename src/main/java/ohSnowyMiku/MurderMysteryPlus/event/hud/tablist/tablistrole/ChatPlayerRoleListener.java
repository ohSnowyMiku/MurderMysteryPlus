package ohSnowyMiku.MurderMysteryPlus.event.hud.tablist.tablistrole;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatPlayerRoleListener {


    // 聊天触发控制开关
    private static final boolean ENABLED = MurderMysteryPlusConfig.showPlayerRolesInTabSwitch;

    // 玩家 → 前缀 映射
    public static final Map<String, String> NAMETOPREFIX = new HashMap<>();

    /**
     * 定义一条角色匹配规则：
     * keyword: 聊天原文里只要包含这个就尝试用 pattern 提取玩家名
     * pattern: 用于从“已清理色码”的文本里提取玩家名
     * prefix : 给这个玩家加的前缀
     * addSelf: 如果为 true，除了标记目标玩家，也额外给自己标记一次
     */
    private static class RoleDef {
        final String keyword;
        final Pattern pattern;
        final String prefix;
        final boolean addSelf;

        RoleDef(String keyword, String regex, String prefix, boolean addSelf) {
            this.keyword = keyword;
            this.pattern = Pattern.compile(regex);
            this.prefix = prefix;
            this.addSelf = addSelf;
        }
    }

    // 所有规则，按优先级排列
    private static final List<RoleDef> RULES = Arrays.asList(
            new RoleDef("§a神明告诉你",
                    "神明告诉你(\\S+)是侦探！",
                    "§b[侦探]§r §b",
                    false),
            new RoleDef("§aKali shows you that",
                    "Kali shows you that (\\S+) is the Detective!",
                    "§b[D]§r §b",
                    false),
            new RoleDef("§9你的侦探同伴是",
                    "你的侦探同伴是： (\\S+)",
                    "§b[侦探]§r §b",
                    true),
            new RoleDef("§9Your fellow Detective is",
                    "Your fellow Detective is: (\\S+)",
                    "§b[D]§r §b",
                    true),
            new RoleDef("§a你意识到",
                    "你意识到(?:\\[(?:VIP\\+{0,2}|MVP\\+{0,2})\\])?\\s?(\\S+)有谋杀其他人的想法！",
                    "§c[杀手]§r §c",
                    false),
            new RoleDef("§aYou see that",
                    "You see that (?:\\[(?:VIP\\+{0,2}|MVP\\+{0,2})\\])?\\s?(\\S+) has murderous",
                    "§c[M]§r §c",
                    false)
    );

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!ENABLED || event.message == null) return;

        String raw = event.message.getFormattedText();
        String clean = stripColor(raw);

        for (RoleDef rule : RULES) {
            if (!raw.contains(rule.keyword)) continue;

            Matcher m = rule.pattern.matcher(clean);
            if (m.find()) {
                String target = m.group(1).trim();
                if (!target.isEmpty()) {
                    applyRole(target, rule.prefix, rule.addSelf);
                }
            }
            // 每条消息只触发一个规则
            break;
        }
    }

    // 去掉 § 颜色和格式码
    private String stripColor(String s) {
        return EnumChatFormatting.getTextWithoutFormattingCodes(s).trim();
    }

    // 核心：写入前缀并发延迟提示
    private void applyRole(String playerName, String prefix, boolean addSelf) {
        // 标记目标玩家
        NAMETOPREFIX.put(playerName, prefix);
        //SimpleDelayedChatUtil.scheduleMessage("§d[MurderMysteryPlus] §a已自动在TabList上标记玩家: §l§f" + playerName);

        // 如果需要，也给自己标记
        if (addSelf) {
            String self = Minecraft.getMinecraft().thePlayer.getName();
            NAMETOPREFIX.put(self, prefix);
            //SimpleDelayedChatUtil.scheduleMessage("§d[MurderMysteryPlus] §a已自动在TabList上标记玩家: §l§f" + self);
        }
    }

    // 外部调用可清空所有标记
    public static void clearAll() {
        NAMETOPREFIX.clear();
    }
}


//接收消息getFormattedText:§r§f                    §r§a神明告诉你Miku_AveMujica§r§a是侦探！§r
//接收消息getUnformattedText:                    神明告诉你Miku_AveMujica是侦探！
//神明告诉你ohSnowyMiku是侦探！
//神明告诉你Miku_AveMujica是侦探！
//Kali shows you that ohSnowyMiku is the Detective!
//你的侦探同伴是： Miku_AveMujica
//Your fellow Detective is: Miku_AveMujica

//You see that [MVP++] Miku_AveMujica has murderous thoughts!
//You see that Miku_AveMujica has murderous thoughts!
//You see that [MVP++] Miku_AveMujica has murderous thoughts!
//你意识到Miku_AveMujica有谋杀其他人的想法！
//你意识到ohSnowyMiku有谋杀其他人的想法！
//你意识到[VIP] Miku_AveMujica有谋杀其他人的想法！
//你意识到[MVP++] Miku_AveMujica有谋杀其他人的想法！

//You see that [MVP++] ohSnowyMiku has murderous thoughts!

//[17:41:05] [Client thread/INFO] [STDOUT/]: [ohSnowyMiku.MurderMysteryPlus.event.events.tablistrole.ChatRoleListener:checkPlayerRole:109]: 已经匹配到正确内容
//[17:41:05] [Client thread/INFO] [STDOUT/]: [ohSnowyMiku.MurderMysteryPlus.event.events.tablistrole.ChatRoleListener:getCleanMessage:120]: 已经清除空格和颜色符 :You see that [MVP++] Miku_AveMujica has murderous
//[17:41:05] [Client thread/INFO] [STDOUT/]: [ohSnowyMiku.MurderMysteryPlus.event.events.tablistrole.ChatRoleListener:getPlayerName:127]: 尝试获取id

//[18:04:49] [Client thread/INFO] [STDOUT/]: [ohSnowyMiku.MurderMysteryPlus.event.events.MessageEvent:getChatText:18]: 接收消息getFormattedText:§r§f       §r§aYou see that §r§6[MVP§r§0++§r§6] Miku_AveMujica§r§f §r§ahas murderous§r
//[18:04:49] [Client thread/INFO] [STDOUT/]: [ohSnowyMiku.MurderMysteryPlus.event.events.MessageEvent:getChatText:19]: 接收消息getUnformattedText:       You see that [MVP++] Miku_AveMujica has murderous
//[18:04:49] [Client thread/INFO] [STDOUT/]: [ohSnowyMiku.MurderMysteryPlus.event.events.tablistrole.ChatRoleListener:checkPlayerRole:109]: 已经匹配到正确内容
//[18:04:49] [Client thread/INFO] [STDOUT/]: [ohSnowyMiku.MurderMysteryPlus.event.events.tablistrole.ChatRoleListener:getCleanMessage:120]: 已经清除空格和颜色符以及rank :You see that [MVP++] Miku_AveMujica has murderous
//[18:04:49] [Client thread/INFO] [STDOUT/]: [ohSnowyMiku.MurderMysteryPlus.event.events.tablistrole.ChatRoleListener:getPlayerName:127]: 尝试获取id

