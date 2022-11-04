package pl.mmdev.theborders.routing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@Getter
class CountriesProvider {
    @Value("${countries.file-name}")
    private String countriesFileName;
    private List<Country> countries;

    @PostConstruct
    void postConstruct() {
        countries = loadCountries();
    }

    private List<Country> loadCountries() {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            Country[] result = objectMapper.readValue(
                    new ClassPathResource(countriesFileName).getInputStream(),
                    Country[].class);
            return List.of(result);
        } catch (IOException e) {
            throw new RuntimeException("Countries source file not found.", e);
        }
    }
}
