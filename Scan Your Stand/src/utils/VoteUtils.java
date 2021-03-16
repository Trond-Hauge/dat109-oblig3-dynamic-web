package utils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import stand.Stand;
import vote.Vote;

public class VoteUtils {
	
	/**
	 * Calculates a vote sum to represent a specified stand's voter score. This score
	 * is a sum of each grade in the casted votes.
	 * 
	 * @author Oliver
	 * @param stand Stand to calculate votes for
	 * @param votes List of all votes that have been casted
	 * @return A calculated sum to represent the specified stand's voter score
	 */
	public static int calculateVotes(Stand stand, List<Vote> votes) {
		
		List<Vote> votesForStand = votes.stream().filter(v -> v.getStandID().equals(stand.getStandID())).collect(Collectors.toList());
		
		int voteSum = 0;
		for(Vote v : votesForStand) {
			voteSum += v.getPoints();
		}
		
		return voteSum;
		
	}
	
	/**
	 * 
	 * Accepts a phonenumber for identification of user votes, a list of all votes and a list of all stands.
	 * Then collects the name of each stand the user has voted for and the corresponding points given in a HashMap.
	 * 
	 * @author Oliver
	 * @param phone Phonenumber
	 * @param votes All votes
	 * @param stands All stands
	 * @return A HashMap of type <String,Integer> where the String value (key) is the name of a stand and the Integer value is the corresponding points.
	 */
	public static HashMap<String,Integer> getUserVotes(String phone, List<Vote> votes, List<Stand> stands) {
		
		if(phone == null || votes == null || stands == null) {
			return null;
		}
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		votes.stream().filter(v -> v.getPhone().equals(phone)).forEach(v -> {
			
			stands.stream().filter(s -> s.getStandID() == v.getStandID()).forEach(s -> map.put(s.getStandName(), v.getPoints()));
			
		});
		
		return map;
		
	}

}
