package ohSnowyMiku.MurderMysteryPlus.event.particle;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;

public class ArmorStandParticle {
    private static final double ANGLE_INCREMENT = 0.04;
    private static final double FULL_CIRCLE = 2 * Math.PI;
    private static final double GROUND_OFFSET = 0.5;
    private static final int PARTICLE_COUNT = 4;
    private static final double PARTICLE_RADIUS = 0.8;

    private final Minecraft mc = Minecraft.getMinecraft();
    private double angle = 0;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!shouldProcessEvent(event)) {
            return;
        }

        updateAngle();
        processArmorStands();
    }

    private boolean shouldProcessEvent(TickEvent.ClientTickEvent event) {
        return MurderMysteryPlusConfig.showBowArmorStandParticleSwitch
                && event.phase == TickEvent.Phase.END
                && mc.theWorld != null;
    }

    private void updateAngle() {
        angle = (angle + ANGLE_INCREMENT) % FULL_CIRCLE;
    }

    private void processArmorStands() {
        mc.theWorld.loadedEntityList.stream()
                .filter(EntityArmorStand.class::isInstance)
                .map(EntityArmorStand.class::cast)
                .filter(this::isValidArmorStand)
                .forEach(this::spawnParticles);
    }

    private boolean isValidArmorStand(EntityArmorStand stand) {
        if (!stand.isInvisible()) {
            return false;
        }
        ItemStack heldItem = stand.getHeldItem();
        return heldItem != null && heldItem.getItem() == Items.bow;
    }

    private void spawnParticles(EntityArmorStand stand) {
        double x = stand.posX;
        double y = stand.posY + GROUND_OFFSET;
        double z = stand.posZ;

        for (int i = 0; i < PARTICLE_COUNT; i++) {
            double theta = angle + (FULL_CIRCLE * i / PARTICLE_COUNT);
            double px = x + Math.cos(theta) * PARTICLE_RADIUS;
            double pz = z + Math.sin(theta) * PARTICLE_RADIUS;

            mc.theWorld.spawnParticle(
                    EnumParticleTypes.SPELL_WITCH,
                    px, y, pz,
                    0, 0, 0
            );
        }
    }
}





