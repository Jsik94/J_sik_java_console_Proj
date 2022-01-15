package academy;

import java.util.ArrayList;
import java.util.Map;

public class ModifyNavigator extends SearchKey implements Actionable,CodeInfo{
    final static private String TITLE = "수정";
    final static private int[] MENUOPTS = new int[]{1,2,3,4,5,6};
    final static int WEIGHT = 300;


    public ModifyNavigator(Map<String,ArrayList<Person>> database) {
        super(database,TITLE);
    }

    @Override
    public int run() {
        /*
            이름이 여러개 일 경우
            몇번인지 선택 할 수 있는 indicator 필요
         */


        Person target = null;
        ArrayList<Person> targets = findPersonByName(TITLE);
        if (targets==null){
            return CURRENT;
        }else if( targets.size()>1){
            target = showindicator(targets);
        }else{
            target = targets.get(0);
        }
        show();
        int result = ip.getMenuInput(MENUOPTS)+WEIGHT;
        Modify selector = new ModifyAll(database,title,target);

        int request_code = selector.modify(result);

        return request_code;
//
//        database.get(MyUtil.getTag(target.getName())).remove(target);
//
//        int result = ip.getMenuInput(MENUOPTS);
//        split(result,target);
//
//        System.out.println("수정 되었습니다.");
//        return COMPLETE;
    }

    @Override
    public void show() {
        System.out.println("=============\t서브 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t\t\t1.이름 2.나이 3.주소 4.학번\t\t=");
        System.out.println("=\t\t\t5.번호 6.이메일 7.뒤로가기\t\t\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");
    }

    private Person showindicator(ArrayList<Person> targets){
        System.out.println(targets.size() +"명이 검색 되었습니다.");
        int num = 0;
        int i = 1;
        for (Person one : targets){
            System.out.println(i+++"]"+showToString(one));
        }
        while (true){
            System.out.println("찾으시는 분의 번호를 정확히 선택해 주십시오.");
            num = ip.getInteger()-1;

            if(targets.get(num)==null){
                System.out.println("잘못된 다시 선택해 주십시오.");
            }else{
                return targets.get(num);
            }
        }

    }




}
