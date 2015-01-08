package info.androidhive.slidingmenu;

import java.net.MalformedURLException;
import java.net.URL;

import org.alexd.jsonrpc.JSONRPCClient;
import org.alexd.jsonrpc.JSONRPCException;
import org.alexd.jsonrpc.JSONRPCParams;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class GetLibraryTask extends AsyncTask<String, Void, JSONObject> {

	@Override
	protected JSONObject doInBackground(String... urls) {
		Log.d("response PLAYPAUSE", "Entering");
		URL serverURL = null;
		String url = urls[0];
		try {
			serverURL = new URL(url);
		} catch (MalformedURLException e) {
			Log.e("erreur", e.getMessage());
		}

		JSONRPCClient client = JSONRPCClient.create(url,
				JSONRPCParams.Versions.VERSION_2);
		client.setConnectionTimeout(2000);
		client.setSoTimeout(2000);
		try {
			JSONObject o = client.callJSONObject("getaudio");
			Log.d("response PLAYPAUSE", "O : " + o);
			return o;
		} catch (JSONRPCException e) {
			Log.e("response PLAYPAUSE", e.getMessage());
		}

		return null;
	}

}