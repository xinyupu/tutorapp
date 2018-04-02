package pxy.com.tutor.ui.adapter;

import android.app.Activity;
import android.content.Intent;
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
import com.pxy.pangjiao.databus.DataBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pxy.com.tutor.R;
import pxy.com.tutor.socketservice.ChatContainer;
import pxy.com.tutor.ui.activity.ChatActivity;
import pxy.com.tutor.ui.utils.GlideCircleTransform;
import pxy.com.tutor.ui.viewmodel.ChatInfoData;

/**
 * Created by pxy on 2018/4/1.
 */

public class HomeChatRecyclerViewAdapter extends RecyclerView.Adapter {

    private Activity activity;

    private Map<String, ChatContainer> container;
    private List<ChatContainer> chatContainers;

    public HomeChatRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
        chatContainers = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_home_chat, parent, false);
        return new HomeChatRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeChatRecyclerViewHolder) {
            ((HomeChatRecyclerViewHolder) holder).tvName.setText(chatContainers.get(position).getName());
            String headUrl = chatContainers.get(position).getHeadUrl();
            if (TextUtils.isEmpty(headUrl)) {
                Resources res = activity.getResources();
                Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.default_head);
                ((HomeChatRecyclerViewHolder) holder).imgHead.setImageBitmap(bmp);
            } else {
                Glide.with(activity).load(headUrl).transform(new GlideCircleTransform(activity)).into(((HomeChatRecyclerViewHolder) holder).imgHead);
            }
            SimpleDateFormat format=new SimpleDateFormat("HH:mm");
            String format1 = format.format(new Date());
            ((HomeChatRecyclerViewHolder) holder).time.setText(format1);
            ((HomeChatRecyclerViewHolder) holder).tvMsg.setText(chatContainers.get(position).getReceivedMsgs().get(0).msg);
            ((HomeChatRecyclerViewHolder) holder).itemView.setOnClickListener(item_Click);
            ((HomeChatRecyclerViewHolder) holder).itemView.setTag(chatContainers.get(position));
        }
    }


    private View.OnClickListener item_Click=view -> {
        ChatContainer chatContainer= (ChatContainer) view.getTag();
        ChatInfoData data = new ChatInfoData(chatContainer.getName(), chatContainer.getNo(), chatContainer.isTutor(),chatContainer.getReceivedMsgs());
        DataBus.getDefault().postStick(data);
        activity.startActivity(new Intent(activity, ChatActivity.class));
    };



    @Override
    public int getItemCount() {
        if (this.container == null) {
            return 0;
        } else {
            chatContainers.clear();
            for (String key : this.container.keySet()) {
                chatContainers.add(container.get(key));
            }
        }
        return this.chatContainers.size();
    }

    public void setContainer(Map<String, ChatContainer> container) {
        this.container = container;
    }


    public static class HomeChatRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;

        public ImageView imgHead;
        public TextView tvMsg;

        public TextView time;
        public View itemView;

        public HomeChatRecyclerViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            tvName = itemView.findViewById(R.id.name);
            imgHead = itemView.findViewById(R.id.user_head);
            tvMsg = itemView.findViewById(R.id.msg);
            time=itemView.findViewById(R.id.time);
        }
    }
}
