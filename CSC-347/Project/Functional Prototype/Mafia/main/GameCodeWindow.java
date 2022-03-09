package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class GameCodeWindow extends JFrame {

	private JPanel contentPane;
	private static GameCodeWindow _frame;
	
	
	//my stuff
	private String _encryptedRoleText;

	
	

	
	/**
	 * Create the frame.
	 */
	public GameCodeWindow(String encryptedRoleText) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea GameCodeTextArea = new JTextArea();
		GameCodeTextArea.setEditable(false);
		GameCodeTextArea.setLineWrap(true);
		GameCodeTextArea.setBounds(28, 126, 379, 108);
		GameCodeTextArea.setText(encryptedRoleText); //display encrypted role text
		contentPane.add(GameCodeTextArea);
		
		JTextArea txtrRolesHaveBeen = new JTextArea();
		txtrRolesHaveBeen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrRolesHaveBeen.setBackground(SystemColor.menu);
		txtrRolesHaveBeen.setEditable(false);
		txtrRolesHaveBeen.setLineWrap(true);
		txtrRolesHaveBeen.setWrapStyleWord(true);
		txtrRolesHaveBeen.setText("Roles have been secretly assigned. Share this code with each other player, then they will have to paste it into their program and click \"Start Game\" to see their role. The host should click \"Start Game As Host\" instead.");
		txtrRolesHaveBeen.setBounds(28, 21, 379, 66);
		contentPane.add(txtrRolesHaveBeen);
		
		JLabel lblGameCode = new JLabel("Game Code:");
		lblGameCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGameCode.setBounds(28, 101, 77, 14);
		contentPane.add(lblGameCode);
	}
	
	/**
	 * Launch the application.
	 */
	public static void openWindow(String encryptedRoleText) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_frame = new GameCodeWindow(encryptedRoleText);
					_frame.setVisible(true);
					_frame.setTitle("Game Code");
					
					//OVERRIDE DEFAULT WINDOW CLOSE COMMAND SO ONLY THIS WINDOW CLOSES
					_frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							_frame.setVisible(false); //you can't see me!
							_frame.dispose(); //Destroy the JFrame object
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		

	}

	
//	/**
//	 * main (for testing)
//	 */
//	public static void main(String[] args) {
//		openWindow("Test Text");
//	}
}
