/*package com.todo;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.todo.util.DatabaseConnection;
import com.todo.gui.TodoAppGUI;
public class Main {
        public static void main(String[] args) {
                DatabaseConnection db_Connection = new DatabaseConnection();
                try {
                    Connection cn = db_Connection.getDBConnection();
                    System.out.println("Database connected successfully.");
                } catch (Exception e){
                    System.err.println("Error: Unable to connect to database.");
                    System.exit(1);
                }
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    System.err.println("Error: Unable to set look and feel. " + e.getMessage());
                }
                SwingUtilities.invokeLater(() -> {
                        try{
                                new TodoAppGUI().setVisible(true);
                        }
                        catch(Exception e){
                                System.err.println("Error: Unable to launch application. " + e.getLocalizedMessage());
                        }
                });
        }
}*/
package com.todo;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JOptionPane;

import com.todo.util.DatabaseConnection;
import com.todo.gui.TodoAppGUI;

public class Main {
    public static void main(String[] args) {
        // Test DB connection before launching GUI
        try (Connection cn = new DatabaseConnection().getDBConnection()) {
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            System.err.println("Error: Unable to connect to database. " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Database connection failed!\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Warning: Unable to set system look and feel. " + e.getMessage());
        }

        // Launch GUI in Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                new TodoAppGUI().setVisible(true);
            } catch (Exception e) {
                System.err.println("Error: Unable to launch application. " + e.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Application failed to start!\n" + e.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

