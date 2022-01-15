package academy;

import java.util.*;

public class PrintOutNavigator implements Actionable ,CodeInfo{
    /*
     * 분리 예정
     * idea 1
     * InsertMenu 클래스와 중복이 과함 출력과 네비게이터는 공유하되
     * 입출력을 구분하는 식으로 하는게 나을 듯
     *
     */
    final static String TITLE ="Print Out";
    final static int[] MENUOPTS = new int[]{1, 2, 3, 4};
    final static int GO_BACK = 104;
    final static int EXIT = 105;
    private final static int WEIGHT = 100;


    InputClass inputClass;
    Map<String,ArrayList<Person>> database = null;


    public PrintOutNavigator(Map<String,ArrayList<Person>> database) {
        inputClass = new InputClass(TITLE);
        this.database = database;
    }



    @Override
    public void show() {
        System.out.println("=============\t서브 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t\t1.학생 2.선생 3.모두 보기 4.뒤로가기\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");

    }


    @Override
    public int run() {
        while (true){
            show();
            int result_code = inputClass.getMenuInput(MENUOPTS)+WEIGHT;

            int request_code = 0;

            if((result_code==GO_BACK) || ( result_code == EXIT)){
                break;
            }

            PrintOut selector = new PrintTypeFactory(database).createInsert(result_code);
            request_code = selector.print();

            if (request_code== CURRENT){
                continue;
            }else{
                break;
            }
        }

        return COMPLETE;
    }



}
