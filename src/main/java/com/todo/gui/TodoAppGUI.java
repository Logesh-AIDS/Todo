/*package com.todo.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.todo.dao.TodoAppDAO;
import com.todo.model.Todo;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
public class TodoAppGUI extends JFrame{
    private TodoAppDAO todoDAO;
    private JTable todoTable;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckBox;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JComboBox<String> filterComboBox;
    public TodoAppGUI() {
        this.todoDAO = new TodoAppDAO();

        

    }
    private void initializeComponents() {
        setTitle("Todo Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        String[] columnNames = {"ID", "Title", "Description", "Completed", "Created At", "Updated At"};
        tableModel = new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        todoTable = new JTable(tableModel);
        todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoTable.getSelectionModel().addListSelectionListener(
            e->{
                if(!e.getValueIsAdjusting()){
                    //loadSelectedTodo();
                }
            }
        );

        titleField = new JTextField(20);
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        completedCheckBox = new JCheckBox( "Completed");
        addButton = new JButton("Add Todo");
        updateButton = new JButton("Update Todo");
        deleteButton = new JButton("Delete Todo");
        refreshButton = new JButton("Refresh");
        String[] filterOptions = {"All", "Completed", "pending"};
        filterComboBox = new JComboBox<>(filterOptions);
        filterComboBox.addActionListener(
            (e)->{
                //filterTodos();
            }
        );

    }
    //////////////////*private void setupLayout(){
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("Title"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(titleField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Description"), gbc);
        gbc.gridx = 1;
        inputPanel.add(descriptionArea, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(completedCheckBox, gbc);
        
        add(inputPanel, BorderLayout.NORTH);
    }//////////////////
    private void setupLayout() {
        setLayout(new BorderLayout());

        // Input panel for title, description, completed checkbox
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(descriptionArea), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(completedCheckBox, gbc);

        // Button panel for Add, Update, Delete, Refresh
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        // Filter panel for filter label and combo box
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterComboBox);

        // North panel to combine filter, input, and button panels
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(filterPanel, BorderLayout.NORTH);
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);
        add(new JScrollPane(todoTable), BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(new JLabel("Status:"));
        add(statusPanel, BorderLayout.SOUTH);
    }

    private void setupEventListeners() {
        addButton.addActionListener((e)-> {addTodo();});
        updateButton.addActionListener((e)-> {updateTodo();});
        deleteButton.addActionListener((e)-> {deleteTodo();});
        refreshButton.addActionListener((e)-> {refreshTodo();});
        filterComboBox.addActionListener((e)-> {filterTodos();});
    }
    private void addTodo() {

    }
    private void updateTodo() {

    }
    private void deleteTodo() {

    }
    private void refreshTodo() {

    }
    private void filterTodos() {

    }
    private void loadTodos() {
        List<Todo> todos = todoDAO.getAllTodos();
        tableModel.setRowCount(0);
    }
}
*/
package com.todo.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.todo.dao.TodoAppDAO;
import com.todo.model.Todo;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TodoAppGUI extends JFrame {
    private TodoAppDAO todoDAO;
    private JTable todoTable;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckBox;
    private JButton addButton, updateButton, deleteButton, refreshButton;
    private JComboBox<String> filterComboBox;
    private JLabel statusLabel;

    public TodoAppGUI() {
        this.todoDAO = new TodoAppDAO();
        initializeComponents();
        setupLayout();
        setupEventListeners();
        loadTodos(); // initial load
    }

    private void initializeComponents() {
        setTitle("Todo Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Title", "Description", "Completed", "Created At", "Updated At"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        todoTable = new JTable(tableModel);
        todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoTable.getSelectionModel().addListSelectionListener(
            e->{
                if(!e.getValueIsAdjusting()){
                    loadSelectedTodo();
                }
            }
        );

        titleField = new JTextField(20);
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        completedCheckBox = new JCheckBox("Completed");
        addButton = new JButton("Add Todo");
        updateButton = new JButton("Update Todo");
        deleteButton = new JButton("Delete Todo");
        refreshButton = new JButton("Refresh");

        String[] filterOptions = {"All", "Completed", "Pending"};
        filterComboBox = new JComboBox<>(filterOptions);
        filterComboBox.addActionListener((e)-> {
            filterTodos();
        });

        statusLabel = new JLabel("Ready");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(descriptionArea), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        inputPanel.add(completedCheckBox, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        // Filter
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterComboBox);

        // North Panel
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(filterPanel, BorderLayout.NORTH);
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);
        add(new JScrollPane(todoTable), BorderLayout.CENTER);

        // Status bar
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(new JLabel("Status:"));
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);
    }

    private void setupEventListeners() {
        addButton.addActionListener(e -> addTodo());
        updateButton.addActionListener(e -> updateTodo());
        deleteButton.addActionListener(e -> deleteTodo());
        refreshButton.addActionListener(e -> loadTodos());
        filterComboBox.addActionListener(e -> filterTodos());
    }

    // === DB Actions ===
    private void addTodo() {
        // TODO: Call DAO insert method (not implemented yet)
        //statusLabel.setText("Add Todo clicked.");
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        boolean completed = completedCheckBox.isSelected();
        if(title.isEmpty()){
            JOptionPane.showMessageDialog(this, "Title cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            Todo todo = new Todo(title, description/* , completed*/);
            todo.setCompleted(completed);
            todoDAO.createTodo(todo);
            JOptionPane.showMessageDialog(this, "Todo added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTodos(); // Refresh table
            
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Error adding todo: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


    }

    private void updateTodo() {
       int row =  todoTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please select a todo to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String title = titleField.getText().trim();
        if(title.isEmpty()){
            JOptionPane.showMessageDialog(this, "Title cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) todoTable.getValueAt(row, 0);
        try{
            Todo todo = new Todo();
            if(todo!=null){
                todo.setId(id);
                todo.setTitle(title);
                todo.setDescription(descriptionArea.getText().trim());
                todo.setCompleted(completedCheckBox.isSelected());
                if(todoDAO.updateTodo(todo)) {
                    JOptionPane.showMessageDialog(this, "Todo updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTodos();
                } else {
                    JOptionPane.showMessageDialog(this, "Todo update failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error updating todo: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /*statusLabel.setText("Update Todo clicked.");*/
    }

    private void deleteTodo() {
        int row = todoTable.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(this,"please select a todo to delete.","Selection Error",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) todoTable.getValueAt(row, 0);
        
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this todo?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            try{
                if(todoDAO.deleteTodo(id)){
                    JOptionPane.showMessageDialog(this,"Todo deleted successfully!","success",JOptionPane.INFORMATION_MESSAGE);
                    titleField.setText("");
                    descriptionArea.setText("");
                    completedCheckBox.setSelected(false);
                    loadTodos();
                }
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error deleting todo: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    }

    private void filterTodos() {
        String filter = (String) filterComboBox.getSelectedItem();
        try {
            List<Todo> todos = todoDAO.getAllTodos();
            if (filter.equals("Completed")) {
                todos.removeIf(todo -> !todo.isCompleted());
            } else if (filter.equals("Pending")) {
                todos.removeIf(Todo::isCompleted);

            }
            updateTable(todos);
            //statusLabel.setText("Filtered to " + filter + " todos.");
        } catch (SQLException ex) {
            /*ex.printStackTrace();
            statusLabel.setText("Error filtering todos: " + ex.getMessage());*/
            JOptionPane.showMessageDialog(this, "Error filtering todos: " + ex.getMessage(), " Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTodos() {
        try {
            List<Todo> todos = todoDAO.getAllTodos();
            updateTable(todos);
            
            /*tableModel.setRowCount(0); // clear table

            for (Todo todo : todos) {
                tableModel.addRow(new Object[]{
                        todo.getId(),
                        todo.getTitle(),
                        todo.getDescription(),
                        todo.isCompleted(),
                        todo.getCreated_at(),
                        todo.getUpdated_at()
                });
            }*/
            //statusLabel.setText("Loaded " + todos.size() + " todos.");
        } 
        catch (SQLException ex) {
            /*ex.printStackTrace();
            statusLabel.setText("Error loading todos: " + ex.getMessage());*/
            JOptionPane.showMessageDialog(this, "Error loading todos: " + ex.getMessage(), " Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSelectedTodo() {
        int row = todoTable.getSelectedRow();
        if (row != -1) {
            String title = (String) tableModel.getValueAt(row, 1);
            String description = (String) tableModel.getValueAt(row, 2);
            String completed = tableModel.getValueAt(row, 3).toString();
            titleField.setText(title);
            descriptionArea.setText(description);
            completedCheckBox.setSelected(Boolean.parseBoolean(completed));
        }
    }
    private void updateTable(List<Todo> todos){
        tableModel.setRowCount(0);
        for(Todo todo : todos){
            Object[] row = {
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted(),
                todo.getCreated_at(),
                todo.getUpdated_at()
            };
            tableModel.addRow(row);
        }
    }
}
