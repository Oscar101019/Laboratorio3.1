package com.example.oscar.serviciosweb;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by oscar on 20/03/18.
 */

public class GetJson extends AsyncTask<Void,Void,Void> {

    String data ="";
    String dataParsed = "";
    String singleParsed ="";

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Tepic,mx&APPID=12940bd5e2f8213af0930330cd183eb3");
            HttpURLConnection httpURLConnection = (HttpURLConnection)
                    url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject JO  = new JSONObject(data);

            JSONObject jsonObjectMain= JO.getJSONObject("main");
            JSONObject jsonObjectSys= JO.getJSONObject("sys");
            JSONObject jsonObjectWind= JO.getJSONObject("wind");
            JSONObject jsonObjectCoord= JO.getJSONObject("coord");


            singleParsed = "Ciudad: " + JO.get("name") +", Pais: " + jsonObjectSys.get("country") + "\n"+
                    "Coordenadas: " +"Longitud = " +jsonObjectCoord.get("lon")+", Latitud = "+ jsonObjectCoord.get("lat") + "\n"+
                        "Humedad: " + jsonObjectMain.get("humidity") + "\n"+
                        "Temperatura: " + jsonObjectMain.get("temp") + "\n"+
                    "Temperatura Minima: " + jsonObjectMain.get("temp_min") + "\n"+
                    "Temperatura Maxima: " + jsonObjectMain.get("temp_max") + "\n"+
                    "Presion: " + jsonObjectMain.get("pressure") + "\n"+
                    "Velocidad del Viento: " + jsonObjectWind.get("speed") + "\n";
                dataParsed = dataParsed + singleParsed +"\n" ;
            }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParsed);
    }


}
