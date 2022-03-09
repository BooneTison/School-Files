package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class StartWindow extends JFrame {

	//UI stuff
	private static StartWindow _frame;
	private JPanel contentPane;
	private JLabel lblAddPlayer;
	private JTextField addPlayerTextField;
	private JTextArea addedPlayersTextArea;
	
	//my stuff
	private ArrayList<String> _players;
	private HashMap<String, RoleAssigner.Role> _playerRoles;
	private String _roleText = "";
	private String _encryptedRoleText = "";
	
	/**
	 * run this in constructor
	 */
	public void setup() {
		_players = new ArrayList<String>();
		_playerRoles = new HashMap<String, RoleAssigner.Role>();
	}
	
	
	
	/**
	 * updateAddedPlayersText
	 * 
	 * Helper that updates text of addedPlayersTextArea with names from _players list
	 */
	private void updateAddedPlayersText() {
		String newText = "";
		for (String p : _players) {
			newText += p + ", ";
		}
		addedPlayersTextArea.setText(newText);
	}
	
	
	
	
	
	
	
	//////////////
	// UI STUFF //
	//////////////
	
	/**
	 * Launch the application window.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_frame = new StartWindow();
					_frame.setVisible(true);
					_frame.setTitle("Mafia Game Setup");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartWindow() {
		setup();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAddPlayer = new JLabel("Add Player:");
		lblAddPlayer.setBounds(10, 11, 76, 14);
		contentPane.add(lblAddPlayer);
		
		addPlayerTextField = new JTextField();
		addPlayerTextField.setToolTipText("Enter a player's name here");
		addPlayerTextField.setBounds(89, 8, 158, 20);
		contentPane.add(addPlayerTextField);
		addPlayerTextField.setColumns(10);
		
		//Add Player Button Handler
		JButton addPlayerButton = new JButton("Add");
		addPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when clicked, add the text from "addPlayerTextField" to the "_players" list,  
				//and update "addedPlayersTextArea" with the new player's name.
				String name = addPlayerTextField.getText();
				_players.add(name);
				updateAddedPlayersText();
			}
		});
		addPlayerButton.setBounds(257, 7, 57, 23);
		contentPane.add(addPlayerButton);
		
		//Reset Button Handler
		JButton ResetButton = new JButton("Reset");
		ResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//empty the players list and update text field
				_players = new ArrayList<String>();
				updateAddedPlayersText();
			}
		});
		ResetButton.setBounds(324, 7, 102, 23);
		contentPane.add(ResetButton);
		
		//AssignRoles Button Handler
		JButton AssignRolesButton = new JButton("Assign Roles");
		AssignRolesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (_players.size() >= 4) {
					//Assigns Roles, gets string, encodes it, and saves it.
					//open the GameCodeWindow and display the encrypted text.
					_playerRoles = RoleAssigner.assignRoles(_players);
					_roleText = RoleAssigner.encodeRolesAsString(_playerRoles);
					_encryptedRoleText = Cipher.encrypt(_roleText);
					GameCodeWindow.openWindow(_encryptedRoleText);
				}
				//if too few players have been added, display a warning:
				else  {
					JOptionPane.showMessageDialog(null, "Error: Not enough players (Need at least 4).");
				}
			}
		});
		AssignRolesButton.setBounds(324, 35, 102, 44);
		AssignRolesButton.setMargin(new Insets(5, 5, 5, 5));
		contentPane.add(AssignRolesButton);
		
		JTextArea gameCodeInputTextArea = new JTextArea();
		gameCodeInputTextArea.setBounds(10, 107, 244, 120);
		gameCodeInputTextArea.setLineWrap(true);
		contentPane.add(gameCodeInputTextArea);
		
		JLabel lblPastAGame = new JLabel("Paste a game code here and click button to start:");
		lblPastAGame.setBounds(10, 90, 304, 14);
		contentPane.add(lblPastAGame);
		
		
		
		//Start Game Button Handler
		JButton StartGameButton = new JButton("Start Game");
		StartGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//decode encrypted role text from gameCodeInputTextArea:
				_encryptedRoleText = gameCodeInputTextArea.getText();
				_roleText = Cipher.decrypt(_encryptedRoleText);
				_playerRoles = RoleAssigner.decodeRolesAsHashMap(_roleText);
				
				//check that roles have been assigned (make sure roleText is not empty)
				if (!_roleText.contentEquals("")) {
					//starts game as player: open PlayerWindow, close StartWindow.
					PlayerWindow.openWindow(_playerRoles);
					_frame.setVisible(false); //you can't see me!
					_frame.dispose(); //Destroy the JFrame object
				}
				else {
					//if no roles have been assigned, show a warning message
					JOptionPane.showMessageDialog(null, "Error: Must assign roles first, or paste a game code from host.");
				}
				
				
			}
		});
		StartGameButton.setBounds(264, 108, 162, 74);
		contentPane.add(StartGameButton);
		
		
		
		//Start Game As Host Button Handler
		JButton StartGameAsHostButton = new JButton("Start Game As Host");
		StartGameAsHostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//do the same thing as Start Game button, but also open HostWindow:

				//decode encrypted role text from gameCodeInputTextArea:
				_encryptedRoleText = gameCodeInputTextArea.getText();
				_roleText = Cipher.decrypt(_encryptedRoleText);
				_playerRoles = RoleAssigner.decodeRolesAsHashMap(_roleText);
				
				//check that roles have been assigned (make sure roleText is not empty)
				if (!_roleText.contentEquals("")) {
					//starts game as player: open PlayerWindow, close StartWindow.
					PlayerWindow.openWindow(_playerRoles);
					_frame.setVisible(false); //you can't see me!
					_frame.dispose(); //Destroy the JFrame object
					
					//open HostWindow
					HostWindow.openWindow(_playerRoles);
				}
				else {
					//if no roles have been assigned, show a warning message
					JOptionPane.showMessageDialog(null, "Error: Must assign roles first, or paste a game code from host.");
				}
				
				
			}
		});
		StartGameAsHostButton.setBounds(264, 189, 162, 40);
		contentPane.add(StartGameAsHostButton);
		
		
		
		addedPlayersTextArea = new JTextArea();
		addedPlayersTextArea.setToolTipText("These players have been added already");
		addedPlayersTextArea.setBackground(SystemColor.menu);
		addedPlayersTextArea.setLineWrap(true);
		addedPlayersTextArea.setWrapStyleWord(true);
		addedPlayersTextArea.setEditable(false);
		addedPlayersTextArea.setBounds(10, 36, 304, 43);
		contentPane.add(addedPlayersTextArea);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetupHelpWindow.openWindow();
			}
		});
		helpButton.setBounds(363, 240, 63, 23);
		contentPane.add(helpButton);
	}
}



