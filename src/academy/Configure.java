package academy;

import java.util.ArrayList;
import java.util.List;

public class Configure implements Actionable ,CodeInfo{

    private final String status_waiting= "TIMED_WAITING";
    private final static int[] MENUOPTS = {1,2,3,4,5};
    private final static String Title = "Configure";
    private final static int WEIGHT = 600;
    InputClass ip = null;

    /*
    Configure Scenario
    3개의 Thread 생성
    ->3분마다 저장하도록(append :false 형태로 ) toggle -> false면 interrupt
    -> 로그 Visible 여부 toggle
    -> 디버그 ->false면 interrupt


    ->설정은 해당 설정을 번호로 선택 - > Y or N -> Y시 Notify 주기

    Defualt set
    - 5분마다 자동저장
    - 로그 InVisible
    - 디버그로그, 에러로그 사용여부
    //향후 추가
    //- 엔터 음향
    //- 시작 음향
     */

    public Configure() {
        ip = new InputClass(Title);
    }



    @Override
    public int run() {
        boolean toogle = true;

        while (toogle){


            show();
            System.out.println("해당 번호를 누르시면 설정이 변경됩니다.");
            int result = ip.getMenuInput(MENUOPTS);

            switch (result){
                case CONFIGURE_TEMPSAVER:

                    break;
                case CONFIGURE_MUSIC:

                    break;
                case CONFIGURE_LOG:

                    break;
                case CONFIGURE_ENTER_SOUND:

                    break;

                case CONFIGURE_BACK:
                    toogle = false;
                    break;

            }

            if(toogle){

            }


        }


        return CodeInfo.COMPLETE;
    }

    @Override
    public void show() {
        System.out.println("=============\tCurrent Configuration\t=============");
        status();
        System.out.println("=============================================");
    }



    public void status(){
        ThreadManage tm = ThreadManage.getUniqueInstance();
        MusicEnterEffect effect = MusicEnterEffect.getInstance();
        StringBuilder sb = new StringBuilder();
        List<Observer> lst =  tm.getMyThreadSet();
        int i = 1;
        for (Observer obs : lst){
//            System.out.println(obs.getName());
            String tmp = tm.getThread(obs.getName()).getState().toString();
            sb.append( i+++") "+obs.getName()+ " : " + (tmp.equals(status_waiting) ? "On" : "Off")).append("\n");
        }
        sb.append(i+++") " + "EnterEffect :" + (effect.getStatus() ?  "On" : "Off") ).append("\n");
        sb.append(i+++") " + "Log Print :" + (MyLog.getStatus() ?  "On" : "Off") ).append("\n");
        sb.append(i+++") " + "뒤로가기 :" + (MyLog.getStatus() ?  "On" : "Off") ).append("\n");

        System.out.println(sb);

    }



}
