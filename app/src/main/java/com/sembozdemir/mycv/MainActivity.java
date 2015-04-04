package com.sembozdemir.mycv;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.sembozdemir.mycv.model.Basics;
import com.sembozdemir.mycv.model.Education;
import com.sembozdemir.mycv.model.Interests;
import com.sembozdemir.mycv.model.Languages;
import com.sembozdemir.mycv.model.Profiles;
import com.sembozdemir.mycv.model.References;
import com.sembozdemir.mycv.model.Resume;
import com.sembozdemir.mycv.model.Skills;
import com.sembozdemir.mycv.model.Volunteer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private PagerSlidingTabStrip mTabs;
    private ViewPager mPager;
    private ProgressBar mProgressBar;
    protected Resume resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.pager);

        if (isNetworkAvailable()) { // internet bağlantısı varsa
            mProgressBar.setVisibility(View.VISIBLE); // progressBar'ı görüntüle
            GetResumeTask getResumeTask = new GetResumeTask();
            getResumeTask.execute();

        } else { // internet bağlantısı yoksa toast goster
            Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = getApplicationContext().getResources().getStringArray(R.array.titles);

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // TODO: her tab için yeni fragment yazılacak ve burada oluşturulup dönderilecek.
            if(resume != null) {
                switch (position) {
                    case Constants.POS_CONTACTS:
                        ContactsFragment contactsFragment = new ContactsFragment();
                        Basics basic = resume.getBasics();
                        Bundle bundleContacts = new Bundle();
                        bundleContacts.putString(Constants.FIELD_NAME, basic.getName());
                        bundleContacts.putString(Constants.FIELD_LABEL, basic.getLabel());
                        bundleContacts.putString(Constants.FIELD_EMAIL, basic.getEmail());
                        bundleContacts.putString(Constants.FIELD_PHONE, basic.getPhone());
                        bundleContacts.putString(Constants.FIELD_WEBSITE, basic.getWebsite());
                        bundleContacts.putString(Constants.FIELD_PROFILE_PIC, basic.getPicture());
                        ArrayList<Profiles> socialProfiles = (ArrayList<Profiles>) basic.getProfiles();
                        String[] socialProfileNames = new String[socialProfiles.size()];
                        String[] socialProfileUrls = new String[socialProfiles.size()];
                        for (int i = 0 ; i < socialProfiles.size() ; i++) {
                            Profiles profile = socialProfiles.get(i);
                            socialProfileNames[i] = profile.getNetwork();
                            socialProfileUrls[i] = profile.getUrl();
                        }
                        bundleContacts.putStringArray(Constants.KEY_SOCIAL_PROFILE_NAMES, socialProfileNames);
                        bundleContacts.putStringArray(Constants.KEY_SOCIAL_PROFILE_URLS, socialProfileUrls);

                        contactsFragment.setArguments(bundleContacts);

                        return contactsFragment;

                    case Constants.POS_OBJECTIVE:
                        ObjectiveFragment objectiveFragment = new ObjectiveFragment();
                        String objective = resume.getBasics().getSummary();
                        Bundle bundleObjective = new Bundle();
                        bundleObjective.putString(Constants.FIELD_OBJECTIVE, objective);

                        objectiveFragment.setArguments(bundleObjective);

                        return objectiveFragment;

                    case Constants.POS_EDUCATION:
                        EducationFragment educationFragment = new EducationFragment();
                        Bundle bundleEducation = new Bundle();
                        ArrayList<Education> educations = (ArrayList<Education>) resume.getEducation();
                        String[] instituionNames = new String[educations.size()];
                        String[] areas = new String[educations.size()];
                        String[] gpas = new String[educations.size()];
                        String[] studyTypes = new String[educations.size()];
                        String[] startDates = new String[educations.size()];
                        String[] endDates = new String[educations.size()];
                        for (int i = 0 ; i < educations.size() ; i++) {
                            Education education = educations.get(i);
                            instituionNames[i] = education.getInstitution();
                            areas[i] = education.getArea();
                            studyTypes[i] = education.getStudyType();
                            gpas[i] = education.getGpa();
                            startDates[i] = education.getStartDate();
                            endDates[i] = education.getEndDate();
                        }

                        bundleEducation.putStringArray(Constants.KEY_INSTITUTION_NAMES, instituionNames);
                        bundleEducation.putStringArray(Constants.KEY_AREAS, areas);
                        bundleEducation.putStringArray(Constants.KEY_STUDY_TYPES, studyTypes);
                        bundleEducation.putStringArray(Constants.KEY_GPAS, gpas);
                        bundleEducation.putStringArray(Constants.KEY_START_DATES, startDates);
                        bundleEducation.putStringArray(Constants.KEY_END_DATES, endDates);

                        educationFragment.setArguments(bundleEducation);

                        return educationFragment;

                    case Constants.POS_VOUNTERISM:
                        VolunteerismFragment volunteerismFragment = new VolunteerismFragment();
                        Bundle bundleVolunteerism = new Bundle();
                        ArrayList<Volunteer> volunteers = (ArrayList<Volunteer>) resume.getVolunteer();
                        String[] organizations = new String[volunteers.size()];
                        String[] positions = new String[volunteers.size()];
                        String[] websites = new String[volunteers.size()];
                        String[] startDatesV = new String[volunteers.size()];
                        String[] endDatesV = new String[volunteers.size()];
                        String[] summaries = new String[volunteers.size()];
                        //ArrayList<String[]> higlightArray = new ArrayList<String[]>();
                        for (int i = 0 ; i < volunteers.size() ; i++) {
                            Volunteer volunteer = volunteers.get(i);
                            organizations[i] = volunteer.getOrganization();
                            positions[i] = volunteer.getPosition();
                            websites[i] = volunteer.getWebsite();
                            startDatesV[i] = volunteer.getStartDate();
                            endDatesV[i] = volunteer.getEndDate();
                            summaries[i] = volunteer.getSummary();
                            //higlightArray.add(i, (String[]) volunteer.getHighlights().toArray());
                        }

                        bundleVolunteerism.putStringArray(Constants.KEY_ORGANIZATIONS, organizations);
                        bundleVolunteerism.putStringArray(Constants.KEY_POSITIONS, positions);
                        bundleVolunteerism.putStringArray(Constants.KEY_WEBSITES, websites);
                        bundleVolunteerism.putStringArray(Constants.KEY_START_DATES_VOLUNTEER, startDatesV);
                        bundleVolunteerism.putStringArray(Constants.KEY_END_DATES_VOLUNTEER, endDatesV);
                        bundleVolunteerism.putStringArray(Constants.KEY_SUMMARIES_VOLUNTEER, summaries);
                        // TODO : bundleVolunteerism.putStringArrayList(Constants.HIGHLIGHTS_VOLUNTEER, higlightArray);

                        volunteerismFragment.setArguments(bundleVolunteerism);

                        return volunteerismFragment;

                    case Constants.POS_SKILLS:
                        SkillsFragment skillsFragment = new SkillsFragment();
                        Bundle bundleSkills = new Bundle();
                        ArrayList<Skills> skills = (ArrayList<Skills>) resume.getSkills();
                        String[] skillNames = new String[skills.size()];
                        String[] keywords = new String[skills.size()];
                        for (int i = 0 ; i < skills.size() ; i++) {
                            Skills skill = skills.get(i);
                            skillNames[i] = skill.getName();
                            keywords[i] = String.valueOf(skill.getKeywords());
                        }

                        bundleSkills.putStringArray(Constants.KEY_SKILL_NAMES, skillNames);
                        bundleSkills.putStringArray(Constants.KEY_SKILL_KEYWORDS, keywords);

                        skillsFragment.setArguments(bundleSkills);

                        return skillsFragment;

                    case Constants.POS_LANGUAGES:
                        LanguagesFragment languagesFragment = new LanguagesFragment();
                        Bundle bundleLanguage = new Bundle();
                        ArrayList<Languages> languagesArrayList = (ArrayList<Languages>) resume.getLanguages();
                        String[] languages = new String[languagesArrayList.size()];
                        String[] fluencies = new String[languagesArrayList.size()];
                        for (int i = 0 ; i < languagesArrayList.size() ; i++) {
                            Languages language = languagesArrayList.get(i);
                            languages[i] = language.getLanguage();
                            fluencies[i] = language.getFluency();
                        }

                        bundleLanguage.putStringArray(Constants.KEY_LANGUAGE, languages);
                        bundleLanguage.putStringArray(Constants.KEY_FLUENCY, fluencies);

                        languagesFragment.setArguments(bundleLanguage);

                        return languagesFragment;

                    case Constants.POS_INTERESTS:
                        InterestsFragment interestsFragment = new InterestsFragment();
                        Bundle bundleInterest = new Bundle();
                        ArrayList<Interests> interests = (ArrayList<Interests>) resume.getInterests();
                        String[] interestsArray = new String[interests.size()];
                        for (int i = 0 ; i < interests.size() ; i++) {
                            Interests interest = interests.get(i);
                            interestsArray[i] = interest.getName();
                        }

                        bundleInterest.putStringArray(Constants.KEY_INTERESTS, interestsArray);

                        interestsFragment.setArguments(bundleInterest);

                        return interestsFragment;

                    case Constants.POS_REFERENCES:
                        ReferencesFragment referencesFragment = new ReferencesFragment();
                        Bundle bundleReferences = new Bundle();
                        ArrayList<References> referencesArrayList = (ArrayList<References>) resume.getReferences();
                        String[] referencesName = new String[referencesArrayList.size()];
                        String[] referencesInfo = new String[referencesArrayList.size()];
                        for (int i = 0 ; i < referencesArrayList.size() ; i++) {
                            References references = referencesArrayList.get(i);
                            referencesName[i] = references.getName();
                            referencesInfo[i] = references.getReference();
                        }

                        bundleReferences.putStringArray(Constants.KEY_REFERENCES_NAME, referencesName);
                        bundleReferences.putStringArray(Constants.KEY_REFERENCES_INFO, referencesInfo);

                        referencesFragment.setArguments(bundleReferences);

                        return referencesFragment;
                }
            }

            return new ContactsFragment();
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

    private void logException(Exception e) {
        Log.v(TAG, "Exception caught: ", e);
    }

    private boolean isNetworkAvailable() { // bağlantının olup olmadığını kontrol eden method

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isAvaible = false;
        if(networkInfo != null && networkInfo.isConnected()){ // isConnected methodu networke bağlı olup olmadığını dönderir.
            isAvaible = true;
        }
        return isAvaible;
    }

    public class GetResumeTask extends AsyncTask<Object, Integer, Void> {
        @Override
        protected Void doInBackground(Object... params) {
            int responseCode = -1;

            try {
                URL resumeUrl = new URL("http://sembozdemir.com/cv/cv.json");
                HttpURLConnection connection = (HttpURLConnection) resumeUrl.openConnection();
                connection.connect();

                responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    Reader reader = new InputStreamReader(inputStream);

                    // json çıktısını karakter karakter okuyan kod
                    int nextCharacter; // read() methodu int döndürür, daha sonra chara cast edeceğiz
                    String responseData = "";
                    do {
                        nextCharacter = reader.read(); // read() methodunu parametresiz çağırdığımızda sadece bir karakter okur
                        responseData += (char) nextCharacter; // her döngüde responseData 'ya okuduğumuz karakteri ekle
                    }while (nextCharacter != -1);

                    Log.v(TAG, responseData); // json çıktısını logcat'te göster

                    Gson gson = new GsonBuilder().create();
                    JsonReader jsonReader = new JsonReader(new StringReader(responseData));
                    jsonReader.setLenient(true);
                    resume = gson.fromJson(jsonReader, Resume.class);

                    if (resume != null) {
                        publishProgress(new Integer[]{responseCode});
                    }

                } else {
                    Log.i(TAG, "Unsuccesful Response code: " + responseCode); // bağlantı başarısızsa Log at
                    publishProgress(new Integer[]{responseCode});

                }

            } catch (MalformedURLException e) {
                logException(e);
            } catch (IOException e) {
                logException(e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer[] values) {
            super.onProgressUpdate(values);

            if (values[0] == HttpURLConnection.HTTP_NOT_FOUND) {
                Toast.makeText(getApplicationContext(), "CV bulunamadı", Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            } else {
                mProgressBar.setVisibility(View.INVISIBLE); // progressBar'ı gizle

                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                mPager.setAdapter(mSectionsPagerAdapter);
                final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                        .getDisplayMetrics());
                mPager.setPageMargin(pageMargin);
                mTabs.setViewPager(mPager);
                mTabs.setIndicatorColorResource(R.color.app_color);
            }
        }

    }

}
