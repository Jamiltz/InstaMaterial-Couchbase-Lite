package android.couchbaselabs.com.instamaterial.document;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Post {
    private static final String DOC_TYPE = "post";
    private static final String VIEW_NAME = "posts";

    public static Document createPost(Database database, String username, String status) throws CouchbaseLiteException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Calendar calendar = GregorianCalendar.getInstance();
        String currentTimeString = dateFormatter.format(calendar.getTime());

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("type", DOC_TYPE);
        properties.put("username", username);
        properties.put("status", status);
        properties.put("created_at", currentTimeString);

        Document document = database.createDocument();

        document.putProperties(properties);

        return document;
    }

    public static Query allPostsQuery(Database database) {
        com.couchbase.lite.View view = database.getView(VIEW_NAME);
        if (view.getMap() == null) {
            Mapper map = new Mapper() {
                @Override
                public void map(Map<String, Object> document, Emitter emitter) {
                    if (DOC_TYPE.equals(document.get("type"))) {
                        emitter.emit(document.get("created_at"), document);
                    }
                }
            };
            view.setMap(map, "2");
        }

        Query query = view.createQuery();
        query.setDescending(true);

        return query;
    }

}
