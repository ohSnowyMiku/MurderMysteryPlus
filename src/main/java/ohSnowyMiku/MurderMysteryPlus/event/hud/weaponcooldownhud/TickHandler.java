package ohSnowyMiku.MurderMysteryPlus.event.hud.weaponcooldownhud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickHandler {
    public static final TickHandler INSTANCE = new TickHandler();

    private TickHandler() {
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        //System.out.println("已经注册tick事件");
        if (event.phase == TickEvent.Phase.END) {
            //System.out.println("tick");
            CountDownManager.INSTANCE.tick();
        }
    }
}
