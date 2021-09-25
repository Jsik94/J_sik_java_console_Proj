package academy;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

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


    public static String makeDataFormat(Map<String, ArrayList<Person>> database){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ArrayList<Person>> entries : database.entrySet()){
            for (Person one : entries.getValue()){
                if( one instanceof Student){
                    sb.append("Student\t")
                            .append(one.getName()+"\t")
                            .append(one.getAge()+"\t")
                            .append(String.format("%-10s",((Student) one).getStrNumber())+"\t\t");

                }else{
                    sb.append("Teacher\t")
                            .append(one.getName()+"\t")
                            .append(one.getAge()+"\t")
                            .append(String.format("%-10s",((Teacher) one).getSubject())+"\t");
                }
                sb.append(one.getTel()+"\t")
                        .append(String.format("%-30s",one.getAddr())+"\t")
                        .append(one.getEmail()+"\t")
                        .append("\r\n");
            }
        }
        //System.out.println(sb);
        return sb.toString();
    }

    public static void connectDataBase(String TITLE, Map<String, ArrayList<Person>> database) {
        MyLog.d(TITLE, "Loading data in Memory...");
        String[] datas = FileReaderClass.getInstance().toStringLine();
        Person one;
        try {

            for (String data : datas) {
                StringTokenizer stk = new StringTokenizer(data, "\t");
                if (stk.nextToken().equals("Student")) {
                    String name = stk.nextToken().trim();
                    String age = stk.nextToken().trim();
                    String strNum = stk.nextToken().trim();
                    String tel = stk.nextToken().trim();
                    String addr = stk.nextToken().trim();
                    String email = stk.nextToken().trim();
                    one = new Student.StudentBuilder(Integer.parseInt(age), name, strNum)
                            .setTel(tel)
                            .setAddr(addr)
                            .setEmail(email)
                            .build();

                } else {
                    String name = stk.nextToken().trim();
                    String age = stk.nextToken().trim();
                    String sub = stk.nextToken().trim();
                    String tel = stk.nextToken().trim();
                    String addr = stk.nextToken().trim();
                    String email = stk.nextToken().trim();
                    one = new Teacher.TeacherBuilder(Integer.parseInt(age), name, sub)
                            .setTel(tel)
                            .setAddr(addr)
                            .setEmail(email)
                            .build();

                }
                String tag = MyUtil.getTag(one.getName());

                if (database.get(tag) == null) {

                    database.put(tag, new ArrayList<Person>());
                }
                database.get(tag).add(one);

            }
        } catch (NoSuchElementException e) {
            MyLog.e(TITLE, "Fail to load data :" + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            MyLog.e(TITLE, "Fail to load data :" + e.getMessage());
            return;
        }

        MyLog.d(TITLE, "Complete to load data !");

    }
}
