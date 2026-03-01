package com.fatihsengun.entity;

import com.fatihsengun.handler.ApiError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RootResponseEntity<T> {

    private boolean result;

    private ApiError<?> errorMessage;

    private T data;

    public static <T> RootResponseEntity<T> ok(T data) {
        RootResponseEntity<T> rootResponseEntity = new RootResponseEntity<>();
        rootResponseEntity.setResult(true);
        rootResponseEntity.setErrorMessage(null);
        rootResponseEntity.setData(data);
        return rootResponseEntity;
    }

    public static <T> RootResponseEntity<T> error(ApiError<?> errorMessage) {
        RootResponseEntity<T> rootResponseEntity = new RootResponseEntity<>();
        rootResponseEntity.setData(null);
        rootResponseEntity.setResult(false);
        rootResponseEntity.setErrorMessage(errorMessage);
        return rootResponseEntity;
    }
}
