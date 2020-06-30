package com.example.profit;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class BottomNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);


        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        final ProfileFragment profileFragment = new ProfileFragment();
        final WorkoutsFragment workoutsFragment = new WorkoutsFragment();
        final ExercisesFragment exercisesFragment = new ExercisesFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.profile) {
                    setFragment(profileFragment);
                    return true;
                } else if (id == R.id.workouts) {
                    setFragment(workoutsFragment);
                    return true;
                }else if (id == R.id.exercises) {
                    setFragment(exercisesFragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}


