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
public class EducationFragment extends Fragment {

    String[] instituionNames;
    String[] gpas;
    String[] studyTypes;
    String[] startDates;
    String[] endDates;
    String[] areas;


    public EducationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundleEducation = this.getArguments();
        instituionNames = bundleEducation.getStringArray(Constants.KEY_INSTITUTION_NAMES);
        areas = bundleEducation.getStringArray(Constants.KEY_AREAS);
        studyTypes = bundleEducation.getStringArray(Constants.KEY_STUDY_TYPES);
        gpas = bundleEducation.getStringArray(Constants.KEY_GPAS);
        startDates = bundleEducation.getStringArray(Constants.KEY_START_DATES);
        endDates = bundleEducation.getStringArray(Constants.KEY_END_DATES);
        View v = inflater.inflate(R.layout.fragment_education, container, false);

        for (int i = 0 ; i < instituionNames.length ; i++) {
            LinearLayout listLayout = (LinearLayout) v.findViewById(R.id.listLayout);
            final View card = View.inflate(getActivity().getApplicationContext(), R.layout.card_view_eduction, null);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animate(card);
                }
            });
            TextView tvInstitution = (TextView) card.findViewById(R.id.education_instituiton_text);
            tvInstitution.setText(instituionNames[i]);
            TextView tvArea = (TextView) card.findViewById(R.id.education_area_text);
            tvArea.setText(areas[i]);
            TextView tvDates = (TextView) card.findViewById(R.id.education_dates_text);
            tvDates.setText(startDates[i] + " - " + endDates[i]);
            TextView tvGpa = (TextView) card.findViewById(R.id.education_gpa_text);
            tvGpa.setText(gpas[i]);
            TextView tvStudyType = (TextView) card.findViewById(R.id.education_study_type_text);
            tvStudyType.setText(studyTypes[i]);
            listLayout.addView(card);
        }

        return v;
    }

    private void animate(View v) {
        YoYo.with(Techniques.Shake).duration(600).playOn(v);
    }
}
