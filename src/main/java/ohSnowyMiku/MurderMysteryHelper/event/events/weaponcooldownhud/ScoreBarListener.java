package ohSnowyMiku.MurderMysteryHelper.event.events.weaponcooldownhud;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;
import ohSnowyMiku.MurderMysteryHelper.enums.RoleEnum;
import ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameTypeUtil;
import ohSnowyMiku.MurderMysteryHelper.utils.CheckScoreBarInfoUtil;

import java.util.Collection;
import java.util.List;

import static ohSnowyMiku.MurderMysteryHelper.enums.RoleEnum.*;

public class ScoreBarListener {
    private String lastRole = null;

    @SubscribeEvent
    public void checkScoreBarRole(TickEvent.ClientTickEvent event) {
        String detectedRole = null;

        if (!CheckPlayerGameTypeUtil.checkPlayerGameType() || !MurderMysteryHelperConfig.showWeaponCooldownHudSwitch || event.phase != TickEvent.Phase.END)
            return;
        //System.out.println("[ScoreBar] Tick END 触发");

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null || mc.thePlayer == null) {
            //System.out.println("[ScoreBar] 世界或玩家为 null");
            return;
        }


        Scoreboard sb = mc.theWorld.getScoreboard();
        if (sb == null) {
            updateRole(null);
            //System.out.println("[ScoreBar] Scoreboard 为 null");
            return;
        }

        List<String> entries = CheckScoreBarInfoUtil.checkScoreBarInfo(sb);

        for (String clean : entries) {

            detectedRole = matchRole(clean);
            if (detectedRole != null) {
                break;
            }
        }
        updateRole(detectedRole);

    }

    private String matchRole(String clean) {
        if (clean.contains(MURDERER.chineseWithColorCode)
                || clean.contains(MURDERER.englishWithColorCode)) {
            //System.out.println("计分板匹配为杀手");
            return MURDERER.chinese;
        }
        if (clean.contains(ALPHA.chineseWithColorCode)
                || clean.contains(ALPHA.englishWithColorCode)) {
            //System.out.println("计分板匹配为母体");
            return ALPHA.chinese;
        }
        if (clean.contains(ASSASSIN.chineseWithColorCode)
                || clean.contains(ASSASSIN.englishWithColorCode)) {
            //System.out.println("计分板匹配为刺客");
            return ASSASSIN.chinese;
        }
        if (clean.contains(INFECTED.chineseWithColorCode)
                || clean.contains(INFECTED.englishWithColorCode)) {
            //System.out.println("计分板匹配为感染者");
            return INFECTED.chinese;
        }
        if (clean.contains(DETECTIVE.chineseWithColorCode)
                || clean.contains(DETECTIVE.englishWithColorCode)) {
            //System.out.println("计分板匹配为侦探");
            return DETECTIVE.chinese;
        }
        if (clean.contains(SURVIVOR.chineseWithColorCode)
                || clean.contains(SURVIVOR.englishWithColorCode)) {
            //System.out.println("计分板匹配为幸存者");
            return SURVIVOR.chinese;
        }
        return null;
    }

    public void updateRole(String newRole) {

        if ((newRole == null && lastRole == null)
                || (newRole != null && newRole.equals(lastRole))) {
            return;
        }
        lastRole = newRole;
        ChatHandler.playerRole = newRole;
        //System.out.println("玩家角色更新为: " + newRole);
        // 侧边栏一旦空了，直接停掉倒计时和 HUD
        if (newRole == null) {
            CountDownManager.stop();
        }
    }
}




