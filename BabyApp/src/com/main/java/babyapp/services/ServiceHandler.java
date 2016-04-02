package com.main.java.babyapp.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.main.java.babyapp.globalObject.GlobalObject;

public class ServiceHandler {

	static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;
 
    public ServiceHandler() {
 
    }
    
    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */
    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method, null);
    }
 
    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */
    public String makeServiceCall(String url, int method, List<NameValuePair> params) {
       try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
             
            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = null;
				try {
					httpPost = new HttpPost(url);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // adding post params
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }
 
                try {
					httpResponse = httpClient.execute(httpPost);
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 
            } else if (method == GET) {
                // appending params to url
                if (params != null) {
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = null;
				try {
					httpGet = new HttpGet(url);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 
                try {
					httpResponse = httpClient.execute(httpGet);
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 
            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        return response;
    }
    
    public String  uploadDataToServer(RequestParams entity){
    	AsyncHttpClient httpClient = new AsyncHttpClient();
    	
    	httpClient.post(GlobalObject.webserviceUrl + "add_bio", entity, new AsyncHttpResponseHandler(Looper.getMainLooper()){

			@Override
			public void onFailure(int statusCode,
					cz.msebera.android.httpclient.Header[] headers, byte[] responseBody,
					Throwable error) {
				
			}

			@Override
			public void onSuccess(int statusCode,
					cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
				int code = statusCode;
		        String decode = Arrays.toString(responseBody);
		        String response = new String(responseBody);
		        Log.i("HttpChild", response);
		        JSONObject jsonObject = null;
		        try {

		            jsonObject = new JSONObject(response);
		            String message = jsonObject.getString("message");

		        } catch (JSONException e) {
		            e.printStackTrace();
		        }

				
			};
    	});
    	return response;
    }
}
