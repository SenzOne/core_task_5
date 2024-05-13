import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BackupFiles {

    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        String backupDirectory = currentDirectory + "/backup";

        File backupDir = new File(backupDirectory);
        if (!backupDir.exists()) {
            backupDir.mkdir();
        }


        copyFiles(new File(currentDirectory), backupDir);
    }

    private static void copyFiles(File sourceDirectory, File destinationDirectory) {
        File[] files = sourceDirectory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    File dir = new File(destinationDirectory, file.getName());
                    dir.mkdir();
                    copyFiles(file, dir);
                } else if (file.isFile()) {
                    try {
                        Files.copy(file.toPath(), new File(destinationDirectory, file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

