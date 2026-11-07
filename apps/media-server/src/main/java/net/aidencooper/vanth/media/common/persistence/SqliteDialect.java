package net.aidencooper.vanth.media.common.persistence;

import org.springframework.data.relational.core.dialect.AbstractDialect;
import org.springframework.data.relational.core.dialect.IdGeneration;
import org.springframework.data.relational.core.dialect.LimitClause;
import org.springframework.data.relational.core.dialect.LockClause;
import org.springframework.data.relational.core.sql.IdentifierProcessing;
import org.springframework.data.relational.core.sql.LockOptions;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.data.relational.core.sql.IdentifierProcessing.LetterCasing;
import org.springframework.data.relational.core.sql.IdentifierProcessing.Quoting;

public class SqliteDialect extends AbstractDialect {
    public static final SqliteDialect INSTANCE = new SqliteDialect();
    
    private static final IdGeneration ID_GENERATION = new IdGeneration() {
        @Override 
        public boolean sequencesSupported() {
		    return false;
	    }

        @Override
		public String createSequenceQuery(SqlIdentifier sequenceName) {
			throw new UnsupportedOperationException(
                "Sequence generation for %s dialect is not supported."
                .formatted(SqliteDialect.class.getSimpleName())
            );
		}
    };

    private static final IdentifierProcessing IDENTIFIER_PROCESSING = IdentifierProcessing.create(Quoting.ANSI, LetterCasing.LOWER_CASE);

    private static final LockClause LOCK_CLAUSE = new LockClause() {
        @Override
        public Position getClausePosition() {
            return Position.AFTER_ORDER_BY;
        }

        @Override
        public String getLock(LockOptions lockOptions) {
            return "";
        }
    };

    private static final LimitClause LIMIT_CLAUSE = new LimitClause() {

        @Override
        public Position getClausePosition() {
            return Position.AFTER_ORDER_BY;
        }

        @Override
        public String getLimit(long limit) {
            return "LIMIT " + limit;
        }

        @Override
        public String getLimitOffset(long limit, long offset) {
            return "LIMIT " + limit + " OFFSET " + offset;
        }

        @Override
        public String getOffset(long offset) {
            throw new UnsupportedOperationException(
                "OFFSET clause without the LIMIT clause for %s dialect is not supported."
                .formatted(SqliteDialect.class.getSimpleName())
            );
        }
    };

    @Override
    public IdGeneration getIdGeneration() {
        return ID_GENERATION;
    }

    @Override
    public IdentifierProcessing getIdentifierProcessing() {
        return IDENTIFIER_PROCESSING;
    }

    @Override
    public LockClause lock() {
        return LOCK_CLAUSE;
    }

    @Override
    public LimitClause limit() {
        return LIMIT_CLAUSE;
    }
}
