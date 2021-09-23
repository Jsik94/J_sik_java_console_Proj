package academy;

import java.util.ArrayList;
import java.util.Map;

public class SearchNavigator implements Actionable, CodeInfo {

    Map<String,ArrayList<Person>> database;
    InputClass ip = null;
    final static private String TITLE = "검색";
    final static private int[] MENUOPTS ={1,2,3,4,5};
    final static private int WEIGHT = 400;
    final static private int GOBACK = 5;

    public SearchNavigator(Map<String,ArrayList<Person>> database) {
        this.database = database;
        ip= new InputClass(TITLE);
    }

    @Override
    public int run() {
        while(true){
            show();
            int result = ip.getMenuInput(MENUOPTS)+WEIGHT;
            Search selector = null;
            int request_code = 0;

            switch (result){
                case SEARCH_NAME:
                    selector = new SearchTypeFactory(database,TITLE).createInsert(SEARCH_NAME);
                    break;
                case GOBACK:
                    return MOVE_PREV;
                default:
                    selector = new SearchTypeFactory(database,TITLE).createInsert(result);
                    break;
            }

            request_code= selector.search();
            if(request_code!=SEARCH_FAIL){
                break;
            }
        }
        return COMPLETE;
    }

    @Override
    public void show() {
        System.out.println("=============\t서브 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t1.이름 2.나이 3.주소 4.학번/과목 5.뒤로가기\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");
    }

}
