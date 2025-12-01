package ohSnowyMiku.MurderMysteryPlus.utils;

import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.events.event.Stage;
import cc.polyfrost.oneconfig.events.event.TickEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.network.play.server.S01PacketJoinGame;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.event.hud.title.titlehider.HiddenMurderTipTitleEvent;
import ohSnowyMiku.MurderMysteryPlus.event.hud.mapeventhud.SecretPassageHudEvent;
import ohSnowyMiku.MurderMysteryPlus.event.chat.RoleSender;
import ohSnowyMiku.MurderMysteryPlus.event.hud.tablist.tablistrole.ChatPlayerRoleListener;

import static ohSnowyMiku.MurderMysteryPlus.utils.CheckPlayerGameMapNameUtil.checkPlayerGameMapName;

public class CheckPlayerJoinGameUtil {

    private int delayTicks = -1;

    @Subscribe
    public void onPlayerJoin(ReceivePacketEvent event) {
        if (event.packet instanceof S01PacketJoinGame) {
            //System.out.println("检测到进入服务器");
            resetHudState();

            ChatPlayerRoleListener.clearAll();
            HiddenMurderTipTitleEvent.shouldCancel = false;
            RoleSender.shouldCheckTitle = true;

            delayTicks = 60;
        }
    }

    @Subscribe
    public void onTick(TickEvent event) {
        //System.out.println("开始执行tick逻辑");
        if (event.stage == Stage.END) { // 只在 tick 结束阶段执行一次
            if (delayTicks > 0) {
                //System.out.println("计时中");
                delayTicks--;
                if (delayTicks == 0) {
                    //System.out.println("计时结束");
                    checkSecretPassageMap(); // 延迟结束后执行地图检测
                }
            }
        }
    }

    private void checkSecretPassageMap() {
        //System.out.println("地图检测方法开始执行");

        if (!MurderMysteryPlusConfig.secretPassageOpenStatusSwitch) {
            return;
        }

        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) {
            return;
        }
        //System.out.println("开始检测地图");

        SecretPassageHudEvent.shouldRenderInRightMap = checkPlayerGameMapName("Spooky Mansion") || checkPlayerGameMapName("Archives");
        //System.out.println("地图检测为:" + NewSecretPassageHudEvent.shouldRenderInRightMap);
    }

    private void resetHudState() {
        //System.out.println("已重置状态");
        // 重置你的 HUD 状态变量，例如 shouldRenderInRightMap = false;
        SecretPassageHudEvent.shouldRenderInRightMap = false;
        SecretPassageHudEvent.openStatus = false;
        SecretPassageHudEvent.textColor = -65536;
    }

    /*   @Subscribe
    public void onPlayerJoin(ReceivePacketEvent event) {
        if (event.packet instanceof S01PacketJoinGame) {
            SecretPassageHudEvent.openStatus = false;
            SecretPassageHudEvent.textColor = -65536;
            SecretPassageHudEvent.shouldRender = false;

            //ChatRoleListener.inTeam = false;
            //playerRoles.clear();
            ChatPlayerRoleListener.clearAll();
            HiddenMurderTitleTipEvent.shouldCancel = false;
        }
    }*/

}
