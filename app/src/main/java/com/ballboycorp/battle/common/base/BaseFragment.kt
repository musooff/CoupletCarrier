package com.ballboycorp.battle.common.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by musooff on 12/01/2019.
 */

open class BaseFragment : Fragment() {

    fun setTitle(title: String){
        (activity as AppCompatActivity).title = title
    }
}