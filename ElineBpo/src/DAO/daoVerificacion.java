package DAO;

import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import BE.beVeriAsignadas;
import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class daoVerificacion 
{	
	public static String[] cargardatos, cargardatos2;
	public static int estado;
		
	public String verificandoSolicitud(String usuario, String tipo_persona)
	{			 
		String mensaje = null;
		
		SoapObject resquest = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMEV);
				
		// SE ENCARGA DE PASAR LOS PARAMETROS NOMBRE USUARIO ...!!
		resquest.addProperty("nom_usuario",usuario.toString());
		resquest.addProperty("tipopersona",tipo_persona.toString());
					
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet=true;
		envelope.setOutputSoapObject(resquest);
		
		HttpTransportSE transporte = new HttpTransportSE(daoUtilitarios.URL);
		
		try 
		{
			// EJECUTA LA LLAMADA ...!!
			transporte.call(daoUtilitarios.SOAP_ACTIONV,envelope);
			
			// OBTIENE LA RESPUESTA STRING, BOOLEAN, ETC ...!!
			SoapPrimitive resultado_usu= (SoapPrimitive) envelope.getResponse();
			String res=resultado_usu.toString();
			mensaje=res;		
		} 
		catch (Exception e) 
		{
			Toast.makeText(getBaseContext(),daoUtilitarios.mensajeError.toString(),Toast.LENGTH_LONG).show();
		}
		
		return mensaje;
	}
	
	public beVeriAsignadas[] VerificacionesAsignadas(String nom_usu)
	{
		beVeriAsignadas[] listadoNro =null;
	
		SoapObject resquest = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMEA);
	    resquest.addProperty("usuario", nom_usu);
	    
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.dotNet= true;
	    envelope.setOutputSoapObject(resquest);
	    
	    HttpTransportSE transporte= new HttpTransportSE(daoUtilitarios.URL);
	    
	    try 
	    {
	       	transporte.call(daoUtilitarios.SOAP_ACTIONA, envelope);
	    	SoapObject resSoap =(SoapObject)envelope.getResponse();
	    	
	    	listadoNro= new beVeriAsignadas[resSoap.getPropertyCount()];
	    	for (int i = 0; i < listadoNro.length; i++) 
	    	{
				SoapObject in= (SoapObject)resSoap.getProperty(i);
				beVeriAsignadas veriAsignada= new beVeriAsignadas();
				
				veriAsignada.cantidad=in.getProperty(0).toString();
								
				listadoNro[i]= veriAsignada;
	    	}		
	    }
	    catch (Exception e) 
	    {
	    	Toast.makeText(getBaseContext(),daoUtilitarios.mensajeCargaLista.toString(),Toast.LENGTH_SHORT).show();
		}	
	    
		return listadoNro;  	
	}

	public Boolean invocarWBU(String usuario, String TipoPersona, int tipo, String numero)
	{		
		Boolean bandera = null;
		SoapObject respuesta =  null;
		
		if(tipo == 1)
		{
			// SE INSTANCIA UN OBJETO SOAP PASANDOLE EL NAMESPACE Y EL METODO DE WEB SERVICE A CONSUMIR ...!!
			respuesta = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMEC);
		}
		else if (tipo == 2)
		{
			// SE INSTANCIA UN OBJETO SOAP PASANDOLE EL NAMESPACE Y EL METODO DE WEB SERVICE A CONSUMIR ...!!
			respuesta = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMED);
		}
		
		// SE INSTANCIA UN OBJETO ENVELOPE Y SE DEFINE QUE VERSION DE SOAP  SE USARA (VER10,11,12) ...!!
		SoapSerializationEnvelope sobre =new SoapSerializationEnvelope(SoapEnvelope.VER11);
    
		if(tipo == 1)
		{
			// ASIGNAMOS LOS PARAMETROS ...!!
			respuesta.addProperty("nom_usuario",usuario.toString());
			respuesta.addProperty("tipopersona",TipoPersona.toString());
		}
		else if(tipo == 2)
		{
			// ASIGNAMOS LOS PARAMETROS ...!!
			respuesta.addProperty("codigo",numero.toString());
			respuesta.addProperty("tipopersona",TipoPersona.toString());
		}
					
		// INDICA SI EL WEB SERVICE FUE DESARROLLADO EN .NET ...!!
		sobre.dotNet= true;
		sobre.setOutputSoapObject(respuesta);
	
		// ESTABLECE LA CONEXION POR HTTP ...!!
		HttpTransportSE trasporte = new HttpTransportSE(daoUtilitarios.URL);
		
		try
		{
			if(tipo == 1)
			{
				// EJECUTA LA LLAMADA ...!!
				trasporte.call(daoUtilitarios.SOAP_ACTIONC, sobre);
			
				// OBTIENE LA RESPUESTA ...!!
				SoapObject response = (SoapObject) sobre.getResponse();
				SoapObject diffgram = (SoapObject) response.getProperty("diffgram");
				SoapObject newdataset =(SoapObject) diffgram.getProperty("NewDataSet");
		
				// LLENA LA TABLA CON 2 COLUMNAS ...!!
				cargardatos = new  String [newdataset.getPropertyCount() * 2];
	
				int fila  = 0;
		
				// ASIGNAMOS EL FOR PARA RECORRER, diffgram, NewDataSet  ...!!
				for(int i=0; i< newdataset.getPropertyCount(); i++)
				{
					SoapObject datosxml = (SoapObject) newdataset.getProperty(i);
					cargardatos [fila]= datosxml.getProperty(0).toString();
					cargardatos [fila+1]= datosxml.getProperty(1).toString();
	   				
					fila+=2;
				}
	    
				// SI LLENA LA TABLA CON SUS DATOS LE ASIGNAMOS UN ESTADO ...!!
				estado=1;
				bandera = true;
			}
			else if(tipo == 2)
			{
				// EJECUTA LA LLAMADA ...!!
				trasporte.call(daoUtilitarios.SOAP_ACTIOND, sobre);
			
				// OBTIENE LA RESPUESTA ...!!
				SoapObject response = (SoapObject) sobre.getResponse();
				SoapObject diffgram = (SoapObject) response.getProperty("diffgram");
				SoapObject newdataset =(SoapObject) diffgram.getProperty("NewDataSet");
		
				// LLENA LA TABLA CON 2 COLUMNAS ...!!
				cargardatos2 = new  String [newdataset.getPropertyCount() * 2];
	
				int fila  = 0;
		
				// ASIGNAMOS EL FOR PARA RECORRER, diffgram, NewDataSet  ...!!
				for(int i=0; i< newdataset.getPropertyCount(); i++)
				{
					SoapObject datosxml = (SoapObject) newdataset.getProperty(i);
					cargardatos2 [fila]= datosxml.getProperty(0).toString();
					cargardatos2 [fila+1]= datosxml.getProperty(1).toString();
	   				
					fila+=2;
				}
	    
				// SI LLENA LA TABLA CON SUS DATOS LE ASIGNAMOS UN ESTADO ...!!
				estado=2;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			bandera=false;
		}
		catch (XmlPullParserException e) 
		{
			e.printStackTrace();
			bandera=false;
		}
		
		return bandera;
	}
			
	public String buscarSolicitud(String numero, String TipoPersona)
	{
		String mensajee = null;
		
		SoapObject resquest = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMEB);
		resquest.addProperty("codigo",numero.toString());
		resquest.addProperty("tipopersona", TipoPersona.toString());
		
		// SE INSTANCIA UN OBJETO ENVELOP Y SE DEFINE QUE VERSION DE SOAP SE USARA (VER10,11,12) ..!!
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		// INDICA SI EL WEB SERVICE FUE DESARROLLADO EN .NET ...!!
		envelope.dotNet=true;
		envelope.setOutputSoapObject(resquest);
		
		// ESTABLECE LA CONEXION POR HTTP ...!!
		HttpTransportSE transporte = new HttpTransportSE(daoUtilitarios.URL);
			
		try 
		{
			// EJECUTA LA LLAMADA ...!!
			transporte.call(daoUtilitarios.SOAP_ACTIONB,envelope);
			
			// OBTIENE LA RESPUESTA STRING, BOOLEAN, ETC ...!!
			SoapPrimitive resultado_usu= (SoapPrimitive) envelope.getResponse();
			String res=resultado_usu.toString();
			mensajee=res;			
		} 
		catch (Exception e) 
		{
			Toast.makeText(getBaseContext(),daoUtilitarios.mensajeError.toString(),Toast.LENGTH_LONG).show();
		}
		
		return mensajee;
	}
	
	private Context getBaseContext() 
	{
		return null;
	}	
}
