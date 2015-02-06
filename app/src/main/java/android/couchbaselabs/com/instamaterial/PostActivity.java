package android.couchbaselabs.com.instamaterial;

import android.couchbaselabs.com.instamaterial.document.Post;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.couchbase.lite.CouchbaseLiteException;

public class PostActivity extends ActionBarActivity {

    Toolbar toolbar;
    private MenuItem saveMenuItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        saveMenuItem = menu.findItem(R.id.action_save);
        saveMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final EditText usernameField = (EditText) findViewById(R.id.username);
                final EditText statusField = (EditText) findViewById(R.id.status);
                try {
                    Post.createPost(((Application) getApplication()).getDatabase(), usernameField.getText().toString(), statusField.getText().toString());
                } catch (CouchbaseLiteException e) {
                    e.printStackTrace();
                }
                finish();
                return true;
            }
        });
        return true;
    }

}
