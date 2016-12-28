package BL;

import DAO.daoCoordenadas;

public class blCoordenadas 
{
	daoCoordenadas daoCoordenadas = new daoCoordenadas();
	
	public String InsertCoordernadas(String latitud, String longitud, String usuario, int tipo, String numero)
	{
		return daoCoordenadas.InsertCoordernadas(latitud, longitud, usuario, tipo, numero);
	}
}
