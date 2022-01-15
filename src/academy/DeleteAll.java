package academy;

import java.util.ArrayList;
import java.util.Map;

public class DeleteAll implements Delete{
    Map<String,ArrayList<Person>> database;
    ArrayList<Person> targets;

    public DeleteAll(Map<String, ArrayList<Person>> database, ArrayList<Person> targets) {
        this.database = database;
        this.targets = targets;
    }


    @Override
    public int delete() {

        for (Person target : targets){
            database.get(MyUtil.getTag(target.getName())).remove(target);
        }
        return CodeInfo.COMPLETE;
    }
}
