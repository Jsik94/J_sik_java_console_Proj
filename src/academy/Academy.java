package academy;

import academy.datas.DataGenerator;

import java.util.*;

public class Academy implements CodeInfo ,DisplayInterface{

    /*

    행동을 나눌 필요가 있음
    클래스명 싹다 변경할 것
    Switch로 흐름 제어만하고 결과만 출력하는 클래스들 [이름]Navigator implement Action

    네비게이터에서 값을 받아 해당 일들을 행동하고 반환하는 클래스들 [이름]Activity

     */
    final static private String TITLE = "Academy";
    final static private int[] MENUOPTS = new int[]{1, 2, 3, 4, 5, 6,7, 9};
    Map<String, ArrayList<Person>> database;

    InputClass inputClass;
    ThreadManage tm =null;
    DataTempSaver dts = null;
    MusicOpening mp =null;



    public Academy(boolean isdebug) {


        if (isdebug){
            System.out.println("<-------------- FOR DEBUG ----------------->");
            System.out.println("Academy Constructor For Debug");
            System.out.println("Prepare to turn on Debug Log...");

        }


        MyLog.setStatus(true);
        this.database = new HashMap<>();
        MyUtil.connectDataBase(TITLE, database);
        tm = ThreadManage.getUniqueInstance();
        dts = new DataTempSaver(tm,database);
        new MusicOpening(tm);
        tm.runALL();
        tm.showCurrentThead();
        if(database.size()==0 && isdebug){
            MyLog.d(TITLE,"Load data doesn't exist.");
            MyLog.d(TITLE,"Create date for debug.");
            DataGenerator dg = new DataGenerator(database);
            dg.gen(100);
        }
        MyLog.setStatus(false);

        Thread test = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    System.out.println(Thread.currentThread());
                    Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
                    StringBuilder sb = new StringBuilder();
                    sb.append("\n");
                    for (Thread thread : map.keySet()) {
                        if (!thread.getThreadGroup().getName().equals("MyThread")) continue;
                        sb.append("Name : " + thread.getName() + ((thread.isDaemon()) ? "[Daemon]" : "[Main]")).append("\n");
                        sb.append("\t" + "Group : " + thread.getThreadGroup().getName()).append("\n");
                        sb.append("\t\t" + "ID : " + thread.getId()).append("\n");
                        sb.append("\t\t\t" + "State : " + thread.getState()).append("\n");

                    }
                    System.out.println(sb);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });

        //test.start();

    }


    public void run() {




        boolean switch_toggle = true;

        while (switch_toggle) {
            //네비게이션 화면 출력
            show();
            //입력 값
            inputClass = new InputClass(TITLE);

            int result = inputClass.getMenuInput(MENUOPTS);

            Actionable spliter = null;

            //입력값에 따른 흐름 분기 나중에 사용
            int request_code;

            //factory로 띄어낼지 말지는 좀 결정하자.
            switch (result) {
                case MAIN_INSERT:

                    spliter = new InsertNavigator(database);
//                    request_code = spliter.run();
                    break;
                case MAIN_PRINTOUT:
                    spliter = new PrintOutNavigator(database);
//                    request_code = spliter.run();
                    break;
                case MAIN_MODIFY:
                    spliter = new ModifyNavigator(database);
//                    request_code = spliter.run();
                    break;
                case MAIN_DELETE:
                    spliter = new DeleteNavigator(database);
//                    request_code = spliter.run();
                    break;

                case MAIN_SEARCH:
                    spliter = new SearchNavigator(database);
//                    request_code = spliter.run();
                    break;

                case MAIN_SAVE:
                    spliter = new SaveData(database);
//                    request_code = spliter.run();
                    break;


                case MAIN_SETTING:
                    spliter = new Configure();
                    /*
                            셋팅하나만 만들자
                            1. 입력에 대한 로그 설정
                            2. debug ,error 로그 설정
                            3. 자동 저장 설정 (Thread 이용)

                            확인시 noti로 3개다 한번에 뿌려주자
                     */
                    break;
                case MAIN_EXIT:
                    switch_toggle = false;
                    break;


            }
            if (switch_toggle)
                request_code = spliter.run();
        }



        MyLog.d(TITLE,"App OFF");
        System.out.println("종료되었습니다.");
        System.exit(0);
    }

    @Override
    public void show(){
        System.out.println("=============\t기본 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t1.입력 2.출력 3.수정 4.삭제 5.검색 9.종료\t=");
        System.out.println("=\t6.파일저장\t7.설정\t\t\t\t\t\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");
    }



}
