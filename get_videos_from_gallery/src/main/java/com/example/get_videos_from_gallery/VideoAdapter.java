package com.example.get_videos_from_gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Activity activity;
    private List<Video> videoList;

    public VideoAdapter(Activity activity) {
        this.activity = activity;
    }

    public void SetVideo(List<Video> videos){
        this.videoList = videos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        // Init view item_video
        View view = layoutInflater. inflate(R.layout.item_video, parent, false);

        // Return VideoViewHolder
        VideoViewHolder videoViewHolder = new VideoViewHolder(view);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        // Get video
        Video video = videoList.get(position);
        if (video == null) return;

        // Set thumbnail cho imageView trong VideoViewHolder
        // Load video
        Glide.with(activity).load(video.getThumb()).into(holder.imageViewVideo);

        holder.relativeLayoutVideoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PlayVideo.class);
                intent.putExtra("video_url", video.getUrl());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(videoList != null){
            return videoList.size();
        }
        return 0;
    }

    // Tạo class VideoViewHolder
    public class VideoViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout relativeLayoutVideoItem;
        private ImageView imageViewVideo;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            InitView(itemView);
        }

        private void InitView(View view){
            imageViewVideo = (ImageView) view.findViewById(R.id.imageViewVideo);
            relativeLayoutVideoItem =(RelativeLayout) view.findViewById(R.id.recyclerViewVideo);
        }
    }
}
