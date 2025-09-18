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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoAppDAO {
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

        String query = "SELECT * FROM todos ORDER BY created_at DESC";

        try (Connection cnn = new DatabaseConnection().getDBConnection();
             PreparedStatement stmt = cnn.prepareStatement(query);
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
