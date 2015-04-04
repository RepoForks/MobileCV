package com.sembozdemir.mycv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


/**
 * A simple {@link Fragment} subclass.
 */
public class ObjectiveFragment extends Fragment {

    private static final String TAG = ObjectiveFragment.class.getSimpleName();
    String mObjective = null;


    public ObjectiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundleContacts = this.getArguments();
        mObjective = bundleContacts.getString(Constants.FIELD_OBJECTIVE);
        View v = inflater.inflate(R.layout.fragment_objective, container, false);
        final CardView card = (CardView) v.findViewById(R.id.cv_objective);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(card);
            }
        });
        TextView tvObjective = (TextView) v.findViewById(R.id.info_objective);
        tvObjective.setText(mObjective);

        return v;
    }

    private void animate(View v) {
        YoYo.with(Techniques.Shake).duration(600).playOn(v);
    }
}
