package ohSnowyMiku.MurderMysteryPlus.command;

import ohSnowyMiku.MurderMysteryPlus.MurderMysteryPlus;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see MurderMysteryPlus
 */
@Command(value = MurderMysteryPlus.MODID, description = "Access the " + MurderMysteryPlus.NAME + " GUI.")
public class ExampleCommand {
    @Main
    private void handle() {
        MurderMysteryPlus.INSTANCE.config.openGui();
    }
}