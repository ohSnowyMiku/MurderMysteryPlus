package ohSnowyMiku.MurderMysteryHelper.utils;

import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;

public class CheckPlayerGameMapNameUtil {
    public static boolean checkPlayerGameMapName(String mapName) {
        LocrawInfo locraw = LocrawUtil.INSTANCE.getLocrawInfo();
        if (locraw == null) {
            return false;
        }
        if (locraw.getMapName() == null) {
            return false;
        }

        return locraw.getMapName().equals(mapName);
    }
}
