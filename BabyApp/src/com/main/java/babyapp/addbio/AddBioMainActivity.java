package com.main.java.babyapp.addbio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.main.java.babyapp.R;
import com.main.java.babyapp.adapters.AddBioMenuAdapter;
import com.main.java.babyapp.globalObject.GlobalObject;
import com.main.java.babyapp.services.ServiceHandler;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddBioMainActivity extends AppCompatActivity {

	private ListView addbio_listview;
	private AddBioMenuAdapter adapter;
	private CircleImageView user_imageview;
	private TextView add_bio_dob;
	private EditText edit_babyname;
	private LinearLayout addbio_listview_layout;
	
	private Bitmap bitmapSelectedImage;
	private ProgressDialog pDialog;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bio_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
        edit_babyname = (EditText)findViewById(R.id.edit_babyname);
        add_bio_dob = (TextView)findViewById(R.id.add_bio_dob);
        //addbio_listview = (ListView)findViewById(R.id.addbio_listview);
        addbio_listview_layout = (LinearLayout)findViewById(R.id.addbio_listview_layout);
        try{
        	addbio_listview = new ListView(this);
        	addbio_listview.setDivider(new ColorDrawable(this.getResources().getColor(R.color.grey_400)));
        	addbio_listview.setDividerHeight(1);
        	addbio_listview.setFooterDividersEnabled(true);
        	
        	adapter = new AddBioMenuAdapter(this, GlobalObject.addBioList);
        	adapter.notifyDataSetChanged();
        	addbio_listview.setAdapter(adapter);
            
        	addbio_listview.invalidateViews();
        	addbio_listview.refreshDrawableState();
        	setListViewHeightBasedOnChildren(addbio_listview);
        	addbio_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					if(position == 0)
						startActivity(new Intent(AddBioMainActivity.this, BirthRecordActivity.class));
					else if(position == 1)
						startActivity(new Intent(AddBioMainActivity.this, ParticularsOfParentsActivity.class));
					else if(position == 3)
						startActivity(new Intent(AddBioMainActivity.this, NewbornScreeningActivity.class));
					else if(position == 4)
						startActivity(new Intent(AddBioMainActivity.this, InverstigationsActivity.class));
					else if(position == 5)
						startActivity(new Intent(AddBioMainActivity.this, DischargeInformationActivity.class));
				}
			});
        	addbio_listview_layout.addView(addbio_listview);
		}catch(NullPointerException npe){}
        
        user_imageview = (CircleImageView)findViewById(R.id.user_circleView);
        user_imageview.setBorderWidth(25);
        user_imageview.setBorderColor(this.getResources().getColor(R.color.colorPrimaryDarkAlpha));
        user_imageview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		        final int ACTIVITY_SELECT_IMAGE = 1234;
		        startActivityForResult(i, ACTIVITY_SELECT_IMAGE); 
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
	    super.onActivityResult(requestCode, resultCode, data); 

	    switch(requestCode) { 
	    case 1234:
	    	System.out.println("RESULT CODE:"+resultCode+"  "+RESULT_OK);
	        if(resultCode == RESULT_OK){  
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = {MediaStore.Images.Media.DATA};
	            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String filePath = cursor.getString(columnIndex);
	            cursor.close();
	            System.out.println("filePath--:"+new File(filePath).getAbsolutePath());
	            /*BitmapFactory.Options options = new BitmapFactory.Options ();
                options.inSampleSize = 4;
	            bitmapSelectedImage = BitmapFactory.decodeFile(filePath, options);
	            if(null == bitmapSelectedImage)
	            	System.out.println("BITMAP NULL");*/
	            /* Now you have choosen image in Bitmap format in object "yourSelectedImage". You can use it in way you want! */
	            user_imageview.setImageURI(selectedImage);
	            user_imageview.buildDrawingCache();
	            bitmapSelectedImage = user_imageview.getDrawingCache();
	        }
	    }

	};
	
	private int year;
	private int month;
	private int day;
	public void setDate(View view){
		//showDialog(DATE_DIALOG_ID);
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
	    month = calendar.get(Calendar.MONTH);
	    day = calendar.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog dpd = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
		    	year = selectedYear;
				month = selectedMonth;
				day = selectedDay;

				// set selected date into textview
				add_bio_dob.setText(new StringBuilder().append(day)
				   .append("/").append(month + 1).append("/").append(year)
				   .append(" "));
				Toast.makeText(getApplicationContext(), ""+add_bio_dob.getText().toString(), Toast.LENGTH_SHORT).show();
		    }
		 }, year, month, day);

		 dpd.show();
    }
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter list_adapter = listView.getAdapter();
	    if (list_adapter == null)
	        return;

	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < list_adapter.getCount(); i++) {
	        view = list_adapter.getView(i, view, listView);
	        if (i == 0)
	            view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	        totalHeight += view.getMeasuredHeight();
	    }
	    //ViewGroup.LayoutParams params = listView.getLayoutParams();
	    //params.height = totalHeight + (listView.getDividerHeight() * (listview_adapter.getCount() - 1));
	    listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, totalHeight + (listView.getDividerHeight() * (list_adapter.getCount() - 1))));
	    listView.requestLayout();
	}
	
	private class SendBabyBioAsyncTask extends AsyncTask<Void, Void, String>{

		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(AddBioMainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
		
		@Override
		protected String doInBackground(Void... params) {
			String result = null;
			// Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            String url = GlobalObject.webserviceUrl + "add_bio";
            
            ByteArrayOutputStream bitmapByteArray = new ByteArrayOutputStream();
            bitmapSelectedImage.compress(Bitmap.CompressFormat.JPEG, 100, bitmapByteArray);
            byte[] imageBytes = bitmapByteArray.toByteArray();
            
            RequestParams entity = new RequestParams();
            entity.put("user_id", 64); //app_user_id
            entity.put("name", edit_babyname.getText().toString());
            entity.put("dob", add_bio_dob.getText().toString());
            entity.put("baby_image", new ByteArrayInputStream(imageBytes), "image.jpg");
            //entity.put("device", "android");
            
            String jsonStr = sh.uploadDataToServer(entity);
            result = "Child ID has been created successfully!";
            /*
            //ByteArrayEntity bab = new ByteArrayEntity(imageBytes);
            String imageEncodedStr = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            
            //url parameters
            List<NameValuePair> list=new ArrayList<NameValuePair>();  
            list.add(new BasicNameValuePair("name", edit_babyname.getText().toString()));
            list.add(new BasicNameValuePair("dob", add_bio_dob.getText().toString()));  
	        list.add(new BasicNameValuePair("baby_image", imageEncodedStr));  
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
            }*/
            
			return result;
		}
		
		@Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_LONG).show();
            //Snackbar.make(main_layout, ""+result, Snackbar.LENGTH_LONG).show();
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toolbar_actions, menu);
		
		MenuItem action_done = menu.findItem(R.id.action_done);
		action_done.setVisible(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		switch (item.getItemId()) {
        case android.R.id.home:
            return true;
        case R.id.action_done:
        	new SendBabyBioAsyncTask().execute();
    		return true;
    }
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
	
}