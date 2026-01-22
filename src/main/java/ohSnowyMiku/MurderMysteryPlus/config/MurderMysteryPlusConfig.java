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

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class MurderMysteryPlusConfig extends Config {
//隐藏杀手在1分钟内未获得击杀的标题提示
    @Switch(
            name = "Hide No Kill Alert Title",
            size = OptionSize.DUAL,
            description = "Hide the title alert when murderer has no kills within 1 minute"
    )
    public static boolean hideNoKillAlertTitleSwitch = true;
//在tab列表显示已知玩家的身份
    @Switch(
            name = "Show Player Roles in Tab",
            size = OptionSize.DUAL,
            description = "Display player roles in the tab list(from chat)"
    )
    public static boolean showPlayerRolesInTabSwitch = true;
//隐藏地图中大部分隐形盔甲架的nametag
    @Switch(
            name = "Hide Useless Armor Stand Tags",
            size = OptionSize.DUAL,
            description = "Hide useless name tags of invisible armor stands on the map"
    )
    public static boolean hideUselessArmorStandTagsSwitch = false;
//隐藏大部分粒子
    @Switch(
            name = "Hide Particles",
            size = OptionSize.DUAL,
            description = "Hide most particles in the game"
    )
    public static boolean hideParticlesSwitch = false;
//切换显示粒子
    @KeyBind(
            name = "Toggle Particles",
            description = "Keybind to toggle particle visibility"
    )
    public static OneKeyBind toggleParticles = new OneKeyBind(UKeyboard.KEY_H);
//在掉落的弓的位置生成一些粒子
    @Switch(
            name = "Show Bow Drop Particles",
            size = OptionSize.DUAL,
            description = "Show particles at the location where the bow drops"
    )
    public static boolean showBowDropParticlesSwitch = false;

//隐藏kali的刷屏消息
    @Switch(
            name = "Hide Kali Spam Message",
            size = OptionSize.SINGLE, // Optional
            category = "Chat",
            description = "Hide Kali spam messages"
    )
    public static boolean hideKaliSpamMessageSwitch = true; // The default value for the boolean Switch.
//进入离开组队自动切换聊天频道
    @Switch(
            name = "Auto Swap Chat Channel",
            size = OptionSize.DUAL,
            category = "Chat",
            description = "Automatically swap chat channel when joining or leaving a party"
    )
    public static boolean autoSwapChatChannelSwitch = false;

//自动发送弓远距离击杀信息
    @Switch(
            name = "Auto Send Bow Kill Distance",
            size = OptionSize.DUAL,
            category = "Chat",
            subcategory = "Distance Messages",
            description = "Automatically send bow kill distance messages"
    )
    public static boolean AutoSendBowKillDistanceSwitch = false;


    @Dropdown(
            name = "Language",
            options = {"English", "Chinese"},
            category = "Chat",
            subcategory = "Distance Messages",
            description = "Select language for distance messages"
    )
    public static int bowDistanceLanguageValue = 0;


    @Checkbox(
            name = "Send to Public",
            category = "Chat",
            subcategory = "Distance Messages",
            description = "Send distance messages to public chat"
    )
    public static boolean sendToPublic = false;


    @Checkbox(
            name = "Send to Party",
            category = "Chat",
            subcategory = "Distance Messages",
            description = "Send distance messages to party chat"
    )
    public static boolean sendToParty = false;


    @Checkbox(
            name = "Send to Guild",
            category = "Chat",
            subcategory = "Distance Messages",
            description = "Send distance messages to guild chat"
    )
    public static boolean sendToGuild = false;

//自动在组队发送自己的身份信息
    @Switch(
            name = "Auto Send Role to Party",
            category = "Chat",
            subcategory = "Role Messages",
            size = OptionSize.DUAL,
            description = "Automatically send your role information to party chat"
    )
    public static boolean autoSendRoleToPartySwitch = false;


    @Dropdown(
            name = "Language",
            options = {"English", "Chinese"},
            category = "Chat",
            subcategory = "Role Messages",
            description = "Select language for role messages"
    )
    public static int roleMessageLanguageValue = 0;

//组队指令快捷按钮
    @Switch(
            name = "Party Quick Command Buttons",
            category = "Chat",
            description = "Show quick command buttons for party management"
    )
    public static boolean showPartyButtonsSwitch = false;

//禁用动作盔甲架(可在shift状态下右键使用)
    @Switch(
            name = "Disable Gestures ArmorStand",
            size = OptionSize.DUAL,
            category = "Item",
            description = "Disable gesture armor stands (usable with right-click while holding shift)"
    )
    public static boolean disableGesturesArmorStandSwitch = false;
//保护侦探弓不会被献祭给kali(可在shift状态下献祭给kali)
    @Switch(
            name = "Protect Detective Bow",
            size = OptionSize.DUAL,
            category = "Item",
            description = "Prevent the detective bow from being sacrificed to Kali (can still sacrifice while holding shift)"
    )
    public static boolean protectDetectiveBowSwitch = false;
//使一些匕首(例如船)右键时不会因为原版特性消失
    @Switch(
            name = "Protect Murderer Knife",
            size = OptionSize.DUAL,
            category = "Item",
            description = "Prevent knives (e.g., boats) from disappearing due to vanilla mechanics when right-clicked"
    )
    public static boolean protectMurdererKnifeSwitch = false;
//隐藏拉弓的动画
    @Switch(
            name = "Hide Bow Pull Animation",
            size = OptionSize.DUAL,
            category = "Item",
            description = "Hide the bow pulling animation"
    )
    public static boolean hideBowPullAnimationSwitch = false;
//自定义显示掉落地面的金锭大小
    @Switch(
            name = "Gold ingot Overlay",
            subcategory = "Item Overlay",
            category = "Item",
            description = "Customize the display size of gold ingots on the ground"
    )
    public static boolean goldIngotOverlaySwitch = false;
//是否只在密室杀手里面生效
    @Checkbox(
            name = "Only Work in MurderMystery",
            subcategory = "Item Overlay",
            category = "Item",
            description = "Only enable this feature in Murder Mystery"
    )
    public static boolean onlyWorkInMurderMysterySwitch = false;
//倍率
    @Slider(
            name = "Gold ingot Overlay Multiplier",
            min = 1f, max = 11f, // Minimum and maximum values for the slider.
            step = 1, // The amount of steps that the slider should have.
            subcategory = "Item Overlay",
            category = "Item",
            description = "Adjust the size multiplier for gold ingot overlay"
    )
    public static float goldIngotOverlayMultiplier = 1f; // The default value for the float Slider.


//在部分地图中显示秘密通道是否开启
    @Switch(
            name = "Show Secret Passage Status",
            size = OptionSize.DUAL,
            subcategory = "Secret Passage Status HUD",
            category = "HUD",
            description = "Display whether secret passages are open in certain maps"
    )
    public static boolean showSecretPassageStatusSwitch = false;

    @Page(
            name = "Secret Passage Status HUD Settings",
            category = "HUD",
            subcategory = "Secret Passage Status HUD",
            location = PageLocation.BOTTOM,
            description = "Press to open HUD settings"
    )
    public static SecretPassageStatusHudPage secretPassageStatusHudPage = new SecretPassageStatusHudPage();

//飞刀和弓重新蓄力cd的hud
    @Switch(
            name = "Show Weapon Cooldown",
            size = OptionSize.DUAL,
            subcategory = "Weapon Cooldown Hud",
            category = "HUD",
            description = "Show cooldown HUD for throwing knives and bow"
    )
    public static boolean showWeaponCooldownSwitch = false;

    @Page(
            name = "Weapon Cooldown Hud Settings",
            category = "HUD",
            subcategory = "Weapon Cooldown Hud",
            location = PageLocation.BOTTOM,
            description = "Press to open HUD settings"
    )
    public static WeaponCooldownHudPage weaponCooldownHud = new WeaponCooldownHudPage();
//为密室杀手添加更多的title
    @Switch(
            name = "Show Title Tips",
            size = OptionSize.DUAL,
            subcategory = "Title Tips Hud",
            category = "HUD",
            description = "Display additional title tips for Murder Mystery"
    )
    public static boolean showTitleTipsSwitch = false;

    //title
    @Dropdown(
            name = "Title Tips Language",
            category = "HUD",
            size = OptionSize.DUAL,
            subcategory = "Title Tips Hud",
            options = {"English", "Chinese"},
            description = "Select language for title tips"
    )
    public static int titleLanguageValue = 0;
//弓被捡起
    @Switch(
            name = "Bow Picked Title",
            category = "HUD",
            subcategory = "Title Tips Hud",
            description = "Show title when bow is picked up"
    )
    public static boolean showBowPickedTitleSwitch = false;
//感染模式最后幸存者
    @Switch(
            name = "Last Survivor Title",
            category = "HUD",
            subcategory = "Title Tips Hud",
            description = "Show title when you are the last survivor in infection mode"
    )
    public static boolean showLastSurvivorTitleSwitch = false;
//地图启用
    @Switch(
            name = "Map Enabled Title",
            category = "HUD",
            subcategory = "Title Tips Hud",
            description = "Show title when map is enabled"
    )
    public static boolean showMapEnabledTitleSwitch = false;


    @Page(
            name = "Title Tips Settings",
            category = "HUD",
            subcategory = "Title Tips Hud",
            location = PageLocation.BOTTOM,
            description = "Press to open HUD settings"
    )
    public static TitleTipsHudPage titleTipsHudPage = new TitleTipsHudPage();

//为密室杀手添加更多的音效提示
    @Switch(
            name = "Enable Sounds",
            category = "Sound",
            size = OptionSize.DUAL,
            description = "Enable additional sound effects for Murder Mystery"
    )
    public static boolean enableSoundsSwitch = false;
//当弓掉落
    @Dropdown(
            name = "Bow Dropped Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb", "Level Up", "Meow", "Harp", "Anvil", "Villager"},
            description = "Select sound effect when bow is dropped"
    )
    public static int bowDroppedSoundValue = 0;
