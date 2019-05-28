package uk.co.threeonefour.zorkhateoas.db;

import java.lang.reflect.UndeclaredThrowableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.SimpleTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionOperations;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class GraphTransactionTemplate implements TransactionOperations {

    private static final Logger LOG = LoggerFactory.getLogger(GraphTransactionTemplate.class);

    private final OrientGraph graph;

    public GraphTransactionTemplate(OrientGraph graph) {
        this.graph = graph;
    }

    @Override
    public <T> T execute(TransactionCallback<T> action) throws TransactionException {

        TransactionStatus status = new SimpleTransactionStatus(true);

        /* start transactions if we need to */
        if (!graph.isAutoStartTx()) {
            graph.begin();
        }

        T result;
        try {
            result = action.doInTransaction(status);
        } catch (RuntimeException ex) {
            // Transactional code threw application exception -> rollback
            rollbackOnException(status, ex);
            throw ex;
        } catch (Error err) {
            // Transactional code threw error -> rollback
            rollbackOnException(status, err);
            throw err;
        } catch (Throwable ex) {
            // Transactional code threw unexpected exception -> rollback
            rollbackOnException(status, ex);
            throw new UndeclaredThrowableException(ex, "TransactionCallback threw undeclared checked exception");
        }
        graph.commit();
        return result;
    }

    /**
     * Perform a rollback, handling rollback exceptions properly.
     * 
     * @param status
     *            object representing the transaction
     * @param ex
     *            the thrown application exception or error
     * @throws TransactionException
     *             in case of a rollback error
     */
    private void rollbackOnException(TransactionStatus status, Throwable ex) throws TransactionException {
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
