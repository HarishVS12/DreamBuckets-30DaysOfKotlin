package com.harish.dreambuckets.utilities

import android.app.Activity
import android.view.View
import android.view.Window
import androidx.annotation.NonNull
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

fun clearLightStatusBar(@NonNull view: View) {
    var flags = view.systemUiVisibility
    flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    view.systemUiVisibility = flags
}


fun sharedElementTransitionEnter(window: Window, activity:Activity) {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    activity.findViewById<View>(android.R.id.content).transitionName = "shared_element_container"
    activity.setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

    window.sharedElementEnterTransition = MaterialContainerTransform().apply {
        addTarget(android.R.id.content)
        duration = 250L
    }

    window.sharedElementReturnTransition = MaterialContainerTransform().apply {
        addTarget(android.R.id.content)
        duration = 250L
    }

    window.sharedElementExitTransition = MaterialContainerTransform().apply {
        addTarget(android.R.id.content)
        duration = 250L
    }

    window.sharedElementReenterTransition = MaterialContainerTransform().apply {
        addTarget(android.R.id.content)
        duration = 250L
    }
}



fun pickerInit(): MaterialDatePicker<Long> {
    val builder = MaterialDatePicker.Builder.datePicker()
    val picker = builder.build()
    return picker
}

fun sharedElementTransitionExit(window:Window,activity: Activity){
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS) //This enables the transition to be performed on this activity
    activity.setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementsUseOverlay = false
}
