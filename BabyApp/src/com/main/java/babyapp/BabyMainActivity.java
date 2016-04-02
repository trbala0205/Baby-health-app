package com.main.java.babyapp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.RequestParams;
import com.main.java.babyapp.addbio.AddBioMainActivity;
import com.main.java.babyapp.encyclopedia.MedicationActivity;
import com.main.java.babyapp.fragments.BabyBookletFragment;
import com.main.java.babyapp.fragments.VisualAcuityActivity;
import com.main.java.babyapp.globalObject.GlobalObject;
import com.main.java.babyapp.immunisations.ImmunisationCalendarActivity;
import com.main.java.babyapp.immunisations.NewImmunisationActivity;
import com.main.java.babyapp.model.ChildDetailsDAO;
import com.main.java.babyapp.services.ServiceHandler;

import de.hdodenhof.circleimageview.CircleImageView;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BabyMainActivity extends AppCompatActivity {

	private final String BABY_BOOKLET_FRAGMENT_TAG = "Baby Booklet";
	private final String NEW_IMMUNISATION_FRAGMENT_TAG = "New Immunisation";
	private final String NEW_SCREENING_FRAGMENT_TAG = "New Screening";
	private final String My_IMMUNISATION_FRAGMENT_TAG = "My Immunisation";
	private final String My_SCREENINGS_FRAGMENT_TAG = "My Screenings";
	private final String My_GROWTH_PERCENTILES_FRAGMENT_TAG = "My Growth Percentiles";
	private final String HEALTH_BOOOK_FRAGMENT_TAG = "Health Book";
	private final String ENCYCLOPEDIA_FRAGMENT_TAG = "Encyclopedia";
	
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View headerLayout;
    private FragmentManager fragmentManager;
    private Spinner spinner_user_grp;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private TextView toolbar_title;
    private CircleImageView child_imageView;
    private Spinner childnames;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation_drawer_layout);
		
		TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/HelveticaNeueCyr-Light.otf");
		
		toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     // You were missing this setHomeAsUpIndicator
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText("Baby Booklet");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(BabyMainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
			
			@Override
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				supportInvalidateOptionsMenu();
			}
			
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				supportInvalidateOptionsMenu();
			}
			
			@Override
			public void onDrawerStateChanged(int state) {
			}
		};
		drawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();
        
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        
        fragmentManager = this.getSupportFragmentManager();
    	fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    	
        if(savedInstanceState == null){
	        fragment = new BabyBookletFragment();
	    	fragmentManager.beginTransaction().replace(R.id.flContent, fragment, BABY_BOOKLET_FRAGMENT_TAG).commit();
        }
    	
    	headerLayout = navigationView.getHeaderView(0); // 0-index header
    	
    	child_imageView = (CircleImageView)headerLayout.findViewById(R.id.circleImgView_child);
    	childnames = (Spinner)headerLayout.findViewById(R.id.spinner_childname);
    	childnames.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				System.out.println("POSITION: "+position);
				new DownloadImageTask().execute(new String[]{""+position});
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
			
		});
    	//LayoutInflater.from(getContext()).inflate(R.layout.nav_header, navigationView);
    	
		/*spinner_user_grp = (Spinner)headerLayout.findViewById(R.id.spinner_user_grp);
		List<String> names = Arrays.asList(getResources().getStringArray(R.array.query_usernames));
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, names);
    	spinner_user_grp.setAdapter(mAdapter);
    	spinner_user_grp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				System.out.println("POSITION: "+position);
				fragment = new BabyBookletFragment();
				drawerLayout.closeDrawers();
				toolbar_title.setText("Baby Booklet");
		    	fragmentManager.beginTransaction().replace(R.id.flContent, fragment, BABY_BOOKLET_FRAGMENT_TAG).commit();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});*/
    	
    	new GetChildrenInfoAsyncTask().execute();
    	
	}

	/*@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}*/
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	   
	    protected Bitmap doInBackground(String... params) {
	        int position = Integer.parseInt(params[0]);
	        Bitmap bitmap = null;
	        try {
        		System.out.println("position-------> "+position);
                InputStream in = new java.net.URL(GlobalObject.childDetailsList.get(position).getBaby_image()).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
	        return bitmap;
	    }

	    protected void onPostExecute(Bitmap result) {
	    	child_imageView.setImageBitmap(result);
	    }
	}
	
	private class GetChildrenInfoAsyncTask extends AsyncTask<Void, Void, String>{

		private ProgressDialog pDialog;
		List<String> spinnerArray =  new ArrayList<String>();
		private Bitmap bitmap = null;
		
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(BabyMainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
		
		@Override
		protected String doInBackground(Void... params) {
			String result = null;
			// Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            String url = GlobalObject.webserviceUrl + "children_details";
            
          //url parameters
            List<NameValuePair> list=new ArrayList<NameValuePair>();  
            list.add(new BasicNameValuePair("user_id", "64"));  
	        //list.add(new BasicNameValuePair("device","android"));
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, list);
 
            Log.d("Response: ", ">>>> " + jsonStr);
            JSONObject jsonObj;
			try {
				jsonObj = new JSONObject(jsonStr);
                
                String status = jsonObj.getString("status");
                String message = jsonObj.getString("message");
                
                if(message.equals("success"))
                {
                	result = message;
                	JSONObject data = jsonObj.getJSONObject("data");
                	JSONArray childernArray = data.getJSONArray("children");
                	for(int i=0; i<childernArray.length();i++)
                	{
                		JSONObject childDetails = (JSONObject) childernArray.get(i);
                		
                		ChildDetailsDAO cd = new ChildDetailsDAO();
                		cd.setChild_id(Integer.parseInt(childDetails.getString("child_id")));
                		cd.setBaby_image(childDetails.getString("baby_image"));
                		cd.setBaby_name(childDetails.getString("name"));
                		cd.setBaby_dob(childDetails.getString("dob"));
                		GlobalObject.childDetailsList.add(cd);
                		
                		spinnerArray.add(childDetails.getString("name"));
                	}
                	
                	try {
                		System.out.println("-------> "+GlobalObject.childDetailsList.get(0).getBaby_image());
                        InputStream in = new java.net.URL(GlobalObject.childDetailsList.get(0).getBaby_image()).openStream();
                        bitmap = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                	
                }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
			return result;
		}
		
		@Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            
            if (result.equals("success")) {
            	
            	ArrayAdapter<String> adapter = new ArrayAdapter<String>(BabyMainActivity.this, android.R.layout.simple_spinner_item, spinnerArray);
            	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            	childnames.setAdapter(adapter);
            	System.out.println("SPINNER CHILD COUNT: "+childnames.getCount());
                 
            	if(bitmap != null){
            		child_imageView.setImageBitmap(bitmap);
            	}
            		
            } else {
                Log.e("Childen info", "Couldn't get any data from the server");
            }
            
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_LONG).show();
            //Snackbar.make(main_layout, ""+result, Snackbar.LENGTH_LONG).show();
            
        }
	}
	
	private Fragment fragment = null;
    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
            	
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                case R.id.nav_home:
                	fragment = new BabyBookletFragment();
                	toolbar_title.setText("Baby Booklet");
                	// set the toolbar title
                	//toolbar.setTitle("New Immunisation");
                    //viewPager.setCurrentItem(0);
                    drawerLayout.closeDrawers();
                    break;
                    case R.id.nav_new_immunisation:
                    	startActivity(new Intent(BabyMainActivity.this, NewImmunisationActivity.class));
                    	//toolbar_title.setText("New Immunisation");
                    	getSupportActionBar().setTitle("New Immunisation");
                    	// set the toolbar title
                    	//toolbar.setTitle("New Immunisation");
                        //viewPager.setCurrentItem(0);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_new_screening:
                    	// set the toolbar title
                        getSupportActionBar().setTitle("New Screening");
                        //viewPager.setCurrentItem(1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_my_immunisation:
                    	//fragment = new HealthFeedsFragment();
                    	startActivity(new Intent(BabyMainActivity.this, ImmunisationCalendarActivity.class));
                    	// set the toolbar title
                        getSupportActionBar().setTitle("My Immunisation");
                        //viewPager.setCurrentItem(2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_my_screenings:
                    	startActivity(new Intent(BabyMainActivity.this, ScreeningsActivity.class));
                    	// set the toolbar title
                        //getSupportActionBar().setTitle("My Screenings");
                    	//toolbar_title.setText("My Screenings");
                        //viewPager.setCurrentItem(3);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_my_growth_percentiles:
                    	//fragment = new EventsFragment();
                    	//fragment = new VisualAcuityFragment();
                    	startActivity(new Intent(BabyMainActivity.this, GrowthSummaryActivity.class));
                    	// set the toolbar title
                        //toolbar_title.setText("My Growth Percentiles");
                        //viewPager.setCurrentItem(3);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_health_book:
                    	startActivity(new Intent(BabyMainActivity.this, HealthBookletActivity.class));
                    	//fragment = new EventsFragment();
                    	// set the toolbar title
                        getSupportActionBar().setTitle("Health Book");
                        //viewPager.setCurrentItem(3);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_encyclopedia:
                    	startActivity(new Intent(BabyMainActivity.this, MedicationActivity.class));
                    	//fragment = new EventsFragment();
                    	// set the toolbar title
                        getSupportActionBar().setTitle("Encyclopedia");
                        //viewPager.setCurrentItem(3);
                        drawerLayout.closeDrawers();
                        break;
                    
                    default:
                        return true;
                }
                
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flContent, fragment, null);  //third parameter null or TAGs we can keep Example: NEW_IMMUNISATION_FRAGMENT_TAG
                    fragmentTransaction.commit();
                    // set the toolbar title
                    //getSupportActionBar().setTitle("Map view");
                }
                return true;
            }
        });
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save custom values into the bundle
        savedInstanceState.putString("FRAGMENT_TAG", toolbar_title.getText().toString());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        toolbar_title.setText(savedInstanceState.getString("FRAGMENT_TAG"));
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toolbar_actions, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
		//MenuItem action_setting = menu.findItem(R.id.action_settings);
		//action_setting.setVisible(true);
		return super.onPrepareOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		System.out.println("INDICATOR: "+mDrawerToggle.isDrawerIndicatorEnabled());
		if (mDrawerToggle.isDrawerIndicatorEnabled() && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		switch (item.getItemId()) {
        case android.R.id.home:
        	if(drawerLayout.isDrawerOpen(Gravity.LEFT)) {
        		drawerLayout.closeDrawer(Gravity.LEFT);
            }else{
            	drawerLayout.openDrawer(Gravity.START);
            }      
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        case R.id.action_settings:
    		return true;
    }
		return super.onOptionsItemSelected(item);
	}
	
	/*@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	}*/
	
	@Override
    public void onBackPressed() {
		if (drawerLayout.isDrawerOpen(Gravity.LEFT | Gravity.START)) {
			drawerLayout.closeDrawers();
            return;
        }
        if (drawerLayout.isDrawerOpen(navigationView))
            drawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }
}
