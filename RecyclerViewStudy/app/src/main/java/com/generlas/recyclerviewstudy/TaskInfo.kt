package com.generlas.recyclerviewstudy

/**
 * description ： TODO:类的作用
 * date : 2025/3/6 11:12
 */
data class TaskInfo(val text: String, val TYPE: Int, val childList: MutableList<ChildInfo>, var isExpand: Boolean)
