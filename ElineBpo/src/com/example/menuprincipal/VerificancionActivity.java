package com.example.menuprincipal;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import BE.beVeriAsignadas;
import BL.blVerificacion;
import DAO.daoVerificacion;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class VerificancionActivity extends Activity implements OnItemClickListener, OnClickListener 
{
	// REFERENCIA A CLASES ...!!
	blVerificacion BlVerificacion = new blVerificacion();
	
	// VARIABLES DE LA NOTIFICACIÓN MENSAJE ...!!
    NotificationManager 	 nm;
    Notification 		   	 notif;
    
     static String  		 ns = Context.NOTIFICATION_SERVICE;
    
    private ProgressDialog 	 dialog;
    
 	public String 			 usuario, tipoPersona, Asignadas, pNro, pTitul, pTipFormat;
	
	public int 				 contador, estadoBusqueda;
	public int 				 icono_Eline = R.drawable.icono;
	
	// LOS COMPONENTES DEL ACTIVITY ...!!
	ScheduledExecutorService scheduledExecutorService;
	EditText 				 txtVeriNroSolicitud,txtVeriCantidadAsignadas;
	TextView 				 lblVeriTituloBienvenido, lblVeriNroSolicitud;
	Button 					 btnVeriProcesar,btnVeriBuscar;
	GridView 				 dgvdatos;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verificacion);
		
		// USUARIO LOGEADO MENU PRINCIPAL ...!! 
		usuario=getIntent().getStringExtra("usuario");
		tipoPersona= getIntent().getStringExtra("id_tipoPersona");
		
		lblVeriTituloBienvenido= (TextView) findViewById(R.id.lblTituloBienvenido);
		lblVeriNroSolicitud=(TextView) findViewById(R.id.lblNroSolicitud);
		
		txtVeriNroSolicitud=(EditText) findViewById(R.id.txtNroSolicitud);
		txtVeriCantidadAsignadas=(EditText) findViewById(R.id.txtAsignadas);
		
		dgvdatos=(GridView) findViewById(R.id.dgvVerificacion);
		dgvdatos.setOnItemClickListener(this);
		
		btnVeriBuscar=(Button) findViewById(R.id.btnBuscarV);
		btnVeriBuscar.setOnClickListener(this);
		
		btnVeriProcesar=(Button) findViewById(R.id.btnProcesar);
		btnVeriProcesar.setOnClickListener(this);
		
		// OBTENER DATOS VERIFICACIONES ASIGNADAS ...!!
		this.ObtenerDatos();
		
		// SE ENCARGA DE CARGAR LOS DATOS EN LA TABLA Y EL PROGRESO BARRA Y LA TABLA CON SUS DATOS ...!! 
		new asyntodos().execute();				
	}

	public void ObtenerDatos()
    {
		// REGISTRO FECHA ...!!
		beVeriAsignadas[] veriAsignadas = BlVerificacion.VerificacionesAsignadas(usuario);
		for (int i = 0; i < veriAsignadas.length; i++) 
		{
			Asignadas= veriAsignadas[i].cantidad.toString();
		}
		
		if(Asignadas.equals("0") )
    	{
    		// QUITAMOS EL MENSAJE DE LA NOTIFICACIÓN ...!!
    		nm.cancel(1);
    	}
    	else
    	{
    		// INICIO EL SERVICIO DE NOTIFICACIÓN ACCEDIENDO ...!!
    	    nm = (NotificationManager) getSystemService(ns);
    	 
    	    // REALIZO UNA NOTIFICACIÓN POR MEDIO DE UN METODO ...!!
    	    notificacion(icono_Eline, "Pendientes Actualizados", "E Line Bpo", "Verificaciones Asignadas: " + Asignadas.toString());
    	    
    	    // LANZO LA NOTIFICACIÓN ...!!
    	    nm.notify(1, notif);
    	    
    	    // VERIFICAR LAS ASIGNADAS CIERTO TIEMPO ...!!
    	    //this.ActualizaAsignada();
    	}  
    }
	
	// RECORRER CADA CIERTO TIEMPO EL CONTADOR DE LAS VERIFICACIONES ASIGNADAS ...!!
	@SuppressLint("NewApi")
	public void ActualizaAsignada()
	{
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
						// OBTENIENDO DATOS ASIGNADOS DE CADA USUARIO LOGEADO ...!!
						ObtenerDatos();
					}
				});	
			}
			
		// ASIGNAMOS EL TIEMPO CUANDO SE EJECUTE LA NOTIFICACION DEL MENSAJE ...!!
		},15,15, TimeUnit.MINUTES);
	}
	
	@SuppressWarnings("deprecation")
	public void notificacion(int icon, CharSequence textoEstado, CharSequence titulo, CharSequence texto) 
	{
		// CAPTURA LA HORA DEL EVENTO ...!!
        long hora = System.currentTimeMillis();
         
        // DEFINICMOS LA ACCIÓN DE LA PULSACIÓN SOBRE LA NOTIFICACIÓN ...!!
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(this, VerificancionActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
 
        // DEFINIMOS LA NOTIFICACION DE MENSAJE (ICONO, TEXTO Y HORA) ...!!
        notif = new Notification(icon, textoEstado, hora);
        notif.setLatestEventInfo(context, titulo, texto, contentIntent);
        
        // NOTIFICACION DE SONIDO ...!!
        notif.defaults |= Notification.DEFAULT_SOUND;
                
        // EL ATRIBUTO FLAGS DE LA NOTIFICACIÓN PERMITE CIERTAS OPCIONES ...!!
        notif.flags = Notification.FLAG_ONGOING_EVENT;
    }  
	
	// CARGAR DATOS EN EL GRIDVIEW ...!!
	public void cargar_elementos(String[] carga)
	{
		// ASIGNAMOS LOS DATOS A LA TABLA ...!!
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, carga);
		dgvdatos.setAdapter(adaptador);		
	}
	
	class asyntodos extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() 
		{
			dialog= new ProgressDialog(VerificancionActivity.this);
			dialog.setMessage("Cargando Solicitud ...!!");
			dialog.setIndeterminate(false);
			dialog.setCancelable(false);
			dialog.show();
		}
		
		@Override
		protected String doInBackground(String... arg0) 
		{
			String numero = "";
			boolean Estado;
			
			Estado = BlVerificacion.invocarWBU(usuario, tipoPersona, 1,numero);
			if(Estado == true)
			{
				numero= "WBU";
			}
			else if(Estado == false)
			{
				numero = "Error";
			}
			
			return numero;
		}
		
		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			
			String [] carga  = daoVerificacion.cargardatos;
			
			dialog.dismiss();
			
			if(result.equals("WBU"))
			{
				cargar_elementos(carga);
			}
		}
	}
	
	class asyntodosP extends AsyncTask<String, String, String>
	{	
		@Override
		protected void onPreExecute() 
		{
			dialog= new ProgressDialog(VerificancionActivity.this);
			dialog.setMessage("Buscando Solicitud ...!!");
			dialog.setIndeterminate(false);
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) 
		{
			String numero = txtVeriNroSolicitud.getText().toString();
			
			// DEVUELVE UN VALOR TRUE, FALSE ...!!
			if(BlVerificacion.invocarWBU(usuario, tipoPersona, 2, numero))
			{
				// SI ES TRUE EL TEXTO SERA WBS ...!!
				return "WBP";
			}
			else
			{
				// SI ES FALSE EL TEXTO SERA ERROR ...!!
				return "Error";
			}
		}
		
		@Override
		protected void onPostExecute(String result) 
		{
			dialog.dismiss();
			
			// VERIFICAMOS EL TEXTO DEL PROCEDIMIENTOS WBP...!!
			if(result.equals("WBP"))
			{
				// SI CONINCIDE CARGAMOS LOS DATOS AL DATAGRIEDVIEW CON SUS PARAMETROS...!!
				//cargar_elementos(carga2);
			}
		}
	}
	
	@SuppressWarnings("unused")
	// AVISAR SI ES UN NUMERO O NO .. !! 
	private Boolean esnumero (String valor)
	{
		try 
		{
			// VERIFICA SI SE PUEDE CONVERTIR O NO ... !!
			int d=Integer.valueOf(valor);	
		} 
		catch (NumberFormatException e) 
		{
			return false;
		}	
		
		return true;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int posicion, long arg3) 
	{
		String [] carga = daoVerificacion.cargardatos;
		int 	  estado = daoVerificacion.estado;
		
		if(estado==1)
		{
		    // ENCARGA PASAR LOS DATOS DEL GRID AL TEXTO ...!!
			if(esnumero(carga[posicion]))
			{
				txtVeriNroSolicitud.setText(carga[posicion]);
						
				// ASIGNAMOS CADA COLUMNA A CADA TEXTOS ...!!
				pNro=carga[posicion];
				pTipFormat=carga[posicion+1];
			}
		}
		
		//if (estado==2)
		//{
		//	// ENCARGA PASAR LOS DATOS DEL GRID AL TEXTO ...!!
		//	if(esnumero(carga2[posicion]))
		//	{
		//		txtVeriNroSolicitud.setText(carga2[posicion]);
		//	
		//		// ASIGNAMOS CADA COLUMNA A CADA TEXTOS ...!!
		//		pNro=carga2[posicion];
		//		pTipFormat=carga2[posicion+1];
		//	}
		//}
	}

	@Override
	public void onClick(View boton) 
	{
		if(boton.getId()== findViewById(R.id.btnBuscarV).getId())
		{
			if(txtVeriNroSolicitud.getText().length()==0)
			{
				txtVeriNroSolicitud.setError("Debe Seleccionar el Nro. Solicitud ..!!");
			}
			else
			{
				String numero = txtVeriNroSolicitud.getText().toString();
				String rpt= BlVerificacion.buscarSolicitud(numero, tipoPersona);
				
				if(rpt.equals("Nro. Solicitud ingresado existe ..!!"))
				{		
					// CONSULTA LA BUSQUEDA DE VERIFICACION CON SU PARAMETRO ...!!
					new asyntodosP().execute();	
				}
				else
				{
					//MOSTRAMOS LA RESPUESTA SI NO SE ENCUENTRA SOLICITUDES ASIGNADAS AL USUARIO ...!!
					Toast.makeText(getBaseContext(), rpt, Toast.LENGTH_SHORT).show();
				}
			}
		}
		
		if(boton.getId() == findViewById(R.id.btnProcesar).getId())
		{
			if(txtVeriNroSolicitud.getText().length()==0)
			{
				txtVeriNroSolicitud.setError("Debe Seleccionar el Nro. Solicitud ..!!");
			}
			else
			{
				String num= txtVeriNroSolicitud.getText().toString();
				if(pTipFormat.equals("EST-DOM"))
				{
					this.pasarActividad(num,pTipFormat,tipoPersona,usuario,DomicilioActivity.class);
				}
				else if(pTipFormat.equals("EST-LAB"))
				{
					this.pasarActividad(num,pTipFormat,tipoPersona,usuario,LaboralActivity.class);
				}
				else if(pTipFormat.equals("EST-EMP"))
				{
					this.pasarActividad(num,pTipFormat,tipoPersona,usuario,EmpresaActivity.class);
				}
				else if(pTipFormat.equals("ESP-CCO"))
				{
					this.pasarActividad(num,pTipFormat,tipoPersona,usuario,ComercialActivity.class);
				}
				else if(pTipFormat.equals("EST-INF"))
				{
					this.pasarActividad(num,pTipFormat,tipoPersona,usuario,InformalActivity.class);
				}		
			}
		}
	}
	
	public void pasarActividad(String numero, String formato, String tipoPersona, String usuario, Class<?> activity)
	{
		// SELECIONA LA ACTIVIDAD REGISTRO VERIFICACIONES ...!!
		Intent optRegverificaciones= new Intent(this,activity);	
		optRegverificaciones.putExtra("ftid", numero.toString());
		optRegverificaciones.putExtra("fTipFormat", formato.toString());
		optRegverificaciones.putExtra("ftipopersona", tipoPersona);
		optRegverificaciones.putExtra("usuarioVerificacion", usuario.toString());
		startActivity(optRegverificaciones);
		finish();
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
			case R.id.regresa:
				Intent optSalir = new Intent(this,MovimientosActivity.class);
				optSalir.putExtra("usuario", usuario.toString());
				startActivity(optSalir);
				finish();
				return true;
		}
	        
		return true;
	}

	@Override
 	public boolean onKeyDown(int keyCode, KeyEvent event)  
	{
		 // DEFINE AL PRECIONAR LA TECLA PACK NO VOLVAMOS PARA ATRAZ ...!!   	 
 		 if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
 		 {
 	         // NO HACE NADA ...!!
 	         return true;
 	     }

 	     if(keyCode == KeyEvent.KEYCODE_HOME) 
 	     {
	        Log.i("Botón de inicio","Click");
	     }
 	     
 	     return super.onKeyDown(keyCode, event);
	}
	
	@Override
  	public void onAttachedToWindow() 
  	{
		 this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	  	 super.onAttachedToWindow();	
  	}
}
