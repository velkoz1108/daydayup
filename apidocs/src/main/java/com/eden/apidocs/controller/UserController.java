package com.eden.apidocs.controller;

import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 */
@RequestMapping("/api/user/")
@RestController
public class UserController {

    /**
     * 用户列表
     *
     * @param page     页数
     * @param pageSize 每页条数
     */
    @ApiDoc(UserVO[].class)
    @RequestMapping(path = "/u/list", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    public void list(@RequestParam(value = "page", required = true) Integer page, int pageSize) {

    }

    /**
     * 用户信息
     *
     * @param userId 用户id
     */
    @ApiDoc(UserVO.class)
    @GetMapping(path = "/u/info")
    public void userInfo(@RequestParam String userId, @PathVariable("Hello") Integer hello) {

    }


//    /**
//     * 获取用户列表2
//     *
//     * @param page 页数
//     * @return
//     */
//    @ApiDoc
//    @PostMapping(path = "/u/list-new")
//    public Result<String, UserVO> getUserList(Integer page) {
//        return null;
//    }
//
//    /**
//     * 获取用户列表[无类型信息]
//     *
//     * @param page 页数
//     * @return
//     */
//    @ApiDoc
//    @PostMapping(path = "/u/list-new-notype")
//    public Result getUserList3(Integer page) {
//        return null;
//    }

    /**
     * 消息列表
     *
     * @return
     */
    @ApiDoc
    @PostMapping(path = "/u/message-list")
    public String[] getMessage() {
        return null;
    }

}
