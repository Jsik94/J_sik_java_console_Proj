package academy;

import java.util.ArrayList;
import java.util.Map;

public class InsertTypeFactory extends Factory<Insert> {

    Map<String, ArrayList<Person>> database;

    public InsertTypeFactory(Map<String,ArrayList<Person>> database){
        this.database = database;
    }

    @Override
    public Insert createInsert(int type_code) {

        Insert insert = null;
        switch (type_code){

            case CodeInfo.INSERT_STUDENT:
                insert = new InsertStudent(database);
                break;
            case CodeInfo.INSERT_TEACHER:
                insert = new InsertTeacher(database);
                break;
        }

        return insert;

    }
}

