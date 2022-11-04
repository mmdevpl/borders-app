package pl.mmdev.theborders.routing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST) // it should be other http status imho
public class LandRouteNotPossibleException extends RuntimeException {
    public LandRouteNotPossibleException(String origin, String destination) {
        super(String.format("No land route is possible between %s and %s", origin, destination));
    }
}
