import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Tree {

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     *
     * @param args
     */
    public static void main(String[] args) {
        print(new File("."), "", true);
        backup(new File("."), new File("backup"), "");
    }

    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            int subDirTotal = 0;
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    subDirTotal++;
                }
            }

            int subDirCounter = 0;
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    print(files[i], indent, subDirTotal == ++subDirCounter);
                } else if (files[i].isFile()) {
                    print(files[i], indent + (isLast ? "  " : "│ "), false);
                }
            }
        }
    }

    public static void backup(File source, File target, String indent) {
        if (source.isDirectory()) {
            File dir = new File(target, source.getName());
            dir.mkdir();
            File[] files = source.listFiles();
            if (files != null) {
                for (File file : files) {
                    backup(file, dir, indent + "  ");
                }
            }
        } else if (source.isFile()) {
            File targetFile = new File(target, source.getName());
            try {
                Files.copy(source.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
