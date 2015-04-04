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
public class LanguagesFragment extends Fragment {

    String[] languages;
    String[] fluencies;

    public LanguagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundleLanguage = this.getArguments();
        languages = bundleLanguage.getStringArray(Constants.KEY_LANGUAGE);
        fluencies = bundleLanguage.getStringArray(Constants.KEY_FLUENCY);
        View v = inflater.inflate(R.layout.fragment_languages, container, false);

        for (int i = 0 ; i < languages.length ; i++) {
            LinearLayout listLayout = (LinearLayout) v.findViewById(R.id.listLayout);
            final View card = View.inflate(getActivity().getApplicationContext(), R.layout.card_view_language, null);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animate(card);
                }
            });
            TextView tvLanguage = (TextView) card.findViewById(R.id.language_text);
            tvLanguage.setText(languages[i]);
            TextView tvFluency = (TextView) card.findViewById(R.id.fluency_text);
            tvFluency.setText(fluencies[i]);
            listLayout.addView(card);
        }

        return v;
    }

    private void animate(View v) {
        YoYo.with(Techniques.Shake).duration(600).playOn(v);
    }

}
