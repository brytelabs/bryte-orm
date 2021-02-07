package org.brytelabs.orm.api;

public interface OrderBy extends Terminable {
    default OrderByBuilder orderAsc(String field) {
        return orderBy(Order.asc(field));
    }

    default OrderByBuilder orderDesc(String field) {
        return orderBy(Order.desc(field));
    }

    default OrderByBuilder orderAsc(Field field) {
        return orderBy(Order.asc(field));
    }

    default OrderByBuilder orderDesc(Field field) {
        return orderBy(Order.desc(field));
    }

    OrderByBuilder orderBy(Order... orders);
}
