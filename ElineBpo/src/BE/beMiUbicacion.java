package BE;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class beMiUbicacion 
{
	private static final int TIEMPO_LIMITE_POSICION = 20000;
	
	private LocationResult locationResult;
	private LocationManager locationManager;
	private Timer timer1;
	private boolean gps = false;
	private boolean network = false;
	
	LocationListener locationListenerGps = new LocationListener() 
	{
		@Override
		public void onLocationChanged(Location paramLoc) 
		{
			beMiUbicacion.this.timer1.cancel();
			beMiUbicacion.this.locationResult.gotLocation(paramLoc);
			beMiUbicacion.this.locationManager.removeUpdates(this);
			beMiUbicacion.this.locationManager.removeUpdates(beMiUbicacion.this.locationListenerNetwork);
		}
		
		@Override
		public void onProviderDisabled(String arg0) {}
		
		@Override
		public void onProviderEnabled(String arg0) {}
		
		@Override
		public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
	};
	
	LocationListener locationListenerNetwork = new LocationListener() 
	{
		@Override
		public void onLocationChanged(Location paramLoc) 
		{
			beMiUbicacion.this.timer1.cancel();
			beMiUbicacion.this.locationResult.gotLocation(paramLoc);
			beMiUbicacion.this.locationManager.removeUpdates(this);
			beMiUbicacion.this.locationManager.removeUpdates(beMiUbicacion.this.locationListenerGps);
		}
		
		@Override
		public void onProviderDisabled(String arg0) {}
		
		@Override
		public void onProviderEnabled(String arg0) {}
		
		@Override
		public void onStatusChanged(String paramStrig, int paramInt, Bundle paramBundle) {}
	};

	public boolean getLocation (Context paramContext, LocationResult paramLocationResult)
	{
		this.locationResult = paramLocationResult;
		if(this.locationManager == null)
		{
			this.locationManager = (LocationManager) paramContext.getSystemService("location");
		}
		
		this.gps= this.locationManager.isProviderEnabled("gps");
		this.network= this.locationManager.isProviderEnabled("network");
		
		if((!this.gps) && (!this.network))
		{
			return false;
		}
		
		if(this.gps)
		{
			this.locationManager.requestLocationUpdates("gps", 1000, 0, this.locationListenerGps);
		}
		
		if(this.network)
		{
			this.locationManager.requestLocationUpdates("network", 1000, 0, this.locationListenerNetwork);
		}
		
		this.timer1 = new Timer();
		this.timer1.schedule(new LastLocation(), TIEMPO_LIMITE_POSICION);
		
		return true;
	}
	
	private class LastLocation extends TimerTask
	{
		LastLocation() {}
    
		public void run()
		{
			beMiUbicacion.this.locationManager.removeUpdates(beMiUbicacion.this.locationListenerGps);
			beMiUbicacion.this.locationManager.removeUpdates(beMiUbicacion.this.locationListenerNetwork);
			Location localLocation1 = null;
			if (beMiUbicacion.this.gps) 
			{
				localLocation1 = beMiUbicacion.this.locationManager.getLastKnownLocation("gps");
			}
      
			Location localLocation2 = null;
      
			if (beMiUbicacion.this.network) 
			{
				localLocation2 = beMiUbicacion.this.locationManager.getLastKnownLocation("network");
			}
      
			if ((localLocation1 != null) && (localLocation2 != null))
			{
				if (localLocation1.getTime() > localLocation2.getTime())
				{
					beMiUbicacion.this.locationResult.gotLocation(localLocation1);
					return;
				}
        
				beMiUbicacion.this.locationResult.gotLocation(localLocation2);
				return;
			}
      
			if (localLocation1 != null)
			{
				beMiUbicacion.this.locationResult.gotLocation(localLocation1);
				return;
			}
      
			if (localLocation2 != null)
			{
				beMiUbicacion.this.locationResult.gotLocation(localLocation2);
				return;
			}
      
			beMiUbicacion.this.locationResult.gotLocation(null);
		}
	}
	
	public static abstract class LocationResult
	{
		public abstract void gotLocation(Location paramLocation);
	}
}
