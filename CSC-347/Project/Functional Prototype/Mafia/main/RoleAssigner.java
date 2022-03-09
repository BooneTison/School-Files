package main;
import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class RoleAssigner {

	public static enum Role{Mafia, Civilian};
	
	
	/**
	 * assignRoles
	 * 
	 * Generates a hashMap of player names and their roles, given a list of player names.
	 * 
	 * @param players is an array containing each player's name
	 * @return 
	 */
	public static HashMap<String, Role> assignRoles(ArrayList<String> players) {
		int numPlayers = players.size();
		//There should be 3:1 civilian to mafia ratio (and always at least one mafia).
		int numMafia = numPlayers / 4;
		if (numPlayers < 4) {numMafia = 1;}
		
		//randomly pick indexes of mafia players
		ArrayList<Integer> mafiaIndexes = nRandomInts(numMafia, numPlayers);
		
		//put players and roles in hashmap
		HashMap<String, Role> playerRoles = new HashMap<String, Role>();
		for (int i = 0; i < numPlayers; i++) {
			if (mafiaIndexes.contains(i)) {
				playerRoles.put(players.get(i), Role.Mafia);
			}
			else {
				playerRoles.put(players.get(i), Role.Civilian);
			}
		}
		return playerRoles;
	}
	
	

	/**
	 * getRoleString
	 * 
	 * Encodes the names and roles of each player (HashMap) into a string, 
	 * which can later be converted back into a HashMap.
	 * 
	 * The string looks like "name1,m,name2,<non-m letter>,name3,<non-m letter>,name4,<non-m letter>, ..."
	 * where "m" means "mafia" and "non-m" means civilian. (this is so that you can't tell by the encrypted string)
	 * 
	 * ex. "ethan,f,jeff,p,mary,c,oswald,m,stephen,y,jerry,m,eliza,q," 
	 * --> this means oswald and jerry are the mafia members.
	 * 
	 * decode using "decodeRolesAsHashMap(String)".
	 * 
	 * @param playerRoles is a HashMap<String, String> containing name:role pairs.
	 * @return
	 */
	public static String encodeRolesAsString(HashMap<String, Role> playerRoles) {
		String roleString = "";
		Random r = new Random();
	    String alphabet = "abcdef"; //no m: because m is reserved for mafia. Just a few letters so "m" isn't the only repeated one.
	    
	    //encode the playerRoles hashmap as a single string
	    for (String name : playerRoles.keySet()) {
	    	if (playerRoles.get(name) == Role.Mafia) {
	    		roleString += name + ":m,";
	    	}
	    	else {
	    		roleString += name + ":" + alphabet.charAt(r.nextInt(alphabet.length())) + ",";
	    	}
	    }
		return roleString;
	}
	
	
	
	/**
	 * 
	 * @param roleString
	 * @return
	 */
	public static HashMap<String, Role> decodeRolesAsHashMap(String roleString) {
		HashMap<String, Role> playerRoles = new HashMap<String, Role>();
		
		//each "word:letter" chunk is a player name and role ("bob:m" means bob is mafia, "mary:<non-m letter>" means mary is civilian)
		String[] chunks = roleString.split(",");
		
		for (String s : chunks) {
			//return empty hashmap if input is incorrect
			if (s.indexOf(':') == -1) {
				return playerRoles;
			}
			
			
			char roleChar = s.charAt(s.indexOf(':') +1);
			String playerName = s.substring(0, s.indexOf(':'));
			//put playerName:role into HashMap
			if (roleChar == 'm') {
				playerRoles.put(playerName, Role.Mafia);
			}
			else {
				playerRoles.put(playerName, Role.Civilian);
			}
		}
		return playerRoles;
	}
	
	
	/**
	 * nRandomInts
	 * 
	 * Helper: returns a list of random indexes between 0 and max (exclusive) which represent mafia players.
	 * @param n is number of unique indexes
	 * @param max (not inclusive)
	 * @return nums
	 */
	private static ArrayList<Integer> nRandomInts(int n, int max) {
		//make a list of ints from 0 up to max
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            list.add(i);
        }
        //shuffle the list of int indexes
        Collections.shuffle(list);
        
        //return the first n indexes
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            nums.add(list.get(i));
        }
        return nums;
	}
	
	
	
	
	
	
//	/**
//	 * Main
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		ArrayList<String> players = new ArrayList<String>(Arrays.asList(new String[] {"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh"}));
//		//HashMap<String, String> playerRoles = assignRoles(players);
//		
//		for (int i = 0; i < 8; i ++) {
//			System.out.println(encodeRolesAsString(assignRoles(players)));
//		}
//		HashMap<String, Role> playerRoles = decodeRolesAsHashMap(encodeRolesAsString(assignRoles(players)));
//		for (String s : playerRoles.keySet()) {
//			System.out.println(s + " " + playerRoles.get(s));
//		}
//		
//		
//    }
}
