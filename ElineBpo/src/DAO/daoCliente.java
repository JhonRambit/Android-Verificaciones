package DAO;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import BE.beCliente;

public class daoCliente 
{
	beCliente tipCliente = new beCliente();
	
	public beCliente[] obtenerCliente(String solicitud)
	{
		beCliente[] listaCliente = null;

		 SoapObject resquest = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMECI);
		    resquest.addProperty("nom_usuario", solicitud);
		   
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.dotNet= true;
		    envelope.setOutputSoapObject(resquest);
		    
		    HttpTransportSE transporte= new HttpTransportSE(daoUtilitarios.URL);
		    
		    try 
		    {
		       	transporte.call(daoUtilitarios.SOAP_ACTIONCI, envelope);
		    	SoapObject resSoap =(SoapObject)envelope.getResponse();
		    	
		    	listaCliente= new beCliente[resSoap.getPropertyCount()];
		    	for (int i = 0; i < listaCliente.length; i++) 
		    	{
					SoapObject in= (SoapObject)resSoap.getProperty(i);
					
					tipCliente.Nombre=in.getProperty(0).toString();					
					tipCliente.Paterno=in.getProperty(1).toString();
					tipCliente.Materno=in.getProperty(2).toString();
					tipCliente.Razon_Social=in.getProperty(3).toString();
					tipCliente.Direccion=in.getProperty(4).toString();
					tipCliente.Distrito=in.getProperty(5).toString();
					tipCliente.Provincia=in.getProperty(6).toString();
					tipCliente.Departamento=in.getProperty(7).toString();
					tipCliente.Id_Verificacion=in.getProperty(8).toString();
					tipCliente.Id_Formato=in.getProperty(9).toString();
					tipCliente.Observaciones=in.getProperty(10).toString();
					tipCliente.Entidad=in.getProperty(11).toString();
					tipCliente.Fecha=in.getProperty(12).toString();
					
					listaCliente[i]= tipCliente;
				}
			} 
		    catch (Exception e) 
		    {
		    	e.printStackTrace();
			}	
		    
			return listaCliente;
	}
}
