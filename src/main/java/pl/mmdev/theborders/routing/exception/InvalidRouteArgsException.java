package pl.mmdev.theborders.routing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRouteArgsException extends RuntimeException {
    public InvalidRouteArgsException(String origin, String destination) {
        super(String.format(
                "Invalid origin (%s) or destination (%s), please verify if you pass correct cca3 country codes.",
                origin,
                destination
        ));
    }
}
