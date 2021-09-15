package academy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Academy implements CodeInfo {
    /*
          Academy 객체

            사용 목적
            -> 필요 자원 적재
            -> 프로그램 사용 흐름제어

            constructor  | param -> FileReader 사용여부, test 모드인지 ,
                필요한 자원 미리 적재

            field


            method
                run

     */

//    //singletone design
//    private static Academy uniqueInstance = null;
//    //singletone design
//    private Academy getInstance(){
//        if(uniqueInstance==null){
//            synchronized (Academy.class){
//                if(uniqueInstance ==null){
//                    uniqueInstance = new Academy();
//                }
//            }
//        }
//        return uniqueInstance;
//    }

    //Field
//    final static private int MOVE_INSERT = 1;
//    final static private int MOVE_PRINTOUT = 2;
//    final static private int MOVE_MODIFY = 3;
//    final static private int MOVE_DELETE = 4;
//    final static private int MOVE_SEARCH = 5;
//    final static private int MOVE_EXIT = 999;
//    final static private int MOVE_FIRST = 998;

    Map<String,ArrayList<Person>> database ;

    InputClass inputClass;

    public Academy() {
        database = new HashMap<>();
    }
    public Academy(int forTest) {

        System.out.println("<-------------- FOR DEBUG ----------------->");
        System.out.println("Constructor For Debug");
        this.database =new HashMap<>();

        database.put("ㅈ",new ArrayList<>());
        database.get("ㅈ").add(new Student.StudentBuilder(21,"정다식","23232").build());
        database.get("ㅈ").add(new Student.StudentBuilder(23,"정나식","23233").build());
        database.get("ㅈ").add(new Student.StudentBuilder(22,"정라식","23234").build());
        database.get("ㅈ").add(new Teacher.TeacherBuilder(29,"정카식","수학").build());
        database.get("ㅈ").add(new Teacher.TeacherBuilder(32,"정과식","기하학").build());
        database.get("ㅈ").add(new Teacher.TeacherBuilder(42,"정구식","영어").build());


    }


    public void run() {

        boolean switch_toggle = true;


        while (switch_toggle) {
            //네비게이션 화면 출력
            ShowMainTemporary.getshow();
            //입력 값
            inputClass = new InputClass();
            int[] menuOps = new int[]{1, 2, 3, 4, 5, 9};
            int result = inputClass.getMainMenuInput(menuOps);

            Actionable spliter =null;

            //입력값에 따른 흐름 분기
            int request_code;


            switch (result) {
                case MAIN_INSERT:
                    spliter = new InserMenutClass(database);
                    request_code = spliter.run();
                    break;
                case MAIN_PRINTOUT:
                    spliter = new PrintOutClass(database);
                    request_code = spliter.run();
                    break;
                case MAIN_MODIFY:
                    spliter = new ModifyInfoClass(database);
                    request_code = spliter.run();
                    break;
                case MAIN_DELETE:
                    spliter = new DeleteClass(database);
                    request_code = spliter.run();
                    break;
                case MAIN_SEARCH:
                    spliter = new SearchClass(database);
                    request_code = spliter.run();
                    break;
                case MAIN_EXIT:
                    switch_toggle = false;
                    break;

            }

        }

        System.out.println("종료되었습니다.");

    }


}
