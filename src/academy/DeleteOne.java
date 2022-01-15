package academy;

import java.util.ArrayList;
import java.util.Map;

public class DeleteOne implements Delete{

    Map<String, ArrayList<Person>> database;
    Person targets;
    public DeleteOne(Map<String, ArrayList<Person>> database, Person targets) {
        this.database = database;
        this.targets = targets;
    }

    @Override
    public int delete() {

        database.get(MyUtil.getTag(targets.getName())).remove(targets);

        return CodeInfo.COMPLETE;
    }
}
