package com.mitdev.exampleCustomParcelable.Model;

import com.mitdev.exampleCustomParcelable.infraestructure.CustomParcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class TestModel extends CustomParcelable{
	protected int id;
	protected String nombre;
	private String version;
	private int logo;
	
	@SuppressWarnings("rawtypes")
	public static Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public TestModel createFromParcel(Parcel in) {
			return new TestModel(in);			
		}
		
		public TestModel[] newArray(int size){
			return new TestModel[size];
		}
	};
	
	public TestModel(Parcel source){
		super(source);
	}
	
	public TestModel(int id, String nombre, String version, int logo){
		super();

		this.id = id;
		this.nombre = nombre;
		this.version = version;
		this.logo = logo;
	}
	
	public int getId(){
		return this.id;
	}
		
	public String getVersion(){
		return this.version;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}	
	
	public int getLogo(){
		return this.logo;
	}
	
	public void setLogo(int logo){
		this.logo = logo;
	}
	
	public void setValues(String nombre, String version, int logo){
		this.nombre = nombre;
		this.version = version;
		this.logo = logo;
	}
}