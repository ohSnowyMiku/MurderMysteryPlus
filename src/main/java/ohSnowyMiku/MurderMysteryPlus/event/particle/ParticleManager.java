package ohSnowyMiku.MurderMysteryPlus.event.particle;

import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.utils.SimpleDelayedChatUtil;

public class ParticleManager {
    private static boolean onEnable = true;

    public static boolean isParticlesEnabled() {
        return onEnable;
    }

    public static void toggleParticles(boolean newState) {
        boolean oldState = onEnable;
        onEnable = newState;

        // 如果状态发生变化，发送聊天消息
        if (oldState != newState) {
            if (newState) {
                SimpleDelayedChatUtil.scheduleMessage("§d[MurderMysteryPlus] §aParticles Enable");
            } else {
                SimpleDelayedChatUtil.scheduleMessage("§d[MurderMysteryPlus] §cParticles Disable");
            }
        }
    }

    public static void toggleParticles() {
        if (!MurderMysteryPlusConfig.hideParticlesSwitch) return;
        toggleParticles(!onEnable);
    }
}
