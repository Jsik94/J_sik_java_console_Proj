package academy;

import java.util.ArrayList;
import java.util.Map;

public class InserNavigator implements Actionable,CodeInfo, Navigatable {
    final static private String TITLE = "입력";
    final static int[] MENUOPTS = new int[]{1,2,3};


    InputClass inputClass ;
    Map<String,ArrayList<Person>> database =null;


    public InserNavigator(Map<String,ArrayList<Person>> database) {
        inputClass = new InputClass();
        this.database = database;
    }

    @Override
    public void show() {
        System.out.println("=============\t서브 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t\t\t1.학생 2.선생 3.뒤로가기\t\t\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");
    }

    @Override
    public int run() {
        while (true){

            show();
            int result = inputClass.getMenuInput(MENUOPTS);
            int request_code = 0 ;

            Insert selector =null;

            switch (result+200){
                case INSERT_STUDENT:
                    selector= new InsertTypeFactory(database).createInsert(INSERT_STUDENT);

                    break;
                case INSERT_TEACHER:
                    selector= new InsertTypeFactory(database).createInsert(INSERT_TEACHER);

                    break;
                case INSERT_BACK:

                    return COMPLETE;

            }

            request_code = selector.insert();
            if(request_code!=CURRENT){
                break;
            }

        }

        return COMPLETE;
    }

}
