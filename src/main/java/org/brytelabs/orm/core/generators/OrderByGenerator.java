package org.brytelabs.orm.core.generators;

import java.util.stream.Collectors;
import org.brytelabs.orm.api.OrderByBuilder;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.builders.OrderByBuilderImpl;
import org.brytelabs.orm.exceptions.SqlQueryException;

public class OrderByGenerator implements Generator {
  private final OrderByBuilderImpl orderByBuilder;
  private final Table fromTable;

  public OrderByGenerator(OrderByBuilder orderByBuilder, Table fromTable) {
    this.orderByBuilder = (OrderByBuilderImpl) orderByBuilder;
    this.fromTable = fromTable;
  }

  @Override
  public void validate() throws SqlQueryException {
    if (orderByBuilder.getOrders().isEmpty()) {
      throw new SqlQueryException("No order by fields specified");
    }
  }

  @Override
  public String generate() {
    return "order by "
        + orderByBuilder.getOrders().stream()
            .map(
                order ->
                    order.field().forCondition(fromTable)
                        + " "
                        + order.direction().getValue())
            .collect(Collectors.joining(", "));
  }
}
