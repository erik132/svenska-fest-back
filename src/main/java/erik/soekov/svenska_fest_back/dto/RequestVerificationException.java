package erik.soekov.svenska_fest_back.dto;

public class RequestVerificationException extends Exception{
    public RequestVerificationException(String msg){
        super(msg);
    }
}
