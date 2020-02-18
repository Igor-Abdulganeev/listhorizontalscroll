package com.example.gorinih.klibscroll

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class SnapHelperHorizon : LinearSnapHelper() {

    override fun calculateDistanceToFinalSnap(
        layoutManager: LayoutManager,
        targetView: View
    ): IntArray? {
        val listInt: IntArray = intArrayOf(0, 0)
        listInt[0] = getDistanceToStart(
            layoutManager,
            targetView,
            OrientationHelper.createHorizontalHelper(layoutManager)
        )
        return listInt
    }

    override fun findSnapView(layoutManager: LayoutManager?): View? {
        var childView: View? = null
        val helper = OrientationHelper.createHorizontalHelper(layoutManager)
        val childCount = layoutManager?.childCount ?: 0
        var distance = Integer.MAX_VALUE
        if (childCount == 0) return childView
        val xStart = helper.startAfterPadding
        var currentView : View?
        var startPosition : Int
        var startDistance : Int
        for (i in 0 until childCount) {
            currentView = layoutManager?.getChildAt(i)
            startPosition = helper.getDecoratedStart(currentView)
            startDistance = Math.abs(startPosition - xStart)
            if (startDistance < distance) {
                distance = startDistance
                childView = currentView
            }
        }
        return childView
    }

    private fun getDistanceToStart(
        layoutManager: LayoutManager,
        targetView: View,
        helper: OrientationHelper
    ): Int = when (layoutManager.clipToPadding) {
            true ->  helper.getDecoratedStart(targetView) - helper.startAfterPadding
            false ->  helper.getDecoratedStart(targetView)

    }

    fun getSnapPosition(recyclerView: RecyclerView) : Int{
        val layoutManager = recyclerView.layoutManager?: return RecyclerView.NO_POSITION
        val snapView = findSnapView(layoutManager)?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }


}