//当弓被捡起
    @Dropdown(
            name = "Bow Picked Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb", "Level Up", "Meow", "Harp", "Anvil", "Villager"},
            description = "Select sound effect when bow is picked up"
    )
    public static int bowPickedSoundValue = 0;
//当最后幸存者出现
    @Dropdown(
            name = "Last Survivor Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb", "Level Up", "Meow", "Harp", "Anvil", "Villager"},
            description = "Select sound effect when last survivor appears"
    )
    public static int lastSurvivorSoundValue = 0;
//当地图启用
    @Dropdown(
            name = "Map Enabled Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb", "Level Up", "Meow", "Harp", "Anvil", "Villager"},
            description = "Select sound effect when map is enabled"
    )
    public static int mapEnabledSoundValue = 0;
//当母体暴露
    @Dropdown(
            name = "Alpha Appears Sound",
            category = "Sound",
            options = {"None", "Pling", "Orb", "Level Up", "Meow", "Harp", "Anvil", "Villager"},
            description = "Select sound effect when alpha appears"
    )
    public static int alphaAppearsSoundValue = 0;


    public MurderMysteryPlusConfig() {
        super(new Mod(MurderMysteryPlus.NAME, ModType.HYPIXEL, "/assets/murdermysteryplus/textures/logo.png"), MurderMysteryPlus.MODID + ".json");
        initialize();

        registerKeyBind(toggleParticles, ParticleManager::toggleParticles);

        //禁用选项addDependency需要放在initialize();下面
        addDependency("onlyWorkInMurderMysterySwitch", "goldIngotOverlaySwitch");
        addDependency("goldIngotOverlayMultiplier", "goldIngotOverlaySwitch");
        addDependency("sendToPublic", "AutoSendBowKillDistanceSwitch");
        addDependency("sendToParty", "AutoSendBowKillDistanceSwitch");
        addDependency("sendToGuild", "AutoSendBowKillDistanceSwitch");
        addDependency("bowDistanceLanguageValue", "AutoSendBowKillDistanceSwitch");
        addDependency("toggleParticles", "hideParticlesSwitch");
        addDependency("titleLanguageValue", "showTitleTipsSwitch");
        addDependency("showBowPickedTitleSwitch", "showTitleTipsSwitch");
        addDependency("showLastSurvivorTitleSwitch", "showTitleTipsSwitch");
        addDependency("showMapEnabledTitleSwitch", "showTitleTipsSwitch");
        addDependency("bowDroppedSoundValue", "enableSoundsSwitch");
        addDependency("bowPickedSoundValue", "enableSoundsSwitch");
        addDependency("lastSurvivorSoundValue", "enableSoundsSwitch");
        addDependency("mapEnabledSoundValue", "enableSoundsSwitch");
        addDependency("alphaAppearsSoundValue", "enableSoundsSwitch");
        addDependency("roleMessageLanguageValue", "autoSendRoleToPartySwitch");
    }

    public static class MyPage {
        @Switch(
                name = "test123"
        )
        public static boolean test123Switch = false;

    }

    public static class SecretPassageStatusHudPage {
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

