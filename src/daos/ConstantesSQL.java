package daos;

public class ConstantesSQL {

	
	public static final String LISTAR_PREGUNTAS = "SELECT * FROM preguntas";
	public static final String SELECCION_RESPUESTASPREGUNTAS ="select * from respuestaspreguntas";
	
	//SQL para registrar respuestas del formulario
	public static final String REGISTRAR_RESPUESTA_TEXTO = "insert into textrespuestas (respuesta, idpregunta) values (?,?)";
	public static final String REGISTRAR_RESPUESTA_RADIO = "insert into radiorespuestas (respuesta, idpregunta) values (?,?)";
	public static final String REGISTRAR_RESPUESTA_CHECK = "insert into checkrespuestas (respuestas, idpregunta) values (?,?)";
	
	//SQL NECESARIAS PARA LA IMPLEMENTACION DE CODIGO DE [ISMAEL]
	public static final String IDENFITICAR_USUARIO = "select id from usuarios where usuario like ? and password like ?";
	public static final String IDENFITICAR_USUARIO_ADMIN = "select id from administradores where"
			+ " usuario like ? and password like ?";
	public static final String REGISTRAR_USUARIO = "insert into usuarios (usuario,password,nombre,apellidos,"
			+ "fechaNacimiento,email,dni,municipio) values (?,?,?,?,?,?,?,?)";
	public static final String LISTAR_PAISES = "SELECT * FROM paises";
	public static final String LISTAR_COMUNIDADES = "SELECT * FROM comunidades where pais = ?";
	public static final String LISTAR_MUNICIPIOS = "SELECT * FROM municipios where provincia_id = ?";
	public static final String IDENTIFICAR_TIPO_PREGUNTA = "SELECT tipo FROM preguntas where idpregunta = ?";
	public static final String EXTRAER_VALORES_DISTINTOS_RESPUESTA = "select distinct(respuesta) FROM radiorespuestas where idpregunta = ?";
	public static final String CONTAR_RESPUESTAS_IGUALES = "Select Count(respuesta) from radiorespuestas where idpregunta = ? and respuesta like(?)";
	//FIN SQLS [ISMAEL]
	public static final String BORRAR_PREGUNTA = "delete from preguntas where idpregunta = ? ";
	public static final String BORRAR_RESPUESTA = "delete from respuestaspreguntas where idpregunta = ? ";
	public static final String OBTENER_PREGUNTA_POR_ID = "select * from preguntas where idpregunta = ? ";
	public static final String REGISTRAR_PREGUNTA_ADMIN = "insert into preguntas (descripcion, tipo) values (?,?)";
	public static final String REGISTRAR_RESPUESTA_ADMIN = "insert into respuestaspreguntas (descripcion, idpregunta) values (?,?)";
	
	public static final String EDITAR_PREGUNTA = "update preguntas set descripcion = ? where idpregunta=?";
	public static final String EDITAR_RESPUESTA = "update respuestaspreguntas set descripcion = ? where idpregunta=?";
	public static final String OBTENER_RESPUESTA_POR_ID = "select * from respuestaspreguntas where idpregunta = ?";
	public static final String DATOS_ESTADISTICOS_PEROSNALES = "SELECT fechaNacimiento, municipio FROM endometriosis.usuarios WHERE id = ?";
	public static final String CREAR_RVP = "INSERT INTO si06_form_datos_estadist (SI06_MUNICIPIO,SI06_FECHA_NACIMIENTO,SI06_PAIS)"
			+ "VALUES (?,?,null)";
	public static final String EXTRAER_ID_REGISTRADO = "SELECT @@IDENTITY AS id";
	public static final String NxN_ESTAD_TEXT = "INSERT INTO si11_form_est_textrespuesta (SI11_ID_FORM_DATOS_ESTADIST,SI11_ID_TEXTRESPUESTA)"
			+ "VALUES (?,?)";
}
