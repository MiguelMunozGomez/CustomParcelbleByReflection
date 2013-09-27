package com.mitdev.exampleCustomParcelable;

import com.mitdev.exampleCustomParcelable.Model.TestModel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditableTestAcivity extends Activity {
	
	private static final int OK_RESULT_CODE=1;
	
	TestModel myTestClass;
	EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_main);
		
		Bundle bundle = getIntent().getExtras();
		myTestClass = bundle.getParcelable("Version");
		
		TextView name = (TextView)findViewById(R.id.idNombre2);
		TextView version = (TextView)findViewById(R.id.idVersion2);
		ImageView logo = (ImageView)findViewById(R.id.idLogo2);
		
		editText = (EditText)findViewById(R.id.editText1);
		
		name.setText(myTestClass.getNombre());
		version.setText(myTestClass.getVersion());
		logo.setImageResource(myTestClass.getLogo());
		
		
        Button crear =(Button) findViewById(R.id.button1);
        crear.setOnClickListener(new View.OnClickListener(){
	        public void onClick(View view){
	        	returnParamsCreate();
	        }
        });
        
        Button modificar =(Button) findViewById(R.id.button3);
        modificar.setOnClickListener(new View.OnClickListener(){
	        public void onClick(View view){
	        	returnParamsModificate();
	        }
        });
	}
	
	protected void returnParamsCreate(){
		
		TestModel version = new TestModel(0, editText.getText().toString(), myTestClass.getVersion(), myTestClass.getLogo());
		
		Intent intent = new Intent();
		intent.putExtra("Result", version);
		intent.putExtra("Valid", true);
		setResult(OK_RESULT_CODE, intent);
		finish();		
	}
	
	protected void returnParamsModificate(){
		
		TestModel version = new TestModel(myTestClass.getId(), editText.getText().toString(), myTestClass.getVersion(), myTestClass.getLogo());
		
		Intent intent = new Intent();
		intent.putExtra("Result", version);
		intent.putExtra("Valid", true);
		setResult(OK_RESULT_CODE, intent);
		finish();		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			//return true;
			Intent intent = new Intent();
			intent.putExtra("Valid", false);
			setResult(OK_RESULT_CODE, intent);
			finish();	
		}
		return super.onKeyDown(keyCode, event);
	}

}
