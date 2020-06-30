package com.example.profit;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

public class ExerciseDescription extends AppCompatActivity {

    private ConstraintLayout layout_MainMenu;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_desc);

        layout_MainMenu = (ConstraintLayout) findViewById( R.id.conlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layout_MainMenu.getForeground().setAlpha( 0);
        }

        //Set parameters for Pop activity window.

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.90), (int)(height*0.90));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        layout_MainMenu.getForeground().setAlpha( 220); // dim

        layout_MainMenu.getForeground().setAlpha( 0); // restore
    }
}
