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

        setData(MyUtil.makeDataFormat(database));

        return CodeInfo.COMPLETE;
    }



    @Override
    public void show() {
        System.out.println("데이터를 저장하시겠습니까 ? ");
    }





}
