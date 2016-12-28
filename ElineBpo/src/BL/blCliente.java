package BL;

import BE.beCliente;
import DAO.daoCliente;

public class blCliente 
{
	daoCliente daoCliente = new daoCliente();
	
	public beCliente[] obtenerCliente(String solicitud)
	{
		return daoCliente.obtenerCliente(solicitud);
	}
}
