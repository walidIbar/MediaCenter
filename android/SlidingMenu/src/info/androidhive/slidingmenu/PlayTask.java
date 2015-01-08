package info.androidhive.slidingmenu;

import org.alexd.jsonrpc.JSONRPCClient;
import org.alexd.jsonrpc.JSONRPCException;
import org.alexd.jsonrpc.JSONRPCParams;
import android.os.AsyncTask;
import android.util.Log;

public class PlayTask extends AsyncTask<String, Void, Boolean> {

	@Override
	protected Boolean doInBackground(String... urls) {
		Log.d("response PLAYPAUSE", "Entering");
		String url = urls[0];
		String artiste = urls[1];
		int id = Integer.parseInt(urls[2]);

		JSONRPCClient client = JSONRPCClient.create(url,
				JSONRPCParams.Versions.VERSION_2);
		client.setConnectionTimeout(2000);
		client.setSoTimeout(2000);
		try {
			boolean o = client.callBoolean("play",artiste,id);
			Log.d("response PLAYPAUSE", "O : " + o);
			return o;
		} catch (JSONRPCException e) {
			Log.e("response PLAYPAUSE", e.getMessage());
		}

		return false;
	}

}