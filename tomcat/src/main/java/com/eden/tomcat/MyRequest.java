package com.eden.tomcat;

import java.io.IOException;
import java.io.InputStream;

public class MyRequest {
    private InputStream is;
    private String url;
    private String method;

    public MyRequest(InputStream is) {
        this.is = is;

        //拿到HTTP协议内容
        String content = "";
        byte[] buff = new byte[1024];
        int len = 0;
        try {
            if ((len = is.read(buff)) > 0) {
                content = new String(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("content = " + content);

        String line = content.split("\\n")[0];
        String[] arr = line.split("\\s");

        this.method = arr[0];
        this.url = arr[1].split("\\?")[0];

    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
