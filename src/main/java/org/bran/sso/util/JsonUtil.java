package org.bran.sso.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Auther: BranSummer
 * @Date: 2018-9-25 14:10
 * @Description:
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 序列化
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        String result = null;
        try {
            result = MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("{}序列化失败", data);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 反序列化
     * @param json
     * @param beenType
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String json, Class<T> beenType) {
        T t = null;
        try {
            t= MAPPER.readValue(json,beenType);
        } catch (IOException e) {
            log.error("{}反序列化失败",json);
            e.printStackTrace();
        }
        return t;
    }

}
