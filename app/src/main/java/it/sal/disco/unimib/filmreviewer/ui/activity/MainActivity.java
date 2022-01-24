package it.sal.disco.unimib.filmreviewer.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.sal.disco.unimib.filmreviewer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Required for navigation between fragment
        NavHostFragment mNavHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.main_fragmentContainerView);
        NavController mNavController =
                mNavHostFragment.getNavController();
        BottomNavigationView mainBottomNavigView =
                findViewById(R.id.main_bottom_nav_view);
        NavigationUI
                .setupWithNavController(mainBottomNavigView, mNavController);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
        super.onPause();
    }
}