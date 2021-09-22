package academy;

import java.util.ArrayList;
import java.util.Map;

public class SearchTypeFactory extends Factory<Search>{
    Map<String, ArrayList<Person>> database;
    String title;

    public SearchTypeFactory(Map<String, ArrayList<Person>> database,String title) {
        this.database = database;
    }

    @Override
    public Search createInsert(int code_type) {
        Search selector = null;

        switch (code_type){

            case CodeInfo.SEARCH_NAME:
                selector = new SearchKey(database,title);
                break;
            case CodeInfo.SEARCH_AGE:
            case CodeInfo.SEARCH_ADDR:
            case CodeInfo.SEARCH_UNIQUE:
            case CodeInfo.SEARCH_TEL:
            case CodeInfo.SEARCH_EMAIL:
                selector = new SearchValue(database,title,code_type);
                break;

        }

        return selector;
    }
}
