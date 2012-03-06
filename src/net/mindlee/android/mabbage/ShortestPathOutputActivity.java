package net.mindlee.android.mabbage;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class ShortestPathOutputActivity extends Activity {
	private Button BackButton;
	private ListView ShortestPathOutputListView;

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shortest_path_output);

		ArrayList<String[]> name = new ArrayList<String[]>();
		name = addValue();
		
		ShortestPathOutputListView = (ListView) findViewById(R.id.shortest_path_output_listView);
		final MyListAdapter mla = new MyListAdapter(
				ShortestPathOutputActivity.this, name.size(),
				name.toArray(new String[][] {}));
		ShortestPathOutputListView.setAdapter(mla);

		BackButton = (Button) findViewById(R.id.back_button);
		BackButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ShortestPathOutputActivity.this,
						MainActivity.class);
				startActivity(intent);
				ShortestPathOutputActivity.this.finish();
			}
		});
	}

	@SuppressWarnings("static-access")
	public ArrayList<String[]> addValue() {
		MainActivity.Distance = 0;
		
		int ShortestPathRoad = MainActivity.G.spfa(MainActivity.StartPoint, MainActivity.EndPoint);
		
		ArrayList<String[]> value = new ArrayList<String[]>();
		
		int des = MainActivity.EndPoint;
		int src = MainActivity.G.pre[des];
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		if (dm.heightPixels > 600) {
		value.add(new String[] { des < MainActivity.Offset ? MainActivity.CampusNameHashMap
				.get(des) : MainActivity.LivingNameHashMap.get(des - MainActivity.Offset) , "" + MainActivity.Distance  + "\t\t\t米"});  
		} else {
			value.add(new String[] { des < MainActivity.Offset ? MainActivity.CampusNameHashMap
					.get(des) : MainActivity.LivingNameHashMap.get(des - MainActivity.Offset) , "" + MainActivity.Distance  + "\t\t米"});  
		}
		while (MainActivity.Distance < ShortestPathRoad) {
			MainActivity.Distance += (MainActivity.G.dis[des] - MainActivity.G.dis[src]);
			String Suffix = MainActivity.Distance < 1000 ? "\t米" : "米";
			value.add(new String[] { src < MainActivity.Offset ? MainActivity.CampusNameHashMap
					.get(src) : MainActivity.LivingNameHashMap.get(src - MainActivity.Offset) , "" + MainActivity.Distance + Suffix});
			src = MainActivity.G.pre[src];
			des = MainActivity.G.pre[des];
		}
		
		return value;
	}
}
