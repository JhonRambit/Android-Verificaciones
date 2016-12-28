package com.example.menuprincipal;

import BL.blVerificacion;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovimientoActivity extends Activity implements OnClickListener
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movimiento);
		
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
		// VALIDANDO TIPO PERSONA ...!!
		//this.ValidandoPerfil();
	}
	
	public void ValidandoPerfil()
	{
		// VALIDAR LOS PERMISOS DEPENDE DEL TIPO DE PERSONA ...!!
		
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
			optItent.putExtra("TipoPersona", tipo_persona.toString());
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
			// VERIFICANDO ACTIVIDAD FOTO ...!!
			this.VerificandoSolicitud(usuario, id_tipoPersona, RegFotoActivity.class);
		}
		
		if(boton.getId() ==  findViewById(R.id.btnverificacion).getId())
		{
			// VERIFICANDO ACTIVIDAD VERIFICANCIÓN ...!!
			this.VerificandoSolicitud(usuario, id_tipoPersona, VerificancionActivity.class);
		}
		
		if(boton.getId() ==  findViewById(R.id.btncobranza).getId())
		{
			// VERIFICANDO ACTIVIDAD COBRANZA ...!!
			this.VerificandoSolicitud(usuario, id_tipoPersona, CobranzaActivity.class);
		}
	}
}
