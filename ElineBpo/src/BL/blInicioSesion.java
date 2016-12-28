package BL;

import DAO.daoInicioSesion;

public class blInicioSesion 
{
	daoInicioSesion daoInicioSesion = new daoInicioSesion();
	
	public String Logeo(String usuario, String password)
	{
		return daoInicioSesion.Logeo(usuario, password);
	}
}
