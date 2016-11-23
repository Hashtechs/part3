package hashtech.com.booklisting.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

import hashtech.com.booklisting.R;
import hashtech.com.booklisting.models.Book;

public class LoaderAcivityExample extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    private static final String TAG = "LoaderAcivityExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_acivity_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportLoaderManager().initLoader(0,null,this).forceLoad();

    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: ");
        BookLoader bookLoader = new BookLoader(this);
        return bookLoader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> data) {

        Log.d(TAG, "onLoadFinished: ");
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {

    }


    public static   class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {

        public BookLoader(Context context) {
            super(context);
        }

        @Override
        public ArrayList<Book> loadInBackground() {

            // do a heavy process
            SystemClock.sleep(4000);

            ArrayList<Book> bookArrayList;
            bookArrayList = parseJson();
            return bookArrayList;
        }
    }

    private static ArrayList<Book> parseJson() {
        ArrayList<Book> bookArrayList = new ArrayList<>();


        Book book = new Book();
        book.setTitle("Book1");

        bookArrayList.add(book);
        Book book2 = new Book();
        book2.setTitle("Book2");

        bookArrayList.add(book2);


        return bookArrayList;
    }

}
