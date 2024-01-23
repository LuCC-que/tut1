import java.io.IOException;
import java.io.InputStream;

public class HttpRequest {
    private InputStream inputStream;
    private String requestData;

    public HttpRequest(InputStream inputStream) throws IOException {
        try{
            this.inputStream = inputStream;

            byte[] bytes = new byte[1024*10];
            int len = inputStream.read(bytes);

            requestData = new String(bytes, 0, len);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getRemoteURI() {
        int oneSpacing = requestData.indexOf(" ");
        int twoSpacing = requestData.indexOf(" ", oneSpacing + 1);

        return requestData.substring(oneSpacing + 1, twoSpacing).trim();
    }

}
