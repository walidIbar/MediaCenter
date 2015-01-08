package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ConnectFragment extends Fragment { 	
	View rootView;
	String scanList;

	public ConnectFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_connect, container, false);
		
		Button boton = (Button) rootView.findViewById(R.id.connect);
		boton.setOnClickListener(new OnClickListener(){
			//final CharSequence[] items = {"Music", "Photos", "Videos"};
			public void onClick(View v) {
				((MainActivity) getActivity()).connected = true;
				MainActivity.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
				MainActivity.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
				Log.d("mywifi","StartCheckWifi");
				Log.d("mywifi","EndCheckWifi");
				//Toast.makeText(getActivity(),scanList,Toast.LENGTH_SHORT).show();

				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, new MusicFragment()).commit();
				//AlertDialog.Builder pd = new AlertDialog.Builder(getActivity());
				//pd.setTitle("Media : ");
				//pd.setCancelable(false);
				/*pd.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						if (item == 0){
							FragmentManager fragmentManager = getFragmentManager();
							fragmentManager.beginTransaction().replace(R.id.frame_container, new MusicFragment()).commit();
						}
						if(item == 1){
							FragmentManager fragmentManager = getFragmentManager();
							fragmentManager.beginTransaction().replace(R.id.frame_container, new PhotosFragment()).commit();
						}
						if(item == 2){
							FragmentManager fragmentManager = getFragmentManager();
							fragmentManager.beginTransaction().replace(R.id.frame_container, new VideosFragment()).commit();
						}
					}
				});*/
				//AlertDialog alertDialog = pd.create();
				//alertDialog.show();

			}
		});
		return rootView;
	}
}
