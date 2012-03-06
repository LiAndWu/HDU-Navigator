package net.mindlee.android.mabbage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button ShortestPathGenerateButton ,AboutButton;
	private Spinner LivingQuartersSpinner, TeachingAreaSpinner;
	private ArrayAdapter<String> LivingQuartersAdapter, TeachingAreaAdapter;
	public static int StartPoint, EndPoint, Distance;
	public static boolean Open = false;
	
	String[] LivingQuartersList;
	String[] TeachingAreaList;
	public static Spfa G = new Spfa();
	
	public static Integer Offset = 100;
    public static Map<Integer, String> CampusNameHashMap = new HashMap<Integer, String>();
    public static Map<Map<Integer,Integer>, Integer> CampusDistanceHashMap= new HashMap<Map<Integer,Integer>, Integer>();
    public static Map<Integer, String> LivingNameHashMap = new HashMap<Integer, String>();
    public static Map<Map<Integer,Integer>, Integer> LivingDistanceHashMap= new HashMap<Map<Integer,Integer>, Integer>();
    
    
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		DisplayToast("一切都是为了学习！");

        HNData.Initiate(this);
        HNData.CheckXMLExistence();		
		
        List<Element> CampusDistance = HNData.GetDistanceOfArea("Campus");
        List<Element> CampusShownName = HNData.GetShownNameOfArea("Campus");
        
        List<Element> LivingDistance = HNData.GetDistanceOfArea("Living");
        List<Element> LivingShownName = HNData.GetShownNameOfArea("Living");
        
        // Store Index:Name of Campus
        for(Element e : CampusShownName){
        	CampusNameHashMap.put(Integer.valueOf(e.getChild("Index").getValue()), e.getChild("Name").getValue());
        }
        
        for(Element dis_node : CampusDistance){
        	if(!dis_node.getChild("Distance").getValue().equals("MAX")) {
        		Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        		mp.put(Integer.valueOf(dis_node.getChild("Start").getValue()), Integer.valueOf(dis_node.getChild("End").getValue()));
        		CampusDistanceHashMap.put(mp,
        				Integer.valueOf(dis_node.getChild("Distance").getValue()));
        	}
        }
        
        // Store Index:Name of Living Area
        for(Element e1 : LivingShownName) {
        	LivingNameHashMap.put(Integer.valueOf(e1.getChild("Index").getValue()), e1.getChild("Name").getValue());
        }
        
        for(Element liv_node : LivingDistance) {
        	if(!liv_node.getChild("Distance").getValue().equals("MAX"))
        	{
        		Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        		mp.put(Integer.valueOf(liv_node.getChild("Start").getValue()), Integer.valueOf(liv_node.getChild("End").getValue()));
        		LivingDistanceHashMap.put(mp,
        				Integer.valueOf(liv_node.getChild("Distance").getValue()));
        	}
        }
        
        int size = LivingNameHashMap.size();
        LivingQuartersList = new String[size];
        for(Integer key: LivingNameHashMap.keySet()){
        	LivingQuartersList[key] = LivingNameHashMap.get(key);
        }
        
        TeachingAreaList = new String[CampusNameHashMap.size()];
        for(Integer key : CampusNameHashMap.keySet()){
        	TeachingAreaList[key] = CampusNameHashMap.get(key);
        }
        
		LivingQuartersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LivingQuartersList);
		LivingQuartersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
		LivingQuartersSpinner = (Spinner) findViewById(R.id.living_quarters_spinner);
		LivingQuartersSpinner.setAdapter(LivingQuartersAdapter); 
		LivingQuartersSpinner.setPrompt("起始点");
		LivingQuartersSpinner.setSelection(8);
		
		LivingQuartersSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						
						MainActivity.EndPoint = (arg2==0 || arg2==1)?arg2:arg2+MainActivity.Offset;
						Log.d("Start" + MainActivity.StartPoint, "345" );
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		TeachingAreaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TeachingAreaList);
		TeachingAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		TeachingAreaSpinner = (Spinner) findViewById(R.id.teaching_area_spinner);
		TeachingAreaSpinner.setAdapter(TeachingAreaAdapter);
		TeachingAreaSpinner.setPrompt("目的地");
		TeachingAreaSpinner.setSelection(21);
		
		TeachingAreaSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						MainActivity.StartPoint = arg2;
					}
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		ShortestPathGenerateButton = (Button) findViewById(R.id.shortest_path_generate_button);
		ShortestPathGenerateButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				CreatGraph();//建图
				Intent intent = new Intent(MainActivity.this, ShortestPathOutputActivity.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		});
		
		AboutButton = (Button) findViewById(R.id.about_button);
		AboutButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		});
		
	}
	
	public void DisplayToast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
	public void CreatGraph() {
		G.init(200);
		
		for(Map<Integer, Integer> map : CampusDistanceHashMap.keySet()){
			for(Integer key : map.keySet()){
				Log.v("教学区key = " + key  + "   map.get(key) = " + map.get(key) , "距离" + CampusDistanceHashMap.get(map));
				G.insert(key, map.get(key), CampusDistanceHashMap.get(map));
				
			}
		}
		
		for(Map<Integer, Integer> map : LivingDistanceHashMap.keySet()){
			for(Integer key : map.keySet()){
				Log.v("生活区key = " + (key + 100)  + "   map.get(key) = " + (map.get(key) + 100) , "距离" + LivingDistanceHashMap.get(map));
					int _key, _map;
					_key = key;
					
					if( (_key==0)||(_key==1)) {
						_map = map.get(_key);
						if( (_map==0)||(_map==1)) 
							;
						else
							_map += Offset;
					}
					else
					{
						_map = map.get(_key);
						_key+=Offset;
						if( (_map==0)||(_map==1))
							;
						else
							_map += Offset;
					}
					G.insert(_key, _map, LivingDistanceHashMap.get(map));
				}
				
			}
		}
	}