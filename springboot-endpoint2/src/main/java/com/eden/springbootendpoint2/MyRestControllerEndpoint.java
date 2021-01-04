package com.eden.springbootendpoint2;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RestControllerEndpoint(id = "user")
public class MyRestControllerEndpoint {

    @GetMapping("/{name}")
    public String getByName(@PathVariable(name = "name") String name) {
        return "name -> " + name;
    }

    @PostMapping("/save")
    public String save() {
        return "save success";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "delete success";
    }

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
}
