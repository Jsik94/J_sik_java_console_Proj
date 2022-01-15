package academy;

import java.util.ArrayList;
import java.util.Map;

public class InsertTeacher implements Insert ,CodeInfo{

    final String TITLE = "선생님";
    InputClass ip;
    Person newOne;
    Map<String, ArrayList<Person>> database;

    public InsertTeacher(Map<String, ArrayList<Person>> database) {
        this.database = database;
    }

    @Override
    public int insert() {
        ip = new InputClass(TITLE);

        System.out.println("["+TITLE+" 필수 정보 입력란]");
        String[] data = new String[Teacher.cols.length];
        for (int i = 0 ; i < Teacher.cols.length ; i++){


            System.out.println(TITLE +"의 "+ Teacher.cols[i]+"을(를) 입력하세요");
            if(Teacher.cols[i].equals("나이")){
                data[i] = Integer.toString(ip.getInteger());
            }

            else{
                if (i ==3) ip.filter_addr = true;
                if (i ==5) ip.filter_email = true;
                data[i] = ip.getString();

                ip.filter_addr = false;
                ip.filter_email = false;
            }

        }

        newOne = new Teacher.TeacherBuilder(Integer.parseInt(data[0]),data[1],data[2])
                .setAddr(data[3])
                .setTel(data[4])
                .setEmail(data[5])
                .build();


        String tag =MyUtil.getTag(newOne.getName());

        if (database.getOrDefault(tag,null)==null){
            database.put(tag,new ArrayList<>());
        }

        (database.get(tag)).add(newOne);


        return CURRENT;
    }
}
