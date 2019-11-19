package daos;

public class ConstantesSQL {
	
	// Sentencia que lista todas las preguntas que aparecen en el formulario
	public static final String LISTAR_PREGUNTAS = 	"SELECT * \r\n" + 
													"FROM preguntas \r\n" + 
													"	LEFT JOIN si18_bloque_preguntas\r\n" + 
													"    ON preguntas.bloquePregunta = si18_bloque_preguntas.SI18_ID\r\n" + 
													"where preguntas.nivel = 0 and bloquePregunta = 0 order by preguntas.orden";
	
	public static final String LISTAR_TODAS_PREGUNTAS = "SELECT * FROM preguntas order by orden";
	
	public static final String SELECCION_RESPUESTASPREGUNTAS = "SELECT * FROM respuestaspreguntas WHERE idpregunta in (SELECT idpregunta FROM preguntas where nivel = 0)";

	//SQL para registrar respuestas del formulario
	public static final String REGISTRAR_RESPUESTA_TEXTO = "insert into textrespuestas (respuesta, idpregunta) values (?,?)";
	
	public static final String REGISTRAR_RESPUESTA_RADIO = "insert into radiorespuestas (respuesta, idpregunta) values (?,?)";
	
	public static final String REGISTRAR_RESPUESTA_CHECK = "insert into checkrespuestas (respuestas, idpregunta) values (?,?)";

	public static final String REGISTRAR_RESPUESTA_SLIDER = "insert into si16_slider_respuestas (si16_respuesta, si16_id_pregunta) values (?,?)";
	
	//INICIO [IGAMARTI] SQL NECESARIAS PARA LA IMPLEMENTACION DE CODIGO DE [ISMAEL]
	public static final String IDENFITICAR_USUARIO = "select id, realizado from usuarios where usuario like ? and password like ?";
	
	public static final String IDENFITICAR_USUARIO_ADMIN = "select id from administradores where"
			+ " usuario like ? and password like ?";
	
	public static final String REGISTRAR_USUARIO = "insert into usuarios (usuario,password,nombre,apellidos,"
			+ "fechaNacimiento,email,dni,pais,municipio) values (?,?,?,?,?,?,?,?,?)";
	
	public static final String LISTAR_PAISES = "SELECT * FROM si14_paises";
	
	// version antigua listar comunidades
//	public static final String LISTAR_COMUNIDADES = "SELECT * FROM comunidades where pais = ?";
	// version nueva lista provincias
	public static final String LISTAR_COMUNIDADES = "Select * FROM provincias p\r\n" + 
													"	INNER JOIN comunidades c on c.id = p.comunidad_id\r\n" + 
													"WHERE c.pais = ? ORDER BY p.provincia";
	
	/**
	 * Consulta que lista los municipios pertenecientes a una comunidad especifica.<br>
	 */
	public static final String LISTAR_MUNICIPIOS = "SELECT * FROM municipios m\r\n" + 
														"inner join provincias p on p.id = m.provincia_id\r\n" + 
														"WHERE p.id = ?";
//	public static final String LISTAR_MUNICIPIOS = "SELECT * FROM municipios m\r\n" + 
//			"inner join provincias p on p.id = m.provincia_id\r\n" + 
//			"inner join comunidades c on c.id = p.comunidad_id\r\n" + 
//			"WHERE c.id = ?";
	
	public static final String IDENTIFICAR_TIPO_PREGUNTA = "SELECT tipo FROM preguntas where idpregunta = ?";
	// Devuelve las diferentes respuesta de las que consta la pregunta a buscar
	public static final String EXTRAER_VALORES_DISTINTOS_RESPUESTA = "select distinct(respuesta) FROM radiorespuestas where idpregunta = ?";
	// Dada una respuesta perteneciente a una pregunta, extraer cuantas veces se ha contestado
	public static final String CONTAR_RESPUESTAS_IGUALES = "Select Count(respuesta) from radiorespuestas where idpregunta = ? and respuesta like(?)";
	
	/** Dejo aqui ubicado, de cada al futuro, una consulta que saca correctamente los municipios para la provincia de salamanca
	 * 
	 * SELECT * FROM `municipios` m
			inner join provincias p on p.id = m.provincia_id
			inner join comunidades c on c.id = p.comunidad_id
			WHERE c.id = 7 and p.provincia like 'Salamanca'
	 * */
	
	
	//FIN  [IGAMARTI] SQLS
	
	public static final String BORRAR_PREGUNTA = "delete from preguntas where idpregunta = ? ";
	
	public static final String BORRAR_RESPUESTA = "delete from respuestaspreguntas where idpregunta = ? ";
	
	public static final String OBTENER_PREGUNTA_POR_ID = "select * from preguntas where idpregunta = ? ";
	
	public static final String REGISTRAR_PREGUNTA_ADMIN = "insert into preguntas (descripcion, tipo, nivel, orden, cajaAdicional, bloquePregunta) values (?,?,?,?,?,?)";
	
	public static final String REGISTRAR_RESPUESTA_ADMIN = "insert into respuestaspreguntas (descripcion, idpregunta) values (?,?)";
	
	
	public static final String EDITAR_PREGUNTA = "update preguntas set descripcion = ? where idpregunta=?";
	
