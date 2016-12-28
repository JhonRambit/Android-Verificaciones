package com.example.menuprincipal;

import BL.blInicioSesion;
import DAO.daoFuncionesAux;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener 
{
	// LINEARLAYOUT ANIMATION ...!!
	private LinearLayout layoutAnimado;
	
	// LOCATION ...!!
 	private Location location;
 	
 	// COODENADAS ...!!
 	public Double latitud, longitud;
 	public String msje="";
 	public String usuario,password;
 	
 	// POWER MANAGER ...!!
 	protected PowerManager.WakeLock wakelock;
 	
 	blInicioSesion blInicio = new blInicioSesion();
 	
 	// DECLARAMOS LOS COMPONENTES DEL ACTIVITY ...!!
 	ProgressDialog 	dialogo;
 	ScaleAnimation 	scale;
 	RotateAnimation rotate;
 	ImageView 		logo;
 	EditText 		txtUsuario, txtpassword;
	Button 			btnAceptar;
	TextView  		lblUbiLatitudX,lblUbiLongitudY,lblUbiEstado,lblUbiDireccion;
	String    		fUsu;
	
	public daoFuncionesAux daofuncAux  = new daoFuncionesAux();
	
	@SuppressWarnings("deprecation")
	@SuppressLint({ "NewApi", "Wakelock" })
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		layoutAnimado = (LinearLayout) findViewById(R.id.linearLayout2);
		this.dibuja();
		
		logo= (ImageView) findViewById(R.id.logo);
		
		final PowerManager pm= (PowerManager) getSystemService(Context.POWER_SERVICE);
		
		this.wakelock= pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "Etiqueta");
		wakelock.acquire();
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);	
		
		// NECESARIO PARA QUE NO SALGA ERROR EN LA OPERACION DE LARGA DURACIÓN EN EL HILO PRINCIPAL...!!
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		// DECLARANDO TEXTOS ...!!
		lblUbiLatitudX=(TextView) findViewById(R.id.lblLogLatitudX);
		lblUbiLongitudY=(TextView) findViewById(R.id.lblLogLongitudY);
		lblUbiEstado=(TextView) findViewById(R.id.lblLogEstado);
		lblUbiDireccion=(TextView) findViewById(R.id.lblLogDireccion);
		
		txtUsuario =(EditText) findViewById(R.id.txtLogUsuario);
		txtpassword=(EditText) findViewById(R.id.txtLogPassword);
		btnAceptar = (Button) findViewById(R.id.btnLogverificacion);
		
		btnAceptar.setOnClickListener(this);
			
		// ACTIVAR UBICACION ...!!
		//this.ActivaUbicacion();
	}
	
	public void dibuja()
	{
		if (layoutAnimado.getVisibility() == View.GONE)
		{
			animar(true);
			layoutAnimado.setVisibility(View.VISIBLE);
		}
	}
	
	private void animar(boolean mostrar)
	{
		AnimationSet set = new AnimationSet(true);
		Animation animation = null;
		if (mostrar)
		{
			//desde la esquina inferior derecha a la superior izquierda
			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		}
		else
		{    //desde la esquina superior izquierda a la esquina inferior derecha
			 animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		}
		//duraciÃ³n en milisegundos
		animation.setDuration(500);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);

		layoutAnimado.setLayoutAnimation(controller);
		
		layoutAnimado.startAnimation(animation);
	}
	
	public void ActivaUbicacion()
	{
		// POSICIÓN LATITUD ...!!
		latitud = this.location.getLatitude();
		
		// CONVERTIR DE DOUBLE A STRING	 ...!!
		String lat= String.valueOf(latitud);
		lblUbiLatitudX.setText(lat);
		
		// POSICION LONGITUD ...!!
		longitud=this.location.getLongitude();
		
		// CONVERTIR DE DOUBLE A STRING ...!!
		String log = String.valueOf(longitud);
		lblUbiLongitudY.setText(log);
	}

	// CLASE PARA SINCRONIZAR LA TAREA AL MOMENTO DE REGISTRAR LA VERIFICACIÓN ...!!
	class asynInicioSesion extends AsyncTask<String, Integer, String>
	{	
		// LO QUE SE HACE ANTES SE CARGA EL PROGRESS DIALOGO ...!!
		@Override
		protected void onPreExecute() 
		{
			// SE EJECUTA EL PRIMER HILO ..!!
			dialogo= new ProgressDialog(LoginActivity.this);
			dialogo.setTitle("Iniciando Sessión ...!!");
			dialogo.setMessage("Cargando ...!!");
			dialogo.setIndeterminate(false);
			dialogo.setCancelable(false);
			dialogo.show();
		}
			
		// DEVUELVE AL FINAL EL HILO DE SEGUNDO PLANO (EJECUTA EL REGISTRO DE LOS DATOS) ...!!
		@Override
		protected String doInBackground(String... params) 
		{
			String mensajito="";
			try 
			{
				usuario= txtUsuario.getText().toString().trim();
				password=txtpassword.getText().toString().trim();
				
				// LOGEO ...!!
				mensajito=blInicio.Logeo(usuario, password);	
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			// RETORNA ...!!
    		return mensajito;
		}
		
		// SE EJECUTA JUSTO DESPUES DE QUE SE ACABE UN HILO ...!! 
		@Override
		protected void onPostExecute(String result) 
		{
			// DESACTIVAR EL PROGRESS DIALOGO ...!!
			dialogo.dismiss();
			
			// VERIFICAMOS CUANDO SE LOGEA ...!!
			if(result.equals("Bienvenido al Sistema de Verificaciones"))
			{
				// PASAR A OTRO ACTIVITY ...!!
				PasarMenu2();
			}
			else if(result.equals("Error, Usuario no existe o datos incorrectos"))
			{
				// PASAR A MENSAJE ALERTA USUARIO ...!!
				Toast.makeText(getBaseContext(),msje.toString(),Toast.LENGTH_LONG).show();
			}
		}
	}
	
	public void PasarMenu2()
	{
		// SELECCIONE LA ACTIVIDAD MENU LATERAL ...!!
		Intent optLogin = new Intent(this,MainActivity.class);
		optLogin.putExtra("usuario", txtUsuario.getText().toString());
		startActivity(optLogin);
		finish();
	}
	
	@Override
	public void onClick(View boton) 
	{
		if(boton.getId()== findViewById(R.id.btnLogverificacion).getId())
		{	
			// LLAMAMOS LA TAREA ASINCRONICO SE ENCARGA DE REGISTRAR EL LOGEO DEL USUAIO ...!!
			new asynInicioSesion().execute();
		}
	}
}
