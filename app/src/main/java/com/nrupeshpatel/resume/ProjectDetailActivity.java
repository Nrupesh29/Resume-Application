package com.nrupeshpatel.resume;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class ProjectDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));

        TextView projectName = (TextView) findViewById(R.id.projectName);
        projectName.setText(getIntent().getStringExtra("name"));
        TextView platform = (TextView) findViewById(R.id.platform);
        platform.setText(getIntent().getStringExtra("platform"));
        TextView duration = (TextView) findViewById(R.id.projectDuration);
        duration.setText(getIntent().getStringExtra("duration"));
        TextView location = (TextView) findViewById(R.id.projectLocation);
        location.setText(getIntent().getStringExtra("location"));
        JustifiedTextView description = (JustifiedTextView) findViewById(R.id.projectDescription);
        description.setText(getIntent().getStringExtra("description"));
        ImageView projectLogo = (ImageView) findViewById(R.id.projectLogo);
        Glide.with(this)
                .load(this.getResources().getIdentifier(getIntent().getStringExtra("logo"), "drawable", this.getPackageName()))
                .into(projectLogo);
        Button link = (Button) findViewById(R.id.linkButton);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getIntent().getStringExtra("link"))));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
