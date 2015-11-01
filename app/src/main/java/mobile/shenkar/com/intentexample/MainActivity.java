package mobile.shenkar.com.intentexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
	
	private  final String TAG = "MainActivity";
	private EditText searchEt;
	private EditText startActivityEt;
	private final  String baseSearchUrl = "http://www.google.com/#q=%s";
	public final static String EXTRA_MESSAGE = "il.ac.shenkar.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchEt  = (EditText) findViewById(R.id.editTextSearch);
        startActivityEt = (EditText) findViewById(R.id.editTextStartActivity);
    }

    
    public void  searchClicked(View v) 
    {
    	Log.i(TAG, "searchClicked was called");
		String toSerach = String.format(baseSearchUrl,searchEt.getText());
		Uri uri = Uri.parse(toSerach);
		//Implicit intent 
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}
    
    public void  startAcivityClicked(View v) 
    {
    	Log.i(TAG, "startAcivityClicked was called");
		String dataToPass = startActivityEt.getText().toString();
		//Explicit intent.
		Intent  i = new Intent(this,SecondActivity.class);
		i.putExtra(EXTRA_MESSAGE, dataToPass);
		//Start the activity
		startActivity(i);
	} 
}
