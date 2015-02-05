package android.couchbaselabs.com.instamaterial;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;

public class Application extends android.app.Application {

    private static final String DATABASE_NAME = "instamaterial";
    private Database database;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            initDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    private void initDatabase() throws IOException, CouchbaseLiteException {
        Manager manager = new Manager(new AndroidContext(getApplicationContext()), Manager.DEFAULT_OPTIONS);
        database = manager.getDatabase(DATABASE_NAME);
    }

    public Database getDatabase() {
        return database;
    }

}
