package com.example.profit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Log out user and redirect to main log/sign up page.

        final ImageView userProfilePic = (ImageView) view.findViewById(R.id.ivProfile);
        final Button logout = (Button) view.findViewById(R.id.btnLogout);

        final FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Logout();
            }

            private void Logout(){
                firebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), MainLogRegActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
