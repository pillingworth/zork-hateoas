package uk.co.threeonefour.zorkhateoas.db;

import java.util.function.Function;
import java.util.function.Supplier;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class GraphSupplierTemplate {
    
    private final Supplier<OrientGraph> graphSupplier;

    public GraphSupplierTemplate(final Supplier<OrientGraph> graphSupplier) {
        this.graphSupplier = graphSupplier;
    }

    public <R> R execute(Function<OrientGraph, R> function) {

        final OrientGraph graph = graphSupplier.get();
        
        try {
            return function.apply(graph);
        } finally {
            graph.shutdown();
        }
    }
}
