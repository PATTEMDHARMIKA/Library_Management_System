import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login_Page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JCheckBox showPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Login_Page frame = new Login_Page();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Login_Page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Library Login");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(140, 20, 250, 40);
		contentPane.add(lblTitle);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(50, 100, 100, 25);
		contentPane.add(lblUsername);

		usernameField = new JTextField();
		usernameField.setBounds(160, 100, 200, 25);
		contentPane.add(usernameField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(50, 150, 100, 25);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(160, 150, 200, 25);
		contentPane.add(passwordField);

		showPassword = new JCheckBox("Show Password");
		showPassword.setBounds(160, 180, 200, 20);
		contentPane.add(showPassword);
		showPassword.addActionListener(e -> {
			if (showPassword.isSelected()) {
				passwordField.setEchoChar((char) 0);
			} else {
				passwordField.setEchoChar('*');
			}
		});

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(0, 128, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(160, 210, 90, 30);
		contentPane.add(btnLogin);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(0, 128, 255));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(270, 210, 90, 30);
		contentPane.add(btnCancel);

		// Action listener for login
		btnLogin.addActionListener(e -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());

			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter both username and password.");
				return;
			}

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/library_management_system", "root", "Dharmika944@");

				// Insert new user into table
				PreparedStatement insert = con.prepareStatement(
						"INSERT INTO login_page (Username, Password) VALUES (?, ?)");
				insert.setString(1, username);
				insert.setString(2, password);
				insert.executeUpdate();

				JOptionPane.showMessageDialog(null, "Login Successful!");

				// Open Book Details Page
				new Book_Details_Page().setVisible(true);
				dispose(); // Close login page

				con.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnCancel.addActionListener(e -> System.exit(0));
	}
}
