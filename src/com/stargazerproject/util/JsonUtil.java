package com.stargazerproject.util;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public class JsonUtil {

    public static StringBuffer cacheToJson(Optional<Cache<String, String>> cache, Optional<String> unitName){
        StringBuffer jsonResult = new StringBuffer();
        jsonResult.append("\"" + unitName.get() + "\":{");

        cache.get().entrySet().get().forEach(result -> jsonResult.append("\""+result.getKey()+"\":\""+result.getValue()+"\","));
        jsonResult.deleteCharAt(jsonResult.lastIndexOf(","));

        jsonResult.append("}");
        return jsonResult;
    }

    public static StringBuffer StringToJson(Optional<String> name, Optional<String> Value){
        StringBuffer jsonResult = new StringBuffer();
        return jsonResult.append("\"" + name.get() + "\":" + "\"" +Value.get() + "\"");
    }

}
