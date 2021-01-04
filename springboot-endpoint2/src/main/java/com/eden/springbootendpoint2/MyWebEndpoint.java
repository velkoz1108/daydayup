package com.eden.springbootendpoint2;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

@Component
@WebEndpoint(id = "myWebPoint")
public class MyWebEndpoint {

    /**
     * get /actuator/myWebPoint
     *
     * @return
     */
    @ReadOperation
    public String ping() {
        return "pong";
    }

}
