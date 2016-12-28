package DAO;

public class daoUtilitarios 
{
	// MENSAJE REGISTRO ...!!
	public static String mensajeError ="No hay conexion a Internet";
	public static String DatoVacio="anyType{}";
	public static String activador ="GPS Desactivado";
	public static String mensajeCargaLista="No se pudo actualizar por falta de datos ...!!";
	
	// DATOS GENERALES ...!! 
	public static String NAMESPACE = "http://tempuri.org/";
	public static String URL="http://200.60.7.90:8094/Service1.asmx"; 
	
	// DATOS GPS ...!!
	public static String SOAP_ACTIONG = "http://tempuri.org/Gps";
	public static String METHOD_NAMEG = "Gps";
	
	// DATOS LOGIN ...!!
	public static String SOAP_ACTIONL = "http://tempuri.org/LoginUsuario";						
	public static String METHOD_NAMEL = "LoginUsuario";
	
	// DATOS TIPOS PERSONA ...!!
	public static String SOAP_ACTIONT = "http://tempuri.org/ObtenerTipoPersona";								
    public static String METHOD_NAMET = "ObtenerTipoPersona";
    
    // DATOS VERIFICANDO SOLICITUD ...!!
 	public static String SOAP_ACTIONV = "http://tempuri.org/VerificandoSolicitud";
 	public static String METHOD_NAMEV = "VerificandoSolicitud";
 	
 	// DATOS VERIFICANDO FOTO ...!!
 	public static String SOAP_ACTIONF = "http://tempuri.org/ObtenerVerificacionFoto";								
 	public static String METHOD_NAMEF = "ObtenerVerificacionFoto";
 	
 	// DATOS FECHA ...!!
	public static String SOAP_ACTIONS = "http://tempuri.org/FechaServer";								
    public static String METHOD_NAMES = "FechaServer";
    
	// DATO UBICACIÓN ...!!
	public static String SOAP_ACTIONU = "http://tempuri.org/RegistroUbicacion";								
    public static String METHOD_NAMEU = "RegistroUbicacion";
    
    // DATOS VERIFICACION ASIGNADA ...!!
 	public static String SOAP_ACTIONA = "http://tempuri.org/ObtenersSolicitudAsignadas";								
 	public static String METHOD_NAMEA = "ObtenersSolicitudAsignadas";
 	
	// DATOS CARGA VERIFICACION ...!!
	public static String SOAP_ACTIONC = "http://tempuri.org/CargarDatos";
	public static String METHOD_NAMEC = "CargarDatos";

	// DATOS BUSCAR VERIFICACION ...!!
	public static String SOAP_ACTIOND = "http://tempuri.org/BuscarDatos";
	public static String METHOD_NAMED = "BuscarDatos";
	
	// DATOS BUSCAR CODIGO VERIFICACION ...!!
	public static String SOAP_ACTIONB = "http://tempuri.org/BuscarSolicitud";
    public static String METHOD_NAMEB = "BuscarSolicitud";
    
    // OBTENER LA LISTA DEL CLIENTE DE LA VERIFICACION ...!!
	public static String SOAP_ACTIONCI = "http://tempuri.org/ObtenerDatosVerificacion";								
    public static String METHOD_NAMECI = "ObtenerDatosVerificacion";
}
