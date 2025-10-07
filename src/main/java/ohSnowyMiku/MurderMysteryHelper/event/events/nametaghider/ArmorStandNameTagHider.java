package ohSnowyMiku.MurderMysteryHelper.event.events.nametaghider;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;
import ohSnowyMiku.MurderMysteryHelper.utils.CheckPlayerGameTypeUtil;

import java.util.Arrays;
import java.util.List;

public class ArmorStandNameTagHider {

    private static final List<String> KEYWORDS = Arrays.asList(
            "2块金锭",
            "2 Gold",
            "捕鼠家",
            "Ratcatcher",
            "1块金锭",
            "1 Gold",
            "1金锭",
            "高分排行",
            "Top Scores",
            "3金锭",
            "3 Gold",
            "2金锭",
            "超级稀有",
            "限量版",
            "Ultra Rare",
            "Limited Edition",
            "超级无敌极度稀有",
            "传奇",
            "Ultimate Mega Supa Rare",
            "Legendary",
            "单轨列车",
            "Monorail",
            "你可以选择进入",
            "You can choose to enter",
            "过山车",
            "Roller Coaster",
            "神秘药水",
            "Mystery Potion",
            "右键点击座位即可乘坐",
            "Right-click seats to ride",
            "Teleporter",
            "传送器",
            "开启陷阱",
            "Open Trapdoor",
            "章鱼缸",
            "河豚缸",
            "Octopus Tank",
            "Pufferfish Tank",
            "Collect Fishing Rod",
            "Gold Reward Varies",
            "拉杆",
            "关门",
            "levers",
            "gate",
            "给老鼠的金锭",
            "Gold for Rats",
            "按钮",
            "拖拉机",
            "buttons",
            "tractor",
            "干草",
            "Hay",
            "hay",
            "摇下苹果",
            "Drop Apples",
            "游玩",
            "Play",
            "Points",
            "购买",
            "Buy",
            "这些门",
            "但要小心",
            "一个陷阱",
            "one of these doors",
            "But be aware",
            "be a trap",
            "Free",
            "免费",
            "挤压",
            "crusher",
            "玄关",
            "porch",
            "滚筒",
            "Barrels",
            "drop light",
            "以使灯砸下",
            "关门",
            "Close Gate",
            "用金锭兑换",
            "Gold for valuables",
            "Button",
            "Fishing Items",
            "Click to gift to Kali",
            "Pull lever to pray",
            "拉下拉杆祈祷神明",
            "点击敬奉祭品给神明",
            "跳！",
            "JUMP!",
            "Tickets",
            "请配合检票",
            "打我！",
            "Punch me!",
            "Don't hit me!",
            "痛！",
            "Ouch!",
            "点击敬奉祭品给",
            "Reach gift milestones for rewards",
            "Click to gift to",
            "尚无！",
            "No one yet!"

    );
    private static final List<String> SPECIAL_WORDS = Arrays.asList(
            "Platform Level",
            "平台站点",
            "楼上",
            "Upper Level",
            "Lobby",
            "1st Floor",
            "Basement",
            "1楼",
            "地窖",
            "大厅",
            "里程碑：",
            "Milestone:"
    );

    @SubscribeEvent
    public void hideArmorStandNameTag(RenderLivingEvent.Specials.Pre<EntityArmorStand> event) {

        if (!MurderMysteryHelperConfig.hideArmorStandNameTagSwitch) return;

        if (!CheckPlayerGameTypeUtil.checkPlayerGameType()) return;

        if (!(event.entity instanceof EntityArmorStand)) return;

        EntityArmorStand stand = (EntityArmorStand) event.entity;
        if (stand.hasCustomName()) {
            String name = stand.getCustomNameTag();

            if (checkArmorStandNameTag(name) == 0) return;

            if (name.contains("临终遗言") || name.contains("Last Words")) return;

            if (checkArmorStandNameTag(name) == 1) {
                event.setCanceled(true); // 取消默认的 nametag 渲染
            }
            if (checkArmorStandNameTag(name) == 2) {
                hideBehindBlock(event,stand);
            }
        }
    }

    private int checkArmorStandNameTag(String name) {
        if (name == null || name.isEmpty()) return 0;

        for (String k : KEYWORDS) {
            if (name.contains(k)) return 1;
        }

        if (name.contains(".") && name.contains("分")) {
            return 1;
        }

        for (String s : SPECIAL_WORDS) {
            if (name.contains(s)) return 2;
        }

        return 0;
    }

    private void hideBehindBlock(RenderLivingEvent.Specials.Pre<EntityArmorStand> e, EntityArmorStand stand) {
        Minecraft mc = Minecraft.getMinecraft();
        Vec3 eyes = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY + mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ);
        Vec3 target = new Vec3(stand.posX, stand.posY + stand.getEyeHeight(), stand.posZ);

        // false,false 表示忽略液体，不允许穿透非透明方块
        MovingObjectPosition result = mc.theWorld.rayTraceBlocks(eyes, target, false, true, false);

        if (result != null && result.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            //System.out.println("step2");
            // 有方块挡住，取消名牌绘制
            e.setCanceled(true);
        }
    }
}
