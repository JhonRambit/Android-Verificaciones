package BL;

import BE.beVeriAsignadas;
import DAO.daoVerificacion;

public class blVerificacion 
{
	daoVerificacion daoVerificacion = new daoVerificacion();
	
	public String VerificandoSolicitud(String usuario, String tipo_persona)
	{
		return daoVerificacion.verificandoSolicitud(usuario, tipo_persona);
	}
	
	public beVeriAsignadas[] VerificacionesAsignadas(String nom_usu)
	{
		return daoVerificacion.VerificacionesAsignadas(nom_usu); 
	}
	
	public Boolean invocarWBU(String usuario, String TipoPersona, int tipo, String numero)
	{
		return daoVerificacion.invocarWBU(usuario, TipoPersona, tipo, numero);
	}
	
	public String buscarSolicitud(String numero, String TipoPersona)
	{
		return daoVerificacion.buscarSolicitud(numero, TipoPersona);
	}
}
