package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class GameHelpWindow extends JFrame {

	private JPanel contentPane;
	private static GameHelpWindow _frame;
	/**
	 * Launch the application.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_frame = new GameHelpWindow();
					_frame.setVisible(true);
					_frame.setTitle("How to Play");
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
	public GameHelpWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 586, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrHowToPlay = new JTextArea();
		txtrHowToPlay.setText("Each player has been randomly assigned a secret identity: Civilian or Mafia. These roles must remain secret until the end of the game.\r\n\r\nThe game takes place in two phases: DAY and NIGHT. \r\n\r\nDuring the DAY phase, players can vote to eliminate a player that they deem suspicious. The host records each player's vote and clicks \"Submit Votes\" to see if a player is eliminated. Then the host clicks \"Begin Night\" to start the night phase.\r\n\r\nIn the NIGHT phase, all players should turn off their cameras and microphones until the 30 second timer is up. During this time, the Mafia players will come to a consensus on one player they would like to eliminate: they must write the chosen player's name in an anonymous text editor (ex. http://collabedit.com) to keep their identity hidden. When the timer is up, all players turn on their mics and cameras, and the host selects the eliminated player from the dropdown and clicks \"Kill.\" Then the host clicks \"Begin Day.\" \r\n\r\nWhen a player is eliminated, they must turn off their camera and microphone. The host must continue to share their screen and facilitate gameplay if they are eliminated.\r\n\r\nThe Civilian players win if all Mafia players are eliminated.\r\nThe Mafia players win if the Civilians no longer have a majority.");
		txtrHowToPlay.setWrapStyleWord(true);
		txtrHowToPlay.setLineWrap(true);
		txtrHowToPlay.setEditable(false);
		txtrHowToPlay.setBounds(10, 11, 552, 366);
		contentPane.add(txtrHowToPlay);
	}

	
	
	
//	public static void main(String[] args) {
//		openWindow();
//	}
}
