package dontsleep.application.model;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;

import dontsleep.application.Main;
import dontsleep.application.manager.Manager;
import dontsleep.application.model.Anotation.FieldAnotation;
import dontsleep.application.model.Anotation.TableAnotation;

@TableAnotation(tableName = "model")
public class Model {

    public static Manager manager = Main.manager;

    private static <T extends Model> T fromResultSet(ResultSet resultSet, Class<T> clazz) {
        T t = null;
        try {
            t = (T) clazz.getDeclaredConstructor().newInstance();
            for (Field field : t.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(FieldAnotation.class)) {
                    FieldAnotation fieldAnotation = field.getAnnotation(FieldAnotation.class);
                    String fieldName = fieldAnotation.fieldName();
                    field.setAccessible(true);
                    field.set(t, resultSet.getObject(fieldName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T extends Model> T getById(Class<T> clazz, int id) {

        if (!clazz.isAnnotationPresent(TableAnotation.class))
            return null;

        String tableName = clazz.getAnnotation(TableAnotation.class).tableName();
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        ResultSet resultSet = manager.getDatabaseHelper().executeQuery(query, id);
        try {
            if (resultSet.next()) {
                return fromResultSet(resultSet, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T extends Model> ArrayList<T> getAll(Class<T> clazz) {
        ArrayList<T> list = new ArrayList<>();
        if (!clazz.isAnnotationPresent(TableAnotation.class))
            return null;
        String tableName = clazz.getAnnotation(TableAnotation.class).tableName();
        String query = "SELECT * FROM " + tableName + "";
        ResultSet resultSet = manager.getDatabaseHelper().executeQuery(query);
        try {
            while (resultSet.next()) {
                T t = fromResultSet(resultSet, clazz);
                if (t != null)
                    list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T extends Model> ArrayList<T> findBy(Class<T> clazz, String conditions, Object... params) {
        ArrayList<T> list = new ArrayList<>();
        if (!clazz.isAnnotationPresent(TableAnotation.class))
            return null;
        String tableName = clazz.getAnnotation(TableAnotation.class).tableName();
        String query = "SELECT * FROM " + tableName + " WHERE " + conditions;
        ResultSet resultSet = manager.getDatabaseHelper().executeQuery(query, params);
        try {
            while (resultSet.next()) {
                T t = fromResultSet(resultSet, clazz);
                if (t != null)
                    list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert() {
        if (!getClass().isAnnotationPresent(TableAnotation.class))
            return false;
        String tableName = getClass().getAnnotation(TableAnotation.class).tableName();
        String query = "INSERT INTO " + tableName + " SET ";
        ArrayList<Object> params = new ArrayList<>();
        boolean haveField = false;
        Field fieldId = null;
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldAnotation.class)) {
                FieldAnotation fieldAnotation = field.getAnnotation(FieldAnotation.class);
                if (!fieldAnotation.isAutoIncrement()) {
                    String fieldName = fieldAnotation.fieldName();
                    haveField = true;
                    query += fieldName + " = ?, ";
                    try {
                        field.setAccessible(true);
                        params.add(field.get(this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    fieldId = field;
                }
            }
        }
        if (haveField) {
            query = query.substring(0, query.length() - 2);
            int i = manager.getDatabaseHelper().executeUpdate(query, params.toArray());
            if (i > 0) {
                if (haveField) {
                    try {
                        fieldId.setAccessible(true);
                        fieldId.set(this, i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean update() {
        if (!getClass().isAnnotationPresent(TableAnotation.class))
            return false;
        String tableName = getClass().getAnnotation(TableAnotation.class).tableName();
        String query = "UPDATE " + tableName + " SET ";
        ArrayList<Object> params = new ArrayList<>();
        boolean haveField = false;
        Field fieldId = null;
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldAnotation.class)) {
                FieldAnotation fieldAnotation = field.getAnnotation(FieldAnotation.class);
                if (!fieldAnotation.isAutoIncrement()) {
                    String fieldName = fieldAnotation.fieldName();
                    haveField = true;
                    query += fieldName + " = ?, ";
                    try {
                        field.setAccessible(true);
                        params.add(field.get(this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    fieldId = field;
                }
            }
        }
        if (haveField && fieldId != null) {
            try {
                fieldId.setAccessible(true);
                int id = (int) fieldId.get(this);
                query = query.substring(0, query.length() - 2);
                query += " WHERE id = ?";
                params.add(id);
                int i = manager.getDatabaseHelper().executeUpdate(query, params.toArray());
                if (i == 0) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean delete() {
        if (!getClass().isAnnotationPresent(TableAnotation.class))
            return false;
        String tableName = getClass().getAnnotation(TableAnotation.class).tableName();
        String fieldName = "id";
        Field fieldId = null;
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldAnotation.class)) {
                FieldAnotation fieldAnotation = field.getAnnotation(FieldAnotation.class);
                if (fieldAnotation.isAutoIncrement()) {
                    fieldId = field;
                    fieldName = fieldAnotation.fieldName();
                }
            }
        }
        String query = "DELETE FROM " + tableName + " WHERE " + fieldName + " = ?";
        if (fieldId != null) {
            try {
                fieldId.setAccessible(true);
                int i = manager.getDatabaseHelper().executeUpdate(query, fieldId.get(this));
                if (i == 0) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}