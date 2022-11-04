package pl.mmdev.theborders.routing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@RestController
@RequestMapping("/routing")
@RequiredArgsConstructor
@Slf4j
@Validated
class RoutingApi {
    private final BordersCalculator bordersCalculator;

    @GetMapping(
            value = "/{origin}/{destination}",
            produces = "application/json"
    )
    RouteResponse getBorderCrossings(
            @PathVariable @Size(min = 3, max = 3, message = "Origin country cca3 code must be 3 characters long.")
            String origin,
            @PathVariable @Size(min = 3, max = 3, message = "Destination country cca3 code must be 3 characters long.")
            String destination
    ) {
        log.info("Request for route from {} to {}.", origin, destination);
        return new RouteResponse(bordersCalculator.getBorders(origin.toUpperCase(), destination.toUpperCase()));
    }
}
