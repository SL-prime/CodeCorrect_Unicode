package nom.sl;

import java.util.ArrayList;
import java.util.List;

public class Correct {

    public final static int TO_UNICODE = 0;
    public final static int TO_UTF8 = 1;

    /**
     *
     * @param file
     * @param type
     * @return
     */
    public static void correct(FileItem file, int type){
        switch (type){
            case TO_UNICODE:
                coding_unicode(file);
                break;
            case TO_UTF8:
                coding_utf8(file);
                break;
        }
        return;
    }

    /**
     *
     * @param file
     * @return
     */
    private static void coding_unicode(FileItem file){
        String code = file.getInfo();
        List<Integer> list = new ArrayList<Integer>();
        Integer start = 0;
        /**
            没有处理非unicode编码出现\\u的情况
            当文档末尾为\\u非Unicode编码时可能出现报错
        * */
        start = code.indexOf("\\u", start);
        while(start != -1){
            file.fileChange();
            //转码
            String unicode = code.substring(start + 2, start + 6);
            int chr = Integer.parseInt(unicode, 16);
            char ch = (char) chr;
            //replace
            code = code.replace(("\\u" + unicode), String.valueOf(ch));
            start = code.indexOf("\\u", start);
        }
        file.setInfo(code);
        return;
    }

    /**
     *
     * @param file
     * @return
     */
    private static void coding_utf8(FileItem file){

        return;
    }
}
