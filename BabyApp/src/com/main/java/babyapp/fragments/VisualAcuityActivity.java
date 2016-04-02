package com.main.java.babyapp.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.LineGraphView;
import com.handstudio.android.hzgrapherlib.vo.linegraph.LineGraph;
import com.handstudio.android.hzgrapherlib.vo.linegraph.LineGraphVO;
import com.main.java.babyapp.R;
import com.main.java.babyapp.adapters.ExpandableListAdapter;

public class VisualAcuityActivity extends AppCompatActivity{

	private ViewGroup layoutGraphView;
	private ExpandableListView expandableListView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
		
		layoutGraphView = (ViewGroup) findViewById(R.id.layoutGraphView);
		setLineGraph();
		
		expandableListView = (ExpandableListView) findViewById(R.id.visual_check_expandable_listview);
		// Setting group indicator null for custom indicator
		expandableListView.setGroupIndicator(null);
		
		setItems();
	}
	
	private void setLineGraph() {
		//all setting
		LineGraphVO vo = makeLineGraphAllSetting();
		
		//default setting
//		LineGraphVO vo = makeLineGraphDefaultSetting();
		
		layoutGraphView.addView(new LineGraphView(VisualAcuityActivity.this, vo));
	}
	
	/**
	 * make simple line graph
	 * @return
	 */
	/*private LineGraphVO makeLineGraphDefaultSetting() {
		
		String[] legendArr 	= {"1","2","3","4","5"};
		float[] graph1 		= {500,100,300,200,100};
		float[] graph2 		= {000,100,200,100,200};
		//float[] graph3 		= {200,500,300,400,000};
		
		List<LineGraph> arrGraph 		= new ArrayList<LineGraph>();
		arrGraph.add(new LineGraph("Left eye", 0xaa66ff33, graph1));
		arrGraph.add(new LineGraph("Right eye", 0xaa00ffff, graph2));
		//arrGraph.add(new LineGraph("tizen", 0xaaff0066, graph3));
		
		LineGraphVO vo = new LineGraphVO(legendArr, arrGraph);
		return vo;
	}*/

	/**
	 * make line graph using options
	 * @return
	 */
	private LineGraphVO makeLineGraphAllSetting() {
		//BASIC LAYOUT SETTING
		//padding
		int paddingBottom 	= 20; //LineGraphVO.DEFAULT_PADDING;
		int paddingTop 		= LineGraphVO.DEFAULT_PADDING;
		int paddingLeft 	= LineGraphVO.DEFAULT_PADDING;
		int paddingRight 	= LineGraphVO.DEFAULT_PADDING;

		//graph margin
		int marginTop 		= LineGraphVO.DEFAULT_MARGIN_TOP;
		int marginRight 	= LineGraphVO.DEFAULT_MARGIN_RIGHT;

		//max value
		int maxValue 		= LineGraphVO.DEFAULT_MAX_VALUE;

		//increment
		int increment 		= LineGraphVO.DEFAULT_INCREMENT;
		
		//GRAPH SETTING
		String[] legendArr 	= {"1","2","3","4","5","6","7","8","9","10","11", "12"};
		float[] graph1 		= {-1,(float)-1.8,(float)-1.8,(float)-2.7,(float)-3.5, (float)-4.5, (float)-5, (float)-5.3, (float)-5.8, (float)-5.8, -7, (float)-7.8};
		float[] graph2 		= {-1,(float)-1.3,(float)-1.3,-2,-3, (float)-3.8, (float)-3.8, (float)-4.5, (float)-4.5, -5, -6, (float)-6.2};
		//float[] graph3 		= {200,500,300,400,000};
		
		List<LineGraph> arrGraph = new ArrayList<LineGraph>();
		
		//arrGraph.add(new LineGraph("Left eye", 0x5566ff33, 0xFF43C618, graph1, R.drawable.checkbox_icon_16px));
		arrGraph.add(new LineGraph("Left eye", 0x5566ff33, 0xFF43C618, graph1));
		arrGraph.add(new LineGraph("Right eye", 0x5500ffff, 0xFF08C6C6, graph2));
		//arrGraph.add(new LineGraph("tizen", 0xaaff0066, graph3));
		
		LineGraphVO vo = new LineGraphVO(
				paddingBottom, paddingTop, paddingLeft, paddingRight,
				marginTop, marginRight, maxValue, increment, legendArr, arrGraph);
		
		//set animation
		vo.setAnimation(new GraphAnimation(GraphAnimation.LINEAR_ANIMATION, GraphAnimation.DEFAULT_DURATION));
		//set graph name box
		//vo.setGraphNameBox(new GraphNameBox());
		//set draw graph region
		vo.setDrawRegion(true);
		
		//use icon
//		arrGraph.add(new Graph(0xaa66ff33, graph1, R.drawable.icon1));
//		arrGraph.add(new Graph(0xaa00ffff, graph2, R.drawable.icon2));
//		arrGraph.add(new Graph(0xaaff0066, graph3, R.drawable.icon3));
		
//		LineGraphVO vo = new LineGraphVO(
//				paddingBottom, paddingTop, paddingLeft, paddingRight,
//				marginTop, marginRight, maxValue, increment, legendArr, arrGraph, R.drawable.bg);
		return vo;
	}
	
	// Setting headers and childs to expandable listview
	void setItems(){
		
		// Array list for header
		ArrayList<String> header = new ArrayList<String>();
		
		// Array list for child items
		List<String> child1 = new ArrayList<String>();
		List<String> child2 = new ArrayList<String>();
		List<String> child3 = new ArrayList<String>();
		List<String> child4 = new ArrayList<String>();
		
		// Hash map for both header and child
		HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
		
		// Adding headers to list
		for (int i = 1; i < 6; i++) {
			header.add("11/02/2011");
		}
		// Adding child data
		for (int i = 1; i < 3; i++) {
			child1.add(" Child" + i);
		}
		// Adding child data
		for (int i = 1; i < 3; i++) {
			child2.add(" Child" + i);
		}
		// Adding child data
		for (int i = 1; i < 3; i++) {
			child3.add(" Child" + i);
		}
		// Adding child data
		for (int i = 1; i < 3; i++) {
			child4.add(" Child" + i);
		}
		
		// Adding header and childs to hash map
		hashMap.put(header.get(0), child1);
		hashMap.put(header.get(1), child2);
		hashMap.put(header.get(2), child3);
		hashMap.put(header.get(3), child4);
		
		ExpandableListAdapter adapter = new ExpandableListAdapter(VisualAcuityActivity.this, header, hashMap);
		// Setting adpater over expandablelistview
		expandableListView.setAdapter(adapter);
		setListViewHeightBasedOnChildren(expandableListView);
	}
	
	public static void setListViewHeightBasedOnChildren(ExpandableListView listView) {
		ListAdapter list_adapter = listView.getAdapter();
	    if (list_adapter == null)
	        return;

	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < list_adapter.getCount(); i++) {
	        view = list_adapter.getView(i, view, listView);
	        if (i == 0)
	            view.setLayoutParams(new LinearLayout.LayoutParams(desiredWidth, LinearLayout.LayoutParams.WRAP_CONTENT));

	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	        totalHeight += view.getMeasuredHeight();
	    }
	    //ViewGroup.LayoutParams params = listView.getLayoutParams();
	    //params.height = totalHeight + (listView.getDividerHeight() * (listview_adapter.getCount() - 1));
	    listView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, totalHeight + (listView.getDividerHeight() * (list_adapter.getCount() - 1))));
	    listView.requestLayout();
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
