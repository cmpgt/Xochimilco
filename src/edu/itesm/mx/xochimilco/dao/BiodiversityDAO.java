package edu.itesm.mx.xochimilco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.appengine.api.rdbms.AppEngineDriver;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BiodiversityDAO implements BiodiversitySitesDAOInterface{

	private Connection connection;
	private static BiodiversityDAO dao;
	
	private BiodiversityDAO(){		
	}
	
	public static BiodiversityDAO getInstance( ){
		if(dao == null){
				dao = new BiodiversityDAO();  
		}
		return dao;
	}
	
	
	
	@Override
	public JSONArray getSites() {
		JSONArray sites = new JSONArray();
		try {
			if(connection.isClosed()) openConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 Statement statement;
		try {
			statement = connection.createStatement();
		      ResultSet rs = statement.executeQuery("select title," +
		      		" description, latitude, longitude, image from sites");
		      while(rs.next()){
		    	  	JSONObject s = new JSONObject();
		    	  	s.put("title", rs.getString(1));
		    	  	s.put("img",rs.getString(5));
		    	  	s.put("lat", rs.getDouble(3));
		    	  	s.put("lon", rs.getDouble(4));
		    	  	sites.add(s);
		    	  }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      // Result set get the result of the SQL query
		
		
		return sites;
	}

	@Override
	public void openConnection() {
		// TODO Auto-generated method stub
		 try {
			DriverManager.registerDriver(new AppEngineDriver());
			connection = DriverManager.getConnection(
					 "jdbc:google:rdbms://xochimilcoccm:xochimilco/xochimilco");		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void agrega(String nombre, String descripcion) {			
		 try {
			 if(connection.isClosed()) openConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into capa (nombre, descripcion) " +
					" VALUES ('" + nombre + "','" + descripcion+ "')");
									
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	public void agregaBiod(String nombre, String descripcion, String imagen, String video, int capa_idcapa, int icono_idicono, float latitud, float altitud) {
		 try {
			 if(connection.isClosed()) openConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into puntos (nombre, descripcion, imagen, video, capa_idcapa, icono_idicono, latitud, altitud) " +
					" VALUES ('" + nombre + "','" + descripcion+ "','" + imagen + "','" + video + "','" + capa_idcapa + "','" + icono_idicono + "','" + latitud + "','" + altitud + "')");
									
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

