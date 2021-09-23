package academy;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SaveData extends FileWriterClass implements Actionable{
    final static private String TITLE = "SAVEDATA";
    Map<String, ArrayList<Person>> database;
    InputClass ip ;
    public SaveData(Map<String, ArrayList<Person>> database) {
        this.database = database;
        ip = new InputClass(TITLE);
    }

    @Override
    public int run() {
        show();
        if(!ip.getYorN()){
            return CodeInfo.CURRENT;
        }

        setData(makeDataFormat());

        return 0;
    }



    @Override
    public void show() {
        System.out.println("데이터를 저장하시겠습니까 ? ");
    }

    public String makeDataFormat(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,ArrayList<Person>> entries : database.entrySet()){
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
        System.out.println(sb);
        return sb.toString();
    }



}
