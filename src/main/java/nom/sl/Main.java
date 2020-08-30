package nom.sl;

public class Main {

    private static String root = "";

    public static void main(String args[]){
        root = args[0];
        menu();
        new CodeConvert(Correct.TO_UNICODE).scan(root);
    }

    private static void menu(){
        System.out.println("scan from " + root);
        System.out.println("-------------------------------------------------");
    }
}
