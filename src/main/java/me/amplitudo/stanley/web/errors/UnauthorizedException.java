package me.amplitudo.stanley.web.errors;

import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;

@Getter
public class UnauthorizedException extends AbstractThrowableProblem {

    private final transient String code;
    private final transient String description;

    public UnauthorizedException(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
