package ohSnowyMiku.MurderMysteryHelper.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreBarPlayerCountUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final Pattern COUNT_PATTERN = Pattern.compile("(?:剩余平民：|Innocents\\s+Left:)\\s*[^0-9]*?(\\d+)");

    public static int getPlayerCount() {
        if (mc.theWorld == null) return -1;

        Scoreboard sb = mc.theWorld.getScoreboard();
        ScoreObjective objective = sb.getObjectiveInDisplaySlot(1);
        //System.out.println("初始化计分板");

        if (objective == null) return -1;

        Collection<Score> scores = sb.getSortedScores(objective);
        //System.out.println("准备遍历");
        for (Score s : scores) {
            String fakeName = s.getPlayerName();

            ScorePlayerTeam team = sb.getPlayersTeam(fakeName);
            String rendered = ScorePlayerTeam.formatPlayerName(team, fakeName);

            String clean = StringUtils.stripControlCodes(rendered);
            //System.out.println("格式化完成");

            if (clean.contains("Innocents")) {
                //System.out.println(clean);
            }

            Matcher m = COUNT_PATTERN.matcher(clean);
            if (m.find()) {
                //System.out.println(Integer.parseInt(m.group(1)));
                return Integer.parseInt(m.group(1));
            }
        }
        return -1;
    }
}
//剩余平民：1🌠
//TODO 在匹配 检测是否在组队 从而决定是否发送