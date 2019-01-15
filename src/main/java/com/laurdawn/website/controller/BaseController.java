package com.laurdawn.website.controller;

import com.alibaba.fastjson.JSON;
import com.laurdawn.website.entity.Result;
import com.laurdawn.website.enums.EResultType;

public abstract class BaseController {
    
    @SuppressWarnings("rawtypes")
	protected String retResultData(EResultType type) {
        return JSON.toJSONString(new Result(type));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected String retResultData(EResultType type, Object data) {
        return JSON.toJSONString(new Result(type, data));
    }

    @SuppressWarnings("rawtypes")
	protected String retResultData(Integer code, String message) {
        return JSON.toJSONString(new Result(code, message));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected String retResultData(Integer code, String message, Object data) {
        return JSON.toJSONString(new Result(code, message, data));
    }

}
