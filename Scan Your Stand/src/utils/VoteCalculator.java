package utils;

import java.util.List;
import java.util.stream.Collectors;
import stand.Stand;
import vote.Vote;

public class VoteCalculator {
	
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

}
