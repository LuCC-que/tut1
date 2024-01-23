import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


public class HttpProcessor {
    private static final String ROOT_DIR = System.getProperty("user.dir") + File.separator;

    public void process(HttpRequest request, HttpResponse response) throws IOException {

        String uri = request.getRemoteURI();
        System.out.println("URI: " + uri);

        File file = new File(ROOT_DIR, uri);

        if (file.exists()) {
            response.write("HTTP/1.1 200 OK\r\n");
            response.write("Content-Length: " + file.length() + "\r\n\r\n");
            response.write(readFileToString(file));
        } else {
            file = new File(ROOT_DIR, "404.html");
            response.write("HTTP/1.1 404 Not Found\r\n");
            response.write("Content-Length: " + file.length() + "\r\n\r\n");
            response.write(readFileToString(file));
        }
    }

    private String readFileToString(File file) throws IOException {

        byte[] bytes = Files.readAllBytes(file.toPath());
        return new String(bytes, StandardCharsets.UTF_8);
    }

}

