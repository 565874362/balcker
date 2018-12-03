package com.joymates.common.rx.Converter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class FastJsonResponseBodyConvert<T> implements Converter<ResponseBody, T> {

    private Type mType;

    public FastJsonResponseBodyConvert(Type type) {
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        return JSON.parseObject(value.string(), mType);
    }

}