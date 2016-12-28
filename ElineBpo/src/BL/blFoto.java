package BL;

import android.graphics.Bitmap;
import android.widget.ImageView;
import BE.beFoto;
import DAO.daoFoto;

public class blFoto 
{
	daoFoto  daoFoto = new daoFoto();

	public beFoto[] verificandoFoto(String solicitud)
	{
		return daoFoto.VerificacionFoto(solicitud);
	}
	
	public String dibujarImagen(String fecServer, String posicion, ImageView img, Bitmap bmp, String numero)
	{
		return daoFoto.dibujarImagen(fecServer, posicion, img, bmp, numero);
	}
}
