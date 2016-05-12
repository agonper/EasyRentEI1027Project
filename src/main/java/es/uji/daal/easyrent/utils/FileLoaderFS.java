package es.uji.daal.easyrent.utils;

import java.io.File;

/**
 * Created by Alberto on 12/05/2016.
 */
public class FileLoaderFS implements FileLoader {
    @Override
    public File load(String folder, String filename) {
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + folder);
        if (!dir.exists())
            dir.mkdirs();

        return new File(dir, filename);
    }
}
