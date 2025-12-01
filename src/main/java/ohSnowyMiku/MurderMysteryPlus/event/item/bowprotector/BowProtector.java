package ohSnowyMiku.MurderMysteryPlus.event.item.bowprotector;

import cc.polyfrost.oneconfig.events.event.SendPacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.utils.CheckScoreBarInfoUtil;
import ohSnowyMiku.MurderMysteryPlus.utils.SimpleDelayedChatUtil;

import java.util.List;

import static ohSnowyMiku.MurderMysteryPlus.enums.RoleEnum.DETECTIVE;
import static ohSnowyMiku.MurderMysteryPlus.utils.CheckPlayerGameMapNameUtil.checkPlayerGameMapName;


public class BowProtector {
    private static final Minecraft mc = Minecraft.getMinecraft();
    //private static final ItemStack held = mc.thePlayer.getCurrentEquippedItem();

    @Subscribe
    public void onSendPacket(SendPacketEvent event) {

        if (!MurderMysteryPlusConfig.bowProtectorSwitch) return;

        if (checkPlayerRole()) return;

        if (!checkPlayerGameMapName("Ancient Tomb")) return;

        onRightClickBlock(event);
        onRightClickEntity(event);
    }


    public void onRightClickEntity(SendPacketEvent event) {
        /*Minecraft mc = Minecraft.getMinecraft();
        ItemStack held = mc.thePlayer.getCurrentEquippedItem();*/

        //if (!MurderMysteryPlusConfig.bowProtectorSwitch) return;

        //if (checkPlayerRole()) return;

        //if (!checkPlayerGameMapName("Ancient Tomb")) return;

        if (event.packet instanceof C02PacketUseEntity) {
            //System.out.println("第一步");
            C02PacketUseEntity packet = (C02PacketUseEntity) event.packet;
            Entity target = packet.getEntityFromWorld(Minecraft.getMinecraft().theWorld);


            if (target instanceof EntityArmorStand) {
                //System.out.println("第2步");

                String standName = target.getName();

                if (!checkArmorStandNameTag(standName)) return;
                //System.out.println("第3步");
                if (checkHeldItem()) {
                    //System.out.println("Entity已取消");
                    event.isCancelled = true;
                    SimpleDelayedChatUtil.scheduleMessage("§d[MurderMysteryPlus] §a已自动保护侦探弓不被献祭(可在潜行状态强制献祭)");
                }
            }
        }
    }


    public void onRightClickBlock(SendPacketEvent event) {
        //if (!MurderMysteryPlusConfig.bowProtectorSwitch) return;

        //if (checkPlayerRole()) return;

        if (event.packet instanceof C08PacketPlayerBlockPlacement) {
            C08PacketPlayerBlockPlacement packet = (C08PacketPlayerBlockPlacement) event.packet;

            BlockPos pos = packet.getPosition();
            if (pos == null) return;

            World world = Minecraft.getMinecraft().theWorld;
            if (!world.isBlockLoaded(pos)) return;

            Block block = world.getBlockState(pos).getBlock();

            if (block == Blocks.end_portal_frame && checkHeldItem()) {
                //System.out.println("stand已取消");
                event.isCancelled = true;
                SimpleDelayedChatUtil.scheduleMessage("§d[MurderMysteryPlus] §a已自动保护侦探弓不被献祭(可在潜行状态强制献祭)");
            }
        }
    }


    private boolean checkHeldItem() {
        /*Minecraft mc = Minecraft.getMinecraft();
        ItemStack held = mc.thePlayer.getCurrentEquippedItem();*/
        ItemStack held = mc.thePlayer.getCurrentEquippedItem();
        if (mc.thePlayer.isSneaking()) {
            return false;
        }
        return held != null
                && held.getItem() == Items.bow
                && held.hasDisplayName()
                && (held.getDisplayName().contains("§aBow")
                //&& (held.getDisplayName().contains("1Bow")
                || held.getDisplayName().contains("§a弓"));
    }

    private boolean checkArmorStandNameTag(String standName) {
        if (standName == null) return false;

        if (standName.contains("拉下拉杆祈祷神明")
                || standName.contains("Pull lever to pray")
                || standName.contains("点击敬奉祭品给神明")
                || standName.contains("Click to gift to Kali")) {
            return true;
        }

        return false;
    }

    private boolean checkPlayerRole() {
        if (mc.theWorld == null || mc.thePlayer == null) {
            return true;
        }


        Scoreboard sb = mc.theWorld.getScoreboard();
        if (sb == null) {
            return true;
        }

        List<String> entries = CheckScoreBarInfoUtil.checkScoreBarInfo(sb);

        for (String clean : entries) {

            if (clean.contains(DETECTIVE.chineseWithColorCode)
                    || clean.contains(DETECTIVE.englishWithColorCode)) {
                return false;
            }
        }
        return true;
    }
}
