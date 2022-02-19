package me.amplitudo.stanley.web.errors;

import me.amplitudo.stanley.util.ExceptionUtil;
import me.amplitudo.stanley.web.vm.ErrorDetailsVM;
import me.amplitudo.stanley.web.vm.JsonErrorResponseVM;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorDetailsVM errorDetailsVM = new ErrorDetailsVM(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorList);
        return handleExceptionInternal(ex, errorDetailsVM, headers, errorDetailsVM.getStatus(), request);
    }

}
