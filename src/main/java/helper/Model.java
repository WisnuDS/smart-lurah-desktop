package helper;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Model {
    public abstract String table();
    public abstract String[] columns();
    public Object _class(){
        return this;
    }

    public Object findData(String id){
        try {
            return this.getAllData("WHERE id = " + id).get(0);
        } catch (Exception e){
            return null;
        }
    }

    public List<Object> getAllData(String condition) {
        List<Object> results = new ArrayList<>();
        ResultSet resultSet = execute("SELECT * FROM " + table() + " " + condition);
        System.out.println("SELECT * FROM " + table() + " " + condition);
        try {
            while (resultSet.next()){
                Object o = Class.forName(_class().getClass().getName()).getDeclaredConstructor().newInstance();
                for(String column : columns()){
                    Method m = o.getClass().getDeclaredMethod("set" + formattingColumn(column), String.class);
                    m.invoke(o, resultSet.getString(column));
                }
                results.add(o);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public boolean insert(Map<String, String> params){
        StringBuilder columns = new StringBuilder(table() + "(");
        StringBuilder values = new StringBuilder("VALUES(");
        for(String key : params.keySet()){
            columns.append(String.format("%s,", key));
        }
        columns = new StringBuilder(columns.substring(0, columns.length() - 1));
        columns.append(") ");

        for(String val : params.values()){
            values.append(String.format("%s,", val));
        }
        values = new StringBuilder(values.substring(0, values.length() - 1));
        values.append(") ");

        String sql = "INSERT INTO " + columns + values;
        System.out.println(sql);
        try {
            DBConnection database = new DBConnection();
            Statement statement = database.connection().createStatement();
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(HashMap<String,String> params,String clause){
        StringBuilder sql = new StringBuilder("UPDATE "+table()+" SET ");
        for (String param : params.keySet()){
            sql.append(param).append("=").append(String.format("%s,",params.get(param)));
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(" WHERE ").append(clause);
        System.out.println(sql);
        try {
            DBConnection connection = new DBConnection();
            Statement statement = connection.connection().createStatement();
            return statement.executeUpdate(String.valueOf(sql)) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ResultSet execute(String query) {
        DBConnection database = new DBConnection();
        Connection connection = database.connection();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return resultSet;
    }

    private String formattingColumn(String column){
        String[] columnNames = column.split("_");
        StringBuilder columnName = new StringBuilder();
        for (String name:columnNames){
            columnName.append(StringUtils.capitalize(name));
        }
        return String.valueOf(columnName);
    }
}
