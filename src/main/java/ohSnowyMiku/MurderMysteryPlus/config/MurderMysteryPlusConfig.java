package ohSnowyMiku.MurderMysteryPlus.config;

import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import cc.polyfrost.oneconfig.config.data.PageLocation;
import cc.polyfrost.oneconfig.libs.universal.UKeyboard;
import ohSnowyMiku.MurderMysteryPlus.MurderMysteryPlus;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import ohSnowyMiku.MurderMysteryPlus.event.particle.ParticleManager;
import ohSnowyMiku.MurderMysteryPlus.hud.SecretPassageHud;
import ohSnowyMiku.MurderMysteryPlus.hud.TitleTipsHud;
import ohSnowyMiku.MurderMysteryPlus.hud.WeaponCooldownHud;
import ohSnowyMiku.MurderMysteryPlus.mixin.MixinEffectRenderer;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class MurderMysteryPlusConfig extends Config {

    @Switch(
            name = "Hide Murder Title Tip",
            size = OptionSize.DUAL
    )
    public static boolean hiddenMurderTitleTipSwitch = true;

    @Switch(
            name = "Show Roles in TabList",
            size = OptionSize.DUAL
    )
    public static boolean showRolesInTabListSwitch = true;

    @Switch(
            name = "Hide ArmorStand NameTag",
            size = OptionSize.DUAL
    )
    public static boolean hideArmorStandNameTagSwitch = false;

    @Switch(
            name = "Disable Particles",
            size = OptionSize.DUAL
    )
    public static boolean disableParticlesSwitch = false;

    @KeyBind(
            name = "Disable Particles Key"
    )
    public static OneKeyBind disableParticlesKeyBind = new OneKeyBind(UKeyboard.KEY_H);

    @Switch(
            name = "Show Bow ArmorStand Particle",
            size = OptionSize.DUAL
    )
    public static boolean showBowArmorStandParticleSwitch = false;




    @Switch(
            name = "Hide Kali Spam Message",
            size = OptionSize.SINGLE, // Optional
            category = "Chat",
            description = "hide spam message"
    )
    public static boolean hiddenKaliSpamMessage = false; // The default value for the boolean Switch.

    @Switch(
            name = "Chat Channel Swap",
            size = OptionSize.DUAL,
            category = "Chat"
    )
    public static boolean chatChannelSwapSwitch = false;


    @Switch(
            name = "Auto Send Bow Shot Distance Message",
            size = OptionSize.DUAL,
            category = "Chat",
            subcategory = "Distance Message Sender"
    )
    public static boolean autoSendBowShotDistanceMessageSwitch = false;


    @Dropdown(
            name = "Language",
            options = {"English", "Chinese"},
            category = "Chat",
            subcategory = "Distance Message Sender"
    )
    public static int autoSendBowShotDistanceMessageLanguageValue = 0;


    @Checkbox(
            name = "Send to Public",
            category = "Chat",
            subcategory = "Distance Message Sender"
    )
    public static boolean sendToPublic = false;


    @Checkbox(
            name = "Send to Party",
            category = "Chat",
            subcategory = "Distance Message Sender"
    )
    public static boolean sendToParty = false;


    @Checkbox(
            name = "Send to Guild",
            category = "Chat",
            subcategory = "Distance Message Sender"
    )
    public static boolean sendToGuild = false;


    @Switch(
            name = "Auto Send My Role In Party Chat",
            category = "Chat",
            subcategory = "Role Sender",
            size = OptionSize.DUAL
    )
    public static boolean autoSendMyRoleInPartyChatSwitch = false;


    @Dropdown(
            name = "Language",
            options = {"English", "Chinese"},
            category = "Chat",
            subcategory = "Role Sender"
    )
    public static int autoSendMyRoleInPartyChatLanguageValue = 0;


    @Switch(
            name = "Party Command Button",
            category = "Chat"
    )
    public static boolean partyCommandButtonSwitch = true;



    @Switch(
            name = "Disable Gestures ArmorStand",
            size = OptionSize.DUAL,
            category = "Item"
    )
    public static boolean disableGesturesArmorStandSwitch = false;

    @Switch(
            name = "Bow Protector",
            size = OptionSize.DUAL,
            category = "Item"
    )
    public static boolean bowProtectorSwitch = false;

    @Switch(
            name = "Knife Protector",
            size = OptionSize.DUAL,
            category = "Item"
    )
    public static boolean knifeProtectorSwitch = false;

    @Switch(
            name = "Hide Bow Pulling Animation",
            size = OptionSize.DUAL,
            category = "Item"
    )
    public static boolean hiddenPullingBowAnimationSwitch = false;

    @Switch(
            name = "Gold ingot Overlay",
            subcategory = "Item Overlay",
            category = "Item"
    )
    public static boolean goldIngotOverlaySwitch = false;

    @Checkbox(
            name = "Only Work in MurderMystery",
            subcategory = "Item Overlay",
            category = "Item"
    )
    public static boolean onlyWorkInMurderMysterySwitch = false;

    @Slider(
            name = "Gold ingot Overlay Multiplier",
            min = 1f, max = 11f, // Minimum and maximum values for the slider.
            step = 1, // The amount of steps that the slider should have.
            subcategory = "Item Overlay",
            category = "Item"
    )
    public static float goldIngotOverlayMultiplier = 1f; // The default value for the float Slider.




    @Switch(
            name = "Show Secret Passage Open Status Hud",
            size = OptionSize.DUAL,
            subcategory = "Secret Passage Status HUD",
            category = "HUD"
    )
    public static boolean secretPassageOpenStatusSwitch = false;

    @Page(
            name = "Secret Passage Status HUD Settings",
            category = "HUD",
            subcategory = "Secret Passage Status HUD",
            location = PageLocation.BOTTOM,
            description = "Press me to open HUD settings"
    )
    public static SecretPassageOpenStatusHudPage secretPassageOpenStatusHudPage = new SecretPassageOpenStatusHudPage();


    @Switch(
            name = "Show Weapon Cooldown Hud",
            size = OptionSize.DUAL,
            subcategory = "Weapon Cooldown Hud",
            category = "HUD"
    )
    public static boolean showWeaponCooldownHudSwitch = false;

    @Page(
            name = "Weapon Cooldown Hud Settings",
            category = "HUD",
            subcategory = "Weapon Cooldown Hud",
            location = PageLocation.BOTTOM,
            description = "Press me to open HUD settings"
    )
    public static WeaponCooldownHudPage weaponCooldownHud = new WeaponCooldownHudPage();

    @Switch(
            name = "Show Title Tips Hud",
            size = OptionSize.DUAL,
            subcategory = "Title Tips Hud",
            category = "HUD"
    )
    public static boolean showTitleTipsHudSwitch = false;

    //title
    @Dropdown(
            name = "Title Tips Language",
            category = "HUD",
            size = OptionSize.DUAL,
            subcategory = "Title Tips Hud",
            options = {"English", "Chinese"}
    )
    public static int titleTipsLanguageValue = 0;

    @Switch(
            name = "Bow Picked Title",
            category = "HUD",
            subcategory = "Title Tips Hud"
    )
    public static boolean bowPickedTitleSwitch = false;

    @Switch(
            name = "Last Survivor Title",
            category = "HUD",
            subcategory = "Title Tips Hud"
    )
    public static boolean lastSurvivorTitleSwitch = false;

    @Switch(
            name = "Map Enabled Title",
            category = "HUD",
            subcategory = "Title Tips Hud"
    )
    public static boolean mapEnabledTitleSwitch = false;


    @Page(
            name = "Title Tips Hud Settings",
            category = "HUD",
            subcategory = "Title Tips Hud",
            location = PageLocation.BOTTOM,
            description = "Press me to open HUD settings"
    )
    public static TitleTipsHudPage titleTipsHudPage = new TitleTipsHudPage();



    @Switch(
            name = "Chat Ping Sound",
            category = "Sound",
            size = OptionSize.DUAL
    )
    public static boolean chatPingSoundSwitch = false;

    @Dropdown(
            name = "Bow Dropped Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb","Level Up","Meow","Harp","Anvil","Villager"}
    )
    public static int bowDroppedSoundValue = 0;

    @Dropdown(
            name = "Bow Picked Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb","Level Up","Meow","Harp","Anvil","Villager"}
    )
    public static int bowPickedSoundValue = 0;

    @Dropdown(
            name = "Last Survivor Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb","Level Up","Meow","Harp","Anvil","Villager"}
    )
    public static int lastSurvivorSoundValue = 0;

    @Dropdown(
            name = "Map Enabled Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb","Level Up","Meow","Harp","Anvil","Villager"}
    )
    public static int mapEnabledSoundValue = 0;



    public MurderMysteryPlusConfig() {
        super(new Mod(MurderMysteryPlus.NAME, ModType.HYPIXEL, "/assets/murdermysteryplus/textures/logo.png"), MurderMysteryPlus.MODID + ".json");
        initialize();

        registerKeyBind(disableParticlesKeyBind, ParticleManager::toggleParticles);

        //禁用选项addDependency需要放在initialize();下面
        addDependency("onlyWorkInMurderMysterySwitch", "goldIngotOverlaySwitch");
        addDependency("goldIngotOverlayMultiplier", "goldIngotOverlaySwitch");
        addDependency("autoSendBowShotDistanceMessageLanguageValue","autoSendBowShotDistanceMessageSwitch");
        addDependency("sendToPublic","autoSendBowShotDistanceMessageSwitch");
        addDependency("sendToParty","autoSendBowShotDistanceMessageSwitch");
        addDependency("sendToGuild","autoSendBowShotDistanceMessageSwitch");
        addDependency("disableParticlesKeyBind","disableParticlesSwitch");
        addDependency("titleTipsLanguageValue","showTitleTipsHudSwitch");
        addDependency("bowPickedTitleSwitch","showTitleTipsHudSwitch");
        addDependency("lastSurvivorTitleSwitch","showTitleTipsHudSwitch");
        addDependency("mapEnabledTitleSwitch","showTitleTipsHudSwitch");
        addDependency("bowDroppedSoundValue","chatPingSoundSwitch");
        addDependency("bowPickedSoundValue","chatPingSoundSwitch");
        addDependency("lastSurvivorSoundValue","chatPingSoundSwitch");
        addDependency("mapEnabledSoundValue","chatPingSoundSwitch");
        addDependency("autoSendMyRoleInPartyChatLanguageValue","autoSendMyRoleInPartyChatSwitch");
    }
    public static class MyPage{
        @Switch(
                name = "test123"
        )
        public static boolean test123Switch = false;

    }

    public static class SecretPassageOpenStatusHudPage {
        @HUD(
                name = "Secret Passage Status HUD",
                category = "HUD"
        )
        public SecretPassageHud secretPassageHud = new SecretPassageHud();
    }

    public static class WeaponCooldownHudPage {
        @HUD(
                name = "Weapon Cooldown Hud",
                category = "HUD"
        )

        public WeaponCooldownHud weaponCooldownHud = new WeaponCooldownHud();

        @Color(
                name = "Text Color",
                description = "Change the color of the text",
                category = "HUD"
        )
        public static OneColor color = new OneColor(255, 255, 255, 255);
    }

    public static class TitleTipsHudPage {
        @HUD(
                name = "Title Tips HUD",
                category = "HUD"
        )
        public TitleTipsHud titleTipsHud = new TitleTipsHud();

        @Color(
                name = "Title Color",
                description = "Change the color of the title",
                category = "HUD"
        )
        public static OneColor titleColor = new OneColor(255, 255, 255, 255);
    }
}

