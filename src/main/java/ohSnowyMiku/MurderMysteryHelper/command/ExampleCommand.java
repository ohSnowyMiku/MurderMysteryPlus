package ohSnowyMiku.MurderMysteryHelper.command;

import ohSnowyMiku.MurderMysteryHelper.MurderMysteryHelper;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see MurderMysteryHelper
 */
@Command(value = MurderMysteryHelper.MODID, description = "Access the " + MurderMysteryHelper.NAME + " GUI.")
public class ExampleCommand {
    @Main
    private void handle() {
        MurderMysteryHelper.INSTANCE.config.openGui();
    }
}