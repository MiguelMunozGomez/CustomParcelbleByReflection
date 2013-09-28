package com.mitdev.exampleCustomParcelable.infraestructure;

import java.lang.reflect.Field;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public abstract class CustomParcelable implements Parcelable{	
	
	public CustomParcelable(){
		super();
	}
	
	public CustomParcelable(Parcel source){
		readToParcel(source);
	}
			
	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		Field propertyList[] = this.getClass().getDeclaredFields();
		Log.d("Test","Write Cantidad : " + propertyList.length);
		for(int i=0; i<propertyList.length;i++){
			Field field = propertyList[i];
			field.setAccessible(true);
			write(field, dest);
		}
	}
	
	protected void readToParcel(Parcel source){
		Field propertyList[] = this.getClass().getDeclaredFields();
		Log.d("Test","Read Cantidad : " + propertyList.length);

		for(int i=0; i<propertyList.length;i++){
			Field field = propertyList[i];		
			field.setAccessible(true);
			read(field, source);
		}
	}
	
	protected void write(Field field, Parcel dest){
		try{
			Log.d("Test","WRITE Nombre : " + field.getName() + "  -- TIPO : " + field.getType().toString());
			String type = field.getType().toString();
			
			if(type.equals("interface android.os.Parcelable$Creator")){
				return;
			}
			else{
				dest.writeValue(field.get(this));
			}
			
		}
		catch(IllegalAccessException e){
			Log.d("Test",e.toString());
		}
	}
	
	protected void read(Field field, Parcel source){
		try{
			Log.d("Test","READ Nombre : " + field.getName() + "  -- TIPO : " + field.getType().toString());
			String type = field.getType().toString();

			if(type.equals("interface android.os.Parcelable$Creator")){
				return;
			}
			else{
				field.set(this, source.readValue(null));
			}
			
		}
		catch(IllegalAccessException e){
			Log.d("Test",e.toString());
		}
	}
}