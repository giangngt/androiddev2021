package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
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
