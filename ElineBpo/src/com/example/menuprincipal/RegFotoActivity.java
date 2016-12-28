package com.example.menuprincipal;

import BE.beFecha;
import BE.beFoto;
import BL.blCoordenadas;
import BL.blFoto;
import BL.blFuncionesAux;
import DAO.daoFuncionesAux;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class RegFotoActivity extends Activity implements OnClickListener 
{
	// REFERENCIA DE CLASES ...!!
	blFuncionesAux BlFuncionesAux  = new blFuncionesAux();
	blCoordenadas BlCoordenadas = new blCoordenadas();
	blFoto BlFoto = new blFoto();
	
	// OBTENEMOS LA RUTA DE LAS FOTO ...!!
	private LocationManager locManager,locManager2;
	private LocationListener locListener,locListener2;
	private Location loc,loc2;
		
	final static int cons=0;
	
	// DATOS BUSQUEDA DE FOTOS ...!!
	public String	nombre, paterno, materno, razon_social, formato, foto_1,foto_2,foto_3;
	public String 	usuario, id_tipoPersona, fec_server;
	
	// GPS ...!!
	public String	latitud = daoFuncionesAux.Latitud.toString();
	public String 	longitud = daoFuncionesAux.Longitud.toString();
	
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
		setContentView(R.layout.activity_regfoto);
		
		 // LARGA DURACIÓN EN EL HILO PRINCIPAL...!!
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
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
   	 	this.ObtenerDatos(usuario, 1);
	}
	
	public void ObtenerDatos(String dato, int veri)
    {
		if(veri==1)
		{
			// REGISTRO FECHA ...!!
			beFecha[] fecServer = BlFuncionesAux.fechaServer(dato);
			for (int i = 0; i < fecServer.length; i++) 
			{
				usuario= fecServer[i].Nom_usuario.toString();
				fec_server = fecServer[i].Fecha.toString();
			}
		}
		else if(veri==2)
		{
			// REGISTRO FECHA ...!!
			beFoto[] fecFoto = BlFoto.verificandoFoto(dato);
			for (int i = 0; i < fecFoto.length; i++) 
			{
			 	nombre= fecFoto[i].Nombre.toString();
			 	paterno = fecFoto[i].Paterno.toString();
			 	materno= fecFoto[i].Materno.toString();
			 	razon_social = fecFoto[i].Razon_social.toString();
			 	formato= fecFoto[i].Formato.toString();
			 	foto_1 = fecFoto[i].Foto_1.toString();
			 	foto_2= fecFoto[i].Foto_2.toString();
			 	foto_3 = fecFoto[i].Foto_3.toString();
			}
		}
    }
		
	public void activaUbicacion()
	{
		String ubi = null;
		ubi=BlFuncionesAux.ActivaUbicacion(locManager, locListener, loc, 1);
				
		if(ubi.equals("No se registro con éxito ...!!"))
		{
			ubi="";
			ubi=BlFuncionesAux.ActivaUbicacion(locManager2, locListener2, loc2, 2);
		}
		
		Toast.makeText(getBaseContext(),ubi.toString(),Toast.LENGTH_SHORT).show();
	}
	
	public void DatosGenerales()
	{		
		// DESABILITANDO TEXTOS MODO LECTURA ...!!
        txtRegTitular.setEnabled(false);
        txtRegRazonSocial.setEnabled(false);
        txtRegTipoFormato.setEnabled(false);
	}
	
	public void DesactivarFotos()
	{
		lblRegMensaje.setVisibility(View.VISIBLE);
		lblRegPrimera.setVisibility(View.GONE);
		imagen1.setVisibility(View.GONE);
		lblRegSegunda.setVisibility(View.GONE);
		imagen2.setVisibility(View.GONE);
		lblRegTercero.setVisibility(View.GONE);
		imagen3.setVisibility(View.GONE);
	}
	
	public void MostarFotos()
	{
		lblRegPrimera.setVisibility(View.VISIBLE);
		imagen1.setVisibility(View.VISIBLE);
		lblRegSegunda.setVisibility(View.VISIBLE);
		imagen2.setVisibility(View.VISIBLE);
		lblRegTercero.setVisibility(View.INVISIBLE);
		imagen3.setVisibility(View.INVISIBLE);
	}
	
	public void ActivarTextoEspecial()
	{
		// CAMBIAR LOS TEXTO DE LOS BOTONES ...!!
		lblRegPrimera.setText("Foto Casa");
		lblRegSegunda.setText("Artefacto Mayor Valor");
		lblRegTercero.setText("Declaración Jurada");
		
		// MOSTRAR LOS TEXTO Y BOTONES DE CADA FOTO ...!!
		lblRegPrimera.setVisibility(View.VISIBLE);
		imagen1.setVisibility(View.VISIBLE);
		lblRegSegunda.setVisibility(View.VISIBLE);
		imagen2.setVisibility(View.VISIBLE);
		lblRegTercero.setVisibility(View.VISIBLE);
		imagen3.setVisibility(View.VISIBLE);
	}
	
	@Override
	public void onClick(View boton) 
	{
		if(boton.getId()== findViewById(R.id.btnBuscaNroSolicitud).getId())
		{
			if(txtRegNrosolicitud.getText().length()==0)
			{
				txtRegNrosolicitud.setError("Debe Digitar el Nro. Solicitud ..!!");
			}
			else
			{
				// OBTENER DATOS ...!!
				this.ObtenerDatos(txtRegNrosolicitud.getText().toString(),2);
				
				// OBTENER UBICACIÓN ...!!
				this.activaUbicacion();
				
				if(foto_1.equals("Registrado") || foto_2.equals("Registrado") || foto_3.equals("Registrado")) 
				{
					this.DesactivarFotos();
					lblRegMensaje.setText("Nro. Solicitud: " + txtRegNrosolicitud.getText().toString() + " tiene fotos registradas ...!!");
				}
				else
				{
					if(formato.equals("EST-CCO"))
					{
						lblRegMensaje.setVisibility(View.GONE);
						this.ActivarTextoEspecial();
					}
					else
					{
						lblRegMensaje.setVisibility(View.GONE);
						this.MostarFotos();
					}
				}
			}
		}
		
		if (boton.getId()== findViewById(R.id.foto1).getId())
		{
			if(latitud.equals("GPS desactivado") || longitud.equals("GPS desactivado"))
			{
				// MOSTRAR MENSAJE DEL GSP DESACTIVADO ...!!
				Toast.makeText(getBaseContext(),"Debe Activar/Refrescar GPS ...!!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				// REGISTRAR UBICACIÓN Y MENSAJE DE RESPUESTA ...!!
				rptCoordenadas= BlCoordenadas.InsertCoordernadas(latitud, longitud, usuario, 2, txtRegNrosolicitud.getText().toString());
				Toast.makeText(getBaseContext(),rptCoordenadas.toString(),Toast.LENGTH_SHORT).show();
			
				// OBTENER LA PRIMERA FOTO DE LA CAMARA ...!!
				i= new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(i, cons);
				estado=1;
			}
		}
		
		if(boton.getId() == findViewById(R.id.foto2).getId())
		{
			// PERMITE GUARDAR EN LA GALERIA Y A LA VEZ TOMAR FOTO GUARDAR DICHA FOTO ...!!
			i2= new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i2, cons);
			estado=2;
		}
		
		if(boton.getId() == findViewById(R.id.foto3).getId())
		{
			// PERMITE GUARDAR EN LA GALERIA Y A LA VEZ TOMAR FOTO GUARDAR DICHA FOTO ...!!
			i3= new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i3, cons);
			estado=3;
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
	
		if(resultCode == RESULT_OK)
		{
			String numero=txtRegNrosolicitud.getText().toString();
			
			if(estado == 1)
			{
				if(data != null)
				{
					if (data.hasExtra("data")) 
	    			{
						// EXTRAER DATOS DE LA FOTO EN BITMAP ...!!
						Bundle ext= data.getExtras();
						this.ExtraFoto(bmp, ext, 350, 420, "_1",numero);
	    			}
				}
			}
			
			if(estado == 2)
			{
				if (data.hasExtra("data")) 
    			{
					// EXTRAER DATOS DE LA FOTO EN BITMAP ...!!
					Bundle ext= data.getExtras();
					this.ExtraFoto(bmp2, ext, 350, 420, "_2",numero);
    			}
			}
			
			if(estado == 3)
			{
				if (data.hasExtra("data")) 
    			{
					// EXTRAER DATOS DE LA FOTO EN BITMAP ...!!
					Bundle ext= data.getExtras();
					this.ExtraFoto(bmp3, ext, 350, 420, "_3",numero);
    			}
			}
		}
	}
	
	public void ExtraFoto(Bitmap bitmp, Bundle ext, int width, int height, String posicion, String numero)
	{
		// EXTRAER DATOS DE LA FOTO EN BITMAP ...!!
		bitmp= (Bitmap) ext.get("data");
		bitmp=Bitmap.createScaledBitmap(bitmp, width, height, true);
		
		// COMPARAR SI EL BITMAP ESTA VACIO ...!!
		if(bitmp != null)
		{
			String rptFot= BlFoto.dibujarImagen(fec_server, posicion, imagen1, bitmp, numero);
			
			// MENSAJE DE LA FUNCIÓN ...!!
			Toast.makeText(getBaseContext(),rptFot.toString(),Toast.LENGTH_SHORT).show();
		}
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
}
