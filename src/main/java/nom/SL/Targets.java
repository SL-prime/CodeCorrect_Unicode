package nom.SL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Targets {
    private List<Target> targets;
    public Targets(){
        targets = new ArrayList<Target>();
    }

    /**
     *
     * @param root 遍历根路径
     */
    public void scan(String root){
        File baseFile = new File(root);
        if (baseFile.isFile() || !baseFile.exists()) {
            return;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scan(file.getAbsolutePath());
            } else {
                if (!file.getName().equals("CodeCorrect_UTF8.jar")){
                    new Target(this, file.getAbsolutePath());
                }
            }
        }
        return;
    }

    /**
     * 编码
     * @param type 编码类型
     */
    public void Correct(int type){
        for(Target target : targets){
            target.setInfo(Correct.correct(target.getInfo(), type));
        }
    }

    /**
     * 保存
     */
    public void save(){
        for(Target target : targets){
            try{
                target.saveFile();
            }catch (IOException e){
                e.getMessage();
            }
        }
    }

    /**
     * 遍历文件
     * @param isshow 是否展示内容
     */
    public void travel(Boolean isshow){
        for(Target target : targets){
            System.out.println("Address: " + target.getAddress());
            if(isshow){
                System.out.println("Info:\n" + target.getInfo());
            }
        }
    }

    /**
     * 新增路径
     */
    public void add(Target target){
        targets.add(target);
    }
    /**
     * 删除路径
     */
    public void del(Target target){
        targets.remove(target);
    }
}
