package BE;

public class beVeriAsignadas 
{
	// PROPIEDADES Y CONSTRUCTORES REGISTRO VERIFICACIONES ...!! 
 	public String cantidad;
 	
 	public beVeriAsignadas (String usuario)
 	{
 		this.cantidad=usuario;
 	}

 	// CREAMOS EL CONSTRUCTOR VACIO ...!! 
 	public beVeriAsignadas(){}

 	public String getNombre()
 	{
 		return cantidad;
 	}
}
