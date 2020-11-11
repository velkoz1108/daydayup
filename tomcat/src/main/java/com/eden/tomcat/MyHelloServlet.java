package com.eden.tomcat;

import java.io.IOException;

public class MyHelloServlet extends MyServlet {
    protected void doPost(MyRequest request, MyResponse response) {

    }

    protected void doGet(MyRequest request, MyResponse response) throws IOException {
        response.write("Hello ");
    }
}
