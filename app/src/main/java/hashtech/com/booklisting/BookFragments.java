package hashtech.com.booklisting;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import hashtech.com.booklisting.adapters.BooksAdapter;
import hashtech.com.booklisting.models.Book;
import hashtech.com.booklisting.utils.ApiHelper;
import hashtech.com.booklisting.utils.HttpRequestHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragments extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    private static final String TAG = "BookFragments";
    private RecyclerView mRecyclerView;
    private TextView tvResponse;
    private ArrayList<Book> mBooksArrayList = new ArrayList<>();
    private ProgressDialog progressDialog;

    public BookFragments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        /**
         * getLoaderManger().initLoader ( Id , Bundle , callBack )  :
         will return new loader if it doesn’t exist
         , when rotating the activity the same loader will be used.
         */
        getLoaderManager().initLoader(0, null, this).forceLoad();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_book_fragments, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);


        return v;
    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {

        progressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
        // return your loader
        return new BooksLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        int coulmnNo = getResources().getInteger(R.integer.grid_couomn);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), coulmnNo));
        mRecyclerView.setAdapter(new BooksAdapter(getActivity(), data));
    }


    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {

    }


    private static class BooksLoader extends AsyncTaskLoader<ArrayList<Book>> {


        public BooksLoader(Context context) {
            super(context);
        }

        @Override
        public ArrayList<Book> loadInBackground() {
            String response = null;
            try {
                response = HttpRequestHelper.makeHttpRequest(ApiHelper.LIST_BOOK_URL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                return parseJsonOld(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        private ArrayList<Book> parseJsonOld(String jsonResponse) throws JSONException {

            ArrayList<Book> bookArrayList = new ArrayList<>();

            JSONObject mainJsonObject = new JSONObject(jsonResponse);

            JSONArray items = mainJsonObject.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {

                JSONObject jsonObject = items.getJSONObject(i);
                Book book = new Book();
                JSONObject volumeInfo = jsonObject.getJSONObject("volumeInfo");
                String title = volumeInfo.get("title").toString();
                String authors = volumeInfo.getJSONArray("authors").get(0).toString();
                String publisher = volumeInfo.get("publisher").toString();
                String publishedDate = volumeInfo.get("publishedDate").toString();
                String previewLink = volumeInfo.get("previewLink").toString();
                String infoLink = volumeInfo.get("infoLink").toString();
                String description = volumeInfo.get("description").toString();
                String thumbnail = volumeInfo.getJSONObject("imageLinks").get("thumbnail").toString();

                book.setTitle(title);
                book.setAuthors(authors);
                book.setDescription(description);
                book.setInfoLink(infoLink);
                book.setPublishedDate(publishedDate);
                book.setPreviewLink(previewLink);
                book.setThumbnail(thumbnail);
                book.setPublisher(publisher);

                bookArrayList.add(book);
            }


            return bookArrayList;
        }

    }


}