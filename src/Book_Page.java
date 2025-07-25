import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Book_Page extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTextField searchField;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Book_Page frame = new Book_Page();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Book_Page() {
        setTitle("Book Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Library Books");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(350, 10, 200, 40);
        contentPane.add(lblTitle);

        searchField = new JTextField();
        searchField.setBounds(30, 60, 200, 25);
        contentPane.add(searchField);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(240, 60, 100, 25);
        contentPane.add(btnSearch);

        JButton btnAdd = new JButton("Add Book");
        btnAdd.setBounds(350, 60, 100, 25);
        contentPane.add(btnAdd);

        JButton btnBorrow = new JButton("Borrow");
        btnBorrow.setBounds(460, 60, 100, 25);
        contentPane.add(btnBorrow);

        JButton btnReturn = new JButton("Return");
        btnReturn.setBounds(570, 60, 100, 25);
        contentPane.add(btnReturn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 100, 820, 400);
        contentPane.add(scrollPane);

        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID", "Name", "ISBN", "Category", "Author", "Publisher", "Available"
            }
        );

        table = new JTable(model);
        scrollPane.setViewportView(table);

        loadAllBooks();

        btnSearch.addActionListener(e -> searchBooks());
        btnAdd.addActionListener(e -> addBook());
        btnBorrow.addActionListener(e -> borrowBook());
        btnReturn.addActionListener(e -> returnBook());
    }

    private void loadAllBooks() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "Dharmika944@")) {
            model.setRowCount(0);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("isbn"),
                    rs.getString("category"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getBoolean("available") ? "Yes" : "No"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading books: " + e.getMessage());
        }
    }

    private void searchBooks() {
        String keyword = searchField.getText();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "Dharmika944@")) {
            model.setRowCount(0);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM books WHERE name LIKE ? OR isbn LIKE ? OR category LIKE ?");
            String likeKeyword = "%" + keyword + "%";
            pst.setString(1, likeKeyword);
            pst.setString(2, likeKeyword);
            pst.setString(3, likeKeyword);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("isbn"),
                    rs.getString("category"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getBoolean("available") ? "Yes" : "No"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error during search: " + e.getMessage());
        }
    }

    private void addBook() {
        JTextField nameField = new JTextField();
        JTextField isbnField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField publisherField = new JTextField();

        Object[] inputFields = {
            "Name:", nameField,
            "ISBN:", isbnField,
            "Category:", categoryField,
            "Author:", authorField,
            "Publisher:", publisherField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Add New Book", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "Dharmika944@")) {
                PreparedStatement pst = con.prepareStatement("INSERT INTO books(name, isbn, category, author, publisher, available) VALUES (?, ?, ?, ?, ?, true)");
                pst.setString(1, nameField.getText());
                pst.setString(2, isbnField.getText());
                pst.setString(3, categoryField.getText());
                pst.setString(4, authorField.getText());
                pst.setString(5, publisherField.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book added successfully!");
                loadAllBooks();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error adding book: " + e.getMessage());
            }
        }
    }

    private void borrowBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a book to borrow.");
            return;
        }
        int id = (int) model.getValueAt(selectedRow, 0);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "Dharmika944@")) {
            PreparedStatement pst = con.prepareStatement("UPDATE books SET available = false WHERE id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book borrowed successfully!");
            loadAllBooks();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error borrowing book: " + e.getMessage());
        }
    }

    private void returnBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a book to return.");
            return;
        }
        int id = (int) model.getValueAt(selectedRow, 0);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "Dharmika944@")) {
            PreparedStatement pst = con.prepareStatement("UPDATE books SET available = true WHERE id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book returned successfully!");
            loadAllBooks();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error returning book: " + e.getMessage());
        }
    }
}
