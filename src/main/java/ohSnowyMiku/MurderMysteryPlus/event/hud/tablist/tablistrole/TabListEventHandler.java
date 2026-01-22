package ohSnowyMiku.MurderMysteryPlus.event.hud.tablist.tablistrole;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;

import java.util.*;

import static ohSnowyMiku.MurderMysteryPlus.event.hud.tablist.tablistrole.ChatPlayerRoleListener.NAMETOPREFIX;

public class TabListEventHandler {
    // 是否无条件覆盖已有 displayName
    private static final boolean FORCE_OVERRIDE = true;

    // 保活频率（Tick）。1秒≈20Tick。10=每0.5秒检查一次
    private static final int KEEPALIVE_TICKS = 10;

    private final Minecraft mc = Minecraft.getMinecraft();
    private int coolDown = 0;

    private final Map<UUID, IChatComponent> originalDisplayName = new HashMap<>();

    String name = null;


    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null || mc.thePlayer == null) return;
        if (!MurderMysteryPlusConfig.showPlayerRolesInTabSwitch) return;

        if (coolDown > 0) {
            coolDown--;
            return;
        }
        coolDown = KEEPALIVE_TICKS;

        applyForMarkedPlayers();
    }

    private void applyForMarkedPlayers() {
        Collection<NetworkPlayerInfo> infos = mc.thePlayer.sendQueue != null
                ? mc.thePlayer.sendQueue.getPlayerInfoMap()
                : Collections.<NetworkPlayerInfo>emptyList();

        if (infos.isEmpty()) return;

        //String name = null;
        for (NetworkPlayerInfo info : infos) {
            name = info.getGameProfile().getName();
            UUID uuid = info.getGameProfile().getId();

            String rolePrefix = NAMETOPREFIX.get(name);

            // 如果该玩家没有被标记，但我们曾经改过，就恢复
            if (rolePrefix == null || rolePrefix.isEmpty()) {
                if (originalDisplayName.containsKey(uuid)) {
                    safeSetDisplayName(info, originalDisplayName.get(uuid));
                    originalDisplayName.remove(uuid);
                }
                continue;
            }

            // 队伍颜色前缀和后缀（后缀 1.8.9 通常为空，这里固定空字符串即可）
            ScorePlayerTeam team = mc.theWorld.getScoreboard().getPlayersTeam(name);
            String teamColorPrefix = (team == null) ? "" : team.getColorPrefix(); // 或者 team.getFormattedName()
            String suffix = ""; // 1.8.9 通常没有后缀

            // 最终显示：队伍色 + 我们的前缀 + 玩家名 + §r + 后缀
            String finalText = teamColorPrefix + rolePrefix + name + "§r" + suffix;


            safeSetDisplayName(info, new ChatComponentText(finalText));
        }
    }

    private void safeSetDisplayName(NetworkPlayerInfo info, IChatComponent component) {
        try {
            info.setDisplayName(component);
        } catch (Throwable t) {
            // 极端环境下映射差异可回退为反射字段写入（通常 1.8.9 不需要）
            try {
                java.lang.reflect.Field f = NetworkPlayerInfo.class.getDeclaredField("displayName");
                f.setAccessible(true);
                f.set(info, component);
            } catch (Throwable ignored) {}
        }
    }
}