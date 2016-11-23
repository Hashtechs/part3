package hashtech.com.booklisting.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hashtech.com.booklisting.R;
import hashtech.com.booklisting.activities.DetailsActivity;
import hashtech.com.booklisting.models.Book;

/**
 * Created by MFQ on 6/25/16.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.LocationViewHolder> {

    Context mContext;
    ArrayList<Book> mBooks;

    public BooksAdapter(Context mContext, ArrayList<Book> mBooks) {
        this.mContext = mContext;
        this.mBooks = mBooks;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.books_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {

        holder.tvTitle.setText(mBooks.get(position).getTitle());

        Glide.with(mContext) // context
                .load(mBooks.get(position).getThumbnail()) // img url
                .crossFade()// animation
                .placeholder(R.drawable.content)// place holder for imgs
                .into(holder.bookImage);// image view to load in



    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    protected class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvAuthor;
        ImageView bookImage;

        public LocationViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            bookImage = (ImageView) itemView.findViewById(R.id.imageView);
            ImageView ivInfo = (ImageView) itemView.findViewById(R.id.ivInfo);
            ImageView ivPreview = (ImageView) itemView.findViewById(R.id.ivPreview);
            ImageView ivShare = (ImageView) itemView.findViewById(R.id.ivShare);

            // on Click on icon
            ivInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openBrowser(mBooks.get(getLayoutPosition()).getInfoLink());
                }
            });
            ivPreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openBrowser(mBooks.get(getLayoutPosition()).getPreviewLink());
                }
            });
            ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    share("Hey check out this great Book " + mBooks.get(getLayoutPosition()).getTitle());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("model",mBooks.get(getLayoutPosition()));
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });




        }
    }

    private void share(String msg) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,msg);
        sendIntent.setType("text/plain");
        mContext.startActivity(sendIntent);
    }


    private void openBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mContext.startActivity(browserIntent);
    }
}
