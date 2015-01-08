package info.androidhive.slidingmenu;

import java.net.MalformedURLException;
import java.net.URL;

import org.alexd.jsonrpc.JSONRPCClient;
import org.alexd.jsonrpc.JSONRPCException;
import org.alexd.jsonrpc.JSONRPCParams;

import android.os.AsyncTask;
import android.util.Log;

public class PlayPauseTask extends AsyncTask<String, Void, Void> {

	@Override
	protected Void doInBackground(String... urls) {
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
			Object o = client.call("playpause");
			Log.d("response PLAYPAUSE", "O : " + o);
		} catch (JSONRPCException e) {
			Log.e("response PLAYPAUSE", e.getMessage());
		}

		return null;
	}

}