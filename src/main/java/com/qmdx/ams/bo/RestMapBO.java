package com.qmdx.ams.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestMapBO {

    private static final String ERROR = "error";
    private static final String REASON = "reason";
    private static final String OK = "ok";
    private static final int SUCCEED = 0;
    private static final int FAILER = -1;

    /**
     * restMap：Default reply successful
     * @param
     * @return: java.util.Map
     */
    public static Map<String, Object> getRestMap() {
        Map <String, Object> restMap = new HashMap<>();
        restMap.put(ERROR, SUCCEED);
        restMap.put(REASON, OK);
        return restMap;
    }

    /**
     * When an error occurs, modify the restMap reply state
     * @param message
     * @return: void
     */
    public static void setErrorRestMap(Map <String, Object> restMap, String message) {
        restMap.put(ERROR, FAILER);
        restMap.put(REASON, message);
    }

}
