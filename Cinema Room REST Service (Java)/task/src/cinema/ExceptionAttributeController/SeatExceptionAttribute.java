package cinema.ExceptionAttributeController;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class SeatExceptionAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.remove("error");
        errorAttributes.remove("status");
        errorAttributes.remove("timestamp");
        errorAttributes.remove("path");
        String errorMessage = errorAttributes.get("message").toString();
        errorAttributes.putIfAbsent("error", errorMessage);
        errorAttributes.remove("message");
        return errorAttributes;
    }
}
