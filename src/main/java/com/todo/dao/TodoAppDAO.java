/*package com.todo.dao;
import com.todo.model.Todo;
import java.util.List;
import java.sql.Connection;
import com.todo.util.DatabaseConnection;
import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TodoAppDAO {
    public List<Todo> getAllTodos() throws SQLDataException {
        List<Todo> todos = new ArrayList<>();
        try(Connection cnn = new DatabaseConnection().getDBConnection();
            PrepareStatement stmt = cnn.prepareStatement(  "SELECT * FROM todos ORDER BY created_at DESC");
            ResultSet res = stmt.getResultSet();
        ){
            while(res.next()){
                Todo todo = new Todo();
                todo.setId(res.getInt("id"));
                todo.setTitle(res.getString("title"));
                todo.setDescription(res.getString("description"));
                todo.setCompleted(res.getBoolean("completed"));
                LocalDateTime created_at = res.getTimestamp("created_at").toLocalDateTime();

                todo.setCreated_at(created_at);
                LocalDateTime updated_at = res.getTimestamp("updated_at").toLocalDateTime();
                todo.setUpdated_at(updated_at);
                todos.add(todo);
            }
            
        }
        return todos;
    } 
    
}*/

package com.todo.dao;

import com.todo.model.Todo;
import com.todo.util.DatabaseConnection;

import java.sql.*;
/*import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class TodoAppDAO {
    private static final String SELECT_ALL_TODOS = "SELECT * FROM todos ORDER BY created_at DESC";
    private static final String INSERT_TODOS = "INSERT INTO todos (title, description, completed, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_TODO_BY_ID = "SELECT * FROM todos WHERE id = ?";
    private static final String UPDATE_TODO = "UPDATE todos SET title = ?, description = ?, completed = ?, updated_at = ? WHERE id = ?";
    private static final String DELETE_TODO = "DELETE FROM todos WHERE id = ?";
    private static final String FILTER_TODO = "SELECT * FROM todos WHERE completed = ? ORDER BY created_at DESC";
    //create a new todo
    public int createTodo(Todo todo) throws SQLException {
        //String qquery = "INSERT INTO todos (title, description, completed, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = 0;

        try (Connection cnn = new DatabaseConnection().getDBConnection();
             PreparedStatement stmt = cnn.prepareStatement(INSERT_TODOS,Statement.RETURN_GENERATED_KEYS/*qquery*/)) {

            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.isCompleted());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(todo.getCreated_at()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(todo.getUpdated_at()));

            rowsAffected = stmt.executeUpdate();
            if(rowsAffected==0){
                throw new SQLException("Creating todo failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    todo.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating todo failed, no ID obtained.");
                }
            }
        }
        return rowsAffected;
    }
    public Todo getTodoById(int todoId) throws SQLException {
        /*String query = "SELECT * FROM todos WHERE id = ?";*/
//Todo todo = null;
        try (Connection cnn =  new DatabaseConnection().getDBConnection();
             PreparedStatement stmt = cnn.prepareStatement(SELECT_TODO_BY_ID/*query*/)) {

            stmt.setInt(1, todoId);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return gettodoRow(res);
                }
            }
        }
        return null;
    }
    public List<Todo> filterTodo(boolean isCompleted) throws SQLException {
        List<Todo> todos = new ArrayList<>();
        try(Connection cnn = new DatabaseConnection().getDBConnection();
        PreparedStatement stmt = cnn.prepareStatement(FILTER_TODO);)
        {
            stmt.setBoolean(1, isCompleted);
            try(ResultSet res =  stmt.executeQuery()){
                while(res.next()){
                    todos.add(gettodoRow(res));
                }
                return todos;
            }
        }
    }
    public boolean deleteTodo(int todoId) throws SQLException {
        try (Connection cnn = new DatabaseConnection().getDBConnection();
             PreparedStatement stmt = cnn.prepareStatement(DELETE_TODO)) {

            stmt.setInt(1, todoId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }


    }
    public boolean updateTodo(Todo todo) throws SQLException {
        try(Connection cnn = new DatabaseConnection().getDBConnection();
            PreparedStatement stmt = cnn.prepareStatement(UPDATE_TODO)){
                stmt.setString(1, todo.getTitle());
                stmt.setString(2, todo.getDescription());
                stmt.setBoolean(3, todo.isCompleted());
                stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                stmt.setInt(5, todo.getId());

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
        }
    }
    private Todo gettodoRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        boolean completed = rs.getBoolean("completed");
        LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();

        Todo todo = new Todo(id, title, description, completed, created_at, updated_at);
        return todo;

    }

    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();

        //String query = "SELECT * FROM todos ORDER BY created_at DESC";

        try (Connection cnn = new DatabaseConnection().getDBConnection();
             PreparedStatement stmt = cnn.prepareStatement(SELECT_ALL_TODOS/*query*/);
             ResultSet res = stmt.executeQuery()) {

            while (res.next()) {
                /*Todo todo = new Todo();
                todo.setId(res.getInt("id"));
                todo.setTitle(res.getString("title"));
                todo.setDescription(res.getString("description"));
                todo.setCompleted(res.getBoolean("completed"));

                LocalDateTime created_at = res.getTimestamp("created_at").toLocalDateTime();
                todo.setCreated_at(created_at);

                LocalDateTime updated_at = res.getTimestamp("updated_at").toLocalDateTime();
                todo.setUpdated_at(updated_at);

                todos.add(todo);*/
                todos.add(gettodoRow(res));
            }
        }
        return todos;
    }
}
