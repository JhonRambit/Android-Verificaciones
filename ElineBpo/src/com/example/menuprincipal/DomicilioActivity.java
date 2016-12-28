package com.example.menuprincipal;

import BE.beCliente;
import BE.beTipoPersona;
import BL.blCliente;
import BL.blTipoPersona;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class DomicilioActivity extends Activity implements OnItemSelectedListener, OnClickListener
{
	// DECLARANDO CLASES ...!!
	blCliente blCliente = new blCliente();	
	
	public AlertDialog.Builder domBuleder;
	public AlertDialog.Builder domBuilder2;
	public AlertDialog.Builder domAlertdialogbuilder;
	
	private ProgressDialog dialogo=null;
	
	private int   dYear, dMonth, dDay, dHour, dMinute;
	private int   DYear, DMonth, DDay, DHour, DMinute;
	int 		  Destado = 0;
	
	public String nroSolicitud,formato,usuario,tipoPersona;
	public String nombre,paterno,materno,razon_social,direccion,distrito,provincia,departamento,observaciones,entidad,fecha_ingreso,idVerificacion,idFormato;
	public String RegDOVisitas,RegDODocInformante,RegDOMostroDoc,RegDOTitular,RegDOHabita,RegDOHabitaForma,RegDOFormaAtencion,
				  RegDOPropiedadInmueble,RegDOTipoMoneda,RegDOFrecuenciaPago,RegDOTipoInmueble,RegDOUbicacionInmueble,RegDOEstadoInmueble,
				  RegDOMaterialInmueble,RegDOTipoBarrio,RegDOEstadoBarrio,RegDOEstadoCivil,RegDOAgua,RegDOLuz,RegDODesague;
	
	TextView  	  lblRegDONroSolicitudGeneral,lblRegDOEntidadGeneral,lblRegDOTitularGeneral,lblRegDODireccionGeneral,
			  	  lblRegDODistritoGeneral,lblRegDOProvinciaGeneral,lblRegDODepartamentoGeneral,lblRegDOObservacionesGeneral,
			  	  lblRegDOFechaIngresoGeneral,lblRegDODatoVerificacion,lblRegDOTipoFormato,lblRegDOVisitas,lblRegDOPrimeraVisita,
			  	  lbRegDOSegundaVisita,lblRegDONombreInformante,lblRegDOTipoDocInformante,lblRegDOMostroDoc,lblRegDONroInformate,
			  	  lblRegDORelacionInformante,lblRegDOHabita,lblRegDOHabitaForma,lblRegDOFormaAtencion,lblRegDOGrupoHabitacional,
			  	  lblRegDOLaboran1,lblRegDONlaboran1,lblRegDOTotal1,lblRegDOGrupoFamiliar,lblRegDOLaboran2,lblRegDONlabora2,
			  	  lblRegDOTotal2,lblRegDOPropiedadinmueble,lblRegDOTipoMoneda,lblRegDOMontoAlquiler,lblRegDOFrecuenciaPago,
			  	  lblRegDOTiempoResidencia,lblRegDOAnoMesDia,lblRegDOTipoinmueble,lblRegDOUbicacionInmueble,lblRegDOEstadoinmueble,
			  	  lblRegDONroPisoInmueble,lblRegDONroPisoConjunto,lblRegDOMaterialInmueble,lblRegDOColorinmueble,lblRegDOServiciobasico,
			  	  lblRegDOTipoBarrio,lblRegDOEstadoBarrio,lblRegDOEstadoCivil,lblRegDOTelefonoTitular,lblRegDNroSuministro,lblRegDODireccionCorrecta,
			  	  lblRegDOObservaciones;
	
	EditText  	  txtRegDONrosolicitudGeneral,txtRegDOEntidadGeneral,txtRegDOTitularGeneral,txtRegDODireccionGeneral,
			  	  txtRegDODistritoGeneral,txtRegDOProvinciaGeneral,txtRegDODepartamentoGeneral,txtRegDObservacionesGeneral,
			  	  txtRegDOFechaIngresoGeneral,txtRegDOTipoFormato,txtRegDOPrimeraFecha,txtRegDOPrimeraHora,txtRegDOSegundaFecha,
			  	  txtRegDOSegundaHora,txtRegDONombreInformante,txtRegDONroInformante,txtRegDOOtrosTitular,txtRegDOOtrosHabitaForma,
			  	  txtRegDOtrosFormaAtencion,txtRegDOlaboran1,txtRegDONlaboran1,txtRegDOTotal1,txtRegDOlaboran2,txtRegDONlaboran2,
			  	  txtRegDOTotal2,txtRegDOOtrosPropiedadInmueble,txtRegDOTipoMoneda,txtRegDOAno,txtRegDOMes,txtRegDODia,
			  	  txtRegDOOtrosTipoInmueble,txtRegDOOtrosUbicacionInmueble,txtRegDONroPisoInmueble,txtRegDONroPisoConjunto,
			  	  txtRegDOOtrosMaterialInmueble,txtRegDOColorInmueble,txtRegDOOtrosTipoBarrio,txtRegDOOtrosEstadoBarrio,
	 		  	  txtRegDOTelefonoTitular,txtRegDONroSuministro,txtRegDODireccionCorrecta,txtRegDOObservaciones;
	 
	Spinner	  	  listaRegDOVisitas,listaRegDODocInformante,listaRegDOMostroDoc,listaRegDOTitular,listaRegDOHabita,listaRegDOHabitaForma,
				  listaRegDOFormaAtencion,listaRegDOPropiedadInmueble,listaRegDOTipoMoneda,listaRegDOFrecuenciaPago,listaRegDOTipoInmueble,
			  	  listaRegDOUbicacionInmueble,listaRegDOEstadoInmueble,listaRegDOMaterialInmueble,listaRegDOTipoBarrio,listaRegDOEstadoBarrio,
			  	  listaRegDOEstadoCivil;
	
	Button	 	  btnRegDOPrimeraFecha,btnRegDOLimpiarPrimeraFecha,btnRegDOPrimeraHora,btnRegDOLimpiarPrimeraHora,btnRegDOSegundaFecha,
				  btnRegDOLimpiarSegundaFecha,btnRegDOSegundaHora,btnRegDOLimpiarSegundaHora,btnRegDORutaFoto,btnRegDORutaFoto2,btnRegDOGuardar,
				  btnRegDORegresa;
	
	ImageView 	  imageViewRegDOPrimeraFoto,imageViewRegDOSegundaFoto;
	
	CheckBox  	  chkRegDOAgua,chkRegDOLuz,chkRegDODesague;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_domicilio);
		
		if(android.os.Build.VERSION.SDK_INT >9)
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		// USUARIO MODIFICADO ...!!
		usuario = getIntent().getStringExtra("usuarioVerificacion");
		nroSolicitud = getIntent().getStringExtra("ftid");
		tipoPersona = getIntent().getStringExtra("ftipopersona");
		formato =  getIntent().getStringExtra("fTipFormat");
		
		// TITULOS ...!!
		lblRegDONroSolicitudGeneral=(TextView) findViewById(R.id.lblRegDONroSolicitudGeneral);
		lblRegDOEntidadGeneral=(TextView) findViewById(R.id.lblRegDOEntidadGeneral);
		lblRegDOTitularGeneral=(TextView) findViewById(R.id.lblRegDOTitularGeneral);
		lblRegDODireccionGeneral=(TextView) findViewById(R.id.lblRegDODireccionGeneral);
		lblRegDODistritoGeneral=(TextView) findViewById(R.id.lblRegDODistritoGeneral);
		lblRegDOProvinciaGeneral=(TextView) findViewById(R.id.lblRegDOProvinciaGeneral);
		lblRegDODepartamentoGeneral=(TextView) findViewById(R.id.lblRegDODepartamentoGeneral);
		lblRegDOObservacionesGeneral=(TextView) findViewById(R.id.lblRegDOObservacionesGeneral);
		lblRegDOFechaIngresoGeneral=(TextView) findViewById(R.id.lblRegDOFechaIngresoGeneral);
		lblRegDODatoVerificacion=(TextView) findViewById(R.id.lblRegDODatoVerificacion);
		lblRegDOTipoFormato=(TextView) findViewById(R.id.lblRegDOTipoFormato);
		lblRegDOVisitas=(TextView) findViewById(R.id.lblRegDOVisitas);
		lblRegDOPrimeraVisita=(TextView) findViewById(R.id.lblRegDOPrimeraVisita);
		lbRegDOSegundaVisita=(TextView) findViewById(R.id.lbRegDOSegundaVisita);
		lblRegDONombreInformante=(TextView) findViewById(R.id.lblRegDONombreInformante);
		lblRegDOTipoDocInformante=(TextView) findViewById(R.id.lblRegDOTipoDocInformante);
		lblRegDOMostroDoc=(TextView) findViewById(R.id.lblRegDOMostroDoc);
		lblRegDONroInformate=(TextView) findViewById(R.id.lblRegDONroInformate);
		lblRegDORelacionInformante=(TextView) findViewById(R.id.lblRegDORelacionInformante);
		lblRegDOHabita=(TextView) findViewById(R.id.lblRegDOHabita);
		lblRegDOHabitaForma=(TextView) findViewById(R.id.lblRegDOHabitaForma);
		lblRegDOFormaAtencion=(TextView) findViewById(R.id.lblRegDOFormaAtencion);
		lblRegDOGrupoHabitacional=(TextView) findViewById(R.id.lblRegDOGrupoHabitacional);
		lblRegDOLaboran1=(TextView) findViewById(R.id.lblRegDOLaboran1);
		lblRegDONlaboran1=(TextView) findViewById(R.id.lblRegDONlaboran1);
		lblRegDOTotal1=(TextView) findViewById(R.id.lblRegDOTotal1);
		lblRegDOGrupoFamiliar=(TextView) findViewById(R.id.lblRegDOGrupoFamiliar);
		lblRegDOLaboran2=(TextView) findViewById(R.id.lblRegDOLaboran2);
		lblRegDONlabora2=(TextView) findViewById(R.id.lblRegDONlabora2);
		lblRegDOTotal2=(TextView) findViewById(R.id.lblRegDOTotal2);
		lblRegDOPropiedadinmueble=(TextView) findViewById(R.id.lblRegDOPropiedadinmueble);
		lblRegDOTipoMoneda=(TextView) findViewById(R.id.lblRegDOTipoMoneda);
		lblRegDOMontoAlquiler=(TextView) findViewById(R.id.lblRegDOMontoAlquiler);
		lblRegDOFrecuenciaPago=(TextView) findViewById(R.id.lblRegDOFrecuenciaPago);
		lblRegDOTiempoResidencia=(TextView) findViewById(R.id.lblRegDOTiempoResidencia);
		lblRegDOAnoMesDia=(TextView) findViewById(R.id.lblRegDOAnoMesDia);
		lblRegDOTipoinmueble=(TextView) findViewById(R.id.lblRegDOTipoinmueble);
		lblRegDOUbicacionInmueble=(TextView) findViewById(R.id.lblRegDOUbicacionInmueble);
		lblRegDOEstadoinmueble=(TextView) findViewById(R.id.lblRegDOEstadoinmueble);
		lblRegDONroPisoInmueble=(TextView) findViewById(R.id.lblRegDONroPisoInmueble);
		lblRegDONroPisoConjunto=(TextView) findViewById(R.id.lblRegDONroPisoConjunto);
		lblRegDOMaterialInmueble=(TextView) findViewById(R.id.lblRegDOMaterialInmueble);
		lblRegDOColorinmueble=(TextView) findViewById(R.id.lblRegDOColorinmueble);
		lblRegDOServiciobasico=(TextView) findViewById(R.id.lblRegDOServiciobasico);
		lblRegDOTipoBarrio=(TextView) findViewById(R.id.lblRegDOTipoBarrio);
		lblRegDOEstadoBarrio=(TextView) findViewById(R.id.lblRegDOEstadoBarrio);
		lblRegDOEstadoCivil=(TextView) findViewById(R.id.lblRegDOEstadoCivil);
		lblRegDOTelefonoTitular=(TextView) findViewById(R.id.lblRegDOTelefonoTitular);
		lblRegDNroSuministro=(TextView) findViewById(R.id.lblRegDNroSuministro);
		lblRegDODireccionCorrecta=(TextView) findViewById(R.id.lblRegDODireccionCorrecta);
		lblRegDOObservaciones=(TextView) findViewById(R.id.lblRegDOObservaciones);
		
		// TEXTOS ...!!
		txtRegDONrosolicitudGeneral=(EditText) findViewById(R.id.txtRegDONrosolicitudGeneral);
		txtRegDOEntidadGeneral=(EditText) findViewById(R.id.txtRegDOEntidadGeneral);
		txtRegDOTitularGeneral=(EditText) findViewById(R.id.txtRegDOTitularGeneral);
		txtRegDODireccionGeneral=(EditText) findViewById(R.id.txtRegDODireccionGeneral);
		txtRegDODistritoGeneral=(EditText) findViewById(R.id.txtRegDODistritoGeneral);
		txtRegDOProvinciaGeneral=(EditText) findViewById(R.id.txtRegDOProvinciaGeneral);
		txtRegDODepartamentoGeneral=(EditText) findViewById(R.id.txtRegDODepartamentoGeneral);
		txtRegDObservacionesGeneral=(EditText) findViewById(R.id.txtRegDObservacionesGeneral);
		txtRegDOFechaIngresoGeneral=(EditText) findViewById(R.id.txtRegDOFechaIngresoGeneral);
		txtRegDOTipoFormato=(EditText) findViewById(R.id.txtRegDOTipoFormato);
		txtRegDOPrimeraFecha=(EditText) findViewById(R.id.txtRegDOPrimeraFecha);
		txtRegDOPrimeraHora=(EditText) findViewById(R.id.txtRegDOPrimeraHora);
		txtRegDOSegundaFecha=(EditText) findViewById(R.id.txtRegDOSegundaFecha);
		txtRegDOSegundaHora=(EditText) findViewById(R.id.txtRegDOSegundaHora);
		txtRegDONombreInformante=(EditText) findViewById(R.id.txtRegDONombreInformante);
		txtRegDONroInformante=(EditText) findViewById(R.id.txtRegDONroInformante);
		txtRegDOOtrosTitular=(EditText) findViewById(R.id.txtRegDOOtrosTitular);
		txtRegDOOtrosHabitaForma=(EditText) findViewById(R.id.txtRegDOOtrosHabitaForma);
		txtRegDOtrosFormaAtencion=(EditText) findViewById(R.id.txtRegDOtrosFormaAtencion);
		txtRegDOlaboran1=(EditText) findViewById(R.id.txtRegDOlaboran1);
		txtRegDONlaboran1=(EditText) findViewById(R.id.txtRegDONlaboran1);
		txtRegDOTotal1=(EditText) findViewById(R.id.txtRegDOTotal1);
		txtRegDOlaboran2=(EditText) findViewById(R.id.txtRegDOlaboran2);
		txtRegDONlaboran2=(EditText) findViewById(R.id.txtRegDONlaboran2);
		txtRegDOTotal2=(EditText) findViewById(R.id.txtRegDOTotal2);
		txtRegDOOtrosPropiedadInmueble=(EditText) findViewById(R.id.txtRegDOOtrosPropiedadInmueble);
		txtRegDOTipoMoneda=(EditText) findViewById(R.id.txtRegDOTipoMoneda);
		txtRegDOAno=(EditText) findViewById(R.id.txtRegDOAno);
		txtRegDOMes=(EditText) findViewById(R.id.txtRegDOMes);
		txtRegDODia=(EditText) findViewById(R.id.txtRegDODia);
		txtRegDOOtrosTipoInmueble=(EditText) findViewById(R.id.txtRegDOOtrosTipoInmueble);
		txtRegDOOtrosUbicacionInmueble=(EditText) findViewById(R.id.txtRegDOOtrosUbicacionInmueble);
		txtRegDONroPisoInmueble=(EditText) findViewById(R.id.txtRegDONroPisoInmueble);
		txtRegDONroPisoConjunto=(EditText) findViewById(R.id.txtRegDONroPisoConjunto);
		txtRegDOOtrosMaterialInmueble=(EditText) findViewById(R.id.txtRegDOOtrosMaterialInmueble);
		txtRegDOColorInmueble=(EditText) findViewById(R.id.txtRegDOColorInmueble);
		txtRegDOOtrosTipoBarrio=(EditText) findViewById(R.id.txtRegDOOtrosTipoBarrio);
		txtRegDOOtrosEstadoBarrio=(EditText) findViewById(R.id.txtRegDOOtrosEstadoBarrio);
		txtRegDOTelefonoTitular=(EditText) findViewById(R.id.txtRegDOTelefonoTitular);
		txtRegDONroSuministro=(EditText) findViewById(R.id.txtRegDONroSuministro);
		txtRegDODireccionCorrecta=(EditText) findViewById(R.id.txtRegDODireccionCorrecta);
		txtRegDOObservaciones=(EditText) findViewById(R.id.txtRegDOObservaciones);
		
		// LISTA ...!!
		listaRegDOVisitas=(Spinner) findViewById(R.id.listaRegDOVisitas);
		listaRegDODocInformante=(Spinner) findViewById(R.id.listaRegDODocInformante);
		listaRegDOMostroDoc=(Spinner) findViewById(R.id.listaRegDOMostroDoc);
		listaRegDOTitular=(Spinner) findViewById(R.id.listaRegDOTitular);
		listaRegDOHabita=(Spinner) findViewById(R.id.listaRegDOHabita);
		listaRegDOHabitaForma=(Spinner) findViewById(R.id.listaRegDOHabitaForma);
		listaRegDOFormaAtencion=(Spinner) findViewById(R.id.listaRegDOFormaAtencion);
		listaRegDOPropiedadInmueble=(Spinner) findViewById(R.id.listaRegDOPropiedadInmueble);
		listaRegDOTipoMoneda=(Spinner) findViewById(R.id.listaRegDOTipoMoneda);
		listaRegDOFrecuenciaPago=(Spinner) findViewById(R.id.listaRegDOFrecuenciaPago);
		listaRegDOTipoInmueble=(Spinner) findViewById(R.id.listaRegDOTipoInmueble);
		listaRegDOUbicacionInmueble=(Spinner) findViewById(R.id.listaRegDOUbicacionInmueble);
		listaRegDOEstadoInmueble=(Spinner) findViewById(R.id.listaRegDOEstadoInmueble);
		listaRegDOMaterialInmueble=(Spinner) findViewById(R.id.listaRegDOMaterialInmueble);
		listaRegDOTipoBarrio=(Spinner) findViewById(R.id.listaRegDOTipoBarrio);
		listaRegDOEstadoBarrio=(Spinner) findViewById(R.id.listaRegDOEstadoBarrio);
		listaRegDOEstadoCivil=(Spinner) findViewById(R.id.listaRegDOEstadoCivil);
		
		// BOTONES ...!!
		btnRegDOPrimeraFecha=(Button) findViewById(R.id.btnRegDOPrimeraFecha);
		btnRegDOLimpiarPrimeraFecha=(Button) findViewById(R.id.btnRegDOLimpiarPrimeraFecha);
		btnRegDOPrimeraHora=(Button) findViewById(R.id.btnRegDOPrimeraHora);
		btnRegDOLimpiarPrimeraHora=(Button) findViewById(R.id.btnRegDOLimpiarPrimeraHora);
		btnRegDOSegundaFecha=(Button) findViewById(R.id.btnRegDOSegundaFecha);
		btnRegDOLimpiarSegundaFecha=(Button) findViewById(R.id.btnRegDOLimpiarSegundaFecha);
		btnRegDOSegundaHora=(Button) findViewById(R.id.btnRegDOSegundaHora);
		btnRegDOLimpiarSegundaHora=(Button) findViewById(R.id.btnRegDOLimpiarSegundaHora);
		btnRegDORutaFoto=(Button) findViewById(R.id.btnRegDORutaFoto);
		btnRegDORutaFoto2=(Button) findViewById(R.id.btnRegDORutaFoto2);
		btnRegDOGuardar=(Button) findViewById(R.id.btnRegDOGuardar);
		btnRegDORegresa=(Button) findViewById(R.id.btnRegDORegresa);
		
		// IMAGENVIEW ...!!
		imageViewRegDOPrimeraFoto=(ImageView) findViewById(R.id.imageViewRegDOPrimeraFoto);
		imageViewRegDOPrimeraFoto.setVisibility(View.GONE);
		imageViewRegDOSegundaFoto=(ImageView) findViewById(R.id.imageViewRegDOSegundaFoto);
		imageViewRegDOSegundaFoto.setVisibility(View.GONE);
				
		// CHECKBOX ...!!
		chkRegDOAgua=(CheckBox) findViewById(R.id.chkRegDOAgua);
		chkRegDOLuz=(CheckBox) findViewById(R.id.chkRegDOLuz);
		chkRegDODesague=(CheckBox) findViewById(R.id.chkRegDODesague);
		
		// EVENTO LISTA ...!!
		listaRegDOVisitas.setOnItemSelectedListener(this);
		listaRegDODocInformante.setOnItemSelectedListener(this);
		listaRegDOMostroDoc.setOnItemSelectedListener(this);
		listaRegDOTitular.setOnItemSelectedListener(this);
		listaRegDOHabita.setOnItemSelectedListener(this);
		listaRegDOHabitaForma.setOnItemSelectedListener(this);
		listaRegDOFormaAtencion.setOnItemSelectedListener(this);
		listaRegDOPropiedadInmueble.setOnItemSelectedListener(this);
		listaRegDOTipoMoneda.setOnItemSelectedListener(this);
		listaRegDOFrecuenciaPago.setOnItemSelectedListener(this);
		listaRegDOTipoInmueble.setOnItemSelectedListener(this);
		listaRegDOUbicacionInmueble.setOnItemSelectedListener(this);
		listaRegDOEstadoInmueble.setOnItemSelectedListener(this);
		listaRegDOMaterialInmueble.setOnItemSelectedListener(this);
		listaRegDOTipoBarrio.setOnItemSelectedListener(this);
		listaRegDOEstadoBarrio.setOnItemSelectedListener(this);
		listaRegDOEstadoCivil.setOnItemSelectedListener(this);
		
		// EVENTO BOTONES ...!!
		btnRegDOPrimeraFecha.setOnClickListener(this);
		btnRegDOLimpiarPrimeraFecha.setOnClickListener(this);
		btnRegDOPrimeraHora.setOnClickListener(this);
		btnRegDOLimpiarPrimeraHora.setOnClickListener(this);
		btnRegDOSegundaFecha.setOnClickListener(this);
		btnRegDOLimpiarSegundaFecha.setOnClickListener(this);
		btnRegDOSegundaHora.setOnClickListener(this);
		btnRegDOLimpiarSegundaHora.setOnClickListener(this);
		btnRegDORutaFoto.setOnClickListener(this);
		btnRegDORutaFoto2.setOnClickListener(this);
		btnRegDOGuardar.setOnClickListener(this);
		btnRegDORegresa.setOnClickListener(this);
		
		// OBTENER CLIENTE ...!!
		this.ObtenerCliente();
		
	}

	public void ObtenerCliente()
	{
		// LISTA DEL CLIENTE ...!!
   	 	beCliente[] listaCli = blCliente.obtenerCliente(nroSolicitud);
   	 	for (int i = 0; i < listaCli.length; i++) 
   	 	{
   	 		nombre= listaCli[i].Nombre.toString();
   	 		paterno = listaCli[i].Paterno.toString();
   	 		materno= listaCli[i].Materno.toString();
   	 		razon_social= listaCli[i].Razon_Social.toString();
   	 		direccion= listaCli[i].Direccion.toString();
   	 		distrito= listaCli[i].Distrito.toString();
   	 		provincia= listaCli[i].Provincia.toString();
   	 		departamento= listaCli[i].Departamento.toString();
   	 		idVerificacion= listaCli[i].Id_Verificacion.toString();
   	 		idFormato= listaCli[i].Id_Formato.toString();
   	 		observaciones= listaCli[i].Observaciones.toString();
   	 		entidad= listaCli[i].Entidad.toString();
   	 		fecha_ingreso= listaCli[i].Fecha.toString();
   	 	}
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
