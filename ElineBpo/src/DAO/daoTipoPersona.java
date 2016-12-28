package DAO;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import BE.beTipoPersona;

public class daoTipoPersona 
{
	beTipoPersona tipPersona= new beTipoPersona();
	
	public beTipoPersona[] obtenerTipoPersona(String usuario)
	{
		beTipoPersona[] listadoNro =null;
		
	    SoapObject resquest = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMET);
	    resquest.addProperty("nom_usuario", usuario);
	   
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.dotNet= true;
	    envelope.setOutputSoapObject(resquest);
	    
	    HttpTransportSE transporte= new HttpTransportSE(daoUtilitarios.URL);
	    
	    try 
	    {
	       	transporte.call(daoUtilitarios.SOAP_ACTIONT, envelope);
	    	SoapObject resSoap =(SoapObject)envelope.getResponse();
	    	
	    	listadoNro= new beTipoPersona[resSoap.getPropertyCount()];
	    	for (int i = 0; i < listadoNro.length; i++) 
	    	{
				SoapObject in= (SoapObject)resSoap.getProperty(i);
				
				tipPersona.Nom_usuario=in.getProperty(0).toString();					
				tipPersona.Nom_persona=in.getProperty(1).toString();
				tipPersona.Ap_paterno=in.getProperty(2).toString();
				tipPersona.Ap_materno=in.getProperty(3).toString();
				tipPersona.Desc_tipo=in.getProperty(4).toString();
				tipPersona.Id_tipopersona=in.getProperty(5).toString();
				
				listadoNro[i]= tipPersona;
			}
		} 
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
		}	
	    
		return listadoNro;
	}
}
