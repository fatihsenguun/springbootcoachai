package com.fatihsengun.handler;

import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.AccessDeniedException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler extends RootResponseEntity {

    private final HttpServletResponse httpServletResponse;

    public GlobalExceptionHandler(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<RootResponseEntity<ApiError<?>>> handleAccessDeniedException(BaseException exception, WebRequest request) {
        return ResponseEntity.badRequest().body(error(createApiError(exception.getMessage(), request, HttpServletResponse.SC_UNAUTHORIZED)));
    }


    @ExceptionHandler(value = {IOException.class, BaseException.class, AuthenticationException.class})
    public ResponseEntity<RootResponseEntity<ApiError<?>>> handleBaseException(BaseException exception, WebRequest request) {
        return ResponseEntity.badRequest().body(error(createApiError(exception.getMessage(), request, null)));
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RootResponseEntity<ApiError<?>>> handleValidationException(MethodArgumentNotValidException exception, WebRequest request) {
        Map<String, List<String>> errorsMap = new HashMap<>();

        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) objectError).getField();
            if (errorsMap.containsKey(fieldName)) {
                errorsMap.put(fieldName, addList(errorsMap.get(fieldName), objectError.getDefaultMessage()));
            } else {
                errorsMap.put(fieldName, addList(new ArrayList<>(), objectError.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(error(createApiError(errorsMap, request, null)));
    }


    public List<String> addList(List<String> list, String newValue) {
        list.add(newValue);
        return list;
    }

    public String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException("hostname not found " + e);
        }
    }

    public <E> ApiError<E> createApiError(E message, WebRequest request, Integer statusCode) {

        ApiError<E> apiError = new ApiError<>();

        apiError.setStatus(statusCode);
        if (statusCode == null) {
            apiError.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        Exception<E> exceptionResponse = new Exception<>();

        exceptionResponse.setHostName(getHostName());
        exceptionResponse.setCreateTime(new Date());
        exceptionResponse.setPath(request.getDescription(false));
        exceptionResponse.setMessage(message);

        apiError.setException(exceptionResponse);
        return apiError;
    }


}
