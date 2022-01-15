package academy;

public interface Search {

    default String showToString(Person one) {
        StringBuilder sb = new StringBuilder();
        String type = null;
        String uniqueData = null;
        sb.append("분류 : ");
        if (one instanceof Student) {
            sb.append("학생 ");
            type = "학번";
            uniqueData = ((Student) one).getStrNumber();
        } else if (one instanceof Teacher) {
            sb.append("선생님 ");
            type = "과목";
            uniqueData = ((Teacher) one).getSubject();
        }
        sb.append("\n");


        sb.append("이름 : " + one.getName() + " ")
                .append("나이 : " + one.getAge() + " ")
                .append(type + " : " + uniqueData + " ")
                .append("주소 : " + one.getAddr() + " ")
                .append("번호 : " + one.getTel() + " ")
                .append("이메일 : " + one.getEmail() + " ");


        return new String(sb);
    }

    int search();
}
