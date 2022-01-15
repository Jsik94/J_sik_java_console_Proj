package academy;

import java.util.ArrayList;
import java.util.Map;

public class ModifyAll implements Modify {
    private static String TILTE= "ModifyAll";
    Map<String,ArrayList<Person>> database;
    Person target= null;
    InputClass ip;
    public ModifyAll(Map<String, ArrayList<Person>> database, String title,Person target) {
        this.target = target;
        this.database = database;
        ip = new InputClass(TILTE);
    }

    @Override
    public int modify(int code_type) {


        String name,addr,tel,e_mail;
        name = target.getName();
        addr = target.getAddr();
        tel = target.getTel();
        e_mail = target.getEmail();
        int age = target.getAge();

        database.get(MyUtil.getTag(target.getName())).remove(target);

        switch (code_type){
            case CodeInfo.MODI_NAME:
                System.out.println("변경할 이름을 입력 해주세요.");
                name = ip.getString();

                break;
            case CodeInfo.MODI_AGE:
                System.out.println("변경할 나이를 입력 해주세요.");
                age = ip.getInteger();
                break;
            case CodeInfo.MODI_ADDR:
                System.out.println("변경할 주소를 입력 해주세요.");
                addr = ip.getString();

                break;
            case CodeInfo.MODI_TEL:
                System.out.println("변경할 연락처를 입력 해주세요.");
                tel = ip.getString();

                break;
            case CodeInfo.MODI_EMAIL:
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

        return CodeInfo.COMPLETE;
    }


}
