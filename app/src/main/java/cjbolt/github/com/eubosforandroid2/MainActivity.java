package cjbolt.github.com.eubosforandroid2;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import eubos.main.EubosSocketWrapper;

public class MainActivity extends ActionBarActivity {

    private EubosSocketWrapper eubosWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void runTest(View v) {
        // Say hello!
        TextView text_feedback = (TextView) findViewById(R.id.feedback);
        text_feedback.setText("Test button deactivated in this build..");
    }

    public void startSocketComms(View v) {
        //mHandler.removeCallbacks(mUpdateUciMessagesLog);
        eubosWrapper = new EubosSocketWrapper();
        eubosWrapper.start();
        mHandler.postDelayed(mUpdateUciMessagesLog, 500);
    }

    private final Runnable mUpdateUciMessagesLog = new Runnable() {
        public void run() {
            EditText uciMessagesLog = (EditText) findViewById(R.id.displayUciMessages);
            uciMessagesLog.append(eubosWrapper.getLatestUciMessages());
            mHandler.postDelayed(mUpdateUciMessagesLog, 500);
        }
    };
    private final Handler mHandler = new Handler();
}
