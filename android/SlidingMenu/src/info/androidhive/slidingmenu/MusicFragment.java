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

public class MusicFragment extends Fragment {

	ListView tagslistview;
	ArrayList<String> listChansons = new ArrayList<String>();
	ArrayList<String> listArtistes = new ArrayList<String>();
	ArrayList<Integer> listId = new ArrayList<Integer>();

	public MusicFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_music, container, false);
		GetLibraryTask o = new GetLibraryTask();
		o.execute("http://192.168.43.143:50420");

		try {
//			String data = 
//					"{\"id\": 1,\"result\" : {\"audio\" : [	{\"title\" : \"title1\", \"id\" : 1},{\"title\" : \"title2\", \"id\" : 2}]}}";
//			JSONObject donnees = new JSONObject(data);
			JSONObject donnees = o.get();
			Log.d("taille","donnees : "+donnees.toString());
			extraireDonnees(donnees);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//String reponse = getDonnees(donnees);
		tagslistview = (ListView) rootView.findViewById(R.id.taglistview);
		ImageButton playBtn = (ImageButton) rootView.findViewById(R.id.play);
		ImageButton pauseBtn = (ImageButton) rootView.findViewById(R.id.pause);
		ImageButton stopBtn = (ImageButton) rootView.findViewById(R.id.stop);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// R.layout.tag_view_item, mapCommande);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.tag_view_item, listChansons);
		tagslistview.setAdapter(adapter);
		tagslistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				int idElt = listId.get(position);
				String artiste = listArtistes.get(position);
				new PlayTask().execute("http://192.168.43.143:50420",artiste,""+idElt);
				Log.d("Simulation Choice","id : "+idElt + ", art : "+artiste);
			}
		});
		return rootView;
	}

	public void extraireDonnees(JSONObject jsonResultat) {
		try {
			//JSONObject jsonResultat = new JSONObject(reponse);
			Log.d("taille", "jsonRes"+jsonResultat.toString());
			//JSONObject result = jsonResultat.getJSONObject("result");
			Log.d("taille", "jsonRes"+jsonResultat.toString());
			JSONArray audio = (JSONArray) jsonResultat.getJSONArray("audio");
			for (int i = 0; i < audio.length(); i++) {
				JSONObject chanson = audio.getJSONObject(i);
				String titre = chanson.getString("title");
				String artist = chanson.getString("artist");
				int id = chanson.getInt("id");
				//Log.d("Simulation Choice","id at insertion : " +id);
				listChansons.add(titre);
				listArtistes.add(artist);
				listId.add(id);
				Log.d("taille",""+listChansons.size());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	//	public String getDonnees(String response) {
	//		JSONRPC2Response respIn = null;
	//		try {
	//			respIn = JSONRPC2Response.parse(response);
	//		} catch (JSONRPC2ParseException e) {
	//			e.printStackTrace();
	//		}
	//		Log.d("taille", "resp : "+response);
	//		return respIn.toString();
	//	}

	//	public class HttpGetTask extends AsyncTask<String, Void, JSONRPC2Response> {
	//
	//		@Override
	//		protected JSONRPC2Response doInBackground(String... urls) {
	//			URL serverURL = null;
	//			String url = urls[0];
	//			try {
	//				serverURL = new URL(url);
	//			} catch (MalformedURLException e) {
	//				Log.e("erreur", e.getMessage());
	//			}
	//			JSONRPC2Session mySession = new JSONRPC2Session(serverURL);
	//			String method = "getServerTime";
	//			int requestID = 0;
	//			JSONRPC2Request request = new JSONRPC2Request(method, requestID);
	//			JSONRPC2Response response = null;
	//			try {
	//				// response = mySession.send(request);
	//				mySession.send(request);
	//			} catch (JSONRPC2SessionException e) {
	//				Log.e("erreur", e.getMessage());
	//			}
	//			// if (response.indicatesSuccess())
	//			// Log.d("response", response.getResult().toString());
	//			// else
	//			// Log.d("response", response.getError().getMessage());
	//			return response;
	//		}
	//
	//	}
}
