package academy;

//data class
public class Person {

    protected static int info_cnt = 5;

    private int age;
    private String name;
    private String addr;
    private String tel;
    private String email;


    public String[] getColumsInfo(){
        String[] cols = new String[]{"나이","이름","주소","전화번호","e-mail"};

        return cols;
    }



    public Person(int age, String name, String addr, String tel, String email) {
        this.age = age;
        this.name = name;
        this.addr = addr;
        this.tel = tel;
        this.email = email;
    }

    public int getInfo_cnt() {
        return info_cnt;
    }

    public boolean contains(String target){
        if(name.equals(target)){
            return true;
        }else if(addr.equals(target)){
            return true;
        }else if(tel.equals(target)){
            return true;
        }else if(email.equals(target)){
            return true;
        }
        return false;
    }

    public boolean contains(int target){
        if(age==target){
            return true;
        }
        return false;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }



}
