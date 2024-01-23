import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

//            Socket socket = serverSocket.accept();
//            InputStream inputStream = socket.getInputStream();
//
//            byte[] bytes = new byte[1024*10];
//            int len = inputStream.read(bytes);
//
//            String requestData = new String(bytes, 0, len);
//            System.out.println(requestData);
public class HttpServer {

    public void HttpServerStart(){
        try{
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("start listening at 1234");


            for(int i = 0; i < 10; ++i) {
                FrontDeck fd = new FrontDeck(serverSocket);
                new Thread(fd).start();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


//for(int i = 0; i < 10; ++i) {
//FrontDeck fd = new FrontDeck(serverSocket);
//                new Thread(fd).start();
//            }
class FrontDeck implements Runnable{
    private final ServerSocket serverSocket;
    public FrontDeck(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run(){
        while(true){
            try{
                Socket socket = serverSocket.accept();
                HttpRequest httpRequest = new HttpRequest(socket.getInputStream());
                HttpResponse httpResponse = new HttpResponse(socket.getOutputStream(), httpRequest);

                new HttpProcessor().process(httpRequest, httpResponse);

                try {
                    sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }

                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}