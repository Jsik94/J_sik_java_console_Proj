package academy;

import java.util.ArrayList;
import java.util.Map;

public class PrintTypeFactory extends PrintFactory {
    Map<String, ArrayList<Person>> database;

    public PrintTypeFactory(Map<String, ArrayList<Person>> database) {
        this.database = database;
    }

    @Override
    PrintOut createInsert(int code_type) {
        PrintOut selector =null ;

        switch (code_type){
            case CodeInfo.PRINTOUT_STUDENT:
                selector = new PrintOutStudent(database);
                break;
            case CodeInfo.PRINTOUT_TEACHER:
                selector = new PrintOutTeacher(database);
                break;
            case CodeInfo.PRINTOUT_ALL:
                selector = new PrintOutAll(database);

        }

        return selector;
    }
}
