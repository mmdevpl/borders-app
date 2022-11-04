package pl.mmdev.theborders.routing;

import net.minidev.json.JSONValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.mmdev.theborders.routing.exception.InvalidRouteArgsException;
import pl.mmdev.theborders.routing.exception.LandRouteNotPossibleException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoutingApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return a land route (list of crossed borders) between origin and destination countries.")
    void should_return_land_route() throws Exception {
        mockMvc.perform(get("/routing/POL/ESP"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedPolEspRoute()));
    }

    @Test
    @DisplayName("Should return Bad Request http 400 status, when land route is not possible.")
    void should_return_400_when_land_route_not_possible() throws Exception {
        mockMvc.perform(get("/routing/POL/USA"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LandRouteNotPossibleException));
    }

    @Test
    void should_return_land_route_when_input_not_capitalized() throws Exception {
        mockMvc.perform(get("/routing/Pol/esp"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedPolEspRoute()));
    }

    @Test
    void should_return_error_when_input_data_has_incorrect_cca3_value() throws Exception {
        mockMvc.perform(get("/routing/XXX/ESP"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidRouteArgsException));
    }

    private String expectedPolEspRoute() {
        var expectedResponse = new RouteResponse(List.of("POL", "DEU", "FRA", "ESP"));
        return JSONValue.toJSONString(expectedResponse);
    }
}
