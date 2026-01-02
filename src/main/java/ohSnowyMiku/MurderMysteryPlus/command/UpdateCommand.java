package ohSnowyMiku.MurderMysteryPlus.command;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.*;
import net.minecraft.client.Minecraft;
import ohSnowyMiku.MurderMysteryPlus.MurderMysteryPlus;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see MurderMysteryPlus
 */
@Command(value = "updatemod", description = "更新MurderMysteryPlus到最新版本")
public class UpdateCommand {

    @Main
    private void main() {
        UChat.chat("用法: /updatemod download <version> <url>");
    }

    @SubCommand(description = "下载并缓存最新版本")
    private void download(@Description("新版本号") String version,
                          @Description("下载链接") @Greedy String urlStr) {
        new Thread(() -> {
            try {
                URL url = new URL(urlStr);
                File mcDir = Minecraft.getMinecraft().mcDataDir;
                File cacheDir = new File(mcDir, "update-cache");
                cacheDir.mkdirs();

                String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
                File target = new File(cacheDir, fileName);
                File info = new File(cacheDir, "update-info.txt");

                if (target.exists() && info.exists()) {
                    UChat.chat("§a更新已下载！");
                    return;
                }

                try (InputStream in = url.openStream()) {
                    Files.copy(in, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                Files.write(info.toPath(), version.getBytes());
                UChat.chat("§a更新已下载，下次启动时会自动应用");
            } catch (Exception e) {
                e.printStackTrace();
                UChat.chat("§c下载失败: " + e.getMessage());
            }
        }).start();
    }
}