package DAO;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class daoInicioSesion 
{
	public String mensaje="";
	public String mensajeError ="No hay conexion a Internet";
	
	@SuppressLint("DefaultLocale")
	public String Logeo(String usuario, String password)
	{
		SoapObject request = new SoapObject(daoUtilitarios.NAMESPACE,  daoUtilitarios.METHOD_NAMEL);
		request.addProperty("user",usuario.toUpperCase()); 
		request.addProperty("password",password ); 
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true; 
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE transporte = new HttpTransportSE(daoUtilitarios.URL);
	
		try 
		{
			transporte.call( daoUtilitarios.SOAP_ACTIONL, envelope);
		
			SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
			String res = resultado_xml.toString();
			mensaje=res;
		}
		catch (Exception e) 
		{
			Toast.makeText(getBaseContext(),mensajeError.toString(),Toast.LENGTH_SHORT).show();
		}
		return mensaje;
	}

	private Context getBaseContext() 
	{
		return null;
	}
}
