package word;

import java.io.File;
import java.util.UUID;


public class FileUtil {
    /**
     * 删除文件或目录
     *
     * @param file file
     */
    public static void deleteFile(File file) {
        //判断是否为目录
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            for (File subFile : subFiles) {
                deleteFile(subFile);
            }
        }
        file.delete();
    }

    /**
     * 判断路径是否是文件夹
     *
     * @param dir
     * @return
     */
    public static boolean isDir(String dir) {
        File d = new File(dir);
        return d.isDirectory();
    }

    /**
     * 创建文件夹,如果存在则返回true，如果不存在则创建文件夹
     *
     * @param dir
     * @return
     */
    public static boolean createDir(String dir) {
        File d = new File(dir);
        if (!d.exists()) {
            return d.mkdirs();
        }
        if (d.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * 获取项目绝对路径
     *
     * @return String
     */
    public static String getProjectAbsolutePath() {
        return System.getProperty("user.dir");
    }

    /**
     * 根据文件名获取文件的相对路径
     * 例如: 2019\02\19\fa\d2\a1\d9\f1\c3\40\79\8c\9d\1b\32\f5\a2\ea\0c
     *
     * @param fileName 文件名
     * @return 相对路径
     */
    public static String strConvertPath(String fileName) {
        StringBuilder path = new StringBuilder();
        String[] parts = fileName.split("[_.]");
        path.append(parts[0].substring(0, 4)).append(File.separator)
                .append(parts[0].substring(4, 6)).append(File.separator)
                .append(parts[0].substring(6, 8)).append(File.separator);
        /*String uuid = parts[1];
        int m = uuid.length() / 2;
        if (m * 2 < uuid.length()) {
            m++;
        }
        String[] strs = new String[m];
        int j = 0;
        for (int i = 0; i < uuid.length(); i++) {
            if (i % 2 == 0) {
                strs[j] = "" + uuid.charAt(i);
            } else {
                strs[j] = strs[j] + "" + uuid.charAt(i);
                path.append(strs[j]).append(File.separator);
                j++;
            }
        }*/
        return path.toString();
    }
}
