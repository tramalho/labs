package com.tramalho.labs.view

import android.app.Activity
import android.widget.Toast


fun Activity.showToastError(rMessageId: Int) {
    android.widget.Toast.makeText(this, getText(rMessageId), Toast.LENGTH_LONG).show()
}