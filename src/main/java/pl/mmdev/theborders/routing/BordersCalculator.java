package pl.mmdev.theborders.routing;

import lombok.RequiredArgsConstructor;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Service;
import pl.mmdev.theborders.routing.exception.InvalidRouteArgsException;
import pl.mmdev.theborders.routing.exception.LandRouteNotPossibleException;

import java.util.List;

@Service
@RequiredArgsConstructor
class BordersCalculator {
    private final CountriesGraphService countriesGraphService;

    List<String> getBorders(String origin, String destination) {
        GraphPath<String, DefaultEdge> path;
        try {
            path = countriesGraphService.getPath(origin, destination);
        } catch (IllegalArgumentException e) {
            throw new InvalidRouteArgsException(origin, destination);
        }
        if (path == null) {
            throw new LandRouteNotPossibleException(origin, destination);
        }
        return path.getVertexList();
    }
}
