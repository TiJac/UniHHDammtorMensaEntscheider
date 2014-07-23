package de.tijamocobs.hamburgmensaentscheider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import chooser.Chooser;
import chooser.ChooserInterface.Mensa;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	Set<Mensa> chosen = new HashSet<Mensa>();
	
	private class MensaCheckBoxListener implements OnCheckedChangeListener{

		Mensa m = null;
		
		private MensaCheckBoxListener(){};
		
		public MensaCheckBoxListener(Mensa m){
			this.m = m;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (buttonView.isChecked()){
				chosen.add(m);
			} else {
				chosen.remove(m);
			}
		}
		
}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		
		CheckBox schwein = (CheckBox) findViewById(R.id.checkBoxSchwein);
		CheckBox phil = (CheckBox) findViewById(R.id.checkBoxPhil);
		CheckBox campus = (CheckBox) findViewById(R.id.checkBoxCampus);
		
		schwein.setOnCheckedChangeListener(new MensaCheckBoxListener( Mensa.SCHWEINE));
		phil.setOnCheckedChangeListener(new MensaCheckBoxListener(Mensa.PHILO));
		campus.setOnCheckedChangeListener(new MensaCheckBoxListener(Mensa.CAMPUS));
		
		chosen.addAll(Arrays.asList(Mensa.values()));
		
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (! chosen.isEmpty()){
				Mensa randomMensa = new Chooser().choose(chosen);
				switch (randomMensa) {
				case CAMPUS:
					((TextView) findViewById(R.id.textViewResult)).setText(getText(R.string.campus));
					break;
				case PHILO:
					((TextView) findViewById(R.id.textViewResult)).setText(getText(R.string.phil));
					break;
				case SCHWEINE:
					((TextView) findViewById(R.id.textViewResult)).setText(getText(R.string.schwein));
					break;
				default:
					break;}
				}else  {
					((TextView) findViewById(R.id.textViewResult)).setText(getText(R.string.funny));
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater
					.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

}
