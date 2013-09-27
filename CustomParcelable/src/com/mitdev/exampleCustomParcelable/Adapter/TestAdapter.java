package com.mitdev.exampleCustomParcelable.Adapter;

import java.util.ArrayList;
import java.util.Random;

import com.mitdev.exampleCustomParcelable.R;
import com.mitdev.exampleCustomParcelable.Model.TestModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TestAdapter extends ArrayAdapter<TestModel>{
	
	private ArrayList<TestModel> versiones;
	
	public TestAdapter(Context context, int layout, ArrayList<TestModel> vers){
		super(context, layout, vers);
			this.versiones = vers;
	}	
	
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder = null;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.listversion, null);
		}
			TestModel version = versiones.get(position);

			holder = new ViewHolder();
			if(version != null){
				holder.hNombre = (TextView) convertView.findViewById(R.id.idNombre);
				holder.hVersion = (TextView) convertView.findViewById(R.id.idVersion);
				holder.hImage = (ImageView) convertView.findViewById(R.id.idLogo);
				
				holder.hNombre.setText(version.getNombre());
				holder.hVersion.setText(version.getVersion());
				holder.hImage.setImageResource(version.getLogo());
			} 
		return convertView;
	}
	
	public void AddElement(String nombre, String version, int logo){
		versiones.add(new TestModel(GenerateId(), nombre, version, logo));
	}
	
	public void SetElement(int id, String nombre, String version, int logo){
		TestModel v = FindVersionById(id);
		v.setValues(nombre, version, logo);
	}
	
	private TestModel FindVersionById(int id){
		for(TestModel android : versiones){
			if(android.getId() == id)return android;
		}
		return null;
	}
	
	private int GenerateId(){
		Random random = new Random();
		int size = versiones.size();
		while(true){
			boolean isFree = true;
			int id = random.nextInt(size + 1000);
			for(TestModel android : versiones){
				if(android.getId() == id)isFree = false;
			}
			if(isFree) return id;
		}
	}	
	
	private class ViewHolder {
		TextView hNombre;
		TextView hVersion;
		ImageView hImage;
	}
}