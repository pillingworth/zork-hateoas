package uk.co.threeonefour.zorkhateoas;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import uk.co.threeonefour.zorkhateoas.db.OrientDbManager;

@Configuration
public class AppConfiguration {

    @Bean
    OrientDbManager orientDbManager() {
        return new OrientDbManager("memory:/hateoas", "admin", "admin");
    }

    @Bean
    Supplier<OrientGraph> orientGraphSupplierNoTx() {
        return orientDbManager().getOrientGraphSupplierNoTx();
    }
    
    @Bean
    Supplier<OrientGraph> orientGraphSupplier() {
        return orientDbManager().getOrientGraphSupplierTx();
    }
    
}
