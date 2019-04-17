package ru.rocket.kitchen.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ru.rocket.kitchen.R;
import ru.rocket.kitchen.activities.EventActivity;
import ru.rocket.kitchen.activities.MainActivity;
import ru.rocket.kitchen.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventViewHolder> {


    private List<Order> mEventList;

    public EventsListAdapter(List<Order> eventList) {
        mEventList = eventList;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        MyEventListener mEventListener;
        TextView eventTitle;
        TextView eventDescribe;
        TextView eventAuthor;
        ImageView eventImage;

        EventViewHolder(View itemView) {
            super(itemView);

            eventTitle = itemView.findViewById(R.id.itemEventTitle);
            eventDescribe = itemView.findViewById(R.id.itemEventDescribe);
            eventAuthor = itemView.findViewById(R.id.itemEventAuthor);
            mEventListener = new MyEventListener();
            eventImage = itemView.findViewById(R.id.itemEventImage);
            itemView.setOnClickListener(mEventListener);

        }

        class MyEventListener implements View.OnClickListener {

            String id;

            @Override
            public void onClick(View v) {

                Intent eventIntent = new Intent(v.getContext(), EventActivity.class);
                eventIntent.putExtra("ID", id);

                Context context = v.getContext();
                context.startActivity(eventIntent);

//toDO: added on Click
            }

        }

    }

    @NonNull
    @Override
    public EventsListAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);

        return new EventViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EventsListAdapter.EventViewHolder holder, int position) {

        holder.eventTitle.setText(mEventList.get(position).getName());
        holder.eventDescribe.setText(mEventList.get(position).getName());
        holder.eventAuthor.setText(mEventList.get(position).getName());
        holder.mEventListener.id = mEventList.get(position).getName();
        Bitmap bitmap = getRandomBitmap();
        if (bitmap != null)
            holder.eventImage.setImageBitmap(getRandomBitmap());

    }

    private Bitmap getRandomBitmap() {
        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_1));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_2));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_3));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_4));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_5));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_6));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_7));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_8));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_9));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_10));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_11));
        drawables.add(MainActivity.getResourseForDraw().getDrawable(R.drawable.food_12));
        int nextInt = new Random().nextInt(12);
        return drawableToBitmap(drawables.get(nextInt));
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

}