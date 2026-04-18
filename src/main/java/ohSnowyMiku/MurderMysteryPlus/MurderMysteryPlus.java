package ohSnowyMiku.MurderMysteryPlus;

import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import ohSnowyMiku.MurderMysteryPlus.command.ExampleCommand;
import ohSnowyMiku.MurderMysteryPlus.config.MurderMysteryPlusConfig;
import ohSnowyMiku.MurderMysteryPlus.event.chat.*;
import ohSnowyMiku.MurderMysteryPlus.event.hud.mapeventhud.SecretPassageHudEvent;
import ohSnowyMiku.MurderMysteryPlus.event.hud.tablist.tablistrole.ChatPlayerRoleListener;
import ohSnowyMiku.MurderMysteryPlus.event.hud.tablist.tablistrole.TabListEventHandler;
import ohSnowyMiku.MurderMysteryPlus.event.hud.title.TitleTips;
import ohSnowyMiku.MurderMysteryPlus.event.hud.title.titlehider.HiddenMurderTipTitleEvent;
import ohSnowyMiku.MurderMysteryPlus.event.hud.weaponcooldownhud.ChatHandler;
import ohSnowyMiku.MurderMysteryPlus.event.hud.weaponcooldownhud.ScoreBarListener;
import ohSnowyMiku.MurderMysteryPlus.event.hud.weaponcooldownhud.TickHandler;
import ohSnowyMiku.MurderMysteryPlus.event.item.bowprotector.BowProtector;
import ohSnowyMiku.MurderMysteryPlus.event.item.gestureprotector.ArmorStandHandlerEvent;
import ohSnowyMiku.MurderMysteryPlus.event.item.invisiblebow.BowHideHandler;
import ohSnowyMiku.MurderMysteryPlus.event.nametag.nametaghider.ArmorStandNameTagHider;
import ohSnowyMiku.MurderMysteryPlus.event.particle.ArmorStandParticle;
import ohSnowyMiku.MurderMysteryPlus.event.sound.ChatPingHandler;
import ohSnowyMiku.MurderMysteryPlus.event.sound.SoundListener;
import ohSnowyMiku.MurderMysteryPlus.other.resourcepack.hook;
import ohSnowyMiku.MurderMysteryPlus.utils.ChatUtil;
import ohSnowyMiku.MurderMysteryPlus.utils.CheckPlayerJoinGameUtil;
import ohSnowyMiku.MurderMysteryPlus.utils.SimpleDelayedChatUtil;
import ohSnowyMiku.MurderMysteryPlus.utils.TickDelayUtil;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = MurderMysteryPlus.MODID, name = MurderMysteryPlus.NAME, version = MurderMysteryPlus.VERSION)
public class MurderMysteryPlus {

    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    @Mod.Instance(MODID)
    public static MurderMysteryPlus INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static MurderMysteryPlusConfig config;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new MurderMysteryPlusConfig();

        CommandManager.INSTANCE.registerCommand(new ExampleCommand());
        //CommandManager.INSTANCE.registerCommand(new UpdateCommand());
        //UpdateApplier.applyCachedUpdate();

        //EventManager.INSTANCE.register(new SecretPassageHudEvent());
        EventManager.INSTANCE.register(new SecretPassageHudEvent());
        //EventManager.INSTANCE.register(new SecretPassageTitleEvent());
        //EventManager.INSTANCE.register(new PlayerJoinGameUtil());
        EventManager.INSTANCE.register(new CheckPlayerJoinGameUtil());
        EventManager.INSTANCE.register(new HiddenMurderTipTitleEvent());
        EventManager.INSTANCE.register(new BowProtector());
        EventManager.INSTANCE.register(new RoleSender());
        //EventManager.INSTANCE.register(new ParticleManager());

        //MinecraftForge.EVENT_BUS.register(new GetChatMessageUtil());
        //MinecraftForge.EVENT_BUS.register(Get1ChatMessageUtil.class);
        MinecraftForge.EVENT_BUS.register(new ChatUtil());
        MinecraftForge.EVENT_BUS.register(new HiddenKaliSpamEvent());
        MinecraftForge.EVENT_BUS.register(new BowHideHandler());
        MinecraftForge.EVENT_BUS.register(new ScoreBarListener());
        MinecraftForge.EVENT_BUS.register(ChatHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(TickHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(new ArmorStandHandlerEvent());
        MinecraftForge.EVENT_BUS.register(new TabListEventHandler());
        MinecraftForge.EVENT_BUS.register(new ChatPlayerRoleListener());
        MinecraftForge.EVENT_BUS.register(new SimpleDelayedChatUtil());
        MinecraftForge.EVENT_BUS.register(new ArmorStandNameTagHider());
        //MinecraftForge.EVENT_BUS.register(new ChatFixHandler());
        MinecraftForge.EVENT_BUS.register(new ChatChannelSwap());
        MinecraftForge.EVENT_BUS.register(new AutoShowShotDistanceInChat());
        //MinecraftForge.EVENT_BUS.register(new UpdateEventHandler());
        //MinecraftForge.EVENT_BUS.register(new MixinEffectRenderer());
        MinecraftForge.EVENT_BUS.register(new ChatPingHandler());
        MinecraftForge.EVENT_BUS.register(new TitleTips());
        MinecraftForge.EVENT_BUS.register(new CommandSender());
        MinecraftForge.EVENT_BUS.register(new ArmorStandParticle());
        //MinecraftForge.EVENT_BUS.register(new SoundListener());
        //MinecraftForge.EVENT_BUS.register(new ArmorStandProtector());
        //MinecraftForge.EVENT_BUS.register(new KnifeProtector());
        //MinecraftForge.EVENT_BUS.register(new SpawnProtector());
        //MinecraftForge.EVENT_BUS.register(new CheckPlayerJoinWorldUtil());
        //MinecraftForge.EVENT_BUS.register(new BetterNametag());
        TickDelayUtil.init();
        if (FMLCommonHandler.instance().getSide().isClient()) {
            // 保证在客户端主线程、资源仓库准备好之后执行
            net.minecraft.client.Minecraft.getMinecraft().addScheduledTask(hook::applyOnce);
        }
    }
}