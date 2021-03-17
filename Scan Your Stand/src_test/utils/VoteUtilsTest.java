package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import stand.Stand;
import vote.Vote;

@TestInstance(Lifecycle.PER_CLASS)
public class VoteUtilsTest {
	
	private Vote vote1;
	private Vote vote2;
	private List<Vote> votes;
	private List<Stand> stands;
	
	@BeforeAll
	public void setup() {
		
		vote1 = new Vote("47474747", "stand1");
		vote2 = new Vote("25252525", "stand1");
		
		vote1.setPoints(1);
		vote2.setPoints(2);
		
		votes = Arrays.asList(vote1, vote2);
		stands = Arrays.asList(new Stand("stand1", "stand1"));
		
	}
	
	@Test
	public void calculateVotesTest() {
		
		int voterScore = VoteUtils.calculateVotes(stands.get(0), votes);
		Assertions.assertEquals(voterScore, 3);
		
	}
	
	@Test
	public void getUserVotesTest() {
		
		HashMap<String,Integer> map = VoteUtils.getUserVotes("47474747", votes, stands);
		
		Assertions.assertTrue(map != null);
		map.keySet().forEach(k -> Assertions.assertEquals(k,"stand1"));
		Assertions.assertEquals(map.get("stand1"), 1);
		
	}

}
