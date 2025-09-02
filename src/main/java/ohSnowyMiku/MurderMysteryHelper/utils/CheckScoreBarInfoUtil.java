package ohSnowyMiku.MurderMysteryHelper.utils;

import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckScoreBarInfoUtil {

    public static List<String> checkScoreBarInfo(Scoreboard sb) {

        ScoreObjective obj = sb.getObjectiveInDisplaySlot(1);
        if (obj == null) {
            return Collections.emptyList();
        }
        List<String> entries = new ArrayList<String>();
        for (Score score : sb.getScores()) {
            if (!score.getObjective().equals(obj)) {
                continue;
            }

            String clean = getFullEntryText(sb, score).trim();
            if (!clean.isEmpty()) {
                entries.add(clean);
            }
        }
        return entries;
    }

    private static String getFullEntryText(Scoreboard sb, Score score) {
        String name = score.getPlayerName();
        ScorePlayerTeam team = sb.getPlayersTeam(name);
        String prefix = team != null ? team.getColorPrefix() : "";
        String suffix = team != null ? team.getColorSuffix() : "";
        return prefix + name + suffix;
    }
}
