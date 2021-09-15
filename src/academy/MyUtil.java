package academy;

public class MyUtil {

    final static private int KOR_CHAR = 2;
    final static private int ANOTHER_CHAR = 2;
    final static private int SIZE_SPACE = 3;
    final static private int SIZE_BORDER = 1;


    private MyUtil() {

    }

    //static method

    //confirm number
    static boolean isNumber(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!(value.codePointAt(i) >= '0' && value.codePointAt(i) <= '9'))
                return false;
        }
        return true;
    }


    //return Display width size for dynamic menu count
    static int calculate_width(int menu_cnt, String[] menus) {
        int total_size = 0;
        int menu_size[] = new int[menus.length];

        for (int i = 0; i < menus.length; i++) {
            for (char ch : menus[i].toCharArray()) {
                if (isKor(ch)) {
                    menu_size[i] += KOR_CHAR;
                } else {
                    menu_size[i] += ANOTHER_CHAR;
                }
            }

        }


        //앞뒤(2) *(칸막이(1) 공백(2))
        total_size += (SIZE_BORDER + SIZE_SPACE);

        for (int size : menu_size) {

            total_size += size + SIZE_SPACE;
        }


        return total_size;
    }

    //Korean Character confirm method
    //korean character - true else - false
    static boolean isKor(char ch) {

        if (ch >= '가' && ch <= '힣') {
            return true;
        }

        return false;
    }

    //return 1st letter
    public static String getTag(String name) {
        //김길동->ㄱ,박길동->ㅂ,홍길동->ㅎ
        char[] jaeum = name.trim().toCharArray();
        //방법1]
        char[] startChar = {'가', '나', '다', '라', '마', '바', '사', '아', '자', '차', '카', '타', '파', '하'};
        char[] endChar = {'낗', '닣', '띻', '맇', '밓', '삫', '앃', '잏', '찧', '칳', '킿', '팋', '핗', '힣'};
        char[] returnTag = {'ㄱ', 'ㄴ', 'ㄷ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

        for (int i = 0; i < startChar.length; i++) {
            if (jaeum[0] >= startChar[i] && jaeum[0] <= endChar[i]) {
                return String.valueOf(returnTag[i]);
            }
        }

        return null;
    }

}
