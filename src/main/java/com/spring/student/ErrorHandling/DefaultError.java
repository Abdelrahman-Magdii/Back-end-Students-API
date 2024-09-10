package com.spring.student.ErrorHandling;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Map;

@Component
public class DefaultError extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        errorAttributes.put("locale", webRequest.getLocale().toString());
        errorAttributes.put("status", errorAttributes.get("status"));
        errorAttributes.put("message", errorAttributes.get("message"));
        errorAttributes.put("details", Arrays.asList(errorAttributes.get("message")));

        errorAttributes.remove("path");
        errorAttributes.remove("error");
        errorAttributes.remove("trace");

        /**
          {
            "timestamp": "2024-09-04T12:41:07.850+00:00",
            "status": 500,
            "message": "No value present",
            "locale": "en_US",
            "details": [
                "No value present"
                ]
          }*/

        return errorAttributes;
    }
}
