package org.brytelabs.orm.core.domain;

import java.util.List;
import lombok.Getter;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.Sign;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.exceptions.DataAccessException;
import org.brytelabs.orm.utils.SqlUtils;

@Getter
public class Expression {
  private final Sign sign;
  private final Field field;
  private final Object value;

  public Expression(Sign sign, String field, Object value) {
    this(sign, Field.with(field), value);
  }

  public Expression(Sign sign, Field field, Object value) {
    this.sign = sign;
    this.field = field;
    this.value = value;
  }

  public String format(Table selectedTable) {
    return String.format(
        "%s%s%s", field.forCondition(selectedTable), sign.getValue(), formatValue());
  }

  @SuppressWarnings("unchecked")
  private String formatValue() {
    if (sign == Sign.BETWEEN) {
      if (!(value instanceof List)) {
        throw new DataAccessException("between requires a list");
      }
      List<Object> values = (List<Object>) value;
      if (values.size() != 2) {
        throw new DataAccessException(
            "between received list with size " + values.size() + " but requires 2");
      }
      return SqlUtils.quoteParam(values.get(0)) + " and " + SqlUtils.quoteParam(values.get(1));
    }
    return SqlUtils.quoteParam(value);
  }
}
