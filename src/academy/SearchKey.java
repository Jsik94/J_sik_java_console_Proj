package academy;

import java.util.ArrayList;
import java.util.Map;

public class SearchKey implements Search {
    final static private String TITLE = "SearchKey";
    Map<String, ArrayList<Person>> database;
    InputClass ip ;
    String title;

    public SearchKey(Map<String, ArrayList<Person>> database, String title) {
        this.database = database;
        this.title = title;
        ip = new InputClass(TITLE);
    }

    @Override
    public int search() {
        ArrayList<Person> result = findPersonByName(title);
        StringBuilder sb = new StringBuilder();

        if(result==null){
            return CodeInfo.SEARCH_FAIL;
        }


        System.out.println(result.size()+ "명 검색 되었습니다 !");

        for (Person one : result){
            sb.append(showToString(one));
        }

        System.out.println(sb);

        return CodeInfo.COMPLETE;
    }

    protected ArrayList<Person> findPersonByName(String title) {
        ArrayList<Person> result= new ArrayList<>();
        System.out.println(title + "될 사람의 이름을 입력 해주세요.");

        String name = ip.getString();

        if (database.get(MyUtil.getTag(name)) != null) {

            for (Person data : database.get(MyUtil.getTag(name))) {
                if (data.getName().equals(name))
                    result.add(data);
            }
            if (!result.isEmpty()){
                return result;
            }
        }

        //갬색된 이름이 없는 경우
        System.out.println(name + "님에 대한 정보는 없습니다.");
        return null;
    }




}
