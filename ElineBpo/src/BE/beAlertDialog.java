package BE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class beAlertDialog 
{  
	  @SuppressWarnings("deprecation")
	  public void ShowAlertDialog(Context context, String title, String message, Boolean status, String setButon)
	  {
		  AlertDialog alertDialog= new AlertDialog.Builder(context).create();
		  alertDialog.setTitle(title);
		  alertDialog.setMessage(message);
		  alertDialog.setButton(setButon, new DialogInterface.OnClickListener()
		  {
			  @Override
			  public void onClick(DialogInterface dialog, int which) 
			  {
				  // NO HACER NADA EN LA OPCION OK AL MOMENTO QUE NO ENCENTRA UNA CONEXION A INTERNET ...!!
				  dialog.cancel();
			  }
		  });
			
		  alertDialog.show();
		}
	  
	  
}
