package ohSnowyMiku.MurderMysteryPlus.config;

import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.data.PageLocation;
import ohSnowyMiku.MurderMysteryPlus.MurderMysteryPlus;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import ohSnowyMiku.MurderMysteryPlus.hud.SecretPassageHud;
import ohSnowyMiku.MurderMysteryPlus.hud.WeaponCooldownHud;

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
            name = "Hide Kali Spam Message",
            size = OptionSize.SINGLE, // Optional
            category = "Chat",
            description = "hide spam message"
    )
    public static boolean hiddenKaliSpamMessage = false; // The default value for the boolean Switch.


    @Switch(
            name = "Disable Gestures ArmorStand",
            size = OptionSize.DUAL,
            category = "Item"
    )
    public static boolean disableGesturesArmorStandSwitch = false;


    @Switch(
            name = "Show Roles in TabList",
            size = OptionSize.DUAL
    )
    public static boolean showRolesInTabListSwitch = true;


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
            name = "Hide ArmorStand NameTag",
            size = OptionSize.DUAL
    )
    public static boolean hideArmorStandNameTagSwitch = false;


    @Switch(
            name = "Chat Channel Swap",
            size = OptionSize.DUAL,
            category = "Chat"
    )
    public static boolean chatChannelSwapSwitch = false;


    @Switch(
            name = "Auto Send Bow Shot Distance Message",
            size = OptionSize.DUAL,
            category = "Chat"
    )
    public static boolean autoSendBowShotDistanceMessageSwitch = false;


    @Dropdown(
            name = "Language",
            options = {"English", "Chinese"},
            category = "Chat"
    )
    public static int autoSendBowShotDistanceMessageLanguageValue = 0;


    @Checkbox(
            name = "Send to Public",
            category = "Chat"
    )
    public static boolean sendToPublic = false;


    @Checkbox(
            name = "Send to Party",
            category = "Chat"
    )
    public static boolean sendToParty = false;


    @Checkbox(
            name = "Send to Guild",
            category = "Chat"
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




    public MurderMysteryPlusConfig() {
        super(new Mod(MurderMysteryPlus.NAME, ModType.HYPIXEL, "/assets/murdermysteryplus/textures/logo.png"), MurderMysteryPlus.MODID + ".json");
        initialize();

        //禁用选项addDependency需要放在initialize();下面
        addDependency("onlyWorkInMurderMysterySwitch", "goldIngotOverlaySwitch");
        addDependency("goldIngotOverlayMultiplier", "goldIngotOverlaySwitch");
        addDependency("autoSendBowShotDistanceMessageLanguageValue","autoSendBowShotDistanceMessageSwitch");
        addDependency("sendToPublic","autoSendBowShotDistanceMessageSwitch");
        addDependency("sendToParty","autoSendBowShotDistanceMessageSwitch");
        addDependency("sendToGuild","autoSendBowShotDistanceMessageSwitch");
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
}

