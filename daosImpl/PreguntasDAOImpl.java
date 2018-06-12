package daosImpl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.Preguntas;
import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.PreguntasDAO;

public class PreguntasDAOImpl extends GenericDAO implements PreguntasDAO {

	@Override
	public List<Preguntas> obtenerPreguntas() {
		conectar();
		List<Preguntas> preguntas = new ArrayList<Preguntas>();
		try {
			PreparedStatement ps = con
					.prepareStatement(ConstantesSQL.LISTAR_PREGUNTAS);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Preguntas pregunta = new Preguntas();
				pregunta.setIdpregunta(resultado.getInt("idpregunta"));
				pregunta.setDescripcion(resultado.getString("descripcion"));
				pregunta.setTipo(resultado.getInt("tipo"));
				preguntas.add(pregunta);

			}
		} catch (SQLException e) {
			System.out.println("Error en la SQL de listar preguntas");
			e.printStackTrace();
		}
		desconectar();
		return preguntas;
	}

	public void borrarPregunta(int idpregunta) {
		conectar();
		try{
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.BORRAR_PREGUNTA);
			ps.setInt(1, idpregunta);
			ps.execute();
			ps.close();
			System.out.println("borrado con exito");
		} catch(SQLException e){
			System.out.println("SQL borrar pregunta esta mal");
			
		}
		desconectar();
	}

	@Override
	public int buscarPreguntaPorId(int idpregunta) {
		conectar();
		int tipopregunta = -1;
		try {
			PreparedStatement ps = con
					.prepareStatement(ConstantesSQL.OBTENER_PREGUNTA_POR_ID);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				tipopregunta = resultado.getInt("tipo");
			}
		} catch (SQLException e) {
			System.out.println("Error en la SQL de obtener pregunta por id");
			System.out.println(e.getMessage());
		}
		desconectar();
		return tipopregunta;
	}
	
	@Override
	public int registrarPregunta(Preguntas nuevaPregunta) {
		conectar();
		
		int idGenerado = -1;
		
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_PREGUNTA_ADMIN, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nuevaPregunta.getDescripcion());
			ps.setInt(2, nuevaPregunta.getTipo());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				idGenerado = rs.getInt(1);
			}
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Error SQL registrar pregunta administrador");
			System.out.println(e.getMessage());
		}
		desconectar();
		
		return idGenerado;
	}
	
	@Override
	public void editarPregunta(String descripcionPregunta, int idPregunta) {
		conectar();
		
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.EDITAR_PREGUNTA);
			
			ps.setString(1, descripcionPregunta);
			ps.setInt(2, idPregunta);
			
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Error SQL editar pregunta");
			System.out.println(e.getMessage());
		}
		
		desconectar();
	}

	@Override
	public Preguntas obtenerPreguntaPorId(int idPregunta) {
		conectar();
		
		Preguntas pregunta = new Preguntas();
		
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.OBTENER_PREGUNTA_POR_ID);
			
			ps.setInt(1, idPregunta);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				pregunta.setIdpregunta(rs.getInt("idPregunta"));
				pregunta.setDescripcion(rs.getString("descripcion"));
				pregunta.setTipo(rs.getInt("tipo"));
				
			}
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Error en la SQL de editar anuncio");
			System.out.println(e.getMessage());
		}

		desconectar();
		return pregunta;
	}

}
