package me.amplitudo.stanley.web.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;

@Getter
@AllArgsConstructor
public class InvalidCredentialsException extends AbstractThrowableProblem {

    private final transient String code;
    private final transient String description;

}
