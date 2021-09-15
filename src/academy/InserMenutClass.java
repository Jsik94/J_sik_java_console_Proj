package academy;

import java.util.ArrayList;
import java.util.Map;

public class InserMenutClass implements Actionable,CodeInfo{
    final static private String TITLE = "입력";
    final static int[] MENUOPTS = new int[]{1,2,3,4};



    InputClass inputClass ;
    Map<String,ArrayList<Person>> database =null;


    public InserMenutClass(Map<String,ArrayList<Person>> database) {
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
            int result = inputClass.getSubMenuInput(MENUOPTS);
            int request_code = 0 ;
            switch (result+200){
                case INSERT_STUDENT:
                    request_code =insert(INSERT_STUDENT);
                    break;
                case INSERT_TEACHER:
                    request_code =insert(INSERT_TEACHER);
                    break;
                case INSERT_BACK:
                    request_code = COMPLETE;
                    break;
                case MOVE_EXIT:
                    return MOVE_EXIT;
            }

            if(request_code!=CURRENT){
                break;
            }

        }

        return COMPLETE;
    }

    //분리예정 (idea.Person 받아서 instanceof 흐름제어)
    //빌더패턴 특성을 고려햐여 refactoring 필요
    public int insert(int type){
        InputClass ip = new InputClass();
        String title;

        Person newOne;
        if(type ==INSERT_STUDENT){
            title ="학생";
        }else{
            title ="선생님";
        }
        String name, unique;
        int age;
        System.out.println("["+title+" 필수 정보 입력란]");
        System.out.println(title + "나이를 입력하세요");
        age = ip.getInteger();
        System.out.println(title + "이름를 입력하세요");
        name = ip.getString();

        if(type ==INSERT_STUDENT){
            System.out.println(title + "학번를 입력하세요");
            unique = Integer.toBinaryString(ip.getInteger());
            newOne = new Student.StudentBuilder(age,name,unique).build();
        }else{
            System.out.println(title + "과목을 입력하세요");
            unique = ip.getString();
            newOne = new Teacher.TeacherBuilder(age,name,unique).build();
        }



//        System.out.println("선택 정보를 입력하시겠습니까 ? Y or N ");
//        if(!ip.getYorN()){
//            database.add(newOne);
//            return COMPLETE;
//        }
//        //구현 예정 ---
//        System.out.println("["+title+" 선택 정보 입력란]");

        String tag =MyUtil.getTag(newOne.getName());

        if (database.getOrDefault(tag,null)==null){
            database.put(tag,new ArrayList<>());
        }

        (database.get(tag)).add(newOne);



        return CURRENT;
    }


}
