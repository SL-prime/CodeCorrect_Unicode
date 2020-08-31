package nom.sl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeConvert {
    private List<String> suffixes;
    private int type;
    private int total = 0;

    public CodeConvert(int type) {
        this.type = type;
        suffixes = new ArrayList<String>();
        suffixes.add(".java");
        suffixes.add(".txt");
    }

    /**
     * @param root
     */
    public void start(String root) {
        scan(root);
        summary();
    }

    /**
     * @param root 遍历根路径
     */
    private void scan(String root) {
        File baseFile = new File(root);
        if (baseFile.isFile() || !baseFile.exists()) {
            return;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scan(file.getAbsolutePath());
            } else {
                String filename = file.getName();
                int sufstart = filename.lastIndexOf(".");
                String suffix = "";
                if (sufstart >= 0) {
                    suffix = filename.substring(sufstart, filename.length());
                }
                if (!filename.equals("CodeCorrect_UTF8.jar") && suffixCheck(suffix)) {
                    total++;
                    System.out.println("-loading file " + file.getName());
                    process(new FileItem(file.getAbsolutePath()));
                }
            }
        }
        return;
    }

    /**
     * 处理事务
     *
     * @param fileItem
     */
    private void process(FileItem fileItem) {
//        System.out.println("-correct code start");
        Correct.correct(fileItem, type);
//        System.out.println("-overwriting");
        save(fileItem);
//        System.out.println("-------------------------------------------------");
    }

    /**
     * 检测是否为需要处理的文件
     *
     * @param suffix
     * @return true or false
     */
    private boolean suffixCheck(String suffix) {
        for (String s : suffixes) {
            if (s.equals(suffix))
                return true;
        }
        return false;
    }

    /**
     * 保存
     */
    private void save(FileItem fileItem) {
        try {
            fileItem.saveFile();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * 任务总结
     */
    private void summary() {
        System.out.println("-------------------------------------------------");
        System.out.println("total: " + total);
    }

    /**
     * 遍历文件
     *
     * @param isshow 是否展示内容
     */
    public void show(FileItem fileItem, Boolean isshow) {
        System.out.println("Address: " + fileItem.getAddress());
        if (isshow) {
            System.out.println("Info:\n" + fileItem.getInfo());
        }
    }

}
