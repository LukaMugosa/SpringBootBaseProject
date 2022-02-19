package me.amplitudo.stanley.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.amplitudo.stanley.web.errors.BadActionException;
import me.amplitudo.stanley.web.errors.EntityNotFoundException;
import me.amplitudo.stanley.web.errors.ExceptionErrors;
import me.amplitudo.stanley.web.errors.InvalidCredentialsException;
import me.amplitudo.stanley.web.errors.UnauthorizedException;
import me.amplitudo.stanley.web.vm.JsonErrorResponseVM;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@NoArgsConstructor
public class ExceptionUtil {

    private static final String MESSAGE_KEY = "message";

    public static JsonErrorResponseVM setCodeAndDescription(Object object) {
        String code = "";
        String description = "";

        if (object instanceof BadActionException) {
            BadActionException ex = (BadActionException) object;
            code = ex.getCode();
            description = ex.getDescription();
        } else if (object instanceof UnauthorizedException) {
            code = ExceptionErrors.UNAUTHORIZED.getCode();
            description = ExceptionErrors.UNAUTHORIZED.getDescription();
        } else if (object instanceof UsernameNotFoundException) {
            code = ExceptionErrors.USER_DOES_NOT_EXIST.getCode();
            description = ExceptionErrors.USER_DOES_NOT_EXIST.getDescription();
        } else if (object instanceof InvalidCredentialsException) {
            code = ExceptionErrors.INVALID_CREDENTIALS.getCode();
            description = ExceptionErrors.INVALID_CREDENTIALS.getDescription();
        } else if (object instanceof EntityNotFoundException) {
            EntityNotFoundException ex = (EntityNotFoundException) object;
            code = ExceptionErrors.ENTITY_NOT_FOUND.getCode();
            description = ex.getMessage();
        } else {
            log.error("Exception is not registered.");
            code = ExceptionErrors.SERVER_ERROR.getCode();
            description = ExceptionErrors.SERVER_ERROR.getDescription();
        }

        return new JsonErrorResponseVM(code, description);
    }

}
