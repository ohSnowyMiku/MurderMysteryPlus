package ohSnowyMiku.MurderMysteryPlus.event.sound;

import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoundListener {

    @SubscribeEvent
    public void onPlaySound(PlaySoundEvent event) {
        if (event.sound == null) {
            return;
        }
if (event.name.contains("mob.enderdragon.wing")) {
            String soundName = event.name;
            System.out.println("已经匹配到唱片机音符盒声音种类" + soundName);

        }
        if ("note.hat".equals(event.name)) {
            //WeaponCooldownHudEvent.isPlayingThrowingSound = true;
        }

    }
}
