package academy;

import java.util.ArrayList;
import java.util.Map;

public class SearchClass extends SearchTool implements Actionable, CodeInfo {

    final static private String TITLE = "검색";
    final static private int[] MENUOPTS ={1,2,3,4,5,6};

    public SearchClass(Map<String,ArrayList<Person>> database) {
        super(database);
    }

    @Override
    public int run() {

        show();
        int result = ip.getMenuInput(MENUOPTS);

        if((result+400) ==SEARCH_NAME){

            Person target = findPersonByName(TITLE);
            if (target == null) {
                return MOVE_PREV;
            }


            System.out.println("찾았습니다. 해당 인원에 대한 정보입니다.");
            System.out.println(showToString(target));

        }else{
            ArrayList<Person> find_list =find(result+400,TITLE);

            for (Person find : find_list){
                System.out.println(showToString(find));
            }
        }


        return COMPLETE;
    }

    @Override
    public void show() {
        System.out.println("=============\t서브 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t\t1.이름 2.나이 3.주소 4.학번/과목 \t\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");
    }

}
