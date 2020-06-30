package com.example.profit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutsFragment extends Fragment {

    private ImageView exerciseImage;
    private TextView exerciseDesc;

    public WorkoutsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workouts, container, false);

        exerciseImage = view.findViewById(R.id.ivExerciseOne);
        exerciseDesc = view.findViewById(R.id.excersiseoneTitle);

        exerciseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), CurrentWorkout.class);
                in.putExtra("some", "somedata");
                String exerciseDescB = exerciseDesc.getText().toString();
                startActivity(in);
            }
        });
        return view;
    }
}
