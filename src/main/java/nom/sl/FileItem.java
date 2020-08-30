package nom.sl;

import java.io.*;

public class FileItem {
    private Boolean isChange = Boolean.FALSE;
    private String address;
    private String info;

    /**
     *
     * @param address 文件地址
     */
    public FileItem(String address){
        this.address = address;
        this.info = "";
        try{
            loadFile();
        }catch (IOException e){
            e.getMessage();
        }
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
        if (!this.isChange){
//            System.out.println("--this file have no change");
            return;
        }
        File file = new File(address);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter out = new OutputStreamWriter(fileOutputStream);
        BufferedWriter writer = new BufferedWriter(out);

        writer.write(info);

        writer.close();
        out.close();
        fileOutputStream.close();
    }

    /**
     * 文件发生改变
     */
    public void fileChange(){
        this.isChange = Boolean.TRUE;
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
