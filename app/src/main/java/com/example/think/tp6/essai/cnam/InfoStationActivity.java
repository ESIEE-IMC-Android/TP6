package com.example.think.tp6.essai.cnam;




import android.app.Activity;
import android.os.Bundle;

import android.widget.Toast;
import com.example.think.tp6.velib.model.InfoStation;
import com.example.think.tp6.velib.model.StationVelib;

public class InfoStationActivity extends Activity {

	InfoStation infoStation;
	StationVelib stationVelib;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		infoStation= (InfoStation) getIntent().getSerializableExtra("info");
		stationVelib= (StationVelib) getIntent().getSerializableExtra("station");


		Toast.makeText(InfoStationActivity.this, "latitude:" + stationVelib.getLatitude() + " , longitude: " + stationVelib.getLongitude(), Toast.LENGTH_LONG).show();
		Toast.makeText(InfoStationActivity.this, "<" + infoStation.getAvailable() + " , " + infoStation.getFree() +">", Toast.LENGTH_LONG).show();

		finish();
    }
	
  
}
