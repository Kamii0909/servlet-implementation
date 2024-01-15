package edu.hust.it4409;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.hust.it4409.booking.BookingConfig;
import edu.hust.it4409.common.jackson.JacksonConfiguration;
import edu.hust.it4409.data.DataAccessConfiguration;
import edu.hust.it4409.data.injector.FakeDataInjectorConfiguration;
import edu.hust.it4409.recommend.RecommendConfig;

@Configuration
@Import({
    JacksonConfiguration.class,
    BookingConfig.class,
    RecommendConfig.class,
    DataAccessConfiguration.class,
    FakeDataInjectorConfiguration.class
})
@SpringBootConfiguration
public class ServerConfiguration {
}
