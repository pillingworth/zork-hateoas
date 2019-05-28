package uk.co.threeonefour.zorkhateoas.db;

import java.util.function.Supplier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

public class OrientDbManager {

//    private static final Logger LOG = LoggerFactory.getLogger(OrientDbManager.class);

    private final String url;
    private final String username;
    private final String password;

    private OrientGraphFactory orientGraphFactoryNoTx;
    private OrientGraphFactory orientGraphFactoryTx;

    public OrientDbManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @PostConstruct
    private void init() {
        this.orientGraphFactoryTx = setupNewConnectionPool(true);
        this.orientGraphFactoryNoTx = setupNewConnectionPool(false);
    }

    private OrientGraphFactory setupNewConnectionPool(boolean requiresTransaction) {
        OrientGraphFactory factory = new OrientGraphFactory(url, username, password);
        factory.setupPool(1, 10);
        factory.setRequireTransaction(requiresTransaction);
        return factory;
    }

    @PreDestroy
    public void close() {
        if (orientGraphFactoryTx != null) {
            orientGraphFactoryTx.close();
        }
        if (orientGraphFactoryNoTx != null) {
            orientGraphFactoryNoTx.close();
        }
    }

    public Supplier<OrientGraph> getOrientGraphSupplierTx() {
        return () -> {
            try {
                return orientGraphFactoryTx.getTx();
            } catch (RuntimeException re) {
                throw new DatabaseUnavailableException(re);
            }
        };
    }

    public Supplier<OrientGraph> getOrientGraphSupplierNoTx() {
        return () -> {
            try {
                return orientGraphFactoryNoTx.getTx();
            } catch (RuntimeException re) {
                throw new DatabaseUnavailableException(re);
            }
        };
    }

    public Supplier<ODatabaseDocumentTx> getOrientDocumentSupplierTx() {
        return () -> {
            try {
                return orientGraphFactoryTx.getDatabase();
            } catch (RuntimeException re) {
                throw new DatabaseUnavailableException(re);
            }
        };
    }
}
