import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Book_Details_Page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLibraryManagementSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book_Details_Page frame = new Book_Details_Page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Book_Details_Page() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtLibraryManagementSystem = new JTextField();
		txtLibraryManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLibraryManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		txtLibraryManagementSystem.setText("Library Management System");
		txtLibraryManagementSystem.setBounds(70, 20, 350, 40);
		txtLibraryManagementSystem.setEditable(false);
		contentPane.add(txtLibraryManagementSystem);
		txtLibraryManagementSystem.setColumns(10);

		JButton btnCategory = new JButton("Category");
		btnCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCategory.setBounds(170, 80, 140, 30);
		contentPane.add(btnCategory);
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Category_Page().setVisible(true);
			}
		});

		JButton btnAuthor = new JButton("Author");
		btnAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAuthor.setBounds(170, 120, 140, 30);
		contentPane.add(btnAuthor);
		btnAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Author_Page().setVisible(true);
			}
		});

		JButton btnPublisher = new JButton("Publisher");
		btnPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPublisher.setBounds(170, 160, 140, 30);
		contentPane.add(btnPublisher);
		btnPublisher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Publisher_Page().setVisible(true);
			}
		});

		JButton btnBook = new JButton("Book");
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBook.setBounds(170, 200, 140, 30);
		contentPane.add(btnBook);
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Book_Page().setVisible(true);
			}
		});

		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.setBounds(170, 240, 140, 30);
		contentPane.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login_Page().setVisible(true);
			}
		});
	}
}
