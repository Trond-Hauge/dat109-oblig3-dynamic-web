package utils;

import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
	 * Then collects the name of each stand the user has voted for and the corresponding points given in a map.
	 * 
	 * @author Oliver
	 * @param phone Phonenumber
	 * @param votes All votes
	 * @param projects All projects
	 * @return A map of types <String,Integer> where the String value (key) is the name of a project and the Integer value is the corresponding points.
	 */
	public static Map<String,Integer> getUserVotes(String phone, List<Vote> votes, List<Project> projects) {
		
		if(phone == null || votes == null || projects == null) {
			return null;
		}
		
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		
		votes.stream().filter(v -> v.getPhone().equals(phone)).forEach(v -> {
			
			for(Project p : projects) {
				
				if(p.getProjectNumber().equals(v.getProjectNumber())) {
					map.put(p.getProjectName(), v.getPoints());
					break;
				}
				
			}
			
		});
		
		return map.isEmpty() ? null : map;
		
	}
	
	/**
	 * Creates and returns a sorted map of projects and their associated voter score.
	 * 
	 * @author Oliver
	 * @param projects List of projects
	 * @param votes List of votes
	 * @return sorted map of projects and associated score
	 */
	public static Map<String,Integer> getSortedProjectScoreMap(List<Project> projects, List<Vote> votes) {
		
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		
		// Calculates each voter score for every project and puts the values into the map
		projects.forEach(s -> {
			
			int voteSum = calculateVotes(s, votes);
			map.put(s.getProjectName(), voteSum);
			
		});
		
		Map<String,Integer> sorted = new LinkedHashMap<String,Integer>();
		List<String> projectNamesList = new ArrayList<String>();
		
		for(String name : map.keySet()) {
			projectNamesList.add(name);
		}
		
		while(projectNamesList.size() > 0) {
			
			int maxIndex = 0;
			int maxSum = 0;
			
			for(int i = 0; i < projectNamesList.size(); i++) {
				
				int sum = map.get(projectNamesList.get(i));
				
				if(sum > maxSum) {
					maxSum = sum;
					maxIndex = i;
				}
				
				if(i == projectNamesList.size() - 1) {
					sorted.put(projectNamesList.remove(maxIndex), maxSum);
				}
				
			}
			
		}

		return sorted;
		
	}
	
}
