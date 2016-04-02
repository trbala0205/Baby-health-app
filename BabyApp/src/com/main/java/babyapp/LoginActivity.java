package com.main.java.babyapp;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.main.java.babyapp.globalObject.GlobalObject;
import com.main.java.babyapp.services.ServiceHandler;

@SuppressLint("CommitPrefEdits")
public class LoginActivity extends AppCompatActivity {

	private ProgressDialog pDialog;
	private ScrollView main_layout;
	private EditText txt_email, txt_password;
	private SharedPreferences sharedpreferences;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/HelveticaNeueCyr-Light.otf");
        
        ImageView img_logo = (ImageView)findViewById(R.id.img_logo);
        Animation popup1 = AnimationUtils.loadAnimation(this, R.anim.view_popup);
        popup1.setStartOffset(50);
        img_logo.startAnimation(popup1);
        
        main_layout = (ScrollView)findViewById(R.id.main_layout);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_password = (EditText)findViewById(R.id.txt_password);
        txt_email.addTextChangedListener(new MyTextWatcher(txt_email));
        txt_password.addTextChangedListener(new MyTextWatcher(txt_password));
        
        Button btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*if(submitSignInForm())
					new SendNewUserAsyncTask().execute();
				else
					Snackbar.make(main_layout, "Please give valid email and password!", Snackbar.LENGTH_LONG).show();*/
				
				startActivity(new Intent(LoginActivity.this, BabyMainActivity.class));
				LoginActivity.this.finish();
			}
		});
	}
	
	public void gotoSignupPage(View view){
		
		startActivity(new Intent(LoginActivity.this, SignupActivity.class));
	}
	
	public void openForgotPasswordDialog(View view){
		// get forgot_password_prompt.xml view
		LayoutInflater layoutInflater = LayoutInflater.from(LoginActivity.this);
		View promptView = layoutInflater.inflate(R.layout.forgot_password_prompt, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);

		// set forgot_password_prompt.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);
		
		// setup a dialog window
		alertDialogBuilder
				.setCancelable(true);
				/*.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// get user input and set it to result
								//editTextMainScreen.setText(input.getText());
							}
						})
				.setNegativeButton("CANCEL",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int id) {
								dialog.cancel();
							}
						});*/

		// create an alert dialog
		final AlertDialog alertD = alertDialogBuilder.create();
		alertD.setCanceledOnTouchOutside(true);
		alertD.show();
		
		final EditText input_email = (EditText) promptView.findViewById(R.id.edit_email);
		Button btn_save = (Button) promptView.findViewById(R.id.btn_save);
		Button btn_cancel = (Button) promptView.findViewById(R.id.btn_cancel);
		btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				alertD.dismiss();
			}
		});
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				alertD.dismiss();
			}
		});
		
	}
	
	/**
     * Validating form
     */
    private boolean submitSignInForm() {
    	
        if (!validateEmail()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }

		return true;
    }
    
	private class MyTextWatcher implements TextWatcher {

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
                case R.id.txt_email:
                    validateEmail();
                    break;
                case R.id.txt_password:
                    validatePassword();
                    break;
			}
		}
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
	
	private class SendNewUserAsyncTask extends AsyncTask<Void, Void, String>{

		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
		
		@Override
		protected String doInBackground(Void... params) {
			String message = null;
			// Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            String url = GlobalObject.webserviceUrl + "login";
            
            //url parameters
            List<NameValuePair> list=new ArrayList<NameValuePair>();  
	        list.add(new BasicNameValuePair("email", txt_email.getText().toString()));  //"bala2@mail.com"
	        list.add(new BasicNameValuePair("password", txt_password.getText().toString())); //"bala2"  
	        //list.add(new BasicNameValuePair("device","android"));
	        
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, list);
 
            Log.d("Response: ", "> " + jsonStr);
            
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    
                    String status = jsonObj.getString("status");
                    message = jsonObj.getString("message");
                    
                    if(0 == Integer.parseInt(status))
                    {
                    	return message;
                    }
                    if(message.equals("success"))
                    {
                    	JSONObject data = jsonObj.getJSONObject("data");
                    	String user_id = data.getString("user_id");
                    	String email = data.getString("email");
                    	String name = data.getString("name");
                    	Log.i("INFO", user_id+" - "+email+" - "+name);
                    	
                    	sharedpreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                    	SharedPreferences.Editor editor = sharedpreferences.edit();
                    	editor.putString("user_id", user_id);
                        editor.putString("user_name", name);
                        editor.putString("email", email);
                        editor.commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            
			return message;
		}
		
		@Override
        protected void onPostExecute(String message) {
            super.onPostExecute(message);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            if("success".equals(message))
            {
            	startActivity(new Intent(LoginActivity.this, BabyMainActivity.class));
				LoginActivity.this.finish();
            }else{
	            //Toast.makeText(getApplicationContext(), ""+message, Toast.LENGTH_LONG).show();
	            Snackbar.make(main_layout, ""+message, Snackbar.LENGTH_LONG).show();
            }
        }
	}
}
