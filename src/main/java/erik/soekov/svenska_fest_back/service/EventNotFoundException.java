package erik.soekov.svenska_fest_back.service;

public class EventNotFoundException extends Exception{

    public static String DEFAULT_MSG = "The event could not be found. Check the given id.";

    public EventNotFoundException(String msg){
        super(msg);
    }

    public EventNotFoundException(){
        super(DEFAULT_MSG);
    }
}
