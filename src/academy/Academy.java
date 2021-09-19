package academy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Academy implements CodeInfo {

    /*

    행동을 나눌 필요가 있음
    클래스명 싹다 변경할 것
    Switch로 흐름 제어만하고 결과만 출력하는 클래스들 [이름]Navigator implement Action

    네비게이터에서 값을 받아 해당 일들을 행동하고 반환하는 클래스들 [이름]Activity





     */

    final static private int[] MENUOPTS = new int[]{1, 2, 3, 4, 5, 9};
    Map<String,ArrayList<Person>> database ;

    InputClass inputClass;

    public Academy() {
        database = new HashMap<>();
        /*
            기본적으로 돌아가야할 것
            Address data를 관리하는 입출력 데이터 메모리에 올려둘 것
            로그용 스레드 실행하고 입력시 마다 로그를 만들어 낼 것.
         */

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

            int result = inputClass.getMenuInput(MENUOPTS);

            Actionable spliter =null;

            //입력값에 따른 흐름 분기 나중에 사용
            int request_code;

            //factory로 띄어낼지 말지는 좀 결정하자.
            switch (result) {
                case MAIN_INSERT:
                    spliter = new InserNavigator(database);
//                    request_code = spliter.run();
                    break;
                case MAIN_PRINTOUT:
                    spliter = new PrintOutClass(database);
//                    request_code = spliter.run();
                    break;
                case MAIN_MODIFY:
                    spliter = new ModifyInfoClass(database);
//                    request_code = spliter.run();
                    break;
                case MAIN_DELETE:
                    spliter = new DeleteClass(database);
//                    request_code = spliter.run();
                    break;

                case MAIN_SEARCH:
                    spliter = new SearchClass(database);
//                    request_code = spliter.run();
                    break;
                case MAIN_EXIT:
                    switch_toggle = false;
                    break;


            }
            request_code = spliter.run();
        }

        System.out.println("종료되었습니다.");

    }


}
