package hashtech.com.booklisting.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hashtech.com.booklisting.R;
import hashtech.com.booklisting.models.Book;

public class DetailsActivity extends AppCompatActivity {

    TextView tvAuther,
            tvpublishDate,
            tvTitle,
            tvDesc;
    ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // retrieve Serializable
        Book book = (Book) getIntent().getSerializableExtra("model");



        tvAuther = (TextView) findViewById(R.id.tvAuther);
        tvpublishDate = (TextView) findViewById(R.id.tvpublishDate);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        ivImg = (ImageView) findViewById(R.id.ivImg);


        tvAuther.setText(book.getAuthors());
        tvpublishDate.setText(book.getPublishedDate());
        tvTitle.setText(book.getTitle());
        tvDesc.setText(book.getDescription());


        Glide.with(this) // context
                .load(book.getThumbnail()) // img url
                .crossFade()// animation
                .placeholder(R.drawable.content)// place holder for imgs
                .into(ivImg);// image view to load in


    }
}
