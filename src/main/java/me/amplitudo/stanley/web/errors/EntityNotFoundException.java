package me.amplitudo.stanley.web.errors;

import org.zalando.problem.AbstractThrowableProblem;

public class EntityNotFoundException extends AbstractThrowableProblem {

    private final transient String entityId;
    private final transient String entityName;

    public EntityNotFoundException(String entityId, String entityName) {
        this.entityId = entityId;
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        return String.format("Entity '%s' with id: '%s' was not found.", entityName, entityId);
    }

}
