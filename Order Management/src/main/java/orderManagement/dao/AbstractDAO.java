package orderManagement.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import orderManagement.connection.ConnectionFactory;

/*
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http ://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private Class<T> type;

    /**
     * Instantiates a new Abstract dao.
     *
     * @param tipClasa specify the type of the DAO
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> tipClasa) {
        type = tipClasa;
        System.out.println(this.type);
    }
    /**
     * Create Select Querry for a given field
     *
     * @param field the field by witch we do the select
     * @return the String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM schooldb.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " = ?");
        return sb.toString();
    }

    /**
     * Create Select Querry for a all field
     *
     *
     * @return the String
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM schooldb.");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Create Insert Querry for a given type
     *
     * @param myT the type for which we want to make the insertion
     * @return the String
     */
    private String createInsertQuery(T myT) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" INTO schooldb.");
        sb.append(type.getSimpleName());
        sb.append(" ( ");
        for(Field field : type.getDeclaredFields()){
            sb.append(field.getName() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" ) ");
        sb.append(" VALUES ");
        sb.append(" ( ");
        for(Field field : type.getDeclaredFields()){
            field.setAccessible(true);
            try {
                sb.append("\'" + field.get(myT) + "\',");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" ) ");
        return sb.toString();
    }

    /**
     * Create Delete Querry for a given type
     *
     * @param myT the type for which we want to make the deletion
     * @return the String
     */
    private String createDeleteQuery(T myT) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM schooldb.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ");
        try {
            Field field = type.getDeclaredField("id");
            field.setAccessible(true);
            sb.append(field.get(myT));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Find all array list.
     *
     * @return the array list
     */
    public ArrayList<T> findAll() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            ArrayList<T> myList = createObjects(resultSet);
            if(myList.size() > 0) return myList.get(0);
            return null;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Creates Array list for a given resultSet
     *
     * @param resultSet the result set
     * @return the Array List
     */
    private ArrayList<T> createObjects(ResultSet resultSet) {
        ArrayList<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Insert int.
     *
     * @param t the t
     * @return the int
     */
    public int insert(T t) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(t);
        //System.out.println(query);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Delete int.
     *
     * @param t the t
     * @return the int
     */
    public int delete(T t) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(t);
        //System.out.println(query);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Update int.
     *
     * @param t the t
     * @return the int
     */
    public int update(T t) {
        delete(t);
        return insert(t);
    }
}

