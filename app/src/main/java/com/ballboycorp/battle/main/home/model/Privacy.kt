package com.ballboycorp.battle.main.home.model

import com.ballboycorp.battle.R

/**
 * Created by musooff on 14/01/2019.
 */

enum class Privacy(val value: Int, val text: String, val desc: Int) {
    PUBLIC(0, "Кушод", R.string.privacy_private_desc),
    PRIVATE(1, "Пушида", R.string.privacy_private_desc),
    SECRET(2, "Махфи", R.string.privacy_secret_desc),
}