package academy.databaseInf;

public interface DataManagable {

    int seed = (int) (Math.random()*(999-1+1)+1);

    private String makeSerialNums(String name){
        int code  = (int) (System.currentTimeMillis()/seed);

        return Integer.toString(code)+(name.hashCode());
    }

    default String getSerial(String name){
        return makeSerialNums(name);
    }

}
