package ohSnowyMiku.MurderMysteryHelper.enums;

public enum RoleEnum {
    MURDERER("杀手",
            "§c杀手",
            "Murderer",
            "§cMurderer"),

    ALPHA("母体",
            "§c母体",
            "Alpha",
            "§cAlpha"),

    ASSASSIN("刺客",
            "§c刺客",
            "Assassin",
            "§cAssassin"),

    INFECTED("感染者",
            "§c感染者",
            "Infected",
            "§cInfected"),

    DETECTIVE("侦探",
            "§b侦探",
            "Detectiv",
            "§bDetectiv"),

    SURVIVOR("幸存者",
            "§a幸存者",
            "Survivor",
            "§aSurvivor"),

    ;
    public final String chinese;
    public final String chineseWithColorCode;
    public final String english;
    public final String englishWithColorCode;

    RoleEnum(String chinese, String chineseWithColorCode, String english, String englishWithColorCode) {
        this.chinese = chinese;
        this.chineseWithColorCode = chineseWithColorCode;
        this.english = english;
        this.englishWithColorCode = englishWithColorCode;


    }

}
