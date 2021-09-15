package academy;

import java.util.ArrayList;
import java.util.Map;

public class SearchTool {

    Map<String, ArrayList<Person>> database = null;
    InputClass ip;

    public SearchTool(Map<String, ArrayList<Person>> database) {
        this.database = database;
        ip = new InputClass();
    }


    protected ArrayList<Person> find(int kind, String title) {
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

        if (find_list == null) {
            System.out.println("해당 정보가 존재하지 않습니다.");
        } else {
            System.out.println(find_list.size() + "명을 찾았습니다.");
        }

        return find_list;
    }


    protected Person findPersonByName(String title) {

        System.out.println(title + "될 사람의 이름을 입력 해주세요.");

        String name = ip.getString();

        if (database.get(MyUtil.getTag(name)) != null) {

            for (Person data : database.get(MyUtil.getTag(name))) {
                if (data.getName().equals(name))
                    return data;
            }
        }

        //갬색된 이름이 없는 경우
        System.out.println(name + "님에 대한 정보는 없습니다.");
        return null;
    }

    protected String showToString(Person one) {
        StringBuilder sb = new StringBuilder();
        String type = null;
        String uniqueData = null;
        sb.append("분류 : ");
        if (one instanceof Student) {
            sb.append("학생 ");
            type = "학번";
            uniqueData = ((Student) one).getStrNumber();
        } else if (one instanceof Teacher) {
            sb.append("선생님 ");
            type = "과목";
            uniqueData = ((Teacher) one).getSubject();
        }
        sb.append("\n");


        sb.append("이름 : " + one.getName() + " ")
                .append("나이 : " + one.getAge() + " ")
                .append(type + " : " + uniqueData + " ")
                .append("주소 : " + one.getAddr() + " ")
                .append("번호 : " + one.getTel() + " ")
                .append("이메일 : " + one.getEmail() + " ");


        return new String(sb);
    }

}
