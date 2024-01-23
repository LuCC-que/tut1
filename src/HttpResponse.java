import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {

    private OutputStream outputStream;
    private HttpRequest request;

    public HttpResponse(OutputStream outputStream, HttpRequest request){
        this.outputStream = outputStream;
        this.request = request;
    }

    public void write(String string){
        try{
            outputStream.write(string.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
