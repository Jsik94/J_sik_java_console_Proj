package academy;

//data class
public class Person {

    protected static int info_cnt = 5;

    private int age;
    private String name;
    private String addr;

    private String tel;
    private String email;

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
