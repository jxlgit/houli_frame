package com.houli.common.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.houli.common.config.Constant;
import com.houli.common.domain.LogDO;
import com.houli.common.service.LogService;
import com.houli.common.utils.HttpServletUtils;
import com.houli.common.utils.Result;
import com.houli.common.utils.ShiroUtils;
import com.houli.system.domain.UserDO;

/**
 * 
 * @Description:    TODO(@ControllerAdvice注解拦截异常并统一处理)   
 * @author: jxl     
 * @date:   2018年12月17日 下午4:13:03   
 * @version V1.0
 */
@RestControllerAdvice
public class BDExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LogService logService;

//    /**
//     * 自定义异常
//     */
//    @ExceptionHandler(BDException.class)
//    public Result handleBDException(BDException e) {
//        logger.error(e.getMessage(), e);
//        Result r = new Result();
//        r.put("code", e.getCode());
//        r.put("msg", e.getMessage());
//        return r;
//    }

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request, HttpServletResponse response) {
    	logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
        	//设置消息状态码，便于在js中error中获取
			response.setStatus(Constant.STATUS_NOT_AUTH);
            return Result.error(Constant.STATUS_NOT_AUTH, "未授权");
        }
        return new ModelAndView("error/403");
    }


    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        LogDO logDO = new LogDO();
        logDO.setGmtCreate(new Date());
        logDO.setOperation(Constant.LOG_ERROR);
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(e.toString());
        UserDO current = ShiroUtils.getUser();
        if(null!=current){
            logDO.setUserId(current.getUserId());
            logDO.setUsername(current.getUsername());
        }
        logService.save(logDO);
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
        	//设置消息状态码，便于在js中error中获取
			response.setStatus(Constant.STATUS_SERVER_ERROR);
            return Result.error(Constant.STATUS_SERVER_ERROR, "服务器错误，请联系管理员");
        }
        return new ModelAndView("error/500");
    }
    
}
