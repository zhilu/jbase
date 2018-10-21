package shi.boot.extend;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import shi.boot.model.MetaData;

/**
 * 
 * @author mm
 *
 */
public class MetaDataResolver implements HandlerMethodArgumentResolver {
    private static final String TRANSACTION_ID = "id";
    private static final String ACCESS_KEY = "name";
    private List<String> metaDataHeaderNames = Arrays.asList(TRANSACTION_ID, ACCESS_KEY);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(MetaData.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        metaDataHeaderNames.forEach(name -> {
            if (webRequest.getParameter(name) != null) {
                map.put(name, webRequest.getParameter(name));
            }
        });
        return new MetaData(map);
    }
}