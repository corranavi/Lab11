package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public void getAllRivers(Map<Integer, River> idMap) {
		
		final String sql = "SELECT id, name FROM river";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(!idMap.containsKey(res.getInt("id"))) {
					River r=new River(res.getInt("id"), res.getString("name"));
					idMap.put(r.getId(), r);
				}
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	public List<Flow> getAllFlows(Map<Integer,River> idMap, int id){
		final String sql="SELECT * FROM flow WHERE river=?";
		List<Flow> result=new ArrayList<>();
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1,id);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Flow f= new Flow(rs.getDate("day").toLocalDate(), rs.getFloat("flow"), idMap.get(rs.getInt("river")));
				result.add(f);
			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Errore nella connessione al DB");
		}
		return result;
		
	}
}
