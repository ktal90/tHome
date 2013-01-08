package com.ktal.thome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	 Button sendButton;
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        // load the layout
	        setContentView(R.layout.activity_main);        
	 
	        sendButton = (Button) findViewById(R.id.sendButton);
	        sendButton.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 // Perform action on click
	            	 new PostTask().execute();
	             }
	         });
    }
	    
	    private class PostTask extends AsyncTask<Void, Void, Void> {

	        // Move the code that was in check_login to the
	        // doInBackground method below

	        protected Void doInBackground(Void... params) {

	        	// this is the function that gets called when you click the button
	    		  HttpClient httpclient = new DefaultHttpClient();
	    	          // put the address to your server and receiver file here
	    		  HttpPost httppost = new HttpPost("http://192.168.137.1/arduinoor/doorphp.php");
	    	          try {
	    		         List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	    	                 // we wont be receiving the parameter ID in your server, but it is here to show you how you can send more data
	    		         nameValuePairs.add(new BasicNameValuePair("id", "12345"));
	    	                 // message is the parameter we are receiving, it has the value of 1 which is the value that will be sent from your server to your Arduino board
	    		         nameValuePairs.add(new BasicNameValuePair("message", "a"));
	    		       httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    		   	   httpclient.execute(httppost); // send the parameter to the server
	    		   	   Log.w("thome","Sent");
	    		     } catch (ClientProtocolException e) {
	    		         // TODO Auto-generated catch block
	    		    	 Log.w("thome","ClientProtocolException");
	    		     } catch (IOException e) {
	    		         // TODO Auto-generated catch block
	    		    	 Log.w("thome","IOException");
	    		     }
					return null;
	    }
	    }
}
