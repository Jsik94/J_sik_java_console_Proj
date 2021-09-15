package academy;

import java.util.ArrayList;
import java.util.Map;

public class DeleteClass extends SearchTool implements Actionable,CodeInfo {
    final static private String TITLE = "삭제";

    public DeleteClass(Map<String,ArrayList<Person>> database) {
        super(database);

    }

    @Override
    public int run() {
        Person target = findPersonByName(TITLE);

        if (target==null){
            return GO_BACK;
        }
        show();
        database.get(MyUtil.getTag(target.getName())).remove(target);
        System.out.println("삭제되었습니다.");

        return COMPLETE;
    }

    @Override
    public void show() {
        System.out.println("해당 인원을 찾았습니다.");
    }


}
