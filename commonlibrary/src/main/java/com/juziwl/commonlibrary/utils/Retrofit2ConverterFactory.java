package com.juziwl.commonlibrary.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/05/10
 * @description 上传的json字符串，返回的也是json字符串
 */
public class Retrofit2ConverterFactory extends Converter.Factory {
    public static Retrofit2ConverterFactory create() {
        return new Retrofit2ConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JsonResponseBodyConverter<String>();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new JsonRequestBodyConverter<String>();
    }

    final class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

        JsonRequestBodyConverter() {

        }

        public RequestBody convert(T value) throws IOException {
            return RequestBody.create(MEDIA_TYPE, value.toString());
        }
    }

    final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        JsonResponseBodyConverter() {

        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            return (T) value.string();
        }
    }
}
