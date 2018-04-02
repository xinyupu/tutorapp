package pxy.com.tutor.ui.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pxy.com.tutor.R;
import pxy.com.tutor.globle.Env;
import pxy.com.tutor.socketservice.ChatContainer;
import pxy.com.tutor.ui.utils.GlideCircleTransform;

/**
 * Created by pxy on 2018/4/1.
 */

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter {


    private Activity activity;
    private List<ChatContainer.Msg> msgs;

    private static final int TYPE_CUSTOME = 0;
    private static final int TYPE_MYSELF = 1;

    public ChatRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == TYPE_CUSTOME) {
            View view = LayoutInflater.from(activity).inflate(R.layout.tiem_chat_left, parent, false);
            holder = new CustomHolder(view);
        } else {
            View view = LayoutInflater.from(activity).inflate(R.layout.tiem_chat_rigt, parent, false);
            holder = new MySelfHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CustomHolder) {
            String headUrl = msgs.get(position).headUrl;
            if (TextUtils.isEmpty(headUrl)) {
                Resources res = activity.getResources();
                Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.default_head);
                ((CustomHolder) holder).head.setImageBitmap(bmp);
            } else {
                Glide.with(activity).load(headUrl).transform(new GlideCircleTransform(activity)).into(((CustomHolder) holder).head);
            }
            ((CustomHolder) holder).tvMsg.setText(msgs.get(position).msg);

        } else if (holder instanceof MySelfHolder) {
            String headUrl = msgs.get(position).headUrl;
            if (TextUtils.isEmpty(headUrl)) {
                Resources res = activity.getResources();
                Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.default_head);
                ((MySelfHolder) holder).head.setImageBitmap(bmp);
            } else {
                Glide.with(activity).load(headUrl).transform(new GlideCircleTransform(activity)).into(((MySelfHolder) holder).head);
            }
            ((MySelfHolder) holder).tvMsg.setText(msgs.get(position).msg);
        }
    }

    @Override
    public int getItemCount() {
        return this.msgs == null ? 0 : this.msgs.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (this.msgs != null) {
            ChatContainer.Msg msg = this.msgs.get(position);
            if (!msg.no.equals(Env.getAppContext().getNo())) {
                return TYPE_CUSTOME;
            } else {
                return TYPE_MYSELF;
            }
        }
        return TYPE_CUSTOME;
    }

    public void setMsgs(List<ChatContainer.Msg> msgs) {
        this.msgs = msgs;
    }

    public List<ChatContainer.Msg> getMsgs() {
        return msgs;
    }

    public class CustomHolder extends RecyclerView.ViewHolder {
        public TextView tvMsg;
        public ImageView head;
        public CustomHolder(View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.img_head);
            tvMsg = itemView.findViewById(R.id.msg);
        }
    }

    public class MySelfHolder extends RecyclerView.ViewHolder {
        public TextView tvMsg;
        public ImageView head;

        public MySelfHolder(View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.img_head);
            tvMsg = itemView.findViewById(R.id.msg);
        }
    }


}
