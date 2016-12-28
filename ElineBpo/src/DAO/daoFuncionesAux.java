package DAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import BE.beFecha;
import BL.blCoordenadas;
import android.R;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class daoFuncionesAux 
{		
	// REFERENCIA A LAS CLASES ...!!
	blCoordenadas blCoordenada = new blCoordenadas();
	
	beFecha beFecha = new beFecha();
	
	public static String Latitud, Longitud;
	public String gps = "";
	
	// CONTADOR ...!!
	ScheduledExecutorService scheduledExecutorService;
	ScheduledFuture<?> beeperHandle;
	
	// VARIABLES DE LA NOTIFICACIÓN MENSAJE ...!!
    NotificationManager nm;
    Notification notif;
    static String ns = Context.NOTIFICATION_SERVICE;
	
	public void ObtenerHora(int h, int m)
	{
		// CALENDARIO ESPECIFICA LA HORA Y MINUTOS DE MOVIL ...!!
		Calendar c = Calendar.getInstance();
		
		// FORMATO HORA ...!!
		SimpleDateFormat FormatHora = new SimpleDateFormat("HH");

		// FORMATO MINUTO ...!!
		SimpleDateFormat FormatMinuto = new SimpleDateFormat("mm");
		
		// DEFINIR LAS VARIABLE HORA Y MINUTO ...!!
		String Hora= FormatHora.format(c.getTime());
 		String Minuto = FormatMinuto.format(c.getTime());
 		
 		// CONVERTIR VARIABLE STRING A INTEGER ...!!
 		h = Integer.parseInt(Hora);
 		m = Integer.parseInt(Minuto);
	}
	
	// RECORRER CADA CIERTO TIEMPO EL CONTADOR DE LAS VERIFICACIONES ASIGNADAS ...!!
	@SuppressLint("NewApi")
	public void ActualizaCoordenadas(final int hora,final int minuto, final String latitud,final String longitud,final String usuario)
	{			
			// REGISTRA LA SINCRONIZACION DE LAS COORDENADAS ...!!
			scheduledExecutorService = Executors.newScheduledThreadPool(1);
		    scheduledExecutorService.scheduleAtFixedRate(new Runnable() 
		    {	
				@Override
				public void run()
				{
					runOnUiThread(new Runnable() 
					{
						@Override
						public void run()
						{
							// OBTENER HORA ...!!
							ObtenerHora(hora, minuto);
								
					 		// VALIDAR SI LA HORA CUMPLE CON LA CONDICIÓN PUNTO DE PARTIDA ...!!
					 		if(hora >= 8 && hora <= 20)
					 		{
					 			// OBTENIENDO LOS DATOS DEL GPS CORDENADAS X,Y ...!!
					 			blCoordenada.InsertCoordernadas(latitud, longitud, usuario,1,null);
					 			
					 			// INICIO EL SERVICIO DE NOTIFICACIÓN ACCEDIENDO ...!!
					 			nm = (NotificationManager) getSystemService(ns);
				    	 
					 			// REALIZO UNA NOTIFICACIÓN POR MEDIO DE UN METODO ...!!
					 			notificacion(R.drawable.ic_dialog_info, "Coordenadas", "Ubicación Actual: ","Se registro con éxito ...!!");
				    	    						 			
					 			// LANZO LA NOTIFICACIÓN ...!!
					 			nm.notify(1, notif); 
					 		}
					 		else 
					 		{
					 			Toast.makeText(getBaseContext(), "Tarea Terminada Ubicación GPS...!!", Toast.LENGTH_SHORT).show();
					 		}  
						}

						private Context getBaseContext() 
						{						
							return null;
						}

						private NotificationManager getSystemService(String ns)
						{
							return null;
						}
					});	
				}
				
			// ASIGNAMOS EL TIEMPO CUANDO SE EJECUTE LA NOTIFICACION DEL MENSAJE ...!!
		    },15,15,TimeUnit.MINUTES);
		}

		@SuppressWarnings("deprecation")
		public void notificacion(int icon, CharSequence textoEstado, CharSequence titulo, CharSequence texto) 
		{
			// CAPTURA LA HORA DEL EVENTO ...!!
	        long hora = System.currentTimeMillis();
	         
	        // DEFINICMOS LA ACCIÓN DE LA PULSACIÓN SOBRE LA NOTIFICACIÓN ...!!
	       	//Context context = getApplicationContext();
	        //Intent notificationIntent = new Intent();
	   // -->  PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
	 
	        // DEFINIMOS LA NOTIFICACION DE MENSAJE (ICONO, TEXTO Y HORA) ...!!
	        notif = new Notification(icon, textoEstado, hora);
	   // -->  notif.setLatestEventInfo(context, titulo, texto, contentIntent);
	        
	        // NOTIFICACION DE SONIDO ...!!
	        notif.defaults |= Notification.DEFAULT_SOUND;
	                
	        // EL ATRIBUTO FLAGS DE LA NOTIFICACIÓN PERMITE CIERTAS OPCIONES ...!!
	        notif.flags = Notification.FLAG_ONGOING_EVENT;
	    }
		
		//private Context getApplicationContext() 
		//{
		//	return null;
		//}

		protected void runOnUiThread(Runnable runnable) {}
		
		
		// ---------------------------------------------------------------------------------------------------
	
		public String ActivaUbicacion(LocationManager locManager, LocationListener locListener,Location loc, int tipo)
		{
			// OBTENEMOS UNA REFERENCIA AL LOCATIONMANAGER ...!!
	    	locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    	
	    	// OBTENEMOS LA ULTIMA POSICIÓN CONOCIDA ...!!
	    	if(tipo ==1)
	    	{
	    		loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    	}
	    	else if(tipo==2)
	    	{
	    		loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    	}
	    	
	    	// MOSTRAMOS LA ULTIMA POSICIÓN DESCONOCIDA ...!!
	    	mostrarPosicion(loc);
	    	
	    	// NOS REGISTRAMOS PARA RECIBIR ACTUALIZACIONES DE LA POSICIÓN ...!!
	    	locListener = new LocationListener() 
	    	{
		    	public void onLocationChanged(Location location) {mostrarPosicion(location);}
		    	
		    	public void onProviderDisabled(String provider){}
		    	
		    	public void onProviderEnabled(String provider){}
		    	
		    	public void onStatusChanged(String provider, int status, Bundle extras){}
	    	};
	    	
	    	if(tipo == 1)
	    	{
	    		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locListener);	
	    	}
	    	else if(tipo == 2)
	    	{
	    		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locListener);	
	    	}
	    	
	    	return gps;
		}
		
		private LocationManager getSystemService(String locationService) 
		{
			return null;
		}

		private void mostrarPosicion(Location loc) 
		{
			if(loc != null)
			{
				Latitud = (String.valueOf(loc.getLatitude()));
				Longitud = (String.valueOf(loc.getLongitude()));
				
				gps="Se registro con éxito ...!!";
			}
			else
			{
				Latitud = ("GPS desactivado");
				Longitud = ("GPS desactivado");
				
				gps="No se registro con éxito ...!!";
			}
		}
		
		public beFecha[] FechaServer(String nom_usu)
		{
			beFecha[] listadoNro =null;
		
			SoapObject resquest = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMES);
		    resquest.addProperty("nom_usuario", nom_usu);
		    
		    // SE INSTANCIA UN OBJETO ENVELOPE Y SE DEFINE QUE VERSION DE SOAP  SE USARA (VER10,11,12) ...!!
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		   
		    // INDICA SI EL WEB SERVICE FUE DESARROLLADO EN .NET ...!!
		    envelope.dotNet= true;
		    envelope.setOutputSoapObject(resquest);
		    
		    // ESTABLECE LA CONEXION POR HTTP ...!!
		    HttpTransportSE transporte= new HttpTransportSE(daoUtilitarios.URL);
		    
		    try 
		    {
		       	transporte.call(daoUtilitarios.SOAP_ACTIONS, envelope);
		    	SoapObject resSoap =(SoapObject)envelope.getResponse();
		    	
		    	listadoNro= new beFecha[resSoap.getPropertyCount()];
		    	for (int i = 0; i < listadoNro.length; i++) 
		    	{
					SoapObject in= (SoapObject)resSoap.getProperty(i);
					beFecha fechaUsu= new beFecha();
					
					fechaUsu.Nom_usuario=in.getProperty(0).toString();
					fechaUsu.Fecha=in.getProperty(1).toString();
					
					listadoNro[i]= fechaUsu;
		    	}
		    }
		    catch (Exception e) 
		    {
		    	Toast.makeText(getBaseContext(),daoUtilitarios.mensajeError.toString(),Toast.LENGTH_SHORT).show();
			}	
		    
			return listadoNro;  	
		}

		private Context getBaseContext() 
		{
			return null;
		}
}