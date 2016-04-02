package com.main.java.babyapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.main.java.babyapp.globalObject.GlobalObject;
import com.main.java.babyapp.services.ServiceHandler;

public class SignupActivity extends AppCompatActivity {

	private ProgressDialog pDialog;
	private RelativeLayout main_layout;
	private EditText txt_name, txt_email, txt_password;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        
        main_layout = (RelativeLayout)findViewById(R.id.main_layout);
        /*ImageView img_logo = (ImageView)findViewById(R.id.img_logo);
        Animation popup1 = AnimationUtils.loadAnimation(this, R.anim.view_popup);
        popup1.setStartOffset(50);
        img_logo.startAnimation(popup1);*/
        txt_name = (EditText)findViewById(R.id.txt_name);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_password = (EditText)findViewById(R.id.txt_password);
        
        txt_email.addTextChangedListener(new MyTextWatcher(txt_email));
        txt_password.addTextChangedListener(new MyTextWatcher(txt_password));
        
	}
	
	/**
     * Validating form
     */
    private boolean submitSignInForm() {
    	if (!validateName()) {
            return false;
        }
        if (!validateEmail()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }

        //Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
		return true;
    }
    
	public class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

		@Override
		public void afterTextChanged(Editable s) {
			switch (view.getId()) {
				case R.id.txt_name:
	                validateName();
	                break;
                case R.id.txt_email:
                    validateEmail();
                    break;
                case R.id.txt_password:
                    validatePassword();
                    break;
			}
		}
   }
	
	private boolean validateName(){
		String email = txt_name.getText().toString().trim();

        if (email.isEmpty()) {
        	//txt_email.setError("Error");
            //requestFocus(txt_email);
            return false;
        } else {
        	//txt_email.setErrorEnabled(false);
        }
        return true;
	}
	private boolean validateEmail() {
        String email = txt_email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
        	//txt_email.setError("Error");
            //requestFocus(txt_email);
            return false;
        } else {
        	//txt_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (txt_password.getText().toString().trim().isEmpty()) {
        	//txt_password.setError("Pass Error");
            //requestFocus(txt_password);
            return false;
        } else {
        	//txt_password.setErrorEnabled(false);
        }
        return true;
    }
    
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    
	public void sendCreateRequest(View view){
		if(submitSignInForm()){
			// Calling async task to get json
	        new SendNewUserAsyncTask().execute();
		}else
			Snackbar.make(main_layout, "Please give valid name, email and password!", Snackbar.LENGTH_LONG).show();
	}
	
	private class SendNewUserAsyncTask extends AsyncTask<Void, Void, String>{

		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SignupActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
		
		@Override
		protected String doInBackground(Void... params) {
			String result = null;
			// Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            String url = GlobalObject.webserviceUrl + "signup";
            
            //url parameters
            List<NameValuePair> list=new ArrayList<NameValuePair>();  
            list.add(new BasicNameValuePair("name", txt_name.getText().toString()));
            list.add(new BasicNameValuePair("email", txt_email.getText().toString()));  
	        list.add(new BasicNameValuePair("password",txt_password.getText().toString()));  
	        list.add(new BasicNameValuePair("device","android"));
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, list);
 
            Log.d("Response: ", "> " + jsonStr);
            
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    
                    String status = jsonObj.getString("status");
                    String message = jsonObj.getString("message");
                    
                    if(0 == Integer.parseInt(status))
                    {
                    	return message;
                    }
                    if(message.equals("success"))
                    {
                    	JSONObject data = jsonObj.getJSONObject("data");
                    	String userid = data.getString("user_id");
                    	String email = data.getString("email");
                    	String name = data.getString("name");
                    	Log.i("INFO", userid+" - "+email+" - "+name);
                    	result = "User has been created successfully!";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            
			return result;
		}
		
		@Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            //Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_LONG).show();
            Snackbar.make(main_layout, ""+result, Snackbar.LENGTH_LONG).show();
        }
	}
}