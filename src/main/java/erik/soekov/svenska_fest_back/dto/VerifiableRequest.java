package erik.soekov.svenska_fest_back.dto;

public interface VerifiableRequest {

    void verifyFields() throws RequestVerificationException;
}
