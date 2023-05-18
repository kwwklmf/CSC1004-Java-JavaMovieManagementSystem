package GUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class addpic {
    public static void main(String[] args) {
        // 指定图片文件路径
        String imagePath = "/Users/williamkong/Downloads/IMG_20181027_084736.jpg";

        // 定义目标文件夹
        String targetFolderPath = "src/main/java/GUI/Picture/Movies/";

        try {
            // 读取源文件
            Path source = Paths.get(imagePath);

            // 构造目标文件路径
            String targetPath = targetFolderPath + "Aoligei.jpg";

            // 创建目标文件夹（如果不存在）
            Files.createDirectories(Paths.get(targetFolderPath));

            // 复制文件到目标文件夹
            Path target = Paths.get(targetPath);
            Files.copy(source, target);

            System.out.println("Image uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

