package main;

import java.util.ArrayList;
import java.util.HashMap;

public class VoteCounter {

	
	/**
	 * Returns name of most voted for player (string in list) or "none" if a tie or no votes
	 * @param votes
	 * @return
	 */
	public static String countVotes(ArrayList<String> votes) {
		if (votes.size() == 0) return "none";
		
		
		//make a hashmap for holding votes
		HashMap<String, Integer> voteTallies = new HashMap<String, Integer>();
		for (String name : votes) voteTallies.put(name, 0);
		
		//count up votes
		for (String name : votes) {
			voteTallies.put(name, voteTallies.get(name) +1);
		}
		
		//now find player with most votes
		String highestName = "";
		Integer highestCount = -1;
		for (String name : voteTallies.keySet()) {
			if (voteTallies.get(name) > highestCount) {
				highestName = name;
				highestCount = voteTallies.get(name);
			}
		}
		
		//check for ties: if tie, return "none". otherwise return highestName.
		for (String name : voteTallies.keySet()) {
			if (!name.equals(highestName) && voteTallies.get(name) == highestCount) {
				return "none";
			}
		}
		return highestName;
	}
}
