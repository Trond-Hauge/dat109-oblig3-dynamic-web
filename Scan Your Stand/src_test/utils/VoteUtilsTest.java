package utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import project.Project;
import vote.Vote;

@TestInstance(Lifecycle.PER_CLASS)
public class VoteUtilsTest {
	
	private Vote vote1;
	private Vote vote2;
	private List<Vote> votes;
	private List<Project> projects;
	
	@BeforeAll
	public void setup() {
		
		vote1 = new Vote("47474747", "stand1");
		vote2 = new Vote("25252525", "stand1");
		
		vote1.setPoints(1);
		vote2.setPoints(2);
		
		votes = Arrays.asList(vote1, vote2);
		projects = Arrays.asList(new Project("stand1", "stand1"));
		
	}
	
	@Test
	public void calculateVotesTest() {
		
		int voterScore = VoteUtils.calculateVotes(projects.get(0), votes);
		Assertions.assertEquals(voterScore, 3);
		
	}
	
	@Test
	public void getUserVotesTest() {
		
		Map<String,Integer> map = VoteUtils.getUserVotes("47474747", votes, projects);
		
		Assertions.assertTrue(map != null);
		map.keySet().forEach(k -> Assertions.assertEquals(k,"stand1"));
		Assertions.assertEquals(map.get("stand1"), 1);
		
	}
	
	@Test
	public void getSortedProjectScoreMapTest() {
		
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		map.put("Key1", 1);
		map.put("Key2", 4);
		map.put("Key3", 3);
		map.put("Key4", 2);
		
		int i = 0;
		for(String s : map.keySet()) {
			
			switch(i) {
			
			case 0: 
				Assertions.assertEquals(s,"Key1"); 
				Assertions.assertNotEquals(map.get(s), 4);
				break;
			
			case 1: 
				Assertions.assertEquals(s,"Key2"); 
				Assertions.assertNotEquals(map.get(s), 3);
				break;
		
			case 2: 
				Assertions.assertEquals(s,"Key3"); 
				Assertions.assertNotEquals(map.get(s), 2);
				break;
			
			case 3: 
				Assertions.assertEquals(s,"Key4"); 
				Assertions.assertNotEquals(map.get(s), 1);
				break;
			
			}
			
			i++;
			
		}
		
		Map<String,Integer> sorted = VoteUtils.getSortedProjectScoreMap(map);
		
		i = 0;
		for(String s : sorted.keySet()) {
			
			switch(i) {
			
			case 0: 
				Assertions.assertEquals(s,"Key2"); 
				Assertions.assertEquals(map.get(s), 4);
				break;
			
			case 1: 
				Assertions.assertEquals(s,"Key3"); 
				Assertions.assertEquals(map.get(s), 3);
				break;
		
			case 2: 
				Assertions.assertEquals(s,"Key4"); 
				Assertions.assertEquals(map.get(s), 2);
				break;
			
			case 3: 
				Assertions.assertEquals(s,"Key1"); 
				Assertions.assertEquals(map.get(s), 1);
				break;
			
			}
			
			i++;
			
		}
		
	}

}
