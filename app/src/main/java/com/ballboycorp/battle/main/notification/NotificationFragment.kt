package com.ballboycorp.battle.main.notification

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ballboycorp.battle.R
import com.ballboycorp.battle.common.base.BaseFragment
import com.ballboycorp.battle.common.preference.AppPreference
import kotlinx.android.synthetic.main.empty_list.*
import kotlinx.android.synthetic.main.fragment_notification.*

/**
 * Created by musooff on 13/01/2019.
 */

class NotificationFragment : BaseFragment(){

    private lateinit var appPreff: AppPreference
    private lateinit var adapter: NotificationAdapter

    private val viewModel by lazy {
        ViewModelProviders
                .of(this)
                .get(NotificationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(getString(R.string.title_notifications))


        appPreff = AppPreference.getInstance(view.context)


        val layoutManager = LinearLayoutManager(activity)
        notification_rv.layoutManager = layoutManager

        adapter = NotificationAdapter()
        notification_rv.adapter = adapter

        viewModel.notifications.observe(this, Observer {
            adapter.submitList(it)
            invalidateEmptyList(adapter.isEmpty())
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotifications(appPreff.getUserId())
    }

    private fun invalidateEmptyList(isEmpty: Boolean){
        if (isEmpty){
            empty.visibility = View.VISIBLE
            empty_text.text = getString(R.string.empty_notifications)
        }
        else{
            empty.visibility = View.GONE

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.notification, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}