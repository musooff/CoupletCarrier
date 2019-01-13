package com.ballboycorp.battle.main.home.newcoupletcarrier.chooseFriends

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ballboycorp.battle.R
import com.ballboycorp.battle.common.base.BaseActivity
import com.ballboycorp.battle.common.preference.AppPreference
import com.ballboycorp.battle.friendlist.FriendListViewModel
import kotlinx.android.synthetic.main.activity_choose_friends.*

/**
 * Created by musooff on 14/01/2019.
 */

class ChooseFriendsActivity  : BaseActivity(){

    companion object {
        const val ACTIVITY_RESULT = 13
        const val SELECTED_LIST = "selectedList"

        fun newIntent(activity: AppCompatActivity) {
            val intent = Intent(activity, ChooseFriendsActivity::class.java)
            activity.startActivityForResult(intent, ACTIVITY_RESULT)
        }
    }

    private val viewModel by lazy {
        ViewModelProviders
                .of(this)
                .get(FriendListViewModel::class.java)
    }

    private lateinit var adapter: ChooseFriendsAdapter

    private lateinit var appPreff: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_friends)


        appPreff = AppPreference.getInstance(this)

        val layoutManager = LinearLayoutManager(this)
        friendlist_rv.layoutManager = layoutManager

        adapter = ChooseFriendsAdapter()
        friendlist_rv.adapter = adapter

        viewModel.friedList.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFriendIds(appPreff.getUserId())
    }

    fun selectFriends(view: View){
        intent.putStringArrayListExtra(SELECTED_LIST, adapter.chosenFriends)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}