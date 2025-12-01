package ohSnowyMiku.MurderMysteryPlus.utils;

import net.minecraft.client.Minecraft;

public class PlayerCountUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static int getPlayerCount() {
        if (mc.getNetHandler() == null) {
            return 0;
        }
        return mc.getNetHandler().getPlayerInfoMap().size();
    }
}
