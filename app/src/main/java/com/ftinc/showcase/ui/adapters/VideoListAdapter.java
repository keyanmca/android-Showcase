package com.ftinc.showcase.ui.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftinc.showcase.R;
import com.ftinc.showcase.data.model.Video;
import com.ftinc.showcase.utils.CircleTransform;

import com.ftinc.fontloader.FontLoader;
import com.ftinc.fontloader.Types;
import com.r0adkll.deadskunk.adapters.BetterListAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Project: VideoLooperProject
 * Package: com.ftapps.kiosk.adapters
 * Created by drew.heavner on 10/3/14.
 */
public class VideoListAdapter extends BetterListAdapter<Video> {

    /**
     * Constructor
     */
    public VideoListAdapter(Context context, List<Video> objects) {
        super(context, R.layout.layout_video_item, objects);
    }


    @Override
    public ViewHolder createHolder(View view) {
        return new VideoViewHolder(view);
    }

    @Override
    public void bindHolder(ViewHolder viewHolder, int i, Video video) {
        VideoViewHolder vvh = (VideoViewHolder) viewHolder;
        vvh.title.setText(video.name);

        File file = new File(video.file);
        String parent = file.getParent();
        if(parent != null){
            String desc = parent.replace("/storage/emulated/0", "/sdcard");
            vvh.description.setText(desc);
        }else{
            String desc = video.file.replace("/storage/emulated/0", "/sdcard");
            vvh.description.setText(desc);
        }

        // Load the thumbnail
        if(video.thumbnail != null && !video.thumbnail.isEmpty()){
            vvh.thumbnail.setVisibility(View.VISIBLE);

            File thumb = new File(video.thumbnail);
            Picasso.with(getContext())
                    .load(thumb)
                    .transform(new CircleTransform())
                    .into(vvh.thumbnail);

        }else{
            vvh.thumbnail.setVisibility(View.GONE);
        }

    }


    static class VideoViewHolder extends ViewHolder {

        @InjectView(R.id.thumbnail)     ImageView thumbnail;
        @InjectView(R.id.title)         TextView title;
        @InjectView(R.id.description)   TextView description;

        /**
         * Butterknife injector constructor
         *
         * @param view the view to inject
         */
        public VideoViewHolder(View view) {
            ButterKnife.inject(this, view);
            FontLoader.applyTypeface(title, Types.ROBOTO_REGULAR);
            FontLoader.applyTypeface(description, Types.ROBOTO_REGULAR);
        }

    }

}
