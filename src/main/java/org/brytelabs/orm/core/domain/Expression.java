package org.brytelabs.orm.core.domain;

import java.util.List;
import lombok.Getter;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.Sign;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.exceptions.DataAccessException;
import org.brytelabs.orm.utils.SqlUtils;

public record Expression(Sign sign, Field field, Object value) {

  public Expression(Sign sign, String field, Object value) {
    this(sign, Field.with(field), value);
  }

  public String format(Table selectedTable) {
    return String.format(
        "%s%s%s", field.forCondition(selectedTable), sign.getValue(), formatValue());
  }

  private String formatValue() {
    if (sign == Sign.BETWEEN) {
      if (value instanceof List<?> values && values.size() == 2) {
        return SqlUtils.quoteParam(values.get(0)) + " and " + SqlUtils.quoteParam(values.get(1));
      }
      throw new DataAccessException("BETWEEN requires a list with exactly 2 values");
    }
    return SqlUtils.quoteParam(value);
  }
}
