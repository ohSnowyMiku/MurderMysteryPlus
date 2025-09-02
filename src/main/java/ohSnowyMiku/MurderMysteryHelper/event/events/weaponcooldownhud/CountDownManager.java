package ohSnowyMiku.MurderMysteryHelper.event.events.weaponcooldownhud;

public class CountDownManager {
    public static boolean active = false;
    public static int tickRemaining = 0;
    public static final CountDownManager INSTANCE = new CountDownManager();

    //开启倒计时 传入tick
    public static void start(int ticks) {
        //System.out.println("倒计时开始");
        active = true;
        tickRemaining = ticks;
    }

    //停止倒计时清除绘制
    public static void stop() {
        //System.out.println("倒计时结束");
        active = false;
        tickRemaining = 0;
    }

    //每tick调用1次 递减
    public static void tick() {
        //SSystem.out.println("开始递减tick");
        if (!active) {
            return;
        }

        if (tickRemaining > 0) {
            tickRemaining--;
        } else {
            active = false;
        }
    }

    public static boolean isActive() {
        return active;
    }

    public static float getSecondsLeft() {
        return tickRemaining / 20.0f;
    }
}
