package ohSnowyMiku.MurderMysteryHelper.utils;

public class CheckPlayerJoinWorldUtil {

    /*private int delayTicks = -1;

    @SubscribeEvent
    public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
        if (event.world.isRemote && event.entity == Minecraft.getMinecraft().thePlayer) {



            //System.out.println("本实体加入");
            resetHudState();

            ChatPlayerRoleListener.clearAll();
            HiddenMurderTitleTipEvent.shouldCancel = false;

            delayTicks = 60;
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (delayTicks > 0) {
                //System.out.println("倒计时开始");
                delayTicks--;
                if (delayTicks == 0) {
                    checkSecretPassageMap();
                }
            }
        }
    }

    private void checkSecretPassageMap() {
        //System.out.println("地图检测方法开始执行");

        if (!MurderMysteryHelperConfig.secretPassageOpenStatusSwitch) {
            return;
        }

        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) {
            return;
        }
        //System.out.println("开始检测地图");

        NewSecretPassageHudEvent.shouldRenderInRightMap = checkPlayerGameMapName("Spooky Mansion") || checkPlayerGameMapName("Archives");
        //System.out.println("地图检测为:" + NewSecretPassageHudEvent.shouldRenderInRightMap);
    }

    private void resetHudState() {
        //System.out.println("已重置状态");
        // 重置你的 HUD 状态变量，例如 shouldRenderInRightMap = false;
        NewSecretPassageHudEvent.shouldRenderInRightMap = false;
        NewSecretPassageHudEvent.openStatus = false;
        NewSecretPassageHudEvent.textColor = -65536;
    }*/

}

