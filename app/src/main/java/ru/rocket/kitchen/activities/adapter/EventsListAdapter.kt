/*
package ru.rocket.kitchen.activities.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.rocket.kitchen.R

class EventsListAdapter(private val mEventList: List<Event>) :
    RecyclerView.Adapter<EventsListAdapter.EventViewHolder>() {

    class EventViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var mEventListener: MyEventListener
        internal var eventTitle: TextView
        internal var eventDescribe: TextView
        internal var eventAuthor: TextView
        internal var eventImage: ImageView

        init {

            eventTitle = itemView.findViewById(R.id.itemEventTitle)
            eventDescribe = itemView.findViewById(R.id.itemEventDescribe)
            eventAuthor = itemView.findViewById(R.id.itemEventAuthor)
            mEventListener = MyEventListener()
            eventImage = itemView.findViewById(R.id.itemEventImage)
            itemView.setOnClickListener(mEventListener)

        }

        internal inner class MyEventListener : View.OnClickListener {

            var id: String? = null

            override fun onClick(v: View) {

                if (Commands.checkUserInEvent(id, MainActivity.sPersonDate.getEmail())) {

                    val eventIntent = Intent(v.context, EventActivity::class.java)
                    eventIntent.putExtra("ID", id)

                    val context = v.context
                    context.startActivity(eventIntent)

                } else {

                    val event = Commands.findEventById(id)
                    MainActivity.sFirstPreviewEvent = EventPreview(

                        event.getID(),
                        event.getTitle(),
                        event.getDescribe(),
                        event.getAuthor(),
                        event.getImage(),
                        event.getKind(),
                        event.getTime(),
                        event.getPosition(),
                        event.getAddress(),
                        event.getDate()

                    )

                    MainActivity.changeFragment(FirstPreviewFragment())

                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsListAdapter.EventViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)

        return EventViewHolder(view)

    }

    override fun onBindViewHolder(holder: EventsListAdapter.EventViewHolder, position: Int) {

        holder.eventTitle.setText(mEventList[position].getTitle())
        holder.eventDescribe.setText(mEventList[position].getDescribe())
        holder.eventAuthor.setText(mEventList[position].getAuthor())
        holder.mEventListener.id = mEventList[position].getID()
        holder.eventImage.setImageBitmap(getBitmap(mEventList[position].getImage()))

    }

    private fun getBitmap(image: ByteArray): Bitmap {

        return BitmapFactory.decodeByteArray(image, 0, image.size)

    }

    override fun getItemCount(): Int {
        return mEventList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        super.onAttachedToRecyclerView(recyclerView)

    }

}*/
