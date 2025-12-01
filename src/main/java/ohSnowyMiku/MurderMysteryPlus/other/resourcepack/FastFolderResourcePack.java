package ohSnowyMiku.MurderMysteryPlus.other.resourcepack;

import net.minecraft.client.resources.AbstractResourcePack;

import java.io.*;
import java.util.*;

public class FastFolderResourcePack extends AbstractResourcePack {
    private final Map<String, File> cache = new HashMap<>();

    public FastFolderResourcePack(File folder) {
        super(folder);
        buildCache(folder);
    }

    private void buildCache(File base) {
        int baseLen = base.getAbsolutePath().length() + 1;
        Deque<File> q = new ArrayDeque<>();
        q.add(base);
        while (!q.isEmpty()) {
            File cur = q.poll();
            File[] arr = cur.listFiles();
            if (arr == null) continue;
            for (File f : arr) {
                if (f.isDirectory()) q.add(f);
                else {
                    String path = f.getAbsolutePath().substring(baseLen).replace(File.separatorChar, '/');
                    cache.put(path, f);
                }
            }
        }
        System.out.println("[FastFolderResourcePack] cached=" + cache.size());
    }

    @Override
    protected InputStream getInputStreamByName(String name) throws IOException {
        File f = cache.get(name);
        if (f != null && f.isFile()) return new FileInputStream(f);
        throw new FileNotFoundException(name);
    }

    @Override
    protected boolean hasResourceName(String name) {
        return cache.containsKey(name);
    }

    @Override
    public Set<String> getResourceDomains() {
        Set<String> dom = new HashSet<>();
        for (String p : cache.keySet()) {
            int idx = p.indexOf('/');
            if (idx > 0) dom.add(p.substring(0, idx));
        }
        return dom;
    }
}
