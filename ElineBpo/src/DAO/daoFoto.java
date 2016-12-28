package DAO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import BE.beFoto;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class daoFoto 
{
	public String ruta;
	
	beFoto  beFoto  = new beFoto();
	Paint 	paint;
	File 	file;
		
	public beFoto[] VerificacionFoto(String nro_solicitud)
	{
		beFoto[] listado = null;
		
		SoapObject resquest = new SoapObject(daoUtilitarios.NAMESPACE,daoUtilitarios.METHOD_NAMEF);
	    resquest.addProperty("nro_solicitud", nro_solicitud);
	   
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.dotNet= true;
	    envelope.setOutputSoapObject(resquest);
	    
	    HttpTransportSE transporte= new HttpTransportSE(daoUtilitarios.URL);
	    
	    try 
	    {
	    	transporte.call(daoUtilitarios.SOAP_ACTIONF, envelope);
	    	SoapObject resSoap =(SoapObject)envelope.getResponse();
	    	
	    	listado= new beFoto[resSoap.getPropertyCount()];
	    	
	    	for (int i = 0; i < listado.length; i++) 
	    	{
	    		SoapObject in= (SoapObject)resSoap.getProperty(i);
	    		beFoto Nrosolicitud= new beFoto();
				
				Nrosolicitud.Nombre=in.getProperty(0).toString();
				Nrosolicitud.Paterno=in.getProperty(1).toString();
				Nrosolicitud.Materno=in.getProperty(2).toString();
				Nrosolicitud.Razon_social=in.getProperty(3).toString();
				Nrosolicitud.Formato=in.getProperty(4).toString();
				Nrosolicitud.Foto_1=in.getProperty(5).toString();
				Nrosolicitud.Foto_2=in.getProperty(6).toString();
				Nrosolicitud.Foto_3=in.getProperty(7).toString();
			
				listado[i]= Nrosolicitud;
	    	}
		} 
	    catch (Exception e) 
		{
	      	Toast.makeText(getBaseContext(),daoUtilitarios.mensajeError.toString(),Toast.LENGTH_SHORT).show();
		}
	    
		return listado;
	}

	private Context getBaseContext() 
	{
		return null;
	}
	
	public void Pain()
	{
		// DIBUJAMOS LA MARCA DE AGUA DE LA FOTO ...!!
        paint= new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLUE);
		paint.setTextSize(25);
	}
	
	public String dibujarImagen(String fecServer, String posicion, ImageView img, Bitmap bmp, String numero)
	{
		// ASIGNAR CORDENADAS DE 4 BORDES ...!!
		Rect rectText = new Rect();
		this.Pain();
		paint.getTextBounds(fecServer, 0, fecServer.length(), rectText);
		
		// CANVAS REPRESENTA SUPERFICIE DONDE PODEMOS DIBUJAR EL BITMAP ...!!
		Canvas newCanvas = new Canvas(bmp);
		
		// DRAWTEXT DIBUJA EL TEXTO CON ORIGEN DE X,Y TAMBIÉN EL USO DEL PAINT ...!!
		newCanvas.drawText(fecServer, 0,rectText.height(), paint);
		
		// ASIGNAMOS EL BITMAP AL IMAGENVIEW ...!!
		img.setImageBitmap(bmp);
		
		// MOSTRAMOS LA IMAGENVIEW ...!!
		img.setVisibility(View.VISIBLE);
		
		// GUARDAMOS LA IMAGENVIEW EN MEMORIA ...!!
		this.GuardarFoto(posicion,bmp, numero);	
		
		return "Se guardo la foto con éxito ...!!";
	}
	
	public String GuardarFoto(String posicion,Bitmap fotoBitmap, String numero)
	{
		// SE ENCARGA DE REGISTRAR EL NOMBRE DE LA FOTO TOMADA ...!!
		String fileName=numero.toString() + posicion.toString() + ".jpg";
		ruta= Environment.getExternalStorageDirectory().getAbsolutePath();
		
		// ASIGNAMOS DONDE GUARDA LAS FOTOS ...!!
		File folder= new  File(ruta + "/Pictures/E line bpo");
		if(!folder.exists())
		{
			folder.mkdirs();
		}
		
		// ASIGNAMOS LA RUTA Y EL NOMBRE DE LA FOTO ...!!
		file= new File(folder,fileName);

		try
		{
			// FLUJO DE SALIDA DE DATOS MATRIZ BYTE ...!! 
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			
			// COMPRIMIR EL BITMAP 100% ...!!
			fotoBitmap.compress(CompressFormat.JPEG, 100, stream);
			byte[] byteArray= stream.toByteArray();
			
			// FLUJO DE SALIDA DE ESCRITURA DE DATOS ASIGNAMOS EL BYTE ...!!
			FileOutputStream outStream =  new  FileOutputStream(file);
			outStream.write(byteArray);
			outStream.flush();
			outStream.close();
			
			MediaStore.Images.Media.insertImage(getContentResolver(), fotoBitmap, file.getName(), file.getName());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return "Exito";
	}

	private ContentResolver getContentResolver() 
	{
		return null;
	}
}
