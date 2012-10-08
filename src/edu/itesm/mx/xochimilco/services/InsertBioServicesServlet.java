package edu.itesm.mx.xochimilco.services;

import java.io.IOException;
import javax.servlet.http.*;

import edu.itesm.mx.xochimilco.dao.BiodiversityDAO;
import edu.itesm.mx.xochimilco.dao.BiodiversitySitesDAO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class InsertBioServicesServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	
		String nombre = (String) req.getParameter("nombre");
		String descripcion = (String) req.getParameter("descripcion");
		String imagen = (String) req.getParameter("imagen");
		String video = (String) req.getParameter("video");
		int icono_idicono =  Integer.parseInt(req.getParameter("icono_idicono"));
		float latitud = Float.parseFloat(req.getParameter("latitud"));
		float altitud = Float.parseFloat(req.getParameter("altitud"));
		//Instancia de DAO
		BiodiversityDAO dao = BiodiversityDAO.getInstance();
		//Abrir conexión
		dao.openConnection();
		//Ejecutar método
		dao.agregaBiod(nombre, descripcion, imagen, video, 4, icono_idicono, latitud, altitud);
		//Regresa al mapa
		resp.sendRedirect("index.html");
	}
}
