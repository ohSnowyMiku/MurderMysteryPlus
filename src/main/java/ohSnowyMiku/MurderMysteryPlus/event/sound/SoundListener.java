package ohSnowyMiku.MurderMysteryPlus.event.sound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SoundListener {

    /*@SubscribeEvent
    public void onPlaySound(PlaySoundEvent event) {
        ISound sound = event.sound;

        String soundName = sound.getSoundLocation().toString();
        float serverVolume = sound.getVolume();

        // 防止多人游戏界面还没进入世界时 thePlayer 为 null
        if (Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                    new ChatComponentText("音效: " + soundName + " | 音量: " + serverVolume)
            );
        }
    }*/
        /**
         * 根据逻辑音效名称解析对应的具体文件名列表
         */
        private List<String> resolveSoundFiles(ResourceLocation soundLoc) {
            List<String> files = new ArrayList<>();
            try {
                // sounds.json 在资源包里，路径是 assets/<domain>/sounds.json
                String domain = soundLoc.getResourceDomain();
                InputStream stream = Minecraft.getMinecraft().getResourceManager()
                        .getResource(new ResourceLocation(domain, "sounds.json"))
                        .getInputStream();

                JsonObject root = new JsonParser().parse(new InputStreamReader(stream)).getAsJsonObject();

                if (root.has(soundLoc.getResourcePath())) {
                    JsonObject soundDef = root.getAsJsonObject(soundLoc.getResourcePath());
                    for (JsonElement elem : soundDef.getAsJsonArray("sounds")) {
                        if (elem.isJsonPrimitive()) {
                            files.add(elem.getAsString());
                        } else {
                            JsonObject obj = elem.getAsJsonObject();
                            files.add(obj.get("name").getAsString());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return files;
        }

        @SubscribeEvent
        public void onPlaySound(PlaySoundEvent event) {
            ISound sound = event.sound;
            ResourceLocation loc = sound.getSoundLocation();

            // 获取具体文件名列表
            List<String> files = resolveSoundFiles(loc);

            if (Minecraft.getMinecraft().thePlayer != null) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(
                        new ChatComponentText("音效: " + loc +
                                " | 文件候选: " + files)
                );
            }
        }
}
