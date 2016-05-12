package es.uji.daal.easyrent.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static org.eclipse.jdt.internal.compiler.parser.Parser.name;

/**
 * Created by Alberto on 12/05/2016.
 */
public class FileUploaderFS implements FileUploader {
    private static final int PREFIX = 0, SUFFIX = 1;

    public String upload(String folder, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + folder);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                String[] nameParts = fileName.split("\\.(?=[^\\.]+$)");
                nameParts[PREFIX] = nameParts[PREFIX].replace("\\s+", "");
                File serverFile = File.createTempFile(nameParts[PREFIX], nameParts[SUFFIX], dir);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                return serverFile.getName();
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
