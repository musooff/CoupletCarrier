package com.ballboycorp.battle.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ballboycorp.battle.R
import com.ballboycorp.battle.common.base.BaseFragment
import com.ballboycorp.battle.main.home.adapters.HomeAuthorsAdapter
import com.ballboycorp.battle.main.home.adapters.HomeFeaturedAdapter
import com.ballboycorp.battle.main.home.adapters.HomeTopUsersAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by musooff on 12/01/2019.
 */

class HomeFragment : BaseFragment() {


    private lateinit var featuredAdapter: HomeFeaturedAdapter
    private lateinit var authorsAdapted: HomeAuthorsAdapter
    private lateinit var topUsersAdapter: HomeTopUsersAdapter
    private val viewModel by lazy {
        ViewModelProviders
                .of(this)
                .get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(getString(R.string.title_home))

        featuredAdapter = HomeFeaturedAdapter()
        battle_featured_tl.setupWithViewPager(battle_featured_vp)
        battle_featured_vp.adapter = featuredAdapter

        authors_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        authorsAdapted = HomeAuthorsAdapter()
        authors_rv.adapter = authorsAdapted


        top_users_rv.layoutManager = LinearLayoutManager(context)
        topUsersAdapter = HomeTopUsersAdapter()
        top_users_rv.adapter = topUsersAdapter


        viewModel.featuredBattles.observe(this, Observer {
            featuredAdapter.submitList(it)
        })

        viewModel.featuredCouplet.observe(this, Observer {
            couplet_line_1.text = it.line1
            couplet_line_2.text = it.line2
            couplet_author.text = it.author
        })

        viewModel.topUsers.observe(this, Observer {
            topUsersAdapter.submitList(it)
        })

        viewModel.authors.observe(this, Observer {
            authorsAdapted.submitList(it)
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFeaturedBattles()
        viewModel.getFeaturedCouplet()
        viewModel.getTopUsers()
        viewModel.getAuthors()
    }
}