package nom.SL;

import java.io.File;

public class Main {
//    private static String root = "F:" + File.separator + "MyProject" + File.separator + "CodeCorrect_Unicode" + File.separator + "src";
//    private static String root = "F:\\MyProject\\Bridge\\src";
    private static String root = "F:\\MyProject\\CodeCorrect_Unicode\\out\\artifacts\\CodeCorrect_UTF8_jar";

    public static void main(String args[]){
        menu();
        root = args[1];
        System.out.println(root);
        Targets targets = new Targets();
        System.out.println("---loading file...---");
        targets.scan(root);
        System.out.println("---correct code...---");
        targets.Correct(Correct.TO_UNICODE);
        targets.travel(true);
        System.out.println("---overwriting...---");
        targets.save();
    }

    private static void menu(){

    }
}
