package ru.rocket.kitchen.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ru.rocket.kitchen.R;
import ru.rocket.kitchen.domain.Message;

import java.util.List;
import java.util.Random;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private static final int TYPE_AUTHOR = 0;
    private static final int TYPE_GUEST = 1;
    List<Message> mMessagesList;

    public MessageAdapter(List<Message> messagesList) {

        mMessagesList = messagesList;
    }

    @Override
    public int getItemViewType(int position) {

        return isAuthor(position) ? TYPE_AUTHOR : TYPE_GUEST;

    }

    private boolean isAuthor(int position) {
        return true;
//        return mMessagesList.get(position).getFrom().equals(EventActivity.sEventPreview.getAuthor());

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = new Random().nextBoolean() ?
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_author, parent, false) :
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_guest, parent, false);
/*        switch (viewType) {
            case TYPE_AUTHOR: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_author, parent, false);
                break;
            }
            default: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_guest, parent, false);
                break;
            }
        }*/
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
//        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!" + mMessagesList.get(position).getMessage() + "    "
//                + mMessagesList.get(position).getFrom() + "!!!" + (mMessagesList.get(position).getDate() == null ? "NOO" : "NORM"));
        holder.messageText.setText(mMessagesList.get(position).getMessage());
        holder.messageAuthor.setText(mMessagesList.get(position).getFrom());
        holder.messageDate.setText(DateFormat.format("dd-MM-yyyy (hh:mm:ss)", mMessagesList.get(position).getDate()));
//        User user = Commands.userByEmail(mMessagesList.get(position).getFrom());
//        holder.messageImage.setImageBitmap(getBitmap(user.getImage()));
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView messageAuthor;
        TextView messageDate;
        ImageView messageImage;

        public MessageViewHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.chat_message);
            messageAuthor = itemView.findViewById(R.id.chat_author);
            messageDate = itemView.findViewById(R.id.chat_date);
            messageImage = itemView.findViewById(R.id.chat_image);

        }

    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private Bitmap getBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);

    }

}
