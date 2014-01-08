package com.example.examenjavierlopez;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaPrincipal extends Activity {

	private Button BotonCalculo;
	private Button radioTarifaNormal;
	private Button radioTarifaUrgente;
	private CheckBox checkBoxRegalo;
	private CheckBox checkBoxTarjeta;
	static double tarifa;
	private EditText Peso;
	int precio;
	String zona,continente;
	int kg;
	
	//Spinner destino
	private Destino[] destino =
			new Destino[]{
					new Destino("Zona A","Asia y Oceania","30"),
					new Destino("Zona B","America y Africa","20"),
					new Destino("Zona C","Europa","10")};
	
	//CLASE ADAPTADOR
	class AdaptadorDestino extends ArrayAdapter<Destino> {
        
        Activity activity;
        AdaptadorDestino(Activity b) {
        super(b, R.layout.listitem_destino, destino);
        this.activity = b;
        }
        
        public View getDropDownView ( int position, View convertView, ViewGroup parent){
                return getView (position, convertView, parent);
        }
        
        public View getView(int position, View convertView, ViewGroup parent) {
                    LayoutInflater inflater = activity.getLayoutInflater();
                    View item = inflater.inflate(R.layout.listitem_destino, null);
                    
                    final TextView lblZona = (TextView)item.findViewById(R.id.LblZona);
                    lblZona.setText(destino[position].getZona());
                    
                    final TextView lblContinente = (TextView)item.findViewById(R.id.LblContinente);
                    lblContinente.setText(destino[position].getContinente());
                    
                    final TextView lblPrecio = (TextView)item.findViewById(R.id.LblPrecio);
                    lblPrecio.setText(destino[position].getPrecio()); 
                    
                    return(item);
            }
}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_principal);
		
		//adaptador del spinner
		final Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
		AdaptadorDestino adaptador = new AdaptadorDestino(this);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbOpciones.setAdapter(adaptador);
		
		
		//listener del spinner
		cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                		public void onItemSelected(AdapterView<?> parent,
                				android.view.View v, int position, long id) {
                				zona = destino[position].getZona();
                				continente = destino[position].getContinente();
                				precio = Integer.parseInt(destino[position].getPrecio());
                		}
                		
                		public void onNothingSelected(AdapterView<?> parent) {
                			        
                		        }
        });
        
		
		BotonCalculo = (Button)findViewById(R.id.BotonCalculo);
		radioTarifaNormal = (Button)findViewById(R.id.radio1);
		radioTarifaUrgente = (Button)findViewById(R.id.radio2);
		checkBoxRegalo = (CheckBox)findViewById(R.id.ChkRegalo);
		checkBoxTarjeta = (CheckBox)findViewById(R.id.ChkTarjeta);
		
		findViewById(R.id.gruporb);
		Peso = (EditText)findViewById(R.id.EditText);
		
		//Al clickar sobre el editText, este se borra
		Peso.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Peso.setText("");
			}
		});
		
		
		BotonCalculo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String peso = Peso.getText().toString();
				kg = Integer.parseInt(peso);
				
				
				if (kg <= 5){
					tarifa = precio +(kg * 1);
				}else{
					if (kg >= 6 && kg <= 10){
						tarifa = precio + (kg * 1.5);
					}else{
						if (kg >= 10){
							tarifa = precio + (kg * 2);
						}
					}
				}
				
				
			}
		});
			
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pantalla_principal, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int itemId = item.getItemId();
		
		switch(itemId) {
		case R.id.MenuOpcDibujo:
			Intent intentCanvas = new Intent(this,Dibujito.class);
			startActivity(intentCanvas);
			break;
		case R.id.MenuOpcAcercaDe:
			Toast.makeText(this, "Desarrollado por Javier Lopez de 2ºDAM", Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);	
}

}
