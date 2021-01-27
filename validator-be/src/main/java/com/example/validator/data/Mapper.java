package com.example.validator.data;

import java.util.HashMap;
import java.util.Map;

public class Mapper {

    public static Map<String, String> codeToCountry = new HashMap<String, String>()
    {{
        put("237", "Cameroon");
        put("251", "Ethiopia");
        put("212", "Morocco");
        put("258", "Mozambique");
        put("256", "Uganda");
    }};

    public static Map<String, String> countryToCode = new HashMap<String, String>()
    {{
        put("Cameroon", "(237)");
        put("Ethiopia", "(251)");
        put("Morocco", "(212)");
        put("Mozambique", "(258)");
        put("Uganda", "(256)");
    }};

    public static Map<String, String> codeToRegex = new HashMap<String, String>()
    {{
        put("237", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        put("251", "\\(251\\)\\ ?[1-59]\\d{8}$");
        put("212", "\\(212\\)\\ ?[5-9]\\d{8}$");
        put("258", "\\(258\\)\\ ?[28]\\d{7,8}$");
        put("256", "\\(256\\)\\ ?\\d{9}$");
    }};
}
