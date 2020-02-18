package com.example.gorinih.klibscroll

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gorinih.klibscroll.аdapter.CreatorBodyAdapter
import com.example.gorinih.klibscroll.аdapter.CreatorHeadAdapter
import com.example.gorinih.klibscroll.interfaces.CreatorBodyItems
import com.example.gorinih.klibscroll.interfaces.CreatorHeadItems
import kotlinx.android.synthetic.main.layout_scrollable_list_edit.view.editScrollableListHead
import kotlinx.android.synthetic.main.layout_scrollable_list_edit.view.editScrollableListBody

class ScrollableList constructor(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    //дополнительные атрибуты компоненты
    private var separatorWidthHeadBody = resources.getDimension(R.dimen.dimens_8dp)  // ширина промежутка между Head и Body

    //RecyclerView отображающие Head и Body  инициализируются в setUpScrollableList()
    private lateinit var creatorScrollableListHead : RecyclerView
    private lateinit var creatorScrollableListBody : RecyclerView

    //адаптеры Head и Body инициализируются в методе setCreatorViewScrollableList
    private lateinit var creatorHeadAdapter: CreatorHeadAdapter
    private lateinit var creatorBodyAdapter: CreatorBodyAdapter

    //изначально при создании первые элементы Head и Body = 0
    private var previousSnapPositionHead: Int = 0
    private var previousSnapPositionBody: Int = 0

    /*прослушиватель движения в Head или Body компонента
    * если компоненты изменились то в зависимости в Head или в Body произоши изменения - меняем положение
    * элементов в другом соответственно
    */
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                val newPosition =
                    getPositionCurrentCell(recyclerView) //получаем текущий номер элемента
                when (recyclerView) { // в зависимости какой список листался изменяем другой
                    creatorScrollableListHead -> { // если изменили Head
//                        Log.d(
//                            "Snap",
//                            "HEAD предыдущая позиция $previousSnapPositionBody текущая $newPosition "
//                        )
                        if (newPosition != previousSnapPositionHead) { //если было изменение в Head то сдвигаем элементы в Body
                            previousSnapPositionHead = newPosition //изменяем номер элемента Head на текущий
                            val positionBody =
                                creatorHeadAdapter.getBodyPosition(newPosition) //получаем первый номер элемента Body для текущего элемента Head
                            if (positionBody != previousSnapPositionBody) { //двигаем Body если есть необходимость
                                previousSnapPositionBody = positionBody
                                creatorScrollableListBody.smoothScrollToPosition(positionBody)
                            }
                        }
                    }
                    creatorScrollableListBody -> { //если изменили Body все аналогично изменению в Head
                        if (newPosition != previousSnapPositionBody) {
                            previousSnapPositionBody = newPosition
                            val positionHead =
                                creatorBodyAdapter.getHeadPosition(newPosition)
                            if (positionHead != previousSnapPositionHead) {
                                previousSnapPositionHead = positionHead
                                creatorScrollableListHead.smoothScrollToPosition(positionHead)
                            }
                        }
                    }
                }
            }
        }
    }

    init { //инициализация модуля
       readAtributes(attrs) //считываем дополнительные свойства маржа между Head и Body
        if (isInEditMode) {
            setUpEditMode() //если в редакторе то визуализируем фиктивный компонент
        } else
            setUpScrollableList() //если запущен в рабочем режиме создаем компонент
    }

    private fun readAtributes(attrs : AttributeSet?) = attrs?.let {
           val typedArray : TypedArray = this.context.obtainStyledAttributes(attrs,R.styleable.ScrollableList)
        separatorWidthHeadBody = typedArray.getDimension(R.styleable.ScrollableList_separatorWidthHeadBody,separatorWidthHeadBody)
        typedArray.recycle()
        }

    //получение номера позиции текущего элемента в Body или Head
    private fun getPositionCurrentCell(recyclerView: RecyclerView): Int {
        return SnapHelperHorizon().getSnapPosition(recyclerView)
    }


    private fun setUpEditMode() { // визуализируем в редакторе
        View.inflate(context, R.layout.layout_scrollable_list_edit, this)
        val bodyItemHeight = resources.getDimension(R.dimen.dimens_48dp)
        val headItemHeight = resources.getDimension(R.dimen.dimens_60dp)
        val bodyItemWidth = resources.getDimension(R.dimen.dimens_93dp)
        val headItemWidth = resources.getDimension(R.dimen.dimens_132dp)

        for (i in 0..3) {
            val headCell = LayoutInflater.from(context).inflate(
                R.layout.head_cell_edit, editScrollableListHead, false
            )
            val bodyCell = LayoutInflater.from(context).inflate(
                R.layout.body_cell_edit, editScrollableListBody, false
            )
            val headMarginLayoutParams =
                MarginLayoutParams(headItemWidth.toInt(), headItemHeight.toInt())
            val bodyMarginLayoutParams =
                MarginLayoutParams(bodyItemWidth.toInt(), bodyItemHeight.toInt())
            bodyMarginLayoutParams.leftMargin = resources.getDimension(R.dimen.dimens_24dp).toInt()
            headMarginLayoutParams.leftMargin = resources.getDimension(R.dimen.dimens_32dp).toInt()

            editScrollableListHead.addView(headCell, headMarginLayoutParams)
            editScrollableListBody.addView(bodyCell, bodyMarginLayoutParams)
        }
    }


    private fun setUpScrollableList() { //создаем рабочий режим
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        orientation = VERTICAL

        val _creatorScrollableListHead = RecyclerView(this.context)
        val _creatorScrollableListBody = RecyclerView(this.context)

        val recyclerViewHeadLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        recyclerViewHeadLayoutParams.setMargins(0,0,0,separatorWidthHeadBody.toInt())

        _creatorScrollableListHead.layoutParams = recyclerViewHeadLayoutParams

        val recyclerViewBodyLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
                recyclerViewBodyLayoutParams.setMargins(8,separatorWidthHeadBody.toInt(),8,8)
        _creatorScrollableListBody.layoutParams = recyclerViewBodyLayoutParams

        creatorScrollableListBody = _creatorScrollableListBody
        creatorScrollableListHead = _creatorScrollableListHead

        removeAllViews()
        addView(creatorScrollableListHead)
        addView(creatorScrollableListBody)

        creatorScrollableListHead.removeOnScrollListener(scrollListener)
        creatorScrollableListBody.removeOnScrollListener(scrollListener)

    }

    // основной метод который вызывает клиент передавая соответствующие интерфейсы Head и Body
    fun setCreatorViewScrollableList(
        creatorHeadItems: CreatorHeadItems,
        creatorBodyItems: CreatorBodyItems
    ) {
        creatorHeadAdapter = CreatorHeadAdapter(creatorHeadItems)
        creatorBodyAdapter = CreatorBodyAdapter(creatorBodyItems)

        creatorScrollableListHead.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        creatorScrollableListBody.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        creatorScrollableListHead.adapter = creatorHeadAdapter
        creatorScrollableListBody.adapter = creatorBodyAdapter

        val positionSnapHelperHead = SnapHelperHorizon()
        val positionSnapHelperBody = SnapHelperHorizon()
        positionSnapHelperHead.attachToRecyclerView(creatorScrollableListHead)
        positionSnapHelperBody.attachToRecyclerView(creatorScrollableListBody)

        creatorScrollableListHead.addOnScrollListener(scrollListener)
        creatorScrollableListBody.addOnScrollListener(scrollListener)
    }
}