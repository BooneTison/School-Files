package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class SetupHelpWindow extends JFrame {

	private JPanel contentPane;
	private static SetupHelpWindow _frame;
	
	/**
	 * Launch the application.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupHelpWindow _frame = new SetupHelpWindow();
					_frame.setTitle("Setup Instructions");
					_frame.setVisible(true);
					
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

	/**
	 * Create the frame.
	 */
	public SetupHelpWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrHello = new JTextArea();
		txtrHello.setEditable(false);
		txtrHello.setWrapStyleWord(true);
		txtrHello.setLineWrap(true);
		txtrHello.setText("Setup Instructions:\r\n\r\nTo host a game, first enter each player's name (at least 4 players are needed) and click \"Add\" to add that player. Once all players have been added, click the \"Assign Roles\" button. A window will open with a text code inside it: copy and paste that code into the large text field, and share the code with each other player so they can see their role. Then click \"Start Game As Host.\" You should then share the new window with the other players via a screen-sharing application.\r\n\r\nTo join a game, copy the code shared by the host and paste it into the large text field. Then click \"Start Game\" to view your role.");
		txtrHello.setBounds(10, 11, 416, 241);
		contentPane.add(txtrHello);
	}
	
	
	
//	public static void main(String[] args) {
//		openWindow();
//	}
}
