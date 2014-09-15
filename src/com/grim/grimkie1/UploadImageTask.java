package com.grim.grimkie1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.AsyncTask;
import android.util.Log;

public class UploadImageTask extends AsyncTask<String, String, String> {
	public AsyncResponse asyncResponseCourier = null;
	
	
	@Override
	protected String doInBackground(String... params) {
		String responseString = "ERROR A";
		String DEBUG_TAG = "Debug";
		
		HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response;
	    HttpGet httpGet = new HttpGet("http://picture-grimkie1.herokuapp.com/api/");
	    JSONObject respJson;
	    JSONArray jsonResult;
	     
	    try {
			response = httpclient.execute(httpGet);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder builder = new StringBuilder();
	    	Log.i(DEBUG_TAG, "1A");

			for (String line = null; (line = reader.readLine()) != null;) {
			    builder.append(line).append("\n");
			}
			JSONTokener tokener = new JSONTokener(builder.toString());
			JSONObject jsonObj = new JSONObject(builder.toString());
		
			responseString = jsonObj.getString("message");
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	     
	    return responseString;
	    }

	    @Override
	    protected void onPostExecute(String responseString) {
	        asyncResponseCourier.returnResponse(responseString);
	        //Do anything with response..
	    }
		
	}

