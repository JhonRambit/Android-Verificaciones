package BE;

public class beCliente 
{
	// PROPIEDADES Y CONSTRUCTORES REGISTRO VERIFICACIONES ...!! 
 	public String Nombre;
 	public String Paterno;
 	public String Materno;
    public String Razon_Social;
	public String Direccion;
	public String Distrito;
	public String Provincia;
	public String Departamento;
	public String Id_Verificacion;
	public String Id_Formato;
	public String Observaciones;
	public String Entidad;
	public String Fecha;
	
	public beCliente(String nombre,String paterno,String materno, String razon_social, String direccion, 
					 String distrito,String provincia,String departamento, String verificacion, 
					 String formato, String observaciones,String entidad, String fecha)
	{
		this.Nombre=nombre;
		this.Paterno=paterno;
		this.Materno=materno;
		this.Razon_Social = razon_social;
		this.Direccion=direccion;
		this.Distrito=distrito;
		this.Provincia = provincia;
		this.Departamento=departamento;
		this.Id_Verificacion=verificacion;
		this.Id_Formato=formato;
		this.Observaciones=observaciones;
		this.Entidad=entidad;
		this.Fecha=fecha;
	}
	
	// CREAMOS EL CONSTRUCTOR VACIO ...!! 
	public beCliente(){}
			
	public String getNombre()
	{
		return Nombre;
	}
	
	public String getApPaterno()
	{
		return Paterno;
	}
	
	public String getApMaterno()
	{
		return Materno;
	}
	
	public String getRazonSocial()
	{
		return Razon_Social;
	}
	
	public String getDireccion()
	{
		return Direccion;
	}
	
	public String getDistrito()
	{
		return Distrito;
	}
	
	public String getProvincia()
	{
		return Provincia;
	}
	
	public String getDepartamento()
	{
		return Departamento;
	}	
	
	public String getVerificacion()
	{
		return Id_Verificacion;
	}
	
	public String getFormato()
	{
		return Id_Formato;
	}
	
	public String getObservaciones()
	{
		return Observaciones;
	}
	
	public String getEntidad()
	{
		return Entidad;
	}
	
	public String getFecha()
	{
		return Fecha;
	}
}
