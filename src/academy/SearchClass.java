package academy;

import java.util.ArrayList;
import java.util.Map;

public class SearchClass extends SearchTool implements Actionable, CodeInfo {

    final static private String title = "검색";

    public SearchClass(Map<String,ArrayList<Person>> database) {
        super(database);
    }

    @Override
    public int run() {

        Person target = findPersonByName("검색");
        if (target == null) {
            return MOVE_PREV;
        }

        show();

        System.out.println(showToString(target));

        return COMPLETE;
    }

    @Override
    public void show() {
        System.out.println("찾았습니다. 해당 인원에 대한 정보입니다.");
    }

}
