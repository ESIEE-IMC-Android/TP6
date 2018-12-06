package com.example.think.tp6.essai.cnam;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.think.tp6.R;
import com.example.think.tp6.velib.model.InfoStation;
import com.example.think.tp6.velib.model.ListeDesStationsVelib;
import com.example.think.tp6.velib.model.StationVelib;

import java.io.InputStream;
import java.net.URL;


public class ListeStationVelibActivity extends ListActivity {

    private ListeDesStationsVelib stations;
    private ProgressDialog progressDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        try {
            this.stations = new ListeDesStationsVelib();
        } catch (Exception e) {
        }
        new ChargeurListeDesStations().execute("http://www.velib.paris.fr/service/carto");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        String item = ((TextView) v).getText().toString();
//        StationVelib st = stations.lireStation(item);
        new ChargeurInfoStation((StationVelib) getListAdapter().getItem(position)).execute();
    }


    private class ChargeurListeDesStations extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... str) {
            try {

                URL url = new URL(str[0]);
                stations.chargerDepuisXML(url.openStream());
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    InputStream is = ListeStationVelibActivity.this.getAssets().open("stations.xml");
                    stations.chargerDepuisXML(is);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressDialog.dismiss();
            setListAdapter(new VebListAdapter(ListeStationVelibActivity.this, stations.getVelibArrayList()));
        }
    }

    private class ChargeurInfoStation extends AsyncTask<Void, Void, Boolean> {

        private StationVelib stationVelib;
        private InfoStation infoStation;
        public ChargeurInfoStation(StationVelib str) {
            this.stationVelib = str;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            infoStation= new InfoStation(stationVelib.getNumber());
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(progressDialog.isShowing())
                progressDialog.dismiss();
            Intent intent=new Intent(ListeStationVelibActivity.this,InfoStationActivity.class);
            intent.putExtra("info",infoStation);
            intent.putExtra("station",stationVelib);
            startActivity(intent);

        }
    }


}