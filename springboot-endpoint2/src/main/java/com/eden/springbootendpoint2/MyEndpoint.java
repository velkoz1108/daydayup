package com.eden.springbootendpoint2;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "myEndpoint")
@Component
public class MyEndpoint {

    /**
     * get /actuator/myEndpoint/eden
     *
     * @param name
     * @return
     */
    @ReadOperation
    public String read(@Selector String name) {
        return "this is readOperation -> " + name;
    }

    /**
     * get /actuator/myEndpoint
     *
     * @return
     */
    @ReadOperation
    public String read2() {
        return "this is readOperation2";
    }

    /**
     * delete /actuator/myEndpoint
     *
     * @return
     */
    @DeleteOperation
    public String delete() {
        return "this is deleteOperation";
    }

    /**
     * post /actuator/myEndpoint
     *
     * @return
     */
    @WriteOperation
    public String write() {
        return "this si writeOperation";
    }
}
