package pl.mmdev.theborders.routing;

import lombok.RequiredArgsConstructor;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
class CountriesGraphService {
    private final CountriesProvider countriesProvider;

    private BFSShortestPath<String, DefaultEdge> shortestPath;

    @PostConstruct
    void postConstruct() {
        this.shortestPath = new BFSShortestPath<>(createGraph());
    }

    GraphPath<String, DefaultEdge> getPath(String origin, String destination) {
        return this.shortestPath.getPath(origin, destination);
    }

    private SimpleGraph<String, DefaultEdge> createGraph() {
        SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        countriesProvider.getCountries().forEach(country -> graph.addVertex(country.getCca3()));
        countriesProvider.getCountries().forEach(
                country -> country.getBorders().forEach(
                        neighbour -> graph.addEdge(country.getCca3(), neighbour)));
        return graph;
    }
}
