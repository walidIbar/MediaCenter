package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class PlayerFragment extends Fragment {

	public PlayerFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_player, container, false);
		ImageButton playBtn = (ImageButton) rootView.findViewById(R.id.play);
		ImageButton pauseBtn = (ImageButton) rootView.findViewById(R.id.pause);
		ImageButton stopBtn = (ImageButton) rootView.findViewById(R.id.stop);
		
		return rootView;
	}

}