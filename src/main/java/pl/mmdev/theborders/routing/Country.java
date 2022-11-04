package pl.mmdev.theborders.routing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
class Country {
    private String cca3;
    private List<String> borders;
}
