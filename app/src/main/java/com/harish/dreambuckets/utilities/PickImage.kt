package com.harish.dreambuckets.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class PickImage : ActivityResultContract<Int, Uri?>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        val intent = Intent()
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        intent.setAction("image/*")
        return Intent.createChooser(intent,"Pick a image")
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if(resultCode != Activity.RESULT_OK) return null
        else
            return intent?.data
    }


}