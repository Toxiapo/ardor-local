// Auto generated code, do not modify
package nxt.http.callers;

import nxt.http.APICall;

public class GenerateFileTokenCall extends APICall.Builder<GenerateFileTokenCall> {
    private GenerateFileTokenCall() {
        super("generateFileToken");
    }

    public static GenerateFileTokenCall create() {
        return new GenerateFileTokenCall();
    }

    public GenerateFileTokenCall file(byte[] b) {
        return parts("file", b);
    }
}
