package academy.datas;

import academy.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataGenerator {

    Map<String, ArrayList<Person>> database;

    private  String randomName() {
        List<String> first_Name = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> second_Name = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(first_Name);
        Collections.shuffle(second_Name);
        return first_Name.get(0) + second_Name.get(0) + second_Name.get(1);
    }

    private  int randomAge(){

        return (int) (Math.random()*(100-10+1)+10);
    }

    private  String randomAddress(){
        List<String> si = Arrays.asList("서울특별시","부산광역시","대구광역시","인천광역시","광주광역시","대전광역시","울산광역시","세종특별자치시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주특별자치시");
        List<String> gu = Arrays.asList("종로구","중구","용산구","성동구","광진구","동대문구","중랑구","은평구","노원구","양천구","서대문구","구로구","금천구","영등포구","관악구","서초구","강남구","강동구","강서구","송파구");
        Collections.shuffle(si);
        Collections.shuffle(gu);
        return si.get(0)+gu.get(0);
    }

    private  String randomStrNumber(){
        //8자리 00000000

        return String.format("%04d%04d",(int) (Math.random()*(9999-0+1)),(int)(Math.random()*(9999-0+1)));
    }

    private  String randomSubject(){
        List<String> subject = Arrays.asList("전자공학","소프트웨어","해양잠수학","학혹힉혹학","컴퓨터공학","관상학","전기공학","건축공학","호텔경영학","목탁디자인학","경영학","경제학","사회복지학","유아교육학","영미문화학","심리학");
        Collections.shuffle(subject);
        return subject.get(0);
    }

    private  String randomTel(){


        return String.format("010-%04d-%04d",(int)(Math.random()*(9999-0+1)),(int)(Math.random()*(9999-0+1)));
    }

    private  String randomEmail(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (int)((Math.random()*(18-8+1)+8));i++ ){
            char d = (char) (Math.random()*(122-97+1)+ 97);
            sb.append(d);
        }

        String id ;
        List<String> domainName = Arrays.asList("korea","naver","google","unkown","kakako","tistory");
        List<String> domain = Arrays.asList(".com",".co.kr",".go.kr",".net",".biz",".kr",".org",".ac.kr");

        Collections.shuffle(domainName);
        Collections.shuffle(domain);
        return sb.toString()+"@"+domainName.get(0)+domain.get(0);
    }

    private Student getMakeStudent(){

        Student student = new Student.StudentBuilder(randomAge(),randomName(),randomStrNumber())
                .setAddr(randomAddress())
                .setTel(randomTel())
                .setEmail(randomEmail()).build();

        return student;
    }

    private Teacher getMakeTeacher(){

        Teacher teacher = new Teacher.TeacherBuilder(randomAge(),randomName(),randomSubject())
                .setAddr(randomAddress())
                .setTel(randomTel())
                .setEmail(randomEmail()).build();

        return teacher;
    }

    public DataGenerator(Map<String, ArrayList<Person>> database) {
        MyLog.d("DataGenerator","데이터 생성용 클래스 실행");

        this.database = database;
    }

    public void gen(int num){
        MyLog.d("DataGenerator","데이터 " +num+"개 생성 시작");
        for (int i = 0 ; i < num/10*9;i++){
            Student one = getMakeStudent();
            String tag = MyUtil.getTag(one.getName());

            if(database.get(tag)==null){

                database.put(tag,new ArrayList<Person>());
            }
            database.get(tag).add(one);
        }

        for (int i = 0 ; i < num/10;i++){
            Teacher one = getMakeTeacher();
            String tag = MyUtil.getTag(one.getName());

            if(database.get(tag)==null){
                database.put(tag,new ArrayList<Person>());
            }
            database.get(tag).add(one);
        }

        MyLog.d("DataGenerator","데이터 " +num+"개 생성 완료");
    }

}
