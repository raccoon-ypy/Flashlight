package com.yangpeiyong.flashlight;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private PowerLED powerLED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        powerLED = new PowerLED();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        powerLED.Destroy();
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

    public void flashLight(View view){
        if (!powerLED.getIsOn()){
            powerLED.turnOn();
            findViewById(R.id.linearlayout).setBackgroundColor(0xfff);
            ((Button)findViewById(R.id.btn)).setText("Turn off");
        } else {
            powerLED.turnOff();
            findViewById(R.id.linearlayout).setBackgroundColor(0);
            ((Button)findViewById(R.id.btn)).setText("Turn on");
        }
    }
}
