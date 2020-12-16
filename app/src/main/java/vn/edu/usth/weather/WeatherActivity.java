package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ForecastFragment firstFragment = new ForecastFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.forecast, firstFragment).commit();
        WeatherFragment secondFragment =new WeatherFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.weather, secondFragment).commit();
        setTitle("USTH Weather");
        Log.i("Weather", "create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Weather", "start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Weather", "resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Weather", "pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Weather", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Weather", "Destroy");
    }
}