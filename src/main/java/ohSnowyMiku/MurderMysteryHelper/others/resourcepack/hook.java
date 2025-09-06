package ohSnowyMiku.MurderMysteryHelper.others.resourcepack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public final class hook {
    private static boolean applied = false;

    public static void applyOnce() {
        if (applied) return;
        applied = true;

        try {
            ResourcePackRepository repo = Minecraft.getMinecraft().getResourcePackRepository();

            // 1) 取出所有条目列表（开发环境/运行环境字段名不同，逐个尝试）
            @SuppressWarnings("unchecked")
            List<ResourcePackRepository.Entry> all = (List<ResourcePackRepository.Entry>) getFieldValue(
                    repo, ResourcePackRepository.class,
                    // 常见的 MCP 名
                    "repositoryEntriesAll",
                    // SRG/混淆名（1.8.9 常见）
                    "field_110620_b",
                    // 兜底：有的映射叫 allEntries
                    "allEntries"
            );
            if (all == null) {
                System.out.println("[FastFolderHook] cannot find repositoryEntriesAll");
                return;
            }

            for (ResourcePackRepository.Entry e : all) {
                // 2) 触发懒加载
                IResourcePack pack = e.getResourcePack();
                if (pack == null) continue;

                // 3) 只替换文件夹包
                if (pack instanceof FolderResourcePack && !(pack instanceof FastFolderResourcePack)) {
                    // 从 AbstractResourcePack 里取出 resourcePackFile
                    File folder = (File) getFieldValue(
                            pack, AbstractResourcePack.class,
                            "resourcePackFile",        // MCP
                            "field_110597_b"           // SRG
                    );
                    if (folder == null || !folder.isDirectory()) continue;

                    IResourcePack fast = new FastFolderResourcePack(folder);

                    // 4) 把 Entry 内缓存的 IResourcePack 指针替换掉
                    boolean ok = setFieldValue(
                            e, e.getClass(),
                            fast,
                            // 常见字段名（不同映射下）
                            "reResourcePack", "resourcePack", "resourcePackInstance",
                            "field_110546_b" // SRG 常见
                    );
                    System.out.println("[FastFolderHook] replace " + folder.getName() + " -> " + ok);
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // 兼容多字段名的反射工具
    private static Object getFieldValue(Object instance, Class<?> owner, String... names) {
        for (String n : names) {
            try {
                Field f = owner.getDeclaredField(n);
                f.setAccessible(true);
                return f.get(instance);
            } catch (NoSuchFieldException ignored) {
            } catch (Throwable t) {
                t.printStackTrace();
                break;
            }
        }
        return null;
    }

    private static boolean setFieldValue(Object instance, Class<?> owner, Object value, String... names) {
        for (String n : names) {
            try {
                Field f = owner.getDeclaredField(n);
                f.setAccessible(true);
                f.set(instance, value);
                return true;
            } catch (NoSuchFieldException ignored) {
            } catch (Throwable t) {
                t.printStackTrace();
                break;
            }
        }
        return false;
    }
}
