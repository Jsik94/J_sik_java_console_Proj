package academy;

public class ShowMainTemporary implements DisplayInterface{


    static void getshow(){
        (new ShowMainTemporary()).show();
    }

    @Override
    public void show() {
        System.out.println("=============\t기본 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t1.입력 2.출력 3.수정 4.삭제 5.검색 9.종료\t=");
        System.out.println("=\t6.파일저장\t\t\t\t\t\t\t\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");
    }
}
