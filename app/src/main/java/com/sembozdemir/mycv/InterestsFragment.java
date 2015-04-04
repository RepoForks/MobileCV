package com.sembozdemir.mycv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


/**
 * A simple {@link Fragment} subclass.
 */
public class InterestsFragment extends Fragment {


    private String[] interests;

    public InterestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundleInterests = this.getArguments();
        interests = bundleInterests.getStringArray(Constants.KEY_INTERESTS);
        View v = inflater.inflate(R.layout.fragment_interests, container, false);

        for (int i = 0 ; i < interests.length ; i++) {
            LinearLayout listLayout = (LinearLayout) v.findViewById(R.id.listLayout);
            final View card = View.inflate(getActivity().getApplicationContext(), R.layout.card_view_interest, null);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animate(card);
                }
            });
            TextView tvInterest = (TextView) card.findViewById(R.id.interest_text);
            tvInterest.setText(interests[i]);
            listLayout.addView(card);
        }

        return v;
    }

    private void animate(View v) {
        YoYo.with(Techniques.Shake).duration(600).playOn(v);
    }
}
