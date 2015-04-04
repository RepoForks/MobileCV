package com.sembozdemir.mycv;


import android.content.Intent;
import android.net.Uri;
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
public class VolunteerismFragment extends Fragment {

    String[] organizations;
    String[] positions;
    String[] websites;
    String[] startDates;
    String[] endDates;
    String[] summaries;


    public VolunteerismFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundleVolunteerism = this.getArguments();
        organizations = bundleVolunteerism.getStringArray(Constants.KEY_ORGANIZATIONS);
        positions = bundleVolunteerism.getStringArray(Constants.KEY_POSITIONS);
        websites = bundleVolunteerism.getStringArray(Constants.KEY_WEBSITES);
        startDates = bundleVolunteerism.getStringArray(Constants.KEY_START_DATES_VOLUNTEER);
        endDates = bundleVolunteerism.getStringArray(Constants.KEY_END_DATES_VOLUNTEER);
        summaries = bundleVolunteerism.getStringArray(Constants.KEY_SUMMARIES_VOLUNTEER);
        View v = inflater.inflate(R.layout.fragment_volunteerism, container, false);

        for (int i = 0 ; i < organizations.length ; i++) {
            LinearLayout listLayout = (LinearLayout) v.findViewById(R.id.listLayout);
            final View card = View.inflate(getActivity().getApplicationContext(), R.layout.card_view_volunteer, null);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animate(card);
                }
            });
            TextView tvOrganization = (TextView) card.findViewById(R.id.volunteer_organization_text);
            tvOrganization.setText(organizations[i]);
            TextView tvPosition = (TextView) card.findViewById(R.id.volunteer_position_text);
            tvPosition.setText(positions[i]);
            TextView tvWebsite = (TextView) card.findViewById(R.id.volunteer_website_text);
            tvWebsite.setText(websites[i]);
            final String url = websites[i];
            tvWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLink(url);
                }
            });
            TextView tvSummary = (TextView) card.findViewById(R.id.volunteer_summary_text);
            tvSummary.setText(summaries[i]);
            listLayout.addView(card);
        }

        return v;
    }

    private void animate(View v) {
        YoYo.with(Techniques.Shake).duration(600).playOn(v);
    }

    private void openLink(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

}
