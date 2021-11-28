package me.amplitudo.stanley.web.errors;

import me.amplitudo.stanley.web.vm.JsonErrorResponseVM;
import me.amplitudo.stanley.util.ExceptionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling, SecurityAdviceTrait {

    @ExceptionHandler
    public ResponseEntity<JsonErrorResponseVM> handleUsernameNotFoundException(UsernameNotFoundException exception, NativeWebRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionUtil.setCodeAndDescription(exception));
    }

    @ExceptionHandler
    public ResponseEntity<JsonErrorResponseVM> handleInvalidCredentialsException(InvalidCredentialsException exception, NativeWebRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionUtil.setCodeAndDescription(exception));
    }

    @ExceptionHandler
    public ResponseEntity<JsonErrorResponseVM> handleBadActionException(BadActionException exception, NativeWebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionUtil.setCodeAndDescription(exception));
    }

    @ExceptionHandler
    public ResponseEntity<JsonErrorResponseVM> handleEntityNotFoundException(EntityNotFoundException exception, NativeWebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionUtil.setCodeAndDescription(exception));
    }



}
