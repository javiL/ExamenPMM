package com.example.examenjavierlopez;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PantallaResult extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallaresultado);
		
		TextView tarifa = (TextView)findViewById(R.id.TxtTarifa);
		
		Bundle bund = this.getIntent().getExtras();
		
		tarifa.setText(bund.getString("Tarifa"));
		
	}
	
	
}
