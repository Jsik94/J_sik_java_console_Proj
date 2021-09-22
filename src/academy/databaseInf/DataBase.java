package academy.databaseInf;

public class DataBase implements DataManagable{







    @Override
    public String getSerial(String name) {


        return DataManagable.super.getSerial(name);
    }
}
