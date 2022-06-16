package com.example.dictionary.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dictionary.R;
import com.example.dictionary.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    private DrawerLayout drawerLayout;
//The savedInstanceState is a reference to a Bundle object that is passed//
// into the onCreate method of every Android Activity. Activities have the ability,//
// under special circumstances,
// to restore themselves to a previous state using the data stored in this bundle.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupToolbar();
        setupNavigation();



    }
//An application may choose to designate a Toolbar as the action bar for an ... //
// be used on the ending side of the bar in the current toolbar configuration.
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = binding.drawerLayout;
        setSupportActionBar(toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment)
                .setOpenableLayout(drawerLayout)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }
//Navigation refers to the interactions that allow users to navigate across, into, and back out from//
// the different pieces of content within your app.
    private void setupNavigation() {
        NavigationView navigationView = binding.navigationView;
        NavigationUI.setupWithNavController(navigationView, navController);
    }
//This method is called whenever the user chooses to navigate Up within //
// your application's activity hierarchy from the action bar.
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp();
    }
//The official documentation states that onBackPressed() method is called //
// when the activity has detected the user's press of the back key. //
// The default implementation simply finishes the current activity,
// but you can override this to do whatever you want.
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
////onDestroyView() allows the fragment to clean up resources associated with its View.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }


}