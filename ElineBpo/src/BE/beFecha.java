package BE;

public class beFecha 
{
	// PROPIEDADES Y CONSTRUCTORES REGISTRO VERIFICACIONES ...!! 
 	public String Nom_usuario;
 	public String Fecha;
 	
	public beFecha (String nom_usuario,String fecha)
	{
		this.Nom_usuario=nom_usuario;
		this.Fecha=fecha;
	}
	
	// CREAMOS EL CONSTRUCTOR VACIO ...!! 
	public beFecha(){}
			
	public String getNroSolicitud()
	{
		return Nom_usuario;
	}
	
	public String getFecha()
	{
		return Fecha;
	}
}
