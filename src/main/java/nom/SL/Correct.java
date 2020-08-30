package nom.SL;

import java.util.ArrayList;
import java.util.List;

public class Correct {

    public final static int TO_UNICODE = 0;
    public final static int TO_UTF8 = 1;

    /**
     *
     * @param info
     * @param type
     * @return
     */
    public static String correct(String info, int type){
        switch (type){
            case TO_UNICODE:
                info = coding_unicode(info);
                break;
            case TO_UTF8:
                info = coding_utf8(info);
                break;
        }
        return info;
    }

    /**
     *
     * @param code
     * @return
     */
    private static String coding_unicode(String code){
        List<Integer> list = new ArrayList<Integer>();
        Integer start = 0;
        /**
            没有处理非unicode编码出现\\u的情况
            当文档末尾为\\u非Unicode编码时可能出现报错
        * */
        start = code.indexOf("\\u", start);
        while(start != -1){
            //转码
            String unicode = code.substring(start + 2, start + 6);
            int chr = Integer.parseInt(unicode, 16);
            char ch = (char) chr;
            //replace
            code = code.replace(("\\u" + unicode), String.valueOf(ch));
            start = code.indexOf("\\u", start);
        }
        return code;
    }

    /**
     *
     * @param code
     * @return
     */
    private static String coding_utf8(String code){

        return code;
    }
}
