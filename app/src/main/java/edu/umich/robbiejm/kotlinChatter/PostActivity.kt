package edu.umich.robbiejm.kotlinChatter

import android.icu.text.Collator.ReorderCodes.FIRST
import android.icu.text.Collator.ReorderCodes.NONE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import edu.umich.robbiejm.kotlinChatter.ChattStore.postChatt
import edu.umich.robbiejm.kotlinChatter.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {
    private lateinit var view: ActivityPostBinding
    private var enableSend = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = ActivityPostBinding.inflate(layoutInflater)
        setContentView(view.root)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.apply {
            add(NONE, FIRST, NONE, getString(R.string.send))
            getItem(0).setIcon(android.R.drawable.ic_menu_send).setEnabled(enableSend)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == FIRST) {
            enableSend = false
            invalidateOptionsMenu()
            submitChatt()
        }
        return super.onOptionsItemSelected(item)
    }

    fun submitChatt() {
        val chatt = Chatt(username = view.usernameTextView.text.toString(),
            message = view.messageTextView.text.toString())

        postChatt(applicationContext, chatt)
        finish()
    }
}