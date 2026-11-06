package net.aidencooper.vanth.media.persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.jdbc.core.dialect.DialectResolver.JdbcDialectProvider;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcOperations;

public class SqliteDialectProvider implements JdbcDialectProvider {
    @Override
    public Optional<Dialect> getDialect(JdbcOperations operations) {
        return Optional.ofNullable(operations.execute((ConnectionCallback<Dialect> )SqliteDialectProvider::getDialect));
    }
    
    private static Dialect getDialect(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
		String name = metaData.getDatabaseProductName().toLowerCase(Locale.ENGLISH);

        if(name.contains("sqlite")) return SqliteDialect.INSTANCE;
        return null;
    }
}
