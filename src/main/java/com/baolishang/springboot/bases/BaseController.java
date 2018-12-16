package com.baolishang.springboot.bases;

import com.baolishang.springboot.exceptions.ArgumentException;
import com.baolishang.springboot.exceptions.BaseException;
import com.baolishang.springboot.exceptions.ObjectNotFoundException;
import com.baolishang.springboot.utils.ResponseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;
import javax.validation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by will on 16/9/12.
 */

public class BaseController<M extends Serializable,PK extends Serializable> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final Validator validator;
    protected final ObjectMapper mapper = new ObjectMapper();

    public BaseController() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected Map model2Map(JsonNode node) {
        return mapper.convertValue(node, Map.class);
    }

    protected List model2List(JsonNode node) {
        return mapper.convertValue(node, List.class);
    }

    private <T> T map2Model(Map<String, Object> map, Class<T> clazz) {
        return mapper.convertValue(map, clazz);
    }

    protected <T> void runRequestValidation(Map<String, Object> map, Class<T> clazz) {

        Set<ConstraintViolation<T>> constraintViolations =
                validator.validate(map2Model(map, clazz));

        if (!constraintViolations.isEmpty()) {
            String errorMsg = "";
            for (ConstraintViolation<T> error : constraintViolations) {
                Path path = error.getPropertyPath();
                for (Path.Node node : path) {
                    errorMsg = errorMsg + " Column: " + node.getName()
                            + " Value: " + error.getInvalidValue();
                }
                errorMsg = errorMsg + " " + error.getMessage() + "\n";
            }
            throw new ArgumentException(errorMsg);
        }
    }

    /**
     * 所有业务上的错误返回
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleBaseException(BaseException e) {
        return ResponseUtils.restResponse(
                e.getCode(),
                e.getMessage(),
                e.getStatus()
        );
    }

    /**
     * 所有输入参数错误异常
     * @param e
     * @return
     */
    @ExceptionHandler(ArgumentException.class)
    public ResponseEntity handleException(ArgumentException e) {
        e.printStackTrace();
        return ResponseUtils.restResponse(
                "illegal_argument",
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity handleException(ObjectNotFoundException e) {
        return ResponseUtils.restResponse(
                "not_found",
                e.getMessage() + "未找到",
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * 所有序列化错误
     * @param e
     * @return
     */
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity handleException(JsonProcessingException e) {
        e.printStackTrace();
        return ResponseUtils.restResponse(
                "json_processing_exception",
                "序列化失败",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        return ResponseUtils.restResponse(
                "unknown_error",
                "网络错误,请重新尝试",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleException(NullPointerException e){
        e.printStackTrace();
        return ResponseUtils.restResponse(
                "null_exception",
                "请求参数错误",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * 数据库连接异常
     * @param e
     * @return
     */
    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity handleException(PersistenceException e){
        e.printStackTrace();
        return ResponseUtils.restResponse(
                "database_connection_error",
                "网络错误,请重新尝试",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException e){
        e.printStackTrace();
        return ResponseUtils.restResponse(
                "request_param_error",
                "请求参数错误",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
