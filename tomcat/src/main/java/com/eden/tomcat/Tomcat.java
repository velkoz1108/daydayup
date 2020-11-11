package com.eden.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Tomcat {
    private int port;
    private Map<String, MyServlet> servletMapping = new HashMap();

    public Tomcat(int port) {
        this.port = port;
        servletMapping.put("/hello", new MyHelloServlet());
        servletMapping.put("/hello2", new MyHelloServlet());
    }

    public void start() throws IOException {
        //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Tomcat Started!!! The port is " + port);
        while (true) {
            Socket client = serverSocket.accept();
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

            //7、Request(InputStrean)/Response(OutputStrean)
            MyRequest request = new MyRequest(is);
            MyResponse response = new MyResponse(os);

            //5、从协议内容中拿到URL，把相应的Servlet用反射进行实例化
            String url = request.getUrl();

            if (servletMapping.containsKey(url)) {
                //6、调用实例化对象的service()方法，执行具体的逻辑doGet/doPost方法
                servletMapping.get(url).service(request, response);
            } else {
                response.write("404 - Not Found");
            }


            os.flush();
            os.close();

            is.close();
            client.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new Tomcat(9090).start();

    }
}
