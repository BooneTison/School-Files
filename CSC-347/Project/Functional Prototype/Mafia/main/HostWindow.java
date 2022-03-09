package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



public class HostWindow extends JFrame {

	private JPanel contentPane;
	public static JLabel timerLabel = new JLabel("timer");
	public static HostWindow frame;
	public static JLabel dayLabel;
	public static JButton submitVotesButton;
	public static JComboBox mafiaKillComboBox;
	public static JButton killButton;
	public static JButton beginNextPhaseButton;
	
	//my stuff
	public static enum Phase {Day, Night};
	private static Phase _currentPhase = Phase.Day;
	private static int _roundNum = 0;
	private static HashMap<String, RoleAssigner.Role> _playerRoles;
	private static HashMap<String, RoleAssigner.Role> _livingPlayerRoles;
	private static JScrollPane scrollPane;
	private static String[] _livingPlayers;
	private static TimerTask _countdownTimer = new Countdown60Sec();
	
	private static HashMap<String, JComboBox> _comboBoxes = new HashMap<String, JComboBox>();
	
	public static ArrayList<String> deathMessages = new ArrayList<String>(Arrays.asList(new String[]{
			"forgot to deliver his monthly payment and was found dead at the bottom of the Hudson River.", 
			"was found dead in bed with their head filled with old combination locks.",
			"was found dead from a lethal injection of lead.",
			"was found dead at their bathroom sink with a toothbrush stuffed down their esophagus.",
			"was found dead with all of their toes replaced with other big toes.",
			"was found decapitated with a baloon where the head once was.",
			"forgot to go to weekly mass and was found burned alive in their home.",
			"was found hanging from a bulldozer in an abandoned parking lot.",
			"was found dead in the woods during hunting season, dressed up in a deer costume.",
			"was found drowned in their doggy bowl.",
			"was found mauled with their eyes scratched out by their 24 Siamese cats.",
			"was found dead with both hands destroyed by the garbage disposal.",
	}));
	private JButton helpButton;
	
	
	
	
	
	
	/**
	 * Start day phase
	 */
	public static void startDay() {
		_currentPhase = Phase.Day;
		_roundNum++;
		
		//cancel last timer, start new 60 sec timer
		_countdownTimer.cancel();
		_countdownTimer = new Countdown60Sec();
		new Timer().schedule(_countdownTimer, 0, 1000);
		
		//change day label to day
		dayLabel.setText("Day " + _roundNum);
		//change button text to "start night"
		beginNextPhaseButton.setText("Start Night");
		
		//disable mafia kill buttons
		mafiaKillComboBox.setEnabled(false);
		killButton.setEnabled(false);
		//enable voting buttons
		submitVotesButton.setEnabled(true);
	}
	
	
	/**
	 * Start night phase
	 */
	public static void startNight() {
		_currentPhase = Phase.Night;
		//start 30 sec timer
		_countdownTimer.cancel();
		_countdownTimer = new Countdown30Sec();
		new Timer().schedule(_countdownTimer, 0, 1000);
		//change day label to night
		dayLabel.setText("Night " + _roundNum);
		//change button text to "start day"
		beginNextPhaseButton.setText("Start Day");
		
		//disable voting button
		submitVotesButton.setEnabled(false);
		//enable mafia kill buttons
		mafiaKillComboBox.setEnabled(true);
		killButton.setEnabled(true);
	}
	
	
	
	/**
	 * checks if game is over and Mafia wins (greater or equal number of mafia than civilians)
	 * @return
	 */
	public static boolean isGameOverMafiaWins() {
		int numMafia = 0, numCivilians = 0;
		for (String p : _livingPlayerRoles.keySet()) {
			if (_livingPlayerRoles.get(p) == RoleAssigner.Role.Mafia) numMafia ++;
			else numCivilians ++;
		}
		return numMafia >= numCivilians;
	}
	
	/**
	 * checks if game is over and Civilians win (no mafia players left)
	 * @return
	 */
	public static boolean isGameOverCiviliansWin() {
		for (String p : _livingPlayerRoles.keySet()) {
			if (_livingPlayerRoles.get(p) == RoleAssigner.Role.Mafia) return false;
		}
		return true;
	}
	
	
	
