package BE;

public class beTipoPersona 
{
	// PROPIEDADES Y CONSTRUCTORES TIPO PERSONA ...!! 
	public String Nom_usuario;
 	public String Nom_persona;
	public String Ap_paterno;
	public String Ap_materno;
 	public String Desc_tipo;
 	public String Id_tipopersona;
 	
	public beTipoPersona(String nom_usuario, String nom_persona,String ap_paterno,
					     String ap_materno, String desc_tipo,String id_tipopersona)
	{
		this.Nom_usuario=nom_usuario;
		this.Nom_persona=nom_persona;
		this.Ap_paterno=ap_paterno;
		this.Ap_materno=ap_materno;
		this.Desc_tipo=desc_tipo;
		this.Id_tipopersona=id_tipopersona;
	}
	
	// CREAMOS EL CONSTRUCTOR VACIO ...!! 
	public beTipoPersona(){}
		
	public String getNom_usuario()
	{
		return Nom_usuario;
	}	
	
	public String getNom_persona()
	{
		return Nom_persona;
	}
	
	public String getAp_paterno()
	{
		return Ap_paterno;
	}
	
	public String getAp_materno()
	{
		return Ap_materno;
	}
		
	public String getDesc_tipo()
	{
		return Desc_tipo;
	}
	
	public String getId_tipopersona()
	{
		return Id_tipopersona;
	}
}