	public static final String EDITAR_RESPUESTA = "update respuestaspreguntas set descripcion = ? where idpregunta=?";
	
	public static final String OBTENER_RESPUESTA_POR_ID = "select * from respuestaspreguntas where idpregunta in (SELECT idpregunta FROM preguntas where nivel = ?)";
	
	public static final String DATOS_ESTADISTICOS_PEROSNALES = "SELECT fechaNacimiento, municipio, pais FROM endometriosis.usuarios WHERE id = ?";
	
	public static final String CREAR_RVP = "INSERT INTO si06_form_datos_estadist (SI06_MUNICIPIO,SI06_FECHA_NACIMIENTO,SI06_PAIS)"
			+ "VALUES (?,?,?)";
	
	public static final String ACTUALIZAR_REALIZADO_USUARIO = "UPDATE usuarios set realizado = ? where id = ?";
	
	public static final String EXTRAER_ID_REGISTRADO = "SELECT @@IDENTITY AS id";
	
	public static final String NxN_ESTAD_TEXT = "INSERT INTO si11_form_est_textrespuesta (SI11_ID_FORM_DATOS_ESTADIST,SI11_ID_TEXTRESPUESTA)"
			+ "VALUES (?,?)";
	
	public static final String NxN_ESTAD_RADIO = "INSERT INTO si12_form_est_radiorespuesta (SI12_ID_FORM_DATOS_ESTADIST,SI12_ID_RADIORESPUESTA)"
			+ "VALUES (?,?)";
	
	public static final String NxN_ESTAD_CHECK = "INSERT INTO si13_form_est_checkrespuesta (SI13_ID_FORM_DATOS_ESTADIST,SI13_ID_CHECKRESPUESTA)"
			+ "VALUES (?,?)";
	
	public static final String NxN_ESTAD_SLIDER = "INSERT INTO si17_form_est_sliderrespuesta (SI17_ID_FORM_DATOS_ESTADIST,SI17_ID_SLIDERRESPUESTA)"
			+ "VALUES (?,?)";
	
	public static final String EXTRAER_ORDEN_PREGUNTAS = "SELECT * FROM preguntas WHERE orden >= ? ORDER by orden DESC";
	
	public static final String MODIFICAR_ORDEN_PREGUNTAS = "UPDATE preguntas set orden = ? WHERE orden = ?";
	
	public static final String EXTRAER_ORDEN_PREGUNTA = "Select orden from preguntas where idpregunta = ?";
	
	public static final String BUSCAR_DEPENDENCIA_PREGUNTA = "SELECT * FROM preguntas WHERE nivel = ?";
	
	public static final String BUSCAR_ULTIMO_ORDEN_FORMULARIO = "SELECT * FROM preguntas order by orden desc limit 1";
	
	// Inserccion de los medicamentos en la tabla SI19
	public static final String REGISTRAR_MEDICAMENTOS = "INSERT INTO si19_medicamentos(SI19_NOMBRE) VALUES (?)";
	
	// Sentencia que devuelve todos los bloque de preguntas en la BBDD.
	public static final String LISTAR_BLOQUE_PREGUNTAS = "SELECT * FROM si18_bloque_preguntas";
	
	// Sentencia que busca el id del bloque pregunta pasado por parametro
	public static final String BUSCAR_BLOQUE_PREGUNTA = "SELECT SI18_ID FROM si18_bloque_preguntas WHERE SI18_NOMBRE_BLOQUE like ?";
	
	// Sentencia que lista los medicamentos en la BDD
	public static final String LISTAR_MEDICAMENTOS = "SELECT * FROM si19_medicamentos";
	
	// Sentenci que registra los tratamientos señalados por el usuario
	public static final String REGISTRAR_TRATAMIENTOS = "insert into si10_form_est_medrespuesta (SI10_ID_FORM_DATOS_ESTADIST, SI10_ID_MEDICAMENTO, SI10_FUNCIONA) values (?,?,?)";
	
	// Sentencia que lista todas las preguntas para borrar
	public static final String LISTAR_PREGUNTAS_BORRAR = "SELECT * \r\n" + 
															"FROM preguntas \r\n" + 
															"	LEFT JOIN si18_bloque_preguntas\r\n" + 
															"    ON preguntas.bloquePregunta = si18_bloque_preguntas.SI18_ID\r\n" + 
															"order by preguntas.orden";
	
	// Sentencia que busca dentro de las preguntas si la pregunta en cuestión es la pregunta madre de maternidad
	public static final String LOCALIZAR_PREGUNTA_MADRE = "SELECT * FROM preguntas WHERE idpregunta = ? and descripcion like ('%podido ser mamá%')";
	
	// Sentencia que extrae el bloque de maternidad de preguntas
	public static final String EXTRACCION_BLOQUE_MATERNIDAD = "select respuestaspreguntas.id, respuestaspreguntas.descripcion as descripcionResp, preguntas.* from respuestaspreguntas\r\n" + 
																"inner join preguntas \r\n" + 
																"on respuestaspreguntas.idpregunta = preguntas.idpregunta\r\n" + 
																"where  bloquePregunta = 1 order by orden desc";
	
}
