package ohSnowyMiku.MurderMysteryHelper;

import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import ohSnowyMiku.MurderMysteryHelper.command.ExampleCommand;
import ohSnowyMiku.MurderMysteryHelper.config.MurderMysteryHelperConfig;
import ohSnowyMiku.MurderMysteryHelper.event.events.ArmorStandHandlerEvent;
import ohSnowyMiku.MurderMysteryHelper.event.events.BowHideHandler;
import ohSnowyMiku.MurderMysteryHelper.event.events.HiddenKaliSpamEvent;
import ohSnowyMiku.MurderMysteryHelper.event.events.HiddenMurderTitleTipEvent;
import ohSnowyMiku.MurderMysteryHelper.event.events.MapEventHud.SecretPassageHudEvent;
import ohSnowyMiku.MurderMysteryHelper.event.events.bowprotector.BowProtector;
import ohSnowyMiku.MurderMysteryHelper.event.events.nametaghider.ArmorStandNameTagHider;
import ohSnowyMiku.MurderMysteryHelper.event.events.tablistrole.ChatPlayerRoleListener;
import ohSnowyMiku.MurderMysteryHelper.event.events.tablistrole.TabListEventHandler;
import ohSnowyMiku.MurderMysteryHelper.event.events.weaponcooldownhud.ChatHandler;
import ohSnowyMiku.MurderMysteryHelper.event.events.weaponcooldownhud.ScoreBarListener;
import ohSnowyMiku.MurderMysteryHelper.event.events.weaponcooldownhud.TickHandler;
import ohSnowyMiku.MurderMysteryHelper.others.font.ChatFixHandler;
import ohSnowyMiku.MurderMysteryHelper.others.resourcepack.hook;
import ohSnowyMiku.MurderMysteryHelper.utils.*;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = MurderMysteryHelper.MODID, name = MurderMysteryHelper.NAME, version = MurderMysteryHelper.VERSION)
public class MurderMysteryHelper {

    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    @Mod.Instance(MODID)
    public static MurderMysteryHelper INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static MurderMysteryHelperConfig config;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new MurderMysteryHelperConfig();

        CommandManager.INSTANCE.registerCommand(new ExampleCommand());

        //EventManager.INSTANCE.register(new SecretPassageHudEvent());
        EventManager.INSTANCE.register(new SecretPassageHudEvent());
        //EventManager.INSTANCE.register(new SecretPassageTitleEvent());
        //EventManager.INSTANCE.register(new PlayerJoinGameUtil());
        EventManager.INSTANCE.register(new CheckPlayerJoinGameUtil());
        EventManager.INSTANCE.register(new HiddenMurderTitleTipEvent());
        EventManager.INSTANCE.register(new BowProtector());

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
        MinecraftForge.EVENT_BUS.register(new ChatFixHandler());
        //MinecraftForge.EVENT_BUS.register(new SpawnProtector());
        //MinecraftForge.EVENT_BUS.register(new CheckPlayerJoinWorldUtil());
        //MinecraftForge.EVENT_BUS.register(new BetterNametag());


        if (FMLCommonHandler.instance().getSide().isClient()) {
            // 保证在客户端主线程、资源仓库准备好之后执行
            net.minecraft.client.Minecraft.getMinecraft().addScheduledTask(hook::applyOnce);
        }
    }
}