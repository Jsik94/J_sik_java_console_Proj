package academy;

import academy.datas.DataGenerator;

import java.util.*;

public class Academy implements CodeInfo {

    /*

    행동을 나눌 필요가 있음
    클래스명 싹다 변경할 것
    Switch로 흐름 제어만하고 결과만 출력하는 클래스들 [이름]Navigator implement Action

    네비게이터에서 값을 받아 해당 일들을 행동하고 반환하는 클래스들 [이름]Activity

     */
    final static private String TITLE = "Academy";
    final static private int[] MENUOPTS = new int[]{1, 2, 3, 4, 5, 6, 9};
    Map<String, ArrayList<Person>> database;

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
        System.out.println("Academy Constructor For Debug");
        System.out.println("Prepare to turn on Debug Log...");
        ThreadManage tm = ThreadManage.getUniqueInstance();

        tm.addAction(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                System.out.println("끝3!");
            }
        });

        tm.addAction(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("끝2!");
            }
        });
        tm.addAction(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("끝!");
            }
        });
        tm.runALL();
        tm.showCurrentThead();
        MyLog.setStatus(true);
        FileReaderClass frc = FileReaderClass.getInstance();
        String[] data = frc.toStringLine();
        this.database = new HashMap<>();
        connectDataBase(database);
        if(database.size()==0){

            DataGenerator dg = new DataGenerator(database);
            dg.gen(100);
        }
        MyLog.setStatus(false);

    }


    public void run() {




        boolean switch_toggle = true;

        while (switch_toggle) {
            //네비게이션 화면 출력
            ShowMainTemporary.getshow();
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
                    spliter = new PrintOutClass(database);
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

                case MAIN_EXIT:
                    switch_toggle = false;
                    break;


            }
            request_code = spliter.run();
        }



        System.out.println("종료되었습니다.");

    }

    private void connectDataBase(Map<String, ArrayList<Person>> database) {
        MyLog.d(TITLE, "Loading data in Memory...");
        String[] datas = FileReaderClass.getInstance().toStringLine();
        Person one;
        try {

            for (String data : datas) {
                StringTokenizer stk = new StringTokenizer(data, "\t");
                if (stk.nextToken().equals("Student")) {
                    String name = stk.nextToken().trim();
                    String age = stk.nextToken().trim();
                    String strNum = stk.nextToken().trim();
                    String tel = stk.nextToken().trim();
                    String addr = stk.nextToken().trim();
                    String email = stk.nextToken().trim();
                    one = new Student.StudentBuilder(Integer.parseInt(age), name, strNum)
                            .setTel(tel)
                            .setAddr(addr)
                            .setEmail(email)
                            .build();

                } else {
                    String name = stk.nextToken().trim();
                    String age = stk.nextToken().trim();
                    String sub = stk.nextToken().trim();
                    String tel = stk.nextToken().trim();
                    String addr = stk.nextToken().trim();
                    String email = stk.nextToken().trim();
                    one = new Teacher.TeacherBuilder(Integer.parseInt(age), name, sub)
                            .setTel(tel)
                            .setAddr(addr)
                            .setEmail(email)
                            .build();

                }
                String tag = MyUtil.getTag(one.getName());

                if (database.get(tag) == null) {

                    database.put(tag, new ArrayList<Person>());
                }
                database.get(tag).add(one);

            }
        } catch (NoSuchElementException e) {
            MyLog.e(TITLE, "Fail to load data :" + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            MyLog.e(TITLE, "Fail to load data :" + e.getMessage());
            return;
        }

        MyLog.d(TITLE, "Complete to load data !");

    }


}
