package academy;

import java.util.ArrayList;
import java.util.Map;

public class ModifyInfoClass extends SearchTool implements Actionable,CodeInfo {
    final static private String TITLE = "수정";
    final static private int[] MENUOPTS = new int[]{1,2,3,4,5,6};
    final static int GO_BACK = 304;

    public ModifyInfoClass(Map<String,ArrayList<Person>> database) {
        super(database);

    }

    @Override
    public int run() {
        Person target = findPersonByName(TITLE);
        if (target==null){
            return GO_BACK;
        }
        show();

        database.get(MyUtil.getTag(target.getName())).remove(target);

        int result = ip.getMenuInput(MENUOPTS);
        split(result,target);

        System.out.println("수정 되었습니다.");
        return COMPLETE;
    }

    @Override
    public void show() {
        System.out.println("=============\t서브 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t\t\t1.이름 2.나이 3.주소 4.학번\t\t=");
        System.out.println("=\t\t\t5.번호 6.이메일 7.뒤로가기\t\t\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");
    }

    private void split(int result,Person target){


        String name,addr,tel,e_mail;
        name = target.getName();
        addr = target.getAddr();
        tel = target.getTel();
        e_mail = target.getEmail();

        int age = target.getAge();
        switch (result+300){
            case MODI_NAME:
                System.out.println("변경할 이름을 입력 해주세요.");
                name = ip.getString();

                break;
            case MODI_AGE:
                age = ip.getInteger();
                break;
            case MODI_ADDR:
                System.out.println("변경할 주소를 입력 해주세요.");
                addr = ip.getString();

                break;
            case MODI_TEL:
                System.out.println("변경할 연락처를 입력 해주세요.");
                tel = ip.getString();

                break;
            case MODI_EMAIL:
                System.out.println("변경할 메일을 입력 해주세요.");
                e_mail = ip.getString();

                break;
        }

        if (target instanceof Student){
            target = new Student.StudentBuilder(age,name, ((Student) target).getStrNumber())
                    .setAddr(addr)
                    .setEmail(e_mail)
                    .setTel(tel)
                    .build();

        }else if (target instanceof Teacher){
            target = new Student.StudentBuilder(age,name, ((Teacher) target).getSubject())
                    .setAddr(addr)
                    .setEmail(e_mail)
                    .setTel(tel)
                    .build();
        }

        if (database.get(MyUtil.getTag(name))==null){
            database.put(MyUtil.getTag(name),new ArrayList<>());
        }
        database.get(MyUtil.getTag(name)).add(target);

    }




}
