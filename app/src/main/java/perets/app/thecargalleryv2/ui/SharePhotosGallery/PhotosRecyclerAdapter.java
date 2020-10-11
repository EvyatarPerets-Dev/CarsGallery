package perets.app.thecargalleryv2.ui.SharePhotosGallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import perets.app.thecargalleryv2.R;

public class PhotosRecyclerAdapter extends RecyclerView.Adapter<PhotosRecyclerAdapter.MyRecyclerViewHolder> {
    private final static int FADE_DURATION = 1000;
    List<String> images;


    public PhotosRecyclerAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new MyRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        setScaleAnimation(holder.itemView);
        Picasso.get().load(images.get(position)).centerCrop().resize(1000,700).placeholder(R.drawable.loading).error(R.drawable.ic_error).into(holder.galleryPhotos);

    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView galleryPhotos;

        public MyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryPhotos = itemView.findViewById(R.id.galleryPhotos);


        }

    }
}
