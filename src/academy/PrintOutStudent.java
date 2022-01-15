package academy;

import java.util.ArrayList;
import java.util.Map;

public class PrintOutStudent implements PrintOut{

    Map<String, ArrayList<Person>> database;
    public PrintOutStudent(Map<String, ArrayList<Person>> database) {
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
        //before format
//        System.out.printf("%-5s %-2s %-8s %-22s%-11s %-32s\n","이름","나이",unique,"주소",
//                "연락처","메일 주소");

        System.out.println("[학생 정보]");
        System.out.printf("%-8s%-6s%-15s%-22s%-20s%-32s\n","이름","나이","학번","주소",
                "연락처","메일 주소");

        for (String keyset : database.keySet()){
            for (Person info : database.get(keyset)){
                if (info instanceof Student){

                    //before format "%-5s %-3d %-9d %-20s %-11s %-32s"
                    sb.append(String.format("%-8s%-6d%-15s%-22s%-22s%-32s",
                            info.getName(),
                            info.getAge(),
                            ((Student) info).getStrNumber(),
                            info.getAddr(),
                            info.getTel(),
                            info.getEmail())).append("\n");
                }

            }
        }

        System.out.println(sb);

        return CodeInfo.CURRENT;
    }
}
