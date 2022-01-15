package academy;

import java.util.ArrayList;
import java.util.Map;

public class PrintOutAll implements PrintOut{
    Map<String, ArrayList<Person>> database;
    public PrintOutAll(Map<String, ArrayList<Person>> database) {
        this.database = database;
    }

    @Override
    public int print() {
        if(database.isEmpty()){
            System.out.println("등록된 정보가 없습니다.");
            return CodeInfo.CURRENT;
        }

        StringBuilder sb = new StringBuilder();

        System.out.println("<--\t\t\t\t\t\t정보 출력\t\t\t\t\t\t-->");


        System.out.println("[전체 정보]");
        System.out.printf("%-8s%-8s%-6s%-15s%-22s%-20s%-32s\n","구분","이름","나이","과목/학번","주소",
                "연락처","메일 주소");


        for (String keyset : database.keySet()){
            for (Person info : database.get(keyset)){
                String unique;
                if(info instanceof Student){
                    unique = ((Student)info).getStrNumber();
                    sb.append(String.format("%-8s","학생"));

                }else {
                    unique = ((Teacher)info).getSubject();
                    sb.append(String.format("%-8s","선생"));

                }



                //before format "%-5s %-3d %-9d %-20s %-11s %-32s"
                sb.append(String.format("%-8s%-6d%-15s%-22s%-22s%-32s",
                        info.getName(),
                        info.getAge(),
                        unique,
                        info.getAddr(),
                        info.getTel(),
                        info.getEmail())).append("\n");

            }
        }

        System.out.println(sb);

        return CodeInfo.COMPLETE;
    }
}
