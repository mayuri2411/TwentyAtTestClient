package com.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
 
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
 
public class HttpConnection { 

	private static final HttpConnection INSTANCE = new HttpConnection();
	private static String URL = "http://ec2-50-16-41-243.compute-1.amazonaws.com:8080/twentyat-web/";
	//private static Logger logger = Logger.getLogger("HttpConnection");
        
	public static HttpConnection getInstance() {
		return INSTANCE;
	}
                 
	public synchronized String execute(String method, Map<String, String> params) throws JSONException, ClientProtocolException, IOException {
            
	//logger.info("START : "+method+" : with parameter :"+params);
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost(URL+method);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        JSONObject json = new JSONObject();
               
        for(Entry<String, String> entry : params.entrySet()) {
        	String key = entry.getKey();
        	String value = entry.getValue();
                System.out.println("========================");
                System.out.println("Key=="+key+"value==="+value);
                System.out.println("========================");
        	json.put(key, value);
        }

        StringEntity entity = new StringEntity(json.toString());
        httpPost.setEntity(entity);

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpclient.execute(httpPost, responseHandler);
 
//        logger.info("END : "+method+" : with parameter :"+params);

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();

        return responseBody;
	}
}
