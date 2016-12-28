package com.example.menuprincipal;

import java.util.ArrayList;
import java.util.List;
import BE.beDrawerItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomDrawerAdapter extends ArrayAdapter<beDrawerItem> 
{
	Context context;
    List<beDrawerItem> drawerItemList;
    int layoutResID;
    String usuarioLogeado;
    String Descripcion;
      
    public CustomDrawerAdapter(Context context, int layoutResourceID, List<beDrawerItem> listItems, String usuario, String descripcion) 
    {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
        this.usuarioLogeado= usuario;
        this.Descripcion= descripcion;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        DrawerItemHolder drawerHolder;
        View view = convertView;
 
            if (view == null)
            {
                  LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                  drawerHolder = new DrawerItemHolder();

                  view = inflater.inflate(layoutResID, parent, false);
                  drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
                  drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
                  drawerHolder.spinner = (Spinner) view.findViewById(R.id.drawerSpinner);
                  drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);
                  drawerHolder.headerLayout = (LinearLayout) view.findViewById(R.id.headerLayout);
                  drawerHolder.itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
                  drawerHolder.spinnerLayout = (LinearLayout) view.findViewById(R.id.spinnerLayout);
 
                  view.setTag(drawerHolder);
            } 
            else 
            {
                  drawerHolder = (DrawerItemHolder) view.getTag();
            }
 
            beDrawerItem dItem = (beDrawerItem) this.drawerItemList.get(position);
 
            if (dItem.isSpinner()) 
            {
                  drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
                  drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
                  drawerHolder.spinnerLayout.setVisibility(LinearLayout.VISIBLE);
 
                  List<SpinnerItem> userList = new ArrayList<SpinnerItem>();
 
                  userList.add(new SpinnerItem(R.drawable.ic_usuario,usuarioLogeado, Descripcion));
 
                  CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(context, R.layout.custom_spinner_item, userList);
 
                  drawerHolder.spinner.setAdapter(adapter);
            } 
            else if (dItem.getTitle() != null) 
            {
                  drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
                  drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
                  drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
                  drawerHolder.title.setText(dItem.getTitle());
            } 
            else 
            {
                  drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
                  drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
                  drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);
 
                  drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
                  drawerHolder.ItemName.setText(dItem.getItemName());
            }
            return view;
      }
 
      private static class DrawerItemHolder 
      {
            TextView ItemName, title;
            ImageView icon;
            LinearLayout headerLayout, itemLayout, spinnerLayout;
            Spinner spinner;
      }
}	
