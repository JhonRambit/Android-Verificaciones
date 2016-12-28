package com.example.menuprincipal;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class FragmentOne extends Fragment 
{
      ImageView ivIcon;
      TextView tvItemName;
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
      public FragmentOne() {}
 
    @SuppressLint("NewApi")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	View view = inflater.inflate(R.layout.fragment_layout_one, container, false);
 
    	ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
    	tvItemName = (TextView) view.findViewById(R.id.frag1_text);
 
    	tvItemName.setText(getArguments().getString(ITEM_NAME));
    	ivIcon.setImageDrawable(view.getResources().getDrawable(getArguments().getInt(IMAGE_RESOURCE_ID)));
    	return view;
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
    	// TODO Auto-generated method stub
    	    	//super.onCreateOptionsMenu(menu, R.id.drawer_icon);
    	inflater.inflate(R.id.drawer_icon, menu);
    }
}
