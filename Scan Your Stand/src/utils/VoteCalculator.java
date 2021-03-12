package utils;

import java.util.List;
import java.util.stream.Collectors;
import stand.Stand;
import vote.Vote;

public class VoteCalculator {
	
	public static int calculateVotes(Stand stand, List<Vote> votes) {
		
		List<Vote> votesForStand = votes.stream().filter(v -> v.getStandId == stand.getStandId).collect(Collectors.toList());
		
		int voteSum = 0;
		votesForStand.forEach(v -> sum += v.getGrade());
		
		return voteSum;
		
	}

}
