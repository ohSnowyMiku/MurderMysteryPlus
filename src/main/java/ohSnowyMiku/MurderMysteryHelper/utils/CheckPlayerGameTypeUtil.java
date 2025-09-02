package ohSnowyMiku.MurderMysteryHelper.utils;

import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;

public class CheckPlayerGameTypeUtil {

    public static boolean checkPlayerGameType() {
        LocrawInfo locraw = LocrawUtil.INSTANCE.getLocrawInfo();
        if (locraw == null) {
            return false;
        }
        if (locraw.getGameType() == null) {
            return false;
        }

        return locraw.getGameType() == LocrawInfo.GameType.MURDER_MYSTERY;
    }

}
