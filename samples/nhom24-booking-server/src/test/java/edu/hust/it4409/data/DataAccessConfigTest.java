package edu.hust.it4409.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(DataAccessConfiguration.class)
@ActiveProfiles(
{
    "test", "pg"
})
class DataAccessConfigTest {
    
    @Autowired
    ApplicationContext context;
    @Autowired
    Environment environment;
    
    @Test
    void testContext() {
        
    }
}
