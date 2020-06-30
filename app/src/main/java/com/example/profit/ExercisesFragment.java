package com.example.profit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExercisesFragment extends Fragment {


    public ExercisesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        ListView lst = (ListView)view.findViewById(R.id.listview);

        //Custom listview - list of exercises.

        final String[] exerciseName = {"Ab wheel", "Around the World", "Back Extension"};
        String[] desc = {"Core", "Cardio", "Back"};
        Integer[] imgid = {R.drawable.exerciseimg, R.drawable.exerciseimg, R.drawable.exerciseimg};
        CustomListview customListview=new CustomListview(getActivity(), exerciseName, desc, imgid);
        lst.setAdapter(customListview);

        //Click on listview items to display exercise description. TODO

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ExerciseDescription.class);
                startActivity(i);
            }
        });
        return view;
    }

}
