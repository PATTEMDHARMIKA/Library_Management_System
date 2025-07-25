import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Category_Page extends JFrame {
    private JTextField nameField;
    private JTable table;
    private DefaultTableModel model;

    public Category_Page() {
        setTitle("Category Page");
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Categories");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setBounds(180, 10, 150, 30);
        getContentPane().add(lblTitle);

        JLabel lblName = new JLabel("Category Name:");
        lblName.setBounds(30, 60, 120, 25);
        getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(150, 60, 200, 25);
        getContentPane().add(nameField);

        JButton addBtn = new JButton("Add Category");
        addBtn.setBounds(360, 60, 120, 25);
        getContentPane().add(addBtn);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Category ID", "Name"});

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 100, 430, 200);
        getContentPane().add(scrollPane);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(190, 320, 100, 25);
        getContentPane().add(backBtn);

        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                model.addRow(new Object[]{model.getRowCount() + 1, name});
                nameField.setText("");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new Book_Details_Page().setVisible(true);
        });
    }
}
