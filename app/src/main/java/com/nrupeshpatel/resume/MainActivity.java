package com.nrupeshpatel.resume;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.nrupeshpatel.resume.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;

    int[] chartId = {R.id.chart1, R.id.chart2, R.id.chart3, R.id.chart4, R.id.chart5, R.id.chart6};
    float[] skillPercent = {92.0F, 78.0F, 80.0F, 95.0F, 88.0F, 70.0F};
    String[] skillPercentText = {"92%", "78%", "80%", "95%", "88%", "70%"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle = (TextView) findViewById(R.id.main_textview_title);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);

        mToolbar.setTitle("");
        mAppBarLayout.addOnOffsetChangedListener(this);

        setSupportActionBar(mToolbar);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setCheckable(true);

        int i = 0;
        for (int id : chartId) {
            PieChart mPie = (PieChart) findViewById(id);
            pieChart(skillPercent[i], 100.0F - skillPercent[i], skillPercentText[i], mPie);
            i++;
        }

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_facebook) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.social_popup);
            CircleImageView imageView = (CircleImageView) dialog.findViewById(R.id.imageView);
            imageView.setBorderColor(Color.parseColor("#ff3c5998"));
            TextView name = (TextView) dialog.findViewById(R.id.name);
            name.setTextColor(Color.parseColor("#ff3c5998"));
            TextView userName = (TextView) dialog.findViewById(R.id.userName);
            userName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_menu_facebook, 0, 0, 0);
            Button viewProfile = (Button) dialog.findViewById(R.id.viewProfile);
            viewProfile.setBackgroundColor(Color.parseColor("#ff3c5998"));
            viewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/Nrupesh29"));
                    startActivity(i);
                }
            });
            dialog.show();
        } else if (id == R.id.nav_twitter) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.social_popup);
            CircleImageView imageView = (CircleImageView) dialog.findViewById(R.id.imageView);
            imageView.setBorderColor(Color.parseColor("#ff1da1f2"));
            TextView name = (TextView) dialog.findViewById(R.id.name);
            name.setTextColor(Color.parseColor("#ff1da1f2"));
            TextView userName = (TextView) dialog.findViewById(R.id.userName);
            userName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_menu_twitter, 0, 0, 0);
            Button viewProfile = (Button) dialog.findViewById(R.id.viewProfile);
            viewProfile.setBackgroundColor(Color.parseColor("#ff1da1f2"));
            viewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/Nrupesh29"));
                    startActivity(i);
                }
            });
            dialog.show();
        } else if (id == R.id.nav_linkedin) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.social_popup);
            CircleImageView imageView = (CircleImageView) dialog.findViewById(R.id.imageView);
            imageView.setBorderColor(Color.parseColor("#ff0077b5"));
            TextView name = (TextView) dialog.findViewById(R.id.name);
            name.setTextColor(Color.parseColor("#ff0077b5"));
            TextView userName = (TextView) dialog.findViewById(R.id.userName);
            userName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_menu_linkedin, 0, 0, 0);
            Button viewProfile = (Button) dialog.findViewById(R.id.viewProfile);
            viewProfile.setBackgroundColor(Color.parseColor("#ff0077b5"));
            viewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("android.intent.action.VIEW", Uri.parse("http://in.linkedin.com/in/Nrupesh29"));
                    startActivity(i);
                }
            });
            dialog.show();
        } else if (id == R.id.nav_experience) {
            startActivity(new Intent(MainActivity.this, ExperienceActivity.class));

        } else if (id == R.id.nav_education) {
            startActivity(new Intent(MainActivity.this, EducationActivity.class));

        } else if (id == R.id.nav_projects) {
            startActivity(new Intent(MainActivity.this, ProjectsActivity.class));

        } else if (id == R.id.nav_contact) {
            String mailto = "mailto:nrupesh.patel@sjsu.edu" +
                    "?subject=" + Uri.encode("Contact Me") +
                    "&body=" + Uri.encode("Contact Query from Android App");

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailto));

            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                //TODO: Handle case where no email app is available
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_site) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://nrupeshpatel.com")));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void pieChart(float used, float remaining, String percentage, PieChart pieChart) {
        pieChart.setDescription("");
        pieChart.setExtraOffsets(5.0F, 10.0F, 5.0F, 5.0F);
        pieChart.setDragDecelerationFrictionCoef(0.95F);
        pieChart.setCenterText(percentage);
        pieChart.setCenterTextColor(Color.parseColor("#9e9e9e"));
        pieChart.setCenterTextSize(15.0F);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(80.0F);
        pieChart.setTransparentCircleRadius(60.0F);
        pieChart.setRotationAngle(0.0F);
        pieChart.setTouchEnabled(false);
        setPieData(used, remaining, pieChart);
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        pieChart.getLegend().setEnabled(false);
    }

    private void setPieData(float usedPercentage, float remainingPercent, PieChart pieChart) {
        ArrayList localArrayList1 = new ArrayList();
        localArrayList1.add(new Entry(usedPercentage, 0));
        localArrayList1.add(new Entry(remainingPercent, 0));
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.add("0");
        localArrayList2.add("1");
        PieDataSet localPieDataSet = new PieDataSet(localArrayList1, "");
        localPieDataSet.setSliceSpace(0.0F);
        localPieDataSet.setSelectionShift(5.0F);
        ArrayList localArrayList3 = new ArrayList();
        localArrayList3.add(Color.parseColor("#383838"));
        localArrayList3.add(Color.parseColor("#9e9e9e"));
        localPieDataSet.setColors(localArrayList3);
        PieData localPieData = new PieData(localArrayList2, localPieDataSet);
        localPieData.setValueTextColor(0);
        pieChart.setData(localPieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }
}