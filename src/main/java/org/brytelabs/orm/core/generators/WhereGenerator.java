package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.core.Expression;
import org.brytelabs.orm.core.SqlValueConverter;
import org.brytelabs.orm.core.builders.ConjunctionBuilderImpl;
import org.brytelabs.orm.core.builders.WhereBuilderImpl;
import org.brytelabs.orm.exceptions.SqlQueryException;

import java.util.List;

public class WhereGenerator implements Generator {
    private final WhereBuilderImpl whereBuilder;

    public WhereGenerator(WhereBuilderImpl whereBuilder) {
        this.whereBuilder = whereBuilder;
    }

    @Override
    public String generate() {
        StringBuilder query = new StringBuilder();
//        whereBuilder.getConjunctionBuilders()
//                .forEach(c -> query.append(conjunctionToString(c)));
        return query.toString();
    }

//    private String conjunctionToString(ConjunctionBuilderImpl conjunction) {
//        if (conjunction == null) {
//            throw  new SqlQueryException("Cannot create query from null conjunction");
//        }
//        Expression expression = conjunction.getExpression();
//        String value = expression.getValue() instanceof List<?>
//                ? SqlValueConverter.quoteList((List<?>) expression.getValue())
//                : SqlValueConverter.quoteParam(expression.getValue());
//
//        return conjunction.getOperation().getValue()
//                + expression.getField()
//                + expression.getSign().getValue()
//                + value;
//    }
}
