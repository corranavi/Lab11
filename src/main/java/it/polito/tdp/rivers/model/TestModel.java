package it.polito.tdp.rivers.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		Model model=new Model();
		List<River> rivers=model.getAllRivers();
		for(River r: rivers) {
			System.out.println(r);
		}
		List<Flow> flows=model.getRiversFlow(new River(1,"Jokulsa Eystri River"));
		for(Flow f: flows) {
			System.out.println(f);
		}
		System.out.println(model.averageFlow(new River(2,"Jokulsa Eystri River")));
	}
	

}
