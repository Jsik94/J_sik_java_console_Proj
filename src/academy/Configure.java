package academy;

public class Configure implements Actionable {

    /*
    Configure Scenario
    3개의 Thread 생성
    ->3분마다 저장하도록(append :false 형태로 ) toggle -> false면 interrupt
    -> 로그 Visible 여부 toggle
    -> 디버그 ->false면 interrupt


    ->설정은 해당 설정을 번호로 선택 - > Y or N -> Y시 Notify 주기

    Defualt set
    - 3분마다 자동저장
    - 로그 InVisible
    - 디버그로그, 에러로그 사용여부
    //향후 추가
    //- 엔터 음향
    //- 시작 음향




     */



    @Override
    public int run() {
        return 0;
    }

    @Override
    public void show() {
        System.out.println("=============\tCurrent Configuration\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t\t\t\t\t\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");
    }



    public void status(){
        ThreadManage tm = ThreadManage.getUniqueInstance();


    }

    public void notify_changed(){

    }


}
