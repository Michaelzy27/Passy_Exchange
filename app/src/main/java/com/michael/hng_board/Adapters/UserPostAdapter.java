package com.michael.hng_board.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michael.hng_board.Models.NotificationModel;
import com.michael.hng_board.Models.UserPostModel;
import com.michael.hng_board.R;
import com.michael.hng_board.Utils.Helper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.ViewHolder>{
    private Context context;
    private List<UserPostModel> userPostList;
    private Helper helper = new Helper();

    public UserPostAdapter(Context context, List<UserPostModel> userPostList) {
        this.context = context;
        this.userPostList = userPostList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name, title, description, time;
        public ImageView circleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_theme);
            description = itemView.findViewById(R.id.post_description);
            time = itemView.findViewById(R.id.post_time);
            name = itemView.findViewById(R.id.post_profile_name);
            circleImage = itemView.findViewById(R.id.post_profile_image);
        }
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_post_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserPostModel postModel = userPostList.get(position);
        holder.name.setText(postModel.getPostName());
        holder.time.setText(postModel.getPostTime());
        holder.title.setText(postModel.getPostTitle());
        holder.description.setText(postModel.getPostDescription());
        if(postModel.getPostImage().equals("default")){
            holder.circleImage.setImageResource(R.drawable.user_post_icon_foreground);
        }else{
            // TODO load image from image link using gide
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return userPostList.size();
    }
}
