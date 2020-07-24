package com.eden.apidocs.controller;

import java.util.List;

public class Result<M, T extends Object> {
    private M message; //消息
    private List<T> list; //列表
}