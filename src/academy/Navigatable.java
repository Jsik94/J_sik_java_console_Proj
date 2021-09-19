package academy;

public interface Navigatable {


    abstract class Factory{


        abstract Navigatable createFactory(int type_code);
    }

}
