package ru.rocket.kitchen.fragments.main


import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.rocket.kitchen.R
import ru.rocket.kitchen.adapters.EventsListAdapter
import ru.rocket.kitchen.domain.Order
import java.lang.Thread.sleep

class HomeFragment : Fragment() {

    // Список события
    private var mOrderList: List<Order>? = null
    // Адаптер для вывода списка событий
    private var mRecyclerView: RecyclerView? = null
    private var mEventsListAdapter: EventsListAdapter? = null

    // Диалог во время выполнения авторизации
    private var mProgressDialog: ProgressDialog? = null
    private var mMyTask: AsyncTask<*, *, *>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_home, container, false)
        mProgressDialog = ProgressDialog(activity)

        mProgressDialog!!.setTitle("Загрузка заказов")
        mProgressDialog!!.setMessage(getString(R.string.progressDialogWait))
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        mProgressDialog!!.isIndeterminate = false
        mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        mRecyclerView = v.findViewById(R.id.recyclerHome)

        mRecyclerView!!.setHasFixedSize(true)

        val llm = LinearLayoutManager(activity)
        mRecyclerView!!.layoutManager = llm

        // Загрузка событий из базы данных
        initializeData()
        return v

    }

    // Загрузка событий из баззы данных
    private fun initializeData() {

        mMyTask = DownloadTask()
            .execute()

    }

    private inner class DownloadTask : AsyncTask<Void?, Int, Void?>() {

        // Before the tasks execution
        override fun onPreExecute() {

            // Display the progress dialog on async task start

            mProgressDialog!!.show()

        }

        // Do the task in background/non UI thread
        override fun doInBackground(vararg tasks: Void?): Void? {

            //todo: added rest to back for load all orders
            mOrderList = listOf(
                order().copy(
                    name = "Salat"
                ),
                order().copy(
                    name = "Wine"
                ),
                order().copy(
                    name = "Drink"
                ),
                order().copy(
                    name = "Meat"
                )
            ).toMutableList()

            return null

        }

        private fun order(): Order {
            return Order(
                name = "Order"
            )
        }

        fun onProgressUpdate(vararg progress: Int) {
            mProgressDialog!!.progress = progress[0]
        }

        override fun onPostExecute(result: Void?) {
//            Collections.reverse(mOrderList)\
            sleep(1 * 1000)
            mProgressDialog!!.dismiss()
            mEventsListAdapter = EventsListAdapter(mOrderList)
            mRecyclerView!!.adapter = mEventsListAdapter


        }

    }

}
