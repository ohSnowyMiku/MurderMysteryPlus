package ohSnowyMiku.MurderMysteryHelper.event.events;

import cc.polyfrost.oneconfig.events.event.HudRenderEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;
import ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameTypeUtil;

import static ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameMapNameUtil.checkPlayerGameMapName;

public class SecretPassageHudEvent {

    public static boolean openStatus = false;
    public static int textColor = -65536;
    public static boolean shouldRender = false;
    
    @Subscribe
    public void renderSecretPassageOpenStatusHud(HudRenderEvent event) {
        if (!MurderMysteryHelperConfig.secretPassageOpenStatusSwitch) {
            return;
        }
            //LocrawInfo locraw = LocrawUtil.INSTANCE.getLocrawInfo();

            if (CheckPlayerGameTypeUtil.checkPlayerGameType()) {

                /*if (locraw.getMapName().equals("Spooky Mansion") || locraw.getMapName().equals("Archives")) {

                    shouldRenderInRightMap = true;
                }*/
                if (checkPlayerGameMapName("Spooky Mansion") || checkPlayerGameMapName("Archives")) {
                    shouldRender = true;
                }
            }
        }
    }

