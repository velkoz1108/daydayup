package com.eden.tomcat;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {
    private OutputStream os;

    public MyResponse(OutputStream os) {
        this.os = os;
    }

    public void write(String s) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200\n")
                .append("Content-Type: application/json;charset=UTF-8\n")
                .append("\r\n")
                .append(s);
        os.write(sb.toString().getBytes());
    }
}
