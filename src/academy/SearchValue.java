package academy;

import java.util.ArrayList;
import java.util.Map;

public class SearchValue implements Search{
    private static final String TITLE = "Search Value";
    Map<String, ArrayList<Person>> database;
    InputClass ip ;
    String title;
    int code_type;


    public SearchValue(Map<String, ArrayList<Person>> database, String title, int code_type) {
        this.database = database;
        this.ip = new InputClass(TITLE);
        this.title = title;
        this.code_type = code_type;
    }



    @Override
    public int search() {
        ArrayList<Person> result = find(code_type);
        StringBuilder sb = new StringBuilder();
        if(result==null){
            return CodeInfo.MOVE_PREV;
        }else{
            System.out.println(result.size()+"명을 찾았습니다!");
        }

        for (Person one : result){
            sb.append(showToString(one));
        }

        System.out.println(sb);
        return CodeInfo.COMPLETE;
    }

    protected ArrayList<Person> find(int kind) {
        String standard = null;
        boolean isNum = false;
        int i_result = 0;
        String str_result = null;

        switch (kind) {

            case CodeInfo.SEARCH_AGE:
                standard = "나이";
                isNum = true;
                break;
            case CodeInfo.SEARCH_ADDR:
                standard = "주소";
                break;
            case CodeInfo.SEARCH_UNIQUE:
                standard = "학번/과목";
                break;

            case CodeInfo.SEARCH_TEL:
                standard = "번호";
                break;
            case CodeInfo.SEARCH_EMAIL:
                standard = "메일";
                break;

        }
        ArrayList<Person> find_list = new ArrayList<>();


        System.out.println(title + "될 사람의" + standard + "을(를) 선택해주세요.");
        if (isNum) {
            i_result = ip.getInteger();
        } else {
            str_result = ip.getOrigin();
        }


        for (String key : database.keySet()) {
            for (Person a : database.get(key)) {
                boolean find = false;

                if (a instanceof Student) {
                    if (isNum) {
                        if (((Student) a).contains(i_result))
                            find = true;
                    } else {
                        if (((Student) a).contains(str_result))
                            find = true;

                    }
                } else if (a instanceof Teacher) {
                    if (isNum) {
                        if (((Teacher) a).contains(i_result))
                            find = true;

                    } else {
                        if (((Teacher) a).contains(str_result))
                            find = true;

                    }

                }

                if (find) {
                    find_list.add(a);
                }
            }
        }

        if (find_list.isEmpty()){
            System.out.println("해당 정보가 존재하지 않았습니다.");
            return null;
        }

        return find_list;
    }
}
