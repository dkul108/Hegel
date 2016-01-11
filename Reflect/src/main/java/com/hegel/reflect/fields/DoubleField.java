package com.hegel.reflect.fields;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface DoubleField<C> extends Field<C> {

    static <C> DoubleField<C> wrap(java.lang.reflect.Field field) {
        assert field.getType() == double.class || field.getType() == float.class;
        field.setAccessible(true);
        return () -> field;
    }

    default double getValue(C object) {
        try {
            return toSrc().getDouble(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    default double getValue() {
        assert isStatic();
        return getValue(null);
    }

    @Override
    default String toString(C object) {
        return Double.toString(getValue(object));
    }
}