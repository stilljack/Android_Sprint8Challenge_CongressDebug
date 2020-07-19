package com.lambdaschool.congressdata.activities

import android.app.Activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.congressdata.model.CongresspersonProfile
import com.lambdaschool.congressdata.R
import com.lambdaschool.congressdata.viewmodel.CongresspersonProfileViewModel
import com.lambdaschool.congressdata.viewmodel.themeUtils

class DetailsActivity : AppCompatActivity() {


    // oh god this is already a shit show

    private var context: Context? = null
    private var profileImage: ImageView? = null
    private var profileName: TextView? = null
    private var profileParty: TextView? = null
    private var profileDistrict: TextView? = null
    private var profileTwitter: TextView? = null
    private var profileFacebook: TextView? = null
    private var profileMap: TextView? = null
    private var profilePhone: TextView? = null
    private var profileVotingBar: ProgressBar? = null
    private var profileCommitteeList: LinearLayout? = null
    private var profileSubcommitteeList: LinearLayout? = null
    private var activity: Activity? = null

    private var memberId: String? = null

    private lateinit var viewModel: CongresspersonProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        activity = this
        themeUtils.onActivityCreateSetTheme(activity!!)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details_view)
        context = this

        val intent = intent
        memberId = intent.getStringExtra("id")

        viewModel = ViewModelProviders.of(this).get(CongresspersonProfileViewModel::class.java)

        profileImage = findViewById(R.id.profile_image)
        profileName = findViewById(R.id.profile_name)
        profileParty = findViewById(R.id.profile_party)
        profileDistrict = findViewById(R.id.profile_district)
        profileTwitter = findViewById(R.id.profile_twitter)
        profileFacebook = findViewById(R.id.profile_facebook)
        profileMap = findViewById(R.id.profile_map)
        profilePhone = findViewById(R.id.profile_phone)
        profileVotingBar = findViewById(R.id.profile_voting_bar)
        profileCommitteeList = findViewById(R.id.profile_committee_list)
        profileSubcommitteeList = findViewById(R.id.profile_subcommittee_list)


        (findViewById<View>(R.id.profile_name) as TextView).setOnClickListener { themeUtils.nextTheme(activity!!) }
    }

    override fun onStart() {
        super.onStart()

        viewModel.id = memberId as String

        viewModel.profile?.observe(this, Observer<CongresspersonProfile> { profile ->
            runOnUiThread {
                assert(profile != null)
                profileImage!!.setImageBitmap(profile!!.image)
                profileName!!.setText(profile!!.displayName)
                profileParty!!.setText(profile!!.party)
                profileDistrict!!.setText(profile!!.location)
                profileTwitter!!.text = Html.fromHtml("<a href=\"https://twitter.com/" + profile!!.twitterAccount + "\">Twitter</a>")
                profileFacebook!!.text = Html.fromHtml("<a href=\"https://www.facebook.com/" + profile!!.facebookAccount + "/\">Facebook</a>")
                profileMap!!.text = Html.fromHtml("<a href=\"https://www.google.com/maps/search/" + profile!!.office.replace(" ", "-") + "\">Office</a>")
                profilePhone!!.setText(profile!!.phone)


                profileVotingBar!!.progress = profile!!.primaryProgress.toInt()
                profileVotingBar!!.secondaryProgress = profile!!.secondaryProgress.toInt()

                for (name in profile!!.committees!!) {
                    profileCommitteeList!!.addView(getDefaultTextView(name))
                }

                for (name in profile!!.subcommittees!!) {
                    profileSubcommitteeList!!.addView(getDefaultTextView(name))
                }

                profileTwitter!!.setOnClickListener {
                    if (profile!!.twitterAccount != "null") {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + profile!!.twitterAccount)))
                    }
                }
                profileFacebook!!.setOnClickListener {
                    if (profile!!.facebookAccount != "null") {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + profile!!.facebookAccount)))
                    }
                }
                profileMap!!.setOnClickListener {
                    if (profile!!.office != "null") {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/" + profile!!.office)))
                    }
                }
            }
        })
        }

                /*@Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    try {
                        setContentView(R.layout.activity_details_view);
                        context = this;

                        Intent intent = getIntent();
                        memberId = intent.getStringExtra("id");


                        profileImage = findViewById(R.id.profile_image);
                        profileName = findViewById(R.id.profile_name);
                        profileParty = findViewById(R.id.profile_party);
                        profileDistrict = findViewById(R.id.profile_district);
                        profileTwitter = findViewById(R.id.profile_twitter);
                        profileFacebook = findViewById(R.id.profile_facebook);
                        profileMap = findViewById(R.id.profile_map);
                        profilePhone = findViewById(R.id.profile_phone);
                        profileVotingBar = findViewById(R.id.profile_voting_bar);
                        profileCommitteeList = findViewById(R.id.profile_committee_list);
                        profileSubcommitteeList = findViewById(R.id.profile_subcommittee_list);

            //        profile = new CongressPersonProfile(CongressDao.getMemberDetails(memberId));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                profileImage.setImageBitmap(CongressDao.getImage(memberId));
                            }
                        });//)).start();

                        Thread networkThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                CongresspersonProfile profile = new CongresspersonProfile(CongressDao.getMemberDetails(memberId));
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        profileName.setText(profile.getDisplayName());
                                        profileParty.setText(profile.getParty());
                                        profileDistrict.setText(profile.getLocation());
                                        profileTwitter.setText(String.format("@%s", profile.getTwitterAccount()));
                                        profileFacebook.setText(String.format("%s", profile.getFacebookAccount()));
                                        profileMap.setText(String.format("%s", profile.getOffice()));
                                        profilePhone.setText(profile.getPhone());


                                        profileVotingBar.setProgress((int) profile.getPrimaryProgress());
                                        profileVotingBar.setSecondaryProgress((int) profile.getSecondaryProgress());

                                        for (String name : profile.getCommittees()) {
                                            profileCommitteeList.addView(getDefaultTextView(name));
                                        }

                                        for (String name : profile.getSubcommittees()) {
                                            profileSubcommitteeList.addView(getDefaultTextView(name));
                                        }

                                        profileTwitter.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (!profile.getTwitterAccount().equals("null")) {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + profile.getTwitterAccount())));
                                                }
                                            }
                                        });
                                        profileFacebook.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (!profile.getFacebookAccount().equals("null")) {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + profile.getFacebookAccount())));
                                                }
                                            }
                                        });
                                        profileMap.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (!profile.getOffice().equals("null")) {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/" + profile.getOffice())));
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        networkThread.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/

                private fun getDefaultTextView(text: String): TextView {
            val dataView = TextView(context)
            dataView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            dataView.setPadding(5, 5, 5, 5)
            dataView.text = text
            return dataView
        }
    }
