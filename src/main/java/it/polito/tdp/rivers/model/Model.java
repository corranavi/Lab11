package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	private Map<Integer, River> idMap;
	RiversDAO dao;
	
	public Model() {
		idMap=new HashMap<>();
		dao=new RiversDAO();
	}
	
	public List<River> getAllRivers(){
		dao.getAllRivers(idMap);
		List<River> result=new ArrayList<>(idMap.values());
		Collections.sort(result);
		return result;
	}
	
	public List<Flow> getRiversFlow(River river){
		List<Flow> result=new ArrayList<>();
		result=dao.getAllFlows(idMap, river.getId());
		Collections.sort(result);
		return result;
	}
	
	public double averageFlow(River river) {
		double tot=0.0;
		for(Flow f: this.getRiversFlow(river)) {
			tot+=f.getFlow();
		}
		return tot/this.getRiversFlow(river).size();
	}

}
