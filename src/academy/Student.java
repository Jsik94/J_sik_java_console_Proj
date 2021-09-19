package academy;

public class Student extends Person{

    private String strNumber;
    public final static String[] cols = {"나이","이름","학번","주소","전화 번호","E-mail"};



    private Student(StudentBuilder builder){
        super(builder.age, builder.name, builder.addr, builder.tel, builder.email);
        this.strNumber = builder.strNumber;
        info_cnt+=1;
    }

    public String getStrNumber() {
        return strNumber;
    }

    @Override
    public String[] getColumsInfo() {
        String[] cols = new String[info_cnt];
        int i = 0;
        for(String col : super.getColumsInfo()){
            cols[i++] =col;
        }
        cols[info_cnt-1] = "학번";

        return cols;
    }

    @Override
    public boolean contains(String target) {
        if(super.contains(target)){

            return true;
        }
        if (strNumber.equals(target)){
            return true;
        }
        return false;
    }

    static class StudentBuilder {
        private int age;
        private String name;
        private String strNumber;


        private String addr = "No Address";
        private String tel = "No Phone NUMBER";
        private String email="No Email";

        public StudentBuilder(int age, String name, String strNumber) {
            this.age = age;
            this.name = name;
            this.strNumber = strNumber;
        }

        public StudentBuilder setAddr(String addr) {
            this.addr = addr;
            return this;
        }
        public StudentBuilder setTel(String tel){
            this.tel = tel;
            return this;
        }

        public StudentBuilder setEmail(String email){
            this.email = email;
            return this;
        }

        public Student build(){
            return new Student(this);
        }

    }
}
