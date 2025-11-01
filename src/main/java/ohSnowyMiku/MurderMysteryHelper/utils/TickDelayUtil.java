package ohSnowyMiku.MurderMysteryHelper.utils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TickDelayUtil {
    private static class Task {
        int ticksRemaining;
        Runnable action;

        Task(int ticks, Runnable action) {
            this.ticksRemaining = ticks;
            this.action = action;
        }
    }

    private static final List<Task> tasks = new LinkedList<>();

    /**
     * 添加一个延迟任务
     * @param ticks 延迟的 tick 数（20 tick ≈ 1 秒）
     * @param action 要执行的逻辑
     */
    public static void runLater(int ticks, Runnable action) {
        if (ticks < 0 || action == null) return;
        tasks.add(new Task(ticks, action));
    }

    /**
     * 每个客户端 tick 调用一次，自动倒计时并执行任务
     */
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return; // 只在 END 阶段执行，避免重复
        if (tasks.isEmpty()) return;

        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            task.ticksRemaining--;
            if (task.ticksRemaining <= 0) {
                try {
                    task.action.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                iterator.remove();
            }
        }
    }

    /**
     * 在 mod 初始化时调用一次，注册事件
     */
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new TickDelayUtil());
    }
}
