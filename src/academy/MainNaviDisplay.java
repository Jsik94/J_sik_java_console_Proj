package academy;

public class MainNaviDisplay implements DisplayInterface{

    final static private String TITLE = "기본 메뉴 설정";
    final static private String BORDER_STYLE = "=";

//    @Override
//    public void show(int menu_cnt, String[] menus) {
//        StringBuilder sb = new StringBuilder();
//
//        int width = MyUtil.calculate_width(menu_cnt, menus);
//
//        sb.append(getTopBottomLine(width));
//        sb.append(getSideLine(width));
//
//        sb.append(getSideLine(width));
//        sb.append(getTopBottomLine(width));
//    }

    @Override
    public void show() {

    }

    //메인 메뉴 디스플레이
    private String getTopBottomLine(int width){


        StringBuilder sb = new StringBuilder();
        //2는 title 사이 공백임
        int start = (width - (TITLE.length() -4))/2;


        for (int i = 1 ; i <= (width - (TITLE.length() -2)) ; i++){
            sb.append(BORDER_STYLE);
        }

        sb.insert(start-1,TITLE).append("\n");

        return new String(sb);
    }


    //사이드 메뉴 디스플레이
    private String getSideLine(int width){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < width ;i++){
            if(i ==0 || i == width-1){
                sb.append(BORDER_STYLE);
            }else{
                sb.append(" ");
            }
        }


        return new String(sb.append("\n"));
    }

}
