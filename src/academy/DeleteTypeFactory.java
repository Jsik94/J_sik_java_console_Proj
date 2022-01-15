package academy;

import java.util.ArrayList;
import java.util.Map;

public class DeleteTypeFactory extends Factory<Delete>{
    Map<String, ArrayList<Person>> database ;
    Object targets ;

    public DeleteTypeFactory(Map<String, ArrayList<Person>> database,Object targets) {
        this.database = database;
        this.targets = targets;
    }


    @Override
    public Delete createInsert(int code_type) {

        Delete selector = null;
        switch (code_type){

            case CodeInfo.DELETE_ALL:

                selector = new DeleteAll(database,(ArrayList<Person>) targets);
                break;
            case CodeInfo.DELETE_ONE:
                selector = new DeleteOne(database,(Person) targets);
                break;


        }

        return selector;
    }
}
