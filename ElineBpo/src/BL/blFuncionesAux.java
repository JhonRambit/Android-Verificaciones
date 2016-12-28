package BL;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import BE.beFecha;
import DAO.daoFuncionesAux;

public class blFuncionesAux 
{
	daoFuncionesAux daoFuncionAuxx = new daoFuncionesAux();
	
	public String ActivaUbicacion(LocationManager locManager, LocationListener locListener,Location loc, int tipo)
	{
		return daoFuncionAuxx.ActivaUbicacion(locManager, locListener, loc, tipo);
	}
	
	public beFecha[] fechaServer(String nom_usu)
	{
		return daoFuncionAuxx.FechaServer(nom_usu);
	}
}
