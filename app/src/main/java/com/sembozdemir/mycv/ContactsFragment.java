package com.sembozdemir.mycv;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    private String name;
    private String label;
    private String email;
    private String phone;
    private String website;
    private String profilePicUrl;
    private String[] socialProfileNames;
    private String[] socialProfileUrls;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundleContacts = this.getArguments();
        name = bundleContacts.getString(Constants.FIELD_NAME);
        label = bundleContacts.getString(Constants.FIELD_LABEL);
        email = bundleContacts.getString(Constants.FIELD_EMAIL);
        phone = bundleContacts.getString(Constants.FIELD_PHONE);
        website = bundleContacts.getString(Constants.FIELD_WEBSITE);
        profilePicUrl = bundleContacts.getString(Constants.FIELD_PROFILE_PIC);
        socialProfileNames = bundleContacts.getStringArray(Constants.KEY_SOCIAL_PROFILE_NAMES);
        socialProfileUrls = bundleContacts.getStringArray(Constants.KEY_SOCIAL_PROFILE_URLS);
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        final CardView cardProfile = (CardView) v.findViewById(R.id.cv_profile_image);
        final CardView cardSocialIcons = (CardView) v.findViewById(R.id.cv_social_icons);
        final CardView cardInfo = (CardView) v.findViewById(R.id.cv_contacts_info);
        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(cardProfile);
            }
        });
        cardSocialIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(cardSocialIcons);
            }
        });
        cardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(cardInfo);
            }
        });

        final CircleImageView imgProfile = (CircleImageView) v.findViewById(R.id.profile_image);
        Picasso.with(getActivity().getApplicationContext())
                .load(profilePicUrl).into(imgProfile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateImg(imgProfile);
            }
        });

        TextView tvName = (TextView) v.findViewById(R.id.info_name);
        tvName.setText(name);

        TextView tvLabel = (TextView) v.findViewById(R.id.info_label);
        tvLabel.setText(label);

        TextView tvEmail = (TextView) v.findViewById(R.id.info_email);
        tvEmail.setText(email);
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(email);
            }
        });

        TextView tvPhone = (TextView) v.findViewById(R.id.info_phone);
        tvPhone.setText(phone);
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNumber(phone);
            }
        });

        TextView tvWebsite = (TextView) v.findViewById(R.id.info_website);
        tvWebsite.setText(website);
        tvWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(website);
            }
        });

        LinearLayout.LayoutParams paramsIcons = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsIcons.width = getActivity().getResources().getDimensionPixelSize(R.dimen.social_icons_size);
        paramsIcons.height = getActivity().getResources().getDimensionPixelSize(R.dimen.social_icons_size);
        int marginPx = getActivity().getResources().getDimensionPixelSize(R.dimen.social_icon_margin);
        paramsIcons.setMargins(marginPx, marginPx, marginPx, marginPx);

        for (int i = 0 ; i < socialProfileNames.length ; i++) {
            LinearLayout listSocialIcons = (LinearLayout) v.findViewById(R.id.listSocialIcons);

            final int finalI = i;
            switch (socialProfileNames[i].toLowerCase()) {
                case Constants.FIELD_FACEBOOK:
                    final ImageView imgFacebok = new ImageView(getActivity().getApplicationContext());
                    imgFacebok.setImageResource(R.drawable.social_ic_fb);
                    imgFacebok.setLayoutParams(paramsIcons);
                    imgFacebok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            animateImg(imgFacebok);
                            openLink(socialProfileUrls[finalI]);
                        }
                    });

                    listSocialIcons.addView(imgFacebok);
                    break;
                case Constants.FIELD_TWITTER:
                    final ImageView imgTwitter = new ImageView(getActivity().getApplicationContext());
                    imgTwitter.setImageResource(R.drawable.social_ic_twitter);
                    imgTwitter.setLayoutParams(paramsIcons);
                    imgTwitter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            animateImg(imgTwitter);
                            openLink(socialProfileUrls[finalI]);
                        }
                    });

                    listSocialIcons.addView(imgTwitter);
                    break;
                case Constants.FIELD_GOOGLE_PLUS:
                    final ImageView imgGooglePlus = new ImageView(getActivity().getApplicationContext());
                    imgGooglePlus.setImageResource(R.drawable.social_ic_google_plus);
                    imgGooglePlus.setLayoutParams(paramsIcons);
                    imgGooglePlus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            animateImg(imgGooglePlus);
                            openLink(socialProfileUrls[finalI]);
                        }
                    });

                    listSocialIcons.addView(imgGooglePlus);
                    break;
                case Constants.FIELD_LINKEDIN:
                    final ImageView imgLinkedin = new ImageView(getActivity().getApplicationContext());
                    imgLinkedin.setImageResource(R.drawable.social_ic_linkedin);
                    imgLinkedin.setLayoutParams(paramsIcons);
                    imgLinkedin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            animateImg(imgLinkedin);
                            openLink(socialProfileUrls[finalI]);
                        }
                    });

                    listSocialIcons.addView(imgLinkedin);
                    break;
                case Constants.FIELD_GITHUB:
                    final ImageView imgGithub = new ImageView(getActivity().getApplicationContext());
                    imgGithub.setImageResource(R.drawable.social_ic_github);
                    imgGithub.setLayoutParams(paramsIcons);
                    imgGithub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            animateImg(imgGithub);
                            openLink(socialProfileUrls[finalI]);
                        }
                    });

                    listSocialIcons.addView(imgGithub);
                    break;
                case Constants.FIELD_GOOGLE_PLAY:
                    final ImageView imgGooglePlay = new ImageView(getActivity().getApplicationContext());
                    imgGooglePlay.setImageResource(R.drawable.social_ic_google_play);
                    imgGooglePlay.setLayoutParams(paramsIcons);
                    imgGooglePlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            animateImg(imgGooglePlay);
                            openLink(socialProfileUrls[finalI]);
                        }
                    });

                    listSocialIcons.addView(imgGooglePlay);
                    break;

            }

        }

        return v;
    }

    private void animateImg(View v) {
        YoYo.with(Techniques.FlipInX).duration(400).playOn(v);
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

    private void callNumber(String number) {
        String tel = "tel:" + number.trim();
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(tel));
        startActivity(callIntent);
    }

    private void sendEmail(String emailAddress) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{emailAddress});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
