
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

class GUI {

	JFrame frm;
	String campus_location = "Львовская";
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
		frm.setBounds(100, 100, 450, 105);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frm.getContentPane().add(panel, BorderLayout.CENTER);

		JButton btn = new JButton("Войти в сеть");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (campus_location.equals("Львовская"))
					campus_address = "https://nnov-wlc-03.hse.ru/login.html";
				else if (campus_location.equals("Сормово"))
					campus_address = "https://nnov-wlc-04.hse.ru/login.html";
				else if (campus_location.equals("Печерская"))
					campus_address = "https://nnov-wlc-01.hse.ru/login.html";
				else if (campus_location.equals("Родионова"))
					campus_address = "https://nnov-wlc-02.hse.ru/login.html";
				try {
					Login.login("hseguest", "hsepassword", campus_address);
				} catch (IOException | InterruptedException e1) {
					JOptionPane.showMessageDialog(null, "Can`t connect!");
				}
			}

		});
		String[] items = { "Львовская", "Сормово", "Печерская", "Родионова" };

		JLabel label = new JLabel("Выберите кампус:");
		JComboBox<String> comboBox = new JComboBox<String>(items);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campus_location = (String) comboBox.getSelectedItem();
			}

		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 217,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBox,
												GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
						.addComponent(btn, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
						.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(32, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
	}
}