	public static String getDeathMessage(String playerName) {
		String msg = playerName;
		String defaultMsg = " was killed by the Mafia.";	//default message if run out of others
		if (deathMessages.size() == 0) {
			return msg + defaultMsg;
		}
		//choose a random message (and delete after use)
		else {
			int msgNum = (new Random()).nextInt(deathMessages.size());
			String p2 = deathMessages.get(msgNum);
			deathMessages.remove(msgNum);
			return msg + " " + p2;
		}
	}
	
	
	/**
	 * run whenever a player is to be eliminated
	 * @param playerName
	 */
	public static void killPlayer(String playerName) {
		//remove from livingPlayers, and remove from dropdowns
		_livingPlayerRoles.remove(playerName);
		updateLivingPlayersArray();
		updateDropdowns();
		
		// display death message
		if (_currentPhase == Phase.Day) {
			JOptionPane.showMessageDialog(null, playerName + " was eliminated.");
		} 
		if (_currentPhase == Phase.Night) {
			JOptionPane.showMessageDialog(null, getDeathMessage(playerName));
		}
		
		//disable dead player's voting dropdown
		_comboBoxes.get(playerName).setEnabled(false);
		
		
		//check if game is over
		if (isGameOverMafiaWins()) JOptionPane.showMessageDialog(null, "Mafia Wins");
		if (isGameOverCiviliansWin()) JOptionPane.showMessageDialog(null, "Civilians Win");
	}
	
	
	
	
	
	
	// UI STUFF //
	

	/**
	 * Create the frame.
	 */
	public HostWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//////////////////////////
		
