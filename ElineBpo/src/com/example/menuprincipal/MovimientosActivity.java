package com.example.menuprincipal;

import com.example.menuprincipal.R;
import BL.blVerificacion;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovimientosActivity extends Activity implements OnClickListener 
{
	// REFERENCIA A CLASES ...!! 
	blVerificacion BlVerificacion = new blVerificacion();
		
	// COMPONENTES DEL ACTIVITY ...!!
	Button 	  	   btnFoto, btnVerifciacion, btnCobranza;
	TextView  	   lblTitulo, lblIdPerfil;
	ImageView 	   ImgTitulo;
	
	// VALIDANDO EL TIPO PERSONA  ...!!
	public String  GestorVerificador="TIP00003";		
	public String  GestorCobranza ="TIP00006";
	public String  GestorYverificador="TIP00008";
	public String  usuario, id_tipoPersona;
	public String  MensajeVericandoSolicitud="";
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movimientos);
		
		ActionBar actionBar= getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// USUARIO LOGEADO ...!!
		usuario=getIntent().getStringExtra("usuario");
		id_tipoPersona= getIntent().getStringExtra("id_tipoPersona");
		
		// COMPARANDO LOS COMPONENTES CON LAS VARIABLES ...!!
		ImgTitulo=(ImageView) findViewById(R.id.imgTitulo);
		lblTitulo = (TextView) findViewById(R.id.lblTitulo);
		
		btnFoto = (Button) findViewById(R.id.btnfoto);
		btnFoto.setOnClickListener(this);
		btnFoto.setVisibility(View.GONE);
		
		btnVerifciacion= (Button) findViewById(R.id.btnverificacion);
		btnVerifciacion.setOnClickListener(this);
		btnVerifciacion.setVisibility(View.GONE);
		
		btnCobranza=(Button) findViewById(R.id.btncobranza);
		btnCobranza.setOnClickListener(this);
		btnCobranza.setVisibility(View.GONE);
				
		// VALIDANDO TIPO PERSONA ...!!
		this.ValidandoPerfil();
	}
	
	public void ValidandoPerfil()
	{
		// VALIDAR LOS PERMISOS DEPENDE DEL TIPO DE PERSONA ...!!
		if(id_tipoPersona.equals(GestorVerificador.toString()))
		{
			btnFoto.setVisibility(View.VISIBLE);
			btnVerifciacion.setVisibility(View.VISIBLE);
			btnCobranza.setVisibility(View.GONE);
		}
				
		if(id_tipoPersona.equals(GestorCobranza.toString()))
		{
			btnFoto.setVisibility(View.VISIBLE);
			btnVerifciacion.setVisibility(View.GONE);
			btnCobranza.setVisibility(View.VISIBLE);
		}
		
		if(id_tipoPersona.equals(GestorYverificador.toString()))
		{
			btnFoto.setVisibility(View.VISIBLE);
			btnVerifciacion.setVisibility(View.VISIBLE);
			btnCobranza.setVisibility(View.VISIBLE);
		}
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
        {
        	case R.id.regresa:
        		// REGRESAR ACTIVIDAD MENÚ LATERAL ...!!		        	
        		Intent optSalir = new Intent(this,MainActivity.class);
        		optSalir.putExtra("usuario", usuario.toString());
        		startActivity(optSalir);
        		finish();
        		return true;
        }
        
		return true;
	}
	 
	public void VerificandoSolicitud(String usuario, String tipo_persona,Class<?> actividad)
	{
		// VERIFICACANDO SOLICITUD ...!!
		MensajeVericandoSolicitud = BlVerificacion.VerificandoSolicitud(usuario, tipo_persona);

		// SELECCIONA LA ACTIVIDAD VERIFICACIÓN ...!!
		if(MensajeVericandoSolicitud.equals("Cargando Solicitudes ..!!"))
		{
			Intent optItent = new Intent(this,actividad);
			optItent.putExtra("usuario", usuario.toString());
			optItent.putExtra("id_tipoPersona", tipo_persona.toString());
			startActivity(optItent);
			finish();
		}
		else
		{
			Toast.makeText(getBaseContext(), MensajeVericandoSolicitud.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View boton) 
	{
		if(boton.getId() ==  findViewById(R.id.btnfoto).getId())
		{
			//Toast.makeText(getBaseContext(),"Boton Foto".toString(),Toast.LENGTH_LONG).show();
			//Intent optMovimientos = new Intent(this,RegFotoActivity.class);
	 		//optMovimientos.putExtra("usuario", usuario);
	 		//optMovimientos.putExtra("id_tipoPersona", id_tipoPersona);
    		//startActivity(optMovimientos);
 			//finish();
			//Intent optItent = new Intent(this,RegFotoActivity.class);
			//optItent.putExtra("usuario", usuario);
			//optItent.p	utExtra("TipoPersona", id_tipoPersona);
			//startActivity(optItent);
			//finish();
			// VERIFICANDO ACTIVIDAD FOTO ...!!
			this.VerificandoSolicitud(usuario, id_tipoPersona, FotoActivity.class);
		}
		else if(boton.getId() ==  findViewById(R.id.btnverificacion).getId())
		{
			//Toast.makeText(getBaseContext(),"Boton Verificacion".toString(),Toast.LENGTH_LONG).show();
			//Intent optItent = new Intent(this,FotoActivity.class);
			//optItent.putExtra("usuario", usuario);
			//optItent.putExtra("id_tipoPersona", id_tipoPersona);
			//startActivity(optItent);
			//finish();
			// VERIFICANDO ACTIVIDAD VERIFICANCIÓN ...!!
			this.VerificandoSolicitud(usuario, id_tipoPersona, VerificancionActivity.class);
		}
		else if(boton.getId() ==  findViewById(R.id.btncobranza).getId())
		{
			// VERIFICANDO ACTIVIDAD COBRANZA ...!!
			//this.VerificandoSolicitud(usuario, id_tipoPersona, CobranzaActivity.class);
		}
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
}
