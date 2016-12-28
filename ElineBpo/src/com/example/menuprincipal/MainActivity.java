package com.example.menuprincipal;

import java.util.ArrayList;
import java.util.List;
import BE.beDrawerItem;
import BE.beTipoPersona;
import BL.blTipoPersona;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MainActivity extends Activity 
{
	 // DECLARANDO CLASES ...!!
	 blTipoPersona blTipoPersona = new blTipoPersona();
		 
	 // DECLARANDO LAS VARIABLES GLOBAL ...!!
	 public static String usu = "Ernesto Dominguez Aco";
	 public static String Nom_Completo;
	
	 private DrawerLayout mDrawerLayout;
     private ListView mDrawerList;
     private ActionBarDrawerToggle mDrawerToggle;
     private CharSequence mDrawerTitle;
     private CharSequence mTitle;

     CustomDrawerAdapter adapter;
     List<beDrawerItem> dataList;
          
     public String usur,nombre, paterno, materno, desc_tipo, id_TipoPersona;
     
     @Override
     protected void onCreate(Bundle savedInstanceState) 
	 {
    	 super.onCreate(savedInstanceState);
    	 setContentView(R.layout.activity_main);
		
    	 // USUARIO LOGEADO ...!!
    	 usu=getIntent().getStringExtra("usuario");
				
    	 // DATOS TIPO PERSONA ...!!
    	 this.ObtenerDatos();
				
		 // Initializing
    	 dataList = new ArrayList<beDrawerItem>();
    	 mTitle = mDrawerTitle = getTitle();
    	 mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    	 mDrawerList = (ListView) findViewById(R.id.left_drawer);

    	 // BARRA  LATRAL DEL MENU ...!!
    	 mDrawerLayout.setDrawerShadow(R.drawable.ic_barra, GravityCompat.START);
        
    	 // AGREGANDO UNA LISTA AL SPINNER ...!!
    	 dataList.add(new beDrawerItem(true)); 
        
    	 // ASIGNAR TITULO PRINCIPAL ...!!
    	 dataList.add(new beDrawerItem("  PRINCIPAL")); 
    	 dataList.add(new beDrawerItem("  Movimientos", R.drawable.ic_movimientos));
    	 dataList.add(new beDrawerItem("  Historial", R.drawable.ic_historial));
    	 dataList.add(new beDrawerItem("  Pendientes", R.drawable.ic_pendientes));
    	 dataList.add(new beDrawerItem("  Coordenadas", R.drawable.ic_coordenadas));
        
    	 // ASIGNAR TITULO OPCIONES ...!!
    	 dataList.add(new beDrawerItem("  OPCIONES")); 
    	 dataList.add(new beDrawerItem("  Información", R.drawable.ic_informacion));
    	 dataList.add(new beDrawerItem("   Cerrar Sesión", R.drawable.ic_salir2));
                
    	 adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,dataList, nombre + " " + paterno + " " + materno, desc_tipo);
        
    	 mDrawerList.setAdapter(adapter);
    	 mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
    	 getActionBar().setDisplayHomeAsUpEnabled(true);
    	 getActionBar().setHomeButtonEnabled(true);
         
    	 mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) 
    	 {
    		 public void onDrawerClosed(View view) 
    		 {
    			 getActionBar().setTitle(mTitle);
    			 invalidateOptionsMenu();
    		 }	
         
    		 public void onDrawerOpened(View drawerView) 
    		 {
    			 getActionBar().setTitle(mDrawerTitle);
    			 invalidateOptionsMenu();
    		 }
    	 };
         
    	 mDrawerLayout.setDrawerListener(mDrawerToggle);
         
    	 if (savedInstanceState == null) 
    	 {	 
    		 if (dataList.get(0).isSpinner() & dataList.get(1).getTitle() != null) {} 
    		 else if (dataList.get(0).getTitle() != null) {} 
    		 else {}
        }
	 }
	
     public void ObtenerDatos()
     {
    	 // LISTA DE LA PERSONA ...!!
    	 beTipoPersona[] listaPer = blTipoPersona.obtenerTipoPersona(usu);
    	 for (int i = 0; i < listaPer.length; i++) 
    	 {
    		 usur= listaPer[i].Nom_usuario.toString();
    		 nombre = listaPer[i].Nom_persona.toString();
    		 paterno= listaPer[i].Ap_paterno.toString();
    		 materno= listaPer[i].Ap_materno.toString();
    		 desc_tipo= listaPer[i].Desc_tipo.toString();
    		 id_TipoPersona= listaPer[i].Id_tipopersona.toString();
    	 }
     }
	
     @SuppressLint("NewApi")
     public void SelectItem(int possition) 
     {	 
    	 switch (possition) 
    	 {
    	 	case 2:
    	 		Intent optMovimientos = new Intent(this,MovimientosActivity.class);
    	 		optMovimientos.putExtra("usuario", usur);
    	 		optMovimientos.putExtra("id_tipoPersona", id_TipoPersona);
        		startActivity(optMovimientos);
     			finish();
        		break;
        	case 3:
        		Intent optEmpresa = new Intent(this,EmpresaActivity.class);
        		optEmpresa.putExtra("usuario", usur);
        		optEmpresa.putExtra("id_tipoPersona", id_TipoPersona);
        		startActivity(optEmpresa);
     			finish();
        		break;
        	case 4:
        		break;
        	case 5:
        		break;
        	case 7:
        		break;
        	case 8:
         		break;
        	default:
        		break;
    	 }

    	 mDrawerList.setItemChecked(possition, true);
    	 setTitle(dataList.get(possition).getItemName());
    	 mDrawerLayout.closeDrawer(mDrawerList);
     }
	
     @SuppressLint("NewApi")
     @Override
     public void setTitle(CharSequence title) 
     {
    	 mTitle = title;
    	 getActionBar().setTitle(mTitle);
     }
	 
     @Override
     protected void onPostCreate(Bundle savedInstanceState) 
     {
    	 super.onPostCreate(savedInstanceState);
    	 mDrawerToggle.syncState();
     }
	 
     @Override
     public boolean onOptionsItemSelected(MenuItem item) 
     {
    	 // MENU BARRA LATERAL ...!!
    	 if (mDrawerToggle.onOptionsItemSelected(item)) 
    	 {
    		 return true;
    	 }	 
    	 
    	 return false;
     }
	 
     @Override
     public void onConfigurationChanged(Configuration newConfig) 	
     {
    	 super.onConfigurationChanged(newConfig);
    	 mDrawerToggle.onConfigurationChanged(newConfig);
     }
     
     @Override
     public boolean onCreateOptionsMenu(Menu menu) 
     {
    	 // INFLATER EL MENÚ: AGREGA ELEMENTOS A LA BARRA DE ACCIÓN SI ESTA PRESENTE ...!!
    	 getMenuInflater().inflate(R.menu.main, menu);
    	 return true;
     }

     private class DrawerItemClickListener implements ListView.OnItemClickListener 
     {
    	 @Override
    	 public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    	 {
    		 if (dataList.get(position).getTitle() == null) 
    		 {
    			 SelectItem(position);
    		 }
    	 }
     }
}
