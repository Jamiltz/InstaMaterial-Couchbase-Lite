package android.couchbaselabs.com.instamaterial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {
    Toolbar toolbar;
    RecyclerView rvFeed;

    private MenuItem inboxMenuItem;
    private FeedAdapter feedAdapter;

    private ImageButton mCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);

        setupFeed();

        mCreateButton = (ImageButton) findViewById(R.id.btnCreate);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvFeed.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(this);
        rvFeed.setAdapter(feedAdapter);
    }

}
