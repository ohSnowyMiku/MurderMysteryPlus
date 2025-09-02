package ohSnowyMiku.MurderMysteryHelper.config;

import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneColor;
import ohSnowyMiku.MurderMysteryHelper.MurderMysteryHelper;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import ohSnowyMiku.MurderMysteryHelper.hud.SecretPassageHud;
import ohSnowyMiku.MurderMysteryHelper.hud.WeaponCooldownHud;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class MurderMysteryHelperConfig extends Config {

    @Switch(
            name = "Hide Kali Spam Message",
            size = OptionSize.SINGLE // Optional
    )
    public static boolean hiddenKaliSpamMessage = false; // The default value for the boolean Switch.

    @Switch(
            name = "Hide Murder Title Tip",
            size = OptionSize.DUAL
    )
    public static boolean hiddenMurderTitleTipSwitch = true;

    @Switch(
            name = "Disable Gestures ArmorStand",
            size = OptionSize.DUAL
    )
    public static boolean disableGesturesArmorStandSwitch = false;


    @Switch(
            name = "Show Roles in TabList from Chat",
            size = OptionSize.DUAL
    )
    public static boolean showRolesInTabListFromChatSwitch = true;


    @Switch(
            name = "Bow Protector",
            size = OptionSize.DUAL
    )
    public static boolean bowProtectorSwitch = false;


    @Switch(
            name = "Hide ArmorStand NameTag",
            size = OptionSize.DUAL
    )
    public static boolean hideArmorStandNameTagSwitch = false;




    @Switch(
            name = "Hide Bow Pulling Animation",
            size = OptionSize.DUAL,
            subcategory = "Item Overlay"
    )
    public static boolean hiddenPullingBowAnimationSwitch = false;


    @Switch(
            name = "Gold ingot Overlay",
            subcategory = "Item Overlay"
    )
    public static boolean goldIngotOverlaySwitch = false;


    @Checkbox(
            name = "Only Work in MurderMystery",
            subcategory = "Item Overlay"
    )
    public static boolean onlyWorkInMurderMysterySwitch = false;


    @Slider(
            name = "Gold ingot Overlay Multiplier",
            min = 1f, max = 11f, // Minimum and maximum values for the slider.
            step = 1, // The amount of steps that the slider should have.
            subcategory = "Item Overlay"
    )
    public static float goldIngotOverlayMultiplier = 1f; // The default value for the float Slider.




    @Switch(
            name = "Show Secret Passage Open Status Hud",
            size = OptionSize.DUAL,
            subcategory = "Secret Passage Status HUD",
            category = "HUD"
    )
    public static boolean secretPassageOpenStatusSwitch = false;


    @Switch(
            name = "Show Weapon Cooldown Hud",
            size = OptionSize.DUAL,
            subcategory = "Weapon Cooldown Hud",
            category = "HUD"
    )
    public static boolean showWeaponCooldownHudSwitch = false;


    @HUD(
            name = "Secret Passage Status HUD",
            category = "HUD"
    )
    public SecretPassageHud secretPassageHud = new SecretPassageHud();


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




    public MurderMysteryHelperConfig() {
        super(new Mod(MurderMysteryHelper.NAME, ModType.HYPIXEL, "/assets/murdermysteryhelper/textures/logo.png"), MurderMysteryHelper.MODID + ".json");
        initialize();

        //禁用选项addDependency需要放在initialize();下面
        addDependency("onlyWorkInMurderMysterySwitch", "goldIngotOverlaySwitch");
        addDependency("goldIngotOverlayMultiplier", "goldIngotOverlaySwitch");
    }
}

