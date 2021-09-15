package academy;

public class Teacher extends Person{
    private String subject;


    private Teacher(TeacherBuilder builder){
        super(builder.age, builder.name, builder.addr, builder.tel, builder.email);
        this.subject = builder.subject;
        info_cnt+=1;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public boolean contains(String target) {
         if(super.contains(target)){
            return true;
         }

         if (subject.equals(target)){
            return true;
        }
         return false;
    }

    static class TeacherBuilder {
        private int age;
        private String name;
        private String subject;


        private String addr ="No Address";
        private String tel = "No Phone NUMBER";
        private String email="No Email";

        public TeacherBuilder(int age, String name, String subject) {
            this.age = age;
            this.name = name;
            this.subject = subject;
        }

        public TeacherBuilder setAddr(String addr) {
            this.addr = addr;
            return this;
        }

        public TeacherBuilder setTel(String tel){
            this.tel = tel;
            return this;
        }

        public TeacherBuilder setEmail(String email){
            this.email = email;
            return this;
        }



        public Teacher build(){
            return new Teacher(this);
        }

    }
}
