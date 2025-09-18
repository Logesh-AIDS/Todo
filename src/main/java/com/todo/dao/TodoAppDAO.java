package com.todo.dao;
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
            PrepareStatement stmt = cnn.prepareStatement(  "SELECT * FROM todos ORCER BY created_at DESC");
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
    
}
