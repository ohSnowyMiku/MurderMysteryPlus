package ohSnowyMiku.MurderMysteryPlus.utils;


import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleDelayedChatUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();

    private static final int TICK_DELAY = 10;

    private static final Queue<Task> pending = new LinkedList<>();


    private static class Task{
        final String msg;
        int tickLeft;

        Task(String msg) {
            this.msg = msg;
            this.tickLeft = TICK_DELAY;
        }
    }

    public static void scheduleMessage(String text) {
        pending.offer(new Task(text));
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END || pending.isEmpty()) return;
        Task t = pending.peek();
        if (--t.tickLeft <= 0) {
            mc.thePlayer.addChatMessage(new ChatComponentText(t.msg));
            pending.poll();
        }
    }
}
