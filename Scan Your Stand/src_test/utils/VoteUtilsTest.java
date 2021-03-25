package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import project.Project;
import vote.Vote;

@TestInstance(Lifecycle.PER_CLASS)
public class VoteUtilsTest {
	
	private Vote vote1;
	private Vote vote2;
	private Vote vote3;
	private List<Vote> votes;
	private List<Project> projects;
	
	@BeforeEach
	public void reset() {
		
		vote1 = new Vote("47474747", "stand1");
		vote2 = new Vote("25252525", "stand1");
		vote3 = new Vote("47474747","stand2");
		
		vote1.setPoints(1);
		vote2.setPoints(2);
		vote3.setPoints(5);
		
		votes = Arrays.asList(vote1, vote2, vote3);
		projects = Arrays.asList(new Project("stand1", "stand1"), new Project("stand2","stand2"));
		
	}
	
	@Test
	public void calculateVotesTest() {
		
		int voterScore = VoteUtils.calculateVotes(projects.get(0), votes);
		Assertions.assertEquals(voterScore, 3);
		
	}
	
	@Test
	public void getUserVotesTest() {
		
		Map<String,Integer> map = VoteUtils.getUserVotes("25252525", votes, projects);
		
		Assertions.assertNotEquals(map, null);
		Assertions.assertFalse(map.isEmpty());
		map.keySet().forEach(k -> Assertions.assertEquals(k,"stand1"));
		Assertions.assertEquals(map.get("stand1"), 2);
		
	}
	
	@Test
	public void getSortedProjectScoreMapTest() {
		
		Map<String,Integer> map = VoteUtils.getSortedProjectScoreMap(projects, votes);
		
		int i = 0;
		for(String s : map.keySet()) {
			
			switch(i) {
			
			case 0: 
				Assertions.assertEquals(s,"stand2"); 
				Assertions.assertEquals(map.get(s), 5);
				break;
			
			case 1: 
				Assertions.assertEquals(s,"stand1"); 
				Assertions.assertEquals(map.get(s), 3);
				break;
			
			}
			
			i++;
			
		}
		
	}

}
