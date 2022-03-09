package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class PlayerWindow extends JFrame {

	private JPanel contentPane;
	private JTextField enterPlayerNameTextField;
	private static PlayerWindow _frame;
	
	
	

	/**
	 * Create the frame.
	 */
	public PlayerWindow(HashMap<String, RoleAssigner.Role> playerRoles) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWhichPlayerAre = new JLabel("Which player are you?");
		lblWhichPlayerAre.setBounds(10, 11, 131, 14);
		contentPane.add(lblWhichPlayerAre);
		
		JLabel roleLabel = new JLabel("");
		roleLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roleLabel.setBounds(10, 115, 402, 67);
		contentPane.add(roleLabel);
		
		JLabel mafiaPlayersLabel = new JLabel("The other Mafia players are: ");
		mafiaPlayersLabel.setEnabled(false);
		mafiaPlayersLabel.setVisible(false);
		mafiaPlayersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mafiaPlayersLabel.setBounds(10, 238, 402, 14);
		contentPane.add(mafiaPlayersLabel);
		
		//Show Role Button Handler
		JButton showRoleButton = new JButton("Show My Role");
		showRoleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//read name from enterPlayerNameTextField
				String name = enterPlayerNameTextField.getText();
				//check if invalid player name
				if (!playerRoles.containsKey(name.trim())) {
					JOptionPane.showMessageDialog(null, "Error: \"" + name + "\" is an invalid player name.");
				} else {
					//display role in roleLabel
					RoleAssigner.Role role = playerRoles.get(name.trim());
					roleLabel.setText(role.toString());
					
					//if mafia, also display the names of other mafia players
					if (role == RoleAssigner.Role.Mafia) {
						mafiaPlayersLabel.setEnabled(true);
						mafiaPlayersLabel.setVisible(true);
						String msg = "";
						for (String p : playerRoles.keySet()) {
							if (playerRoles.get(p) == RoleAssigner.Role.Mafia && !p.equals(name)) {
								msg += p + ", ";
							}
						}
						if (!msg.contentEquals("")) msg = "The other Mafia players are: " + msg;
						mafiaPlayersLabel.setText(msg);
					}
					//disable showRoleButton so you can't use it twice
					showRoleButton.setEnabled(false);
				}
			}
		});
		showRoleButton.setToolTipText("Click to see your secret identity (either Mafia or Civilian)");
		showRoleButton.setBounds(311, 7, 115, 23);
		contentPane.add(showRoleButton);
		
		
		
		enterPlayerNameTextField = new JTextField();
		enterPlayerNameTextField.setToolTipText("Enter your name here");
		enterPlayerNameTextField.setBounds(154, 8, 147, 20);
		contentPane.add(enterPlayerNameTextField);
		enterPlayerNameTextField.setColumns(10);
		
		JTextArea AllPlayerNamesTextArea = new JTextArea();
		AllPlayerNamesTextArea.setLineWrap(true);
		AllPlayerNamesTextArea.setWrapStyleWord(true);
		AllPlayerNamesTextArea.setToolTipText("Names of all players in this game");
		AllPlayerNamesTextArea.setBackground(SystemColor.menu);
		AllPlayerNamesTextArea.setEditable(false);
		AllPlayerNamesTextArea.setBounds(10, 38, 402, 49);
		contentPane.add(AllPlayerNamesTextArea);
		
		JLabel lblMyRole = new JLabel("My Role:");
		lblMyRole.setBounds(10, 101, 79, 14);
		contentPane.add(lblMyRole);
		
		
		
		//add all player names to AllPlayerNamesTextArea and show them
		String newText = "Players: ";
		for (String p : playerRoles.keySet()) {
			newText += p + ", ";
		}
		AllPlayerNamesTextArea.setText(newText);
		
		
	}

	
	
	/**
	 * Launch the application window.
	 */
	public static void openWindow(HashMap<String, RoleAssigner.Role> playerRoles) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_frame = new PlayerWindow(playerRoles);
					_frame.setVisible(true);
					_frame.setTitle("View My Role");
					
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
}
