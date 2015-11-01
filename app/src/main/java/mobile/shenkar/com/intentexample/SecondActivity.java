package mobile.shenkar.com.intentexample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends Activity {

	private  final String TAG = "SecondActivity";
	private ImageView imageView;
	private Bitmap bitmap;

	private static final int REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG,"On create was started.");
		setContentView(R.layout.second_activity);
		imageView = (ImageView) findViewById(R.id.imageView1);
		Intent intent = getIntent();
		String data  = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		TextView tv =  (TextView) findViewById(R.id.labelTextView);
		if(tv!=null)
		{
			tv.setText(data);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		InputStream stream = null;
		if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
			try {
				// recycle unused bitmaps
				if (bitmap != null) {
					bitmap.recycle();
				}
				//read the image from the database using the URI and the content resolver.
				stream = getContentResolver().openInputStream(data.getData());
				bitmap = BitmapFactory.decodeStream(stream);

				//set the image.
				imageView.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.e(TAG,e.getMessage());
			} finally {
				if (stream != null)
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
						
					}
			}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void selectImageClicked(View v) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				REQUEST_CODE);
	}

	public void doneClicked(View v) {
		finish();
	}

}
