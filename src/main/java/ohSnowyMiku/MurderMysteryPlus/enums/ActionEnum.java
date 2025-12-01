package ohSnowyMiku.MurderMysteryPlus.enums;

public enum ActionEnum {
    THROWING("§6投掷",
            "§6THROWING",
            "0.5s§r"),

    STOPCHARGING(
            "§c已停止蓄力",
            "§cStopped charging",
            ""
    ),

    CHARGING(
            "§6蓄力",
            "§6CHARGING",
            "5.0s§r"
    ),

    ALPHACHARGING(
            "§6蓄力",
            "§6CHARGING",
            "14.9s§r"
    ),

    SURVIVORCHARGING(
            "§6蓄力",
            "§6CHARGING",
            "1.4s§r"
    ),

    ;
    //public final String chinese;
    public final String chineseWithColorCode;
    //public final String english;
    public final String englishWithColorCode;
    public final String coolDownMessage;

    ActionEnum(String chineseWithColorCode, String englishWithColorCode, String coolDownMessage) {
        //this.chinese = chinese;
        this.chineseWithColorCode = chineseWithColorCode;
        //this.english = english;
        this.englishWithColorCode = englishWithColorCode;
        this.coolDownMessage = coolDownMessage;


    }

}
