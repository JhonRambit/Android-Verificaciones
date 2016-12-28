package BL;

import BE.beTipoPersona;
import DAO.daoTipoPersona;

public class blTipoPersona 
{
	daoTipoPersona  daoTipoPersona = new daoTipoPersona();
	
	public beTipoPersona[] obtenerTipoPersona(String usuario)
	{
		return daoTipoPersona.obtenerTipoPersona(usuario); 
	}
}
