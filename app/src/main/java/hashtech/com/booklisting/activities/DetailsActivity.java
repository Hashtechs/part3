package hashtech.com.booklisting.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hashtech.com.booklisting.R;
import hashtech.com.booklisting.models.Book;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // retrieve Serializable
       Book book = (Book) getIntent().getSerializableExtra("Model");
    }
}
