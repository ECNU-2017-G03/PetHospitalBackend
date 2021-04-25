package com.ecnu.g03.pethospital.advicer;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.exception.AuthenticationException;
import com.ecnu.g03.pethospital.exception.NoDataException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Shen Lei
 * @date 2021/4/25 23:33
 */
@ControllerAdvice
public class ExceptionAdvicer {

    @ExceptionHandler(value = AuthenticationException.class)
    public BaseResponse noDataException(AuthenticationException exception) {
        BaseResponse response = new BaseResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(ResponseStatus.AUTHORIZATION_ERROR);
        return response;
    }

    @ExceptionHandler(value = NoDataException.class)
    public BaseResponse noDataException(NoDataException exception) {
        BaseResponse response = new BaseResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(ResponseStatus.NO_DATA);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse exception(Exception exception) {
        BaseResponse response = new BaseResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(ResponseStatus.UNKNOWN_ERROR);
        return response;
    }

}
