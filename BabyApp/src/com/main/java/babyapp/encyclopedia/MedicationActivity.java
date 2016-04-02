package com.main.java.babyapp.encyclopedia;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.main.java.babyapp.R;
import com.main.java.babyapp.adapters.ScreeningSummaryMenuAdapter;
import com.main.java.babyapp.encyclopedia.fragment.EncycImmunisationFragment;
import com.main.java.babyapp.encyclopedia.fragment.MedicationFragment;
import com.main.java.babyapp.globalObject.GlobalObject;

public class MedicationActivity extends AppCompatActivity {

	private ListView screening_listview;
	private ScreeningSummaryMenuAdapter adapter;
	
	private ViewPager viewPager;
	private MyViewPagerAdapter myViewPagerAdapter;
	private ArrayList<Integer> listOfItems;
	
	private LinearLayout dotsLayout;
	private int dotsCount;
	private TextView[] dots;
	private TextView toolbar_title;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
        initViews();
        setViewPagerItemsWithAdapter();
        setUiPageViewController();
        
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        
        screening_listview = (ListView)findViewById(R.id.screening_summary_listview);
        try{
        	adapter = new ScreeningSummaryMenuAdapter(this, GlobalObject.screeningList, GlobalObject.screeningHintList);
        	adapter.notifyDataSetChanged();
        	screening_listview.setAdapter(adapter);
        	screening_listview.invalidateViews();
        	screening_listview.refreshDrawableState();
        	
        	screening_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					System.out.println("CLICKED:"+position);
					//startActivity(new Intent(MedicationActivity.this, MyScreeningListItemActivity.class));
				}
			});
		}catch(NullPointerException npe){}
	}

	private void setUiPageViewController() {
		dotsLayout = (LinearLayout)findViewById(R.id.viewPagerCountDots);
		dotsCount = myViewPagerAdapter.getCount();
		System.out.println("dotsCount:"+dotsCount);
		dots = new TextView[dotsCount];
		
		for (int i = 0; i < dotsCount; i++) {
			dots[i] = new TextView(this);
			dots[i].setText(Html.fromHtml("&#8226;"));
			dots[i].setTextSize(30);
			dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
			dotsLayout.addView(dots[i]);			
		}
		
		dots[0].setTextColor(getResources().getColor(R.color.white));
	}

	private void setViewPagerItemsWithAdapter() {
		myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
		
		Bundle args = new Bundle();
		MedicationFragment medicationFrag = new MedicationFragment();
        //args.putInt(ProductListFragment.ARG_FRAG_TYPE, ProductListFragment.FRAG_TYPE_OS);
		medicationFrag.setArguments(args);

        Bundle args1 = new Bundle();
        EncycImmunisationFragment immunisationFrag = new EncycImmunisationFragment();
        //args1.putInt(ProductListFragment.ARG_FRAG_TYPE, ProductListFragment.FRAG_TYPE_DEVICE);
        immunisationFrag.setArguments(args1);
        
        myViewPagerAdapter.addFragment(medicationFrag, "Medication");
        myViewPagerAdapter.addFragment(immunisationFrag, "Immunisation");
        
		viewPager.setAdapter(myViewPagerAdapter);
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(viewPagerPageChangeListener);
	}
	
//	page change listener
	OnPageChangeListener viewPagerPageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int position) {
			for (int i = 0; i < dotsCount; i++) {
				dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
			}
			dots[position].setTextColor(getResources().getColor(R.color.white));
			if(position == 0)
				toolbar_title.setText("Medication");
			else if(position == 1)
				toolbar_title.setText("Immunisation");
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	
	private void initViews() {
		
		//getActionBar().hide();
		
		viewPager = (ViewPager)findViewById(R.id.viewPager);
		
		listOfItems = new ArrayList<Integer>();
		listOfItems.add(1);
		listOfItems.add(2);
		listOfItems.add(3);
		listOfItems.add(4);
		listOfItems.add(5);
	}
	
//	adapter
	public class MyViewPagerAdapter extends FragmentPagerAdapter{
		
		private LayoutInflater layoutInflater;
		private ArrayList<Integer> items;
		
		private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
		
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        
		/*@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.view_pager_item, container,false);
			
			TextView tView = (TextView)view.findViewById(R.id.txt_more_info);
			
			tView.setText(listOfItems.get(position).toString());
			
			((ViewPager) container).addView(view);
			
			return view;
		}*/
		
		/*@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = (View)object;
	        ((ViewPager) container).removeView(view);
		}
*/
		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
		
		@Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}