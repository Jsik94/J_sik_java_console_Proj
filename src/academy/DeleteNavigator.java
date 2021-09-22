package academy;

import java.util.ArrayList;
import java.util.Map;

public class DeleteNavigator extends SearchKey implements Actionable, CodeInfo{
    final static private String TITLE = "삭제";
    //    Map<String,ArrayList<Person>> database = null;
    final static int WEIGHT = 500;

    public DeleteNavigator(Map<String, ArrayList<Person>> database) {
        super(database, TITLE);

    }

    @Override
    public int run() {

        Person target = null;
        ArrayList<Person> targets = findPersonByName(TITLE);
        System.out.println(targets.size() +"명이 검색 되었습니다.");
        int num = 0;
        int i = 1;
        for (Person one : targets){
            System.out.println(i+++"]"+showToString(one));
        }

        System.out.println("모두 삭제하시겠습니까 ?");

        Delete selector = null;
        if(ip.getYorN()){
            selector = new DeleteTypeFactory(database,targets).createInsert(DELETE_ALL);
        }else{
            while (true){
                System.out.println("찾으시는 분의 번호를 정확히 선택해 주십시오.");
                num = ip.getInteger()-1;

                if(targets.get(num)==null){
                    System.out.println("잘못된 다시 선택해 주십시오.");
                }else{
                    break;
                }
            }

            selector = new DeleteTypeFactory(database,targets.get(num)).createInsert(DELETE_ONE);
        }

        int request_code = selector.delete();

//        if (targets==null){
//            return CURRENT;
//        }else if( targets.size()>1){
//            target = showindicator(targets);
//        }else{
//            target = targets.get(0);
//        }
//
//        if (targets == null) {
//            return MOVE_PREV;
//        }
//        show();

        return request_code;
    }

    @Override
    public void show() {
        System.out.println("삭제되었습니다.");
    }


}
