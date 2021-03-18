package utils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import project.Project;
import vote.Vote;

public class VoteUtils {
	
	/**
	 * Calculates a vote sum to represent a specified project's voter score. This score
	 * is a sum of each grade in the casted votes.
	 * 
	 * @author Oliver
	 * @param project Project to calculate votes for
	 * @param votes List of all votes that have been casted
	 * @return A calculated sum to represent the specified project's voter score
	 */
	public static int calculateVotes(Project project, List<Vote> votes) {
		
		List<Vote> projectVotes = votes.stream().filter(v -> v.getProjectNumber().equals(project.getProjectNumber())).collect(Collectors.toList());
		
		int voteSum = 0;
		for(Vote v : projectVotes) {
			voteSum += v.getPoints();
		}
		
		return voteSum;
		
	}
	
	/**
	 * 
	 * Accepts a phonenumber for identification of user votes, a list of all votes and a list of all projects.
	 * Then collects the name of each stand the user has voted for and the corresponding points given in a HashMap.
	 * 
	 * @author Oliver
	 * @param phone Phonenumber
	 * @param votes All votes
	 * @param projects All projects
	 * @return A HashMap of type <String,Integer> where the String value (key) is the name of a Project and the Integer value is the corresponding points.
	 */
	public static HashMap<String,Integer> getUserVotes(String phone, List<Vote> votes, List<Project> projects) {
		
		if(phone == null || votes == null || projects == null) {
			return null;
		}
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		votes.stream().filter(v -> v.getPhone().equals(phone)).forEach(v -> {
			
			projects.stream().filter(s -> s.getProjectNumber() == v.getProjectNumber()).forEach(s -> map.put(s.getProjectName(), v.getPoints()));
			
		});
		
		return map;
		
	}

}