		//Scroll Pane
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 145, 312, 255);
		contentPane.add(scrollPane);
		
		//GridBag panel that holds rows of labels + comboBoxes
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		//make a voting dropdown for each player, which contains each player as a vote. 
		int row = 0;
		for (String s : _playerRoles.keySet()) {
			JLabel lblNewLabel = new JLabel(s);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			//gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = row;
			panel.add(lblNewLabel, gbc_lblNewLabel);
			
			JComboBox comboBox = new JComboBox(_livingPlayers);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = row;
			gbc_comboBox.anchor  = GridBagConstraints.NORTHWEST;
			comboBox.setSelectedItem(comboBox.getItemAt(Arrays.asList(_livingPlayers).indexOf("skip")));
			panel.add(comboBox, gbc_comboBox);
			
			
			_comboBoxes.put(s, comboBox);
			row++;
		}
		
		//can't figure out how to keep the components tightly aligned vertically...
		//...so i'm adding disabled components to fill the remaining space.
		if (row < 8) {
			for (int i = row; i <= 8; i++) {
				//aesthetic filler, disabled
				JLabel lblNewLabel = new JLabel("empty");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = i;
				panel.add(lblNewLabel, gbc_lblNewLabel);
				lblNewLabel.setEnabled(false);
				
				//aesthetic filler, disabled
				JComboBox comboBox = new JComboBox();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 5, 0);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = i;
				gbc_comboBox.anchor  = GridBagConstraints.NORTHWEST;
				panel.add(comboBox, gbc_comboBox);
				comboBox.setEnabled(false);
			}
		}
		
		
		
		dayLabel = new JLabel("Day 1");
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		dayLabel.setBounds(20, 11, 127, 41);
		contentPane.add(dayLabel);
		
		timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		timerLabel.setBounds(283, 14, 97, 34);
		contentPane.add(timerLabel);
		
		//Submit Votes Button Handler
		submitVotesButton = new JButton("Submit Votes");
		submitVotesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//count up votes: whoever gets most votes dies (nobody if tie)
				//need a list to hold votes (names of players who were voted for)
				ArrayList<String> votes = new ArrayList<String>();
				
				//loop over all the combo boxes (of just living players), tally up the selections, find selection with most votes.
				for (String name : _comboBoxes.keySet()) {
					//if player is alive, count their vote
					if (_livingPlayerRoles.keySet().contains(name)) {
						String voteFor = (String) _comboBoxes.get(name).getSelectedItem();
						if (_livingPlayerRoles.containsKey(voteFor))	//don't count "skip"
							votes.add(voteFor);
					}
				}
				//see who got highest votes (or "none" if tied)
				String winner = VoteCounter.countVotes(votes);
				if (winner.equals("none")) {
					JOptionPane.showMessageDialog(null, "Nobody was eliminated.");
					updateDropdowns();
				}
				else {
					killPlayer(winner);
				}
			}
		});
		submitVotesButton.setBounds(191, 406, 143, 23);
		contentPane.add(submitVotesButton);
		
		JLabel lblVoteToEliminate = new JLabel("Vote to eliminate a player:");
		lblVoteToEliminate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVoteToEliminate.setBounds(20, 108, 227, 23);
		contentPane.add(lblVoteToEliminate);
		
		
		mafiaKillComboBox = new JComboBox(_livingPlayers);
		mafiaKillComboBox.setBounds(376, 145, 161, 23);
		contentPane.add(mafiaKillComboBox);
		_comboBoxes.put("MAFIA", mafiaKillComboBox);
		
		JLabel lblWhoWasKilled = new JLabel("Who was killed in the night?");
		lblWhoWasKilled.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWhoWasKilled.setBounds(376, 114, 202, 17);
		contentPane.add(lblWhoWasKilled);
		
		//Mafia Kill Button Handler
		killButton = new JButton("Kill");
		killButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get player name from mafiaKillComboBox dropdown
				String playerName = (String) mafiaKillComboBox.getItemAt(mafiaKillComboBox.getSelectedIndex());
				//remove selected player from _livingPlayers, update dropdowns, reveal identity?, check if game over
				killPlayer(playerName);
				
			}
		});
		killButton.setBounds(544, 145, 69, 23);
		contentPane.add(killButton);
		
		//Phase Change Button Handler
		beginNextPhaseButton = new JButton("Begin Night");
		beginNextPhaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (_currentPhase == Phase.Day) {
					startNight();
				} else {
					startDay();
				}
			}
		});
		beginNextPhaseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		beginNextPhaseButton.setBounds(472, 375, 161, 65);
		contentPane.add(beginNextPhaseButton);
		
		helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameHelpWindow.openWindow();
			}
		});
		helpButton.setBounds(544, 11, 89, 23);
		contentPane.add(helpButton);
		
		//revalidate is called on a container once new components are added or old ones removed.
		//validate();
		//repaint();
		
		startDay();
	}
	
	
	/**
	 * Launch the application
	 */
	public static void openWindow(HashMap<String, RoleAssigner.Role> playerRoles) {
		_playerRoles = playerRoles;
		_livingPlayerRoles = (HashMap<String, RoleAssigner.Role>) playerRoles.clone();
		updateLivingPlayersArray();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new HostWindow();
					frame.setVisible(true);
					frame.setTitle("Host Window");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}
	
	

	
	
	
	
	//do this whenever someone dies so the dropdowns can update
	private static void updateLivingPlayersArray() {
		_livingPlayers = _livingPlayerRoles.keySet().toArray(new String[_livingPlayerRoles.keySet().size()+1]);
		_livingPlayers[_livingPlayers.length -1] = "skip";
	}
	
	
	private static void updateDropdowns() {
		for (String boxName : _comboBoxes.keySet()) {
			JComboBox currentBox = _comboBoxes.get(boxName);
			
			//clear each box, then add back the remaining players
			currentBox.removeAllItems();
			currentBox.validate();
			
			for (String p : _livingPlayers) currentBox.addItem(p);
			currentBox.setSelectedItem(currentBox.getItemAt(Arrays.asList(_livingPlayers).indexOf("skip")));
			currentBox.validate();
			currentBox.repaint();
		}
	}
	
	
//	/**
//	 * Main (for testing)
//	 */
//	public static void main(String[] args) {
//		HashMap<String, RoleAssigner.Role> roles = RoleAssigner.assignRoles(new ArrayList<String>(Arrays.asList(new String[] {"testPlayer1", "testPlayer2", "testPlayer3", "testPlayer4", "testPlayer5"})));
//		openWindow(roles);
//	}
}










/**
 * TimerTask that counts down for 30 seconds. 
 * Code to start timer:
 *   new Timer().schedule(new Countdown30Sec(), 0, 1000);
 */
class Countdown30Sec extends TimerTask {
    int countdown = 30;
    public void run() {
    	countdown -= 1;
        HostWindow.timerLabel.setText(countdown +" sec");
        if (countdown == 0) this.cancel();
    }
}

class Countdown60Sec extends TimerTask {
    int countdown = 60;
    public void run() {
    	countdown -= 1;
        HostWindow.timerLabel.setText(countdown +" sec");
        if (countdown == 0) this.cancel();
    }
}


