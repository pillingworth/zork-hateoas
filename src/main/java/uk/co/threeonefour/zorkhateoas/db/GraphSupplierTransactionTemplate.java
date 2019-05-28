package uk.co.threeonefour.zorkhateoas.db;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionSystemException;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class GraphSupplierTransactionTemplate {

    private static final Logger LOG = LoggerFactory.getLogger(GraphSupplierTransactionTemplate.class);

    private final Supplier<OrientGraph> graphSupplier;

    public GraphSupplierTransactionTemplate(final Supplier<OrientGraph> graphSupplier) {
        this.graphSupplier = graphSupplier;
    }

    public <R> R execute(Function<OrientGraph, R> function) throws TransactionException {
        
        //final OrientGraph graph = graphFactory.getTx();
        final OrientGraph graph = graphSupplier.get();
        
        try {

            /* start transactions if we need to */
            if (!graph.isAutoStartTx()) {
                graph.begin();
            }

            R result;
            try {
                result = function.apply(graph);
            } catch (RuntimeException ex) {
                // Transactional code threw application exception -> rollback
                rollbackOnException(graph, ex);
                throw ex;
            } catch (Error err) {
                // Transactional code threw error -> rollback
                rollbackOnException(graph, err);
                throw err;
            } catch (Throwable ex) {
                // Transactional code threw unexpected exception -> rollback
                rollbackOnException(graph, ex);
                throw new UndeclaredThrowableException(ex, "TransactionCallback threw undeclared checked exception");
            }
            graph.commit();
            return result;
        } finally {
            graph.shutdown();
        }
    }

    /**
     * Perform a rollback, handling rollback exceptions properly.
     * 
     * @param graph
     *            the graph
     * @param ex
     *            the thrown application exception or error
     * @throws TransactionException
     *             in case of a rollback error
     */
    private void rollbackOnException(OrientGraph graph, Throwable ex) throws TransactionException {
        LOG.debug("Initiating transaction rollback on application exception", ex);
        try {
            graph.rollback();
        } catch (TransactionSystemException ex2) {
            LOG.error("Application exception overridden by rollback exception", ex);
            ex2.initApplicationException(ex);
            throw ex2;
        } catch (RuntimeException ex2) {
            LOG.error("Application exception overridden by rollback exception", ex);
            throw ex2;
        } catch (Error err) {
            LOG.error("Application exception overridden by rollback error", ex);
            throw err;
        }
    }

    public static final class Void {

        public static final Void INSTANCE = new Void();
    }
}
