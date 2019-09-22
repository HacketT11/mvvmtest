package net.test.cloudmade.utils

import android.text.Editable
import android.text.TextWatcher

interface StubTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
}