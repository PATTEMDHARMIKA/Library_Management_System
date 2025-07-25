import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Logout_page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logout_page frame = new Logout_page();
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
	public class Logout_Page {
	    public static void confirmLogout(JFrame currentFrame) {
	        int choice = JOptionPane.showConfirmDialog(
	            currentFrame,
	            "Are you sure you want to logout?",
	            "Logout Confirmation",
	            JOptionPane.YES_NO_OPTION
	        );
	        if (choice == JOptionPane.YES_OPTION) {
	            currentFrame.dispose();
	            new Login_Page().setVisible(true);
	        }
	    }
	}


}

