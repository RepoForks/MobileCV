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
public class SkillsFragment extends Fragment {


    private String[] keywords;
    private String[] skillNames;

    public SkillsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundleSkills = this.getArguments();
        skillNames = bundleSkills.getStringArray(Constants.KEY_SKILL_NAMES);
        keywords = bundleSkills.getStringArray(Constants.KEY_SKILL_KEYWORDS);
        View v = inflater.inflate(R.layout.fragment_skills, container, false);

        for (int i = 0 ; i < skillNames.length ; i++) {
            LinearLayout listLayout = (LinearLayout) v.findViewById(R.id.listLayout);
            final View card = View.inflate(getActivity().getApplicationContext(), R.layout.card_view_skill, null);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animate(card);
                }
            });
            TextView tvSkill = (TextView) card.findViewById(R.id.skill_text);
            tvSkill.setText(skillNames[i]);
            TextView tvKeywords = (TextView) card.findViewById(R.id.skill_keywords_text);
            tvKeywords.setText(keywords[i]);
            listLayout.addView(card);
        }

        return v;
    }

    private void animate(View v) {
        YoYo.with(Techniques.Shake).duration(600).playOn(v);
    }
}
