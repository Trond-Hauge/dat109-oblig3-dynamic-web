package qr;

import java.util.ArrayList;
import java.util.List;

import project.Project;

/**
 * 
 * @author anders.aarsaether
 *	
 *	Meant for testing
 *	Everything here works
 */
public class MainQR {

	public static void main(String[] args) {
		
		Project p1 = new Project("MB12", "Project1");
		Project p2 = new Project("AB42", "Project2");
		Project p3 = new Project("YR56", "Project3");
		Project p4 = new Project("UG54", "Project4");
		Project p5 = new Project("OT64", "Project5");
		
		List<Project> list = new ArrayList<Project>();
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
	
		MyQr.createQrCodesForProjects(list);
	}

}
