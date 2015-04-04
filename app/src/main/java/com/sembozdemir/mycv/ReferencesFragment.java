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
public class ReferencesFragment extends Fragment {


    private String[] referenceName;
    private String[] referencesInfo;

    public ReferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundleReferences = this.getArguments();
        referenceName = bundleReferences.getStringArray(Constants.KEY_REFERENCES_NAME);
        referencesInfo = bundleReferences.getStringArray(Constants.KEY_REFERENCES_INFO);
        View v = inflater.inflate(R.layout.fragment_references, container, false);

        for (int i = 0 ; i < referenceName.length ; i++) {
            LinearLayout listLayout = (LinearLayout) v.findViewById(R.id.listLayout);
            final View card = View.inflate(getActivity().getApplicationContext(), R.layout.card_view_reference, null);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animate(card);
                }
            });
            TextView tvReferenceName = (TextView) card.findViewById(R.id.reference_name_text);
            tvReferenceName.setText(referenceName[i]);
            TextView tvReferenceInfo = (TextView) card.findViewById(R.id.reference_info_text);
            tvReferenceInfo.setText(referencesInfo[i]);
            listLayout.addView(card);
        }

        return v;
    }

    private void animate(View v) {
        YoYo.with(Techniques.Shake).duration(600).playOn(v);
    }

}
