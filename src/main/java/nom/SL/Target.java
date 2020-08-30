package nom.SL;

import java.io.*;

public class Target {
    private Targets targets;
    private String address;
    private String info;

    /**
     *
     * @param targets 文件链
     * @param address 文件地址
     */
    public Target(Targets targets, String address){
        this.targets = targets;
        this.address = address;
        this.info = "";
        try{
            loadFile();
        }catch (IOException e){
            e.getMessage();
        }
        targets.add(this);
    }

    /**
     * 读取文件内容
     * @throws IOException
     */
    public void loadFile() throws IOException {
        File file = new File(address);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader in = new InputStreamReader(fileInputStream);
        BufferedReader read = new BufferedReader(in);
        char c;
        while ((c = (char) read.read()) != '\uFFFF') {
            info = info + c;
        }
        read.close();
        in.close();
        fileInputStream.close();
    }

    /**
     * 保存文件内容
     * @throws IOException
     */
    public void saveFile() throws IOException{
        File file = new File(address);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter out = new OutputStreamWriter(fileOutputStream);
        BufferedWriter writer = new BufferedWriter(out);

        writer.write(info);

        writer.close();
        out.close();
        fileOutputStream.close();
    }

    public Targets getTargets() {
        return targets;
    }

    public void setTargets(Targets targets) {
        this.targets = targets;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
