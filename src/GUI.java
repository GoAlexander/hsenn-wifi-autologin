
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import org.openqa.selenium.NoSuchElementException;

class GUI {

	JFrame frm;
	String campus_location = "Lvovskaya";
	String campus_address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm = new JFrame();
		frm.setTitle("Wi-Fi connection");
		frm.setResizable(false);
		frm.setBounds(100, 100, 455, 106);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frm.getContentPane().add(panel, BorderLayout.CENTER);

		JButton btn1 = new JButton("Connect");
		btn1.setMinimumSize(new Dimension(85, 23));
		btn1.setPreferredSize(new Dimension(85, 23));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (campus_location.equals("Lvovskaya"))
					campus_address = "https://nnov-wlc-03.hse.ru/login.html";
				else if (campus_location.equals("Sormovo"))
					campus_address = "https://nnov-wlc-04.hse.ru/login.html";
				else if (campus_location.equals("Pecherskaya"))
					campus_address = "https://nnov-wlc-01.hse.ru/login.html";
				else if (campus_location.equals("Rodionova"))
					campus_address = "https://nnov-wlc-02.hse.ru/login.html";
				try {
					Login.connect("hseguest", "hsepassword", campus_address);
					JOptionPane.showMessageDialog(null, "Connection succesful!");
				} catch (IOException | InterruptedException | NoSuchElementException e1) {
					JOptionPane.showMessageDialog(null, "Connection error!");
				}
			}

		});
		String[] items = { "Lvovskaya", "Sormovo", "Pecherskaya", "Rodionova" };

		JLabel label = new JLabel("Choose campus:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JComboBox<String> comboBox = new JComboBox(items);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campus_location = (String) comboBox.getSelectedItem();
			}

		});

		JButton btn2 = new JButton("Disconnect");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.disconnect();
				} catch (IOException | InterruptedException e1) {
					JOptionPane.showMessageDialog(null, "Disconnection error!");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn1, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn2, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
