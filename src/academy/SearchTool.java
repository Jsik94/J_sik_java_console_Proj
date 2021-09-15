package academy;

import java.util.ArrayList;
import java.util.Map;

public class SearchTool  {

    Map<String,ArrayList<Person>> database =null ;
    InputClass ip;

    public SearchTool(Map<String,ArrayList<Person>>database) {
        this.database = database;
        ip = new InputClass();
    }


    protected Person findPersonByName(String title) {

        System.out.println(title+"될 사람의 이름을 입력 해주세요.");

        String name = ip.getString();


        for(Person data:database.get(MyUtil.getTag(name))) {
            if(data.getName().equals(name))
                return data;
        }
        //갬색된 이름이 없는 경우
        System.out.println(name+"님에 대한 정보는 없습니다.");
        return null;
    }

    protected String showToString(Person one){
        StringBuilder sb =new StringBuilder();
        String type = null;
        String uniqueData =null;
        sb.append("분류 : ");
        if(one instanceof Student){
            sb.append("학생 ");
            type = "학번";
            uniqueData = ((Student) one).getStrNumber();
        }else if(one instanceof Teacher){
            sb.append("선생님 ");
            type="과목";
            uniqueData = ((Teacher) one).getSubject();
        }
        sb.append("\n");


        sb.append("이름 : " +one.getName()+" ")
                .append("나이 : " +one.getAge()+" ")
                .append(type+" : " +uniqueData+" ")
                .append("주소 : "+one.getAddr()+" ")
                .append("번호 : "+one.getTel()+" ")
                .append("이메일 : "+one.getEmail()+" ");



        return new String(sb);
    }

}
