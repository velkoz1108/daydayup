package com.eden.tomcat;

import java.io.IOException;

public abstract class MyServlet {
    public void service(MyRequest request, MyResponse response) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            try {
                doGet(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            doPost(request, response);
        }
    }

    protected abstract void doPost(MyRequest request, MyResponse response);

    protected abstract void doGet(MyRequest request, MyResponse response) throws IOException;
}
