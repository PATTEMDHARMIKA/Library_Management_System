import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Author_Page extends JFrame {
    private JTextField nameField;
    private JTable table;
    private DefaultTableModel model;

    public Author_Page() {
        setTitle("Author Page");
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Authors");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setBounds(200, 10, 150, 30);
        getContentPane().add(lblTitle);

        JLabel lblName = new JLabel("Author Name:");
        lblName.setBounds(30, 60, 120, 25);
        getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(150, 60, 200, 25);
        getContentPane().add(nameField);

        JButton addBtn = new JButton("Add Author");
        addBtn.setBounds(360, 60, 120, 25);
        getContentPane().add(addBtn);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Author ID", "Name"});

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
