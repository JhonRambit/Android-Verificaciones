package com.example.menuprincipal;

import BL.blCoordenadas;
import BL.blFoto;
import BL.blFuncionesAux;
//import DAO.daoFuncionesAux;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
//import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FotoActivity extends Activity implements OnClickListener 
{
	// REFERENCIA DE CLASES ...!!
		blFuncionesAux BlFuncionesAux  = new blFuncionesAux();
		blCoordenadas BlCoordenadas = new blCoordenadas();
		blFoto BlFoto = new blFoto();
		
		// OBTENEMOS LA RUTA DE LAS FOTO ...!!
		//private LocationManager locManager,locManager2;
		//private LocationListener locListener,locListener2;
		//private Location loc,loc2;
			
		final static int cons=0;
		
		// DATOS BUSQUEDA DE FOTOS ...!!
		public String	nombre, paterno, materno, razon_social, formato, foto_1,foto_2,foto_3;
		public String 	usuario, id_tipoPersona, fec_server;
		
		// GPS ...!!
		//public String	latitud = daoFuncionesAux.Latitud.toString();
		//public String 	longitud = daoFuncionesAux.Longitud.toString();
		
		// REGISTRO COORDENADAS ...!!
		public String rptCoordenadas;
		
		// ESTADO AL TOMAR LA FOTO ...!!
		public int 		estado;
		
		// LEER TARJETA MEMORIA ...!!
		public boolean 	sdDisponible= false;
	    public boolean 	sdAccesoEscritura= false;
	    
	    // DECLARAMOS LOS COMPONENTES DEL ACTIVITY ...!!
		Button 			btnRegFoto1, btnRegFoto2, btnRegFoto3, btnRegBuscaNroSolicitud;
		EditText 		txtRegNrosolicitud, txtRegTitular, txtRegRazonSocial, txtRegTipoFormato;
		TextView 		lblRegTitular, lblRegRazonSocial, lblTipoFormato, lblRegPrimera,lblRegSegunda,lblRegTercero, lblRegMensaje, lblRegEstado, lblRegLatitud,lblRegLongitud;
		ImageView 		imagen1,imagen2,imagen3;
		ImageButton 	bfoto1,bfoto2,bfoto3;
		
		// DECLARAMOS LOS COMPONENTES DE LA CAMARA ...!!
		Intent 			i,i2,i3;
		Bitmap 			bmp,bmp2,bmp3;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foto);
		
		 // LARGA DURACIÓN EN EL HILO PRINCIPAL...!!
		//if (android.os.Build.VERSION.SDK_INT > 9) 
		//{
		//	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		//	StrictMode.setThreadPolicy(policy);
		//}
		
		// USUARIO LOGEADO ...!!
		usuario=getIntent().getStringExtra("usuario");
		id_tipoPersona= getIntent().getStringExtra("id_tipoPersona");
	
		txtRegNrosolicitud=(EditText) findViewById(R.id.txtNroSolicitud);
		txtRegTitular=(EditText) findViewById(R.id.txtTitular);
		txtRegRazonSocial =(EditText) findViewById(R.id.txtRazonSocial);
		txtRegTipoFormato=(EditText) findViewById(R.id.txtFormato);
		
		lblRegTitular=(TextView) findViewById(R.id.lblTitular);
		lblRegRazonSocial=(TextView) findViewById(R.id.lblRazonSocial);
		lblTipoFormato=(TextView) findViewById(R.id.lblTipoFormato);
		lblRegPrimera=(TextView) findViewById(R.id.lblPrimera);
		lblRegSegunda=(TextView) findViewById(R.id.lblSegunda);
		lblRegTercero=(TextView) findViewById(R.id.lblTercero);
		lblRegMensaje=(TextView) findViewById(R.id.lblMensajeRegistro);
		
		 // CONPROBAMOS EL ESTADO DE LA MEMORIA EXTERNA (TARJETA SD) ...!!
        String estado = Environment.getExternalStorageState();
        if(estado.equals(Environment.MEDIA_MOUNTED))
        {
        	sdDisponible= true;
        	sdAccesoEscritura=true;
        }
        else if(estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
        	sdDisponible=true;
        	sdAccesoEscritura=false;
        }
        else  
        {
        	sdDisponible=false;
        	sdAccesoEscritura=false;
        }
	
		 // IMAGEN 1,2,3 ...!!
		imagen1=(ImageView) findViewById(R.id.img1);
		imagen1.setVisibility(View.GONE);
		imagen2=(ImageView) findViewById(R.id.img2);
		imagen2.setVisibility(View.GONE);
		imagen3=(ImageView) findViewById(R.id.img3);
		imagen3.setVisibility(View.GONE);
		
		// ACCION PARA EL BOTON  FOTO1, FOTO2 ...!!
		bfoto1= (ImageButton) findViewById(R.id.foto1);
		bfoto1.setVisibility(View.GONE);
		bfoto1.setOnClickListener(this);
		
		bfoto2 =(ImageButton) findViewById(R.id.foto2);
		bfoto2.setVisibility(View.GONE);
		bfoto2.setOnClickListener(this);
		
		bfoto3=(ImageButton) findViewById(R.id.foto3);
		bfoto3.setVisibility(View.GONE);
		bfoto3.setOnClickListener(this);
	
		// EVENTO ONCLICKLISTENER EN EL BOTONES ...!!
		btnRegBuscaNroSolicitud=(Button) findViewById(R.id.btnBuscaNroSolicitud);
		btnRegBuscaNroSolicitud.setOnClickListener(this);
		
		// OBTENIENDO DATOS DE LA FECHA DEL SERVIDOR ...!!
   	 	//this.ObtenerDatos(usuario, 1);
	}

	@Override
	public void onClick(View arg0) 
	{
		
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
}
