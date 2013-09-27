package com.mitdev.exampleCustomParcelable;

import java.util.ArrayList;

import com.mitdev.exampleCustomParcelable.Adapter.TestAdapter;
import com.mitdev.exampleCustomParcelable.Model.TestModel;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class TestListActivity extends ListActivity{
	
	protected static final int REQUEST_CODE = 10;
	TestAdapter version;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<TestModel> versiones = new ArrayList<TestModel>();
        version = new TestAdapter(TestListActivity.this,R.layout.listversion,versiones);       
        setListAdapter(version);
        
        AddElement("creep001", "1.0", R.drawable.creep001);
        AddElement("creep002", "1.5", R.drawable.creep002);
        AddElement("creep003", "1.6", R.drawable.creep003);
        AddElement("creep004", "2.0", R.drawable.creep004);
        
        Button boton =(Button) findViewById(R.id.button2);
        boton.setOnClickListener(new View.OnClickListener(){
	        public void onClick(View view){
	        	AddElement("creep", "1.1", R.drawable.creep005);
	        }
        });
        
    }
    protected void onListItemClick(ListView l, View view, int position, long id){
	    super.onListItemClick(l, view, position, id);
	    TestModel versionSelected =  (TestModel) getListAdapter().getItem(position);
	    
    	Intent intent = new Intent(this, EditableTestAcivity.class );
    	intent.putExtra("Version", versionSelected);
    	startActivityForResult(intent, REQUEST_CODE);
     }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
	    	if(requestCode == REQUEST_CODE){
	    		Bundle bundle = data.getExtras();
	    		if(bundle.getBoolean("Valid")){
		    		TestModel android = bundle.getParcelable("Result");
		    		if(android.getId() == 0) AddElement(android.getNombre(), android.getVersion(), android.getLogo());
		    		else SetElement(android.getId(), android.getNombre(), android.getVersion(), android.getLogo());
	    		}
    	}
    }
    
    private void AddElement(String nombre, String version, int logo){		
		this.version.AddElement(nombre,version,logo);
		this.version.notifyDataSetChanged();
    }
    
    private void SetElement(int id, String nombre, String version, int logo){		
		this.version.SetElement(id, nombre,version,logo);
		this.version.notifyDataSetChanged();
    }
}
