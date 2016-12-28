package DAO;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.content.Context;
import android.widget.Toast;

public class daoCoordenadas 
{
   	public String InsertCoordernadas(String latitud, String longitud, String usuario, int tipo, String numero)
	{
   		String mensaje = null;
   		SoapObject request = null;
   
   		if(tipo == 1)
   		{
   			request = new SoapObject(daoUtilitarios.NAMESPACE, daoUtilitarios.METHOD_NAMEG);
   			request.addProperty("latitud",latitud); 
   			request.addProperty("longitud",longitud);
   			request.addProperty("usuario",usuario);
   		}
   		else if(tipo == 2)
   		{
   			request = new SoapObject(daoUtilitarios.NAMESPACE, daoUtilitarios.METHOD_NAMEU);
   			request.addProperty("nro_solicitud",numero); 
   			request.addProperty("posicion_x",latitud); 
   			request.addProperty("posicion_y",longitud);
   			request.addProperty("usuario",usuario);
   		}
   		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true; 
		envelope.setOutputSoapObject(request);
		
		HttpTransportSE transporte = new HttpTransportSE(daoUtilitarios.URL);

		try 
		{
			if(tipo == 1)
			{
				transporte.call(daoUtilitarios.SOAP_ACTIONG, envelope);
			}
			else if(tipo == 2)
			{
				transporte.call(daoUtilitarios.SOAP_ACTIONU, envelope);
			}

			SoapPrimitive responde =(SoapPrimitive)envelope.getResponse();
			String res = responde.toString();
			mensaje=res;
		} 
		catch (Exception e) 
		{
			Toast.makeText(getBaseContext(),daoUtilitarios.mensajeError.toString(),Toast.LENGTH_SHORT).show();
		} 	
		
		return mensaje;
	}

	private Context getBaseContext() 
	{
		return null;
	}
}
