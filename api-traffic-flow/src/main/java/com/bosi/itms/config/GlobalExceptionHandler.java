package com.bosi.itms.config;

import com.bosi.itms.utils.pojo.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhz on 2018/6/27.
 * 全局异常捕获
 */
@Controller
@RequestMapping("/error")
public class GlobalExceptionHandler extends AbstractErrorController {


    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Value("${server.error.path:${error.path:/error}}")
    public static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    //@RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        statusCode = statusCode == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : statusCode;
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = new HashMap<>();
        switch (statusCode){
            case 404:
                break;
            case 500:
                break;
            default:
                statusCode = 500;
                break;
        }
        return new ModelAndView(""+statusCode, model);
    }

    @RequestMapping
    @ResponseBody
    public R defultExcepitonHandler(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        statusCode = statusCode == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : statusCode;
        Map<String, Object> body = new HashMap<>();
        HttpStatus status = getStatus(request);

        String msg = HttpStatus.valueOf(statusCode) + ":" + HttpStatus.valueOf(statusCode).getReasonPhrase();

        if (statusCode != null)
            if(404 == statusCode)
                return R.error(statusCode, msg + ",请求地址‘" + requestUri + "’没有找到！");
            else
                return R.error(statusCode, msg);
        else//未知错误
            return R.error(msg);
    }

}
