package com.generlas.tasklistdemo

/**
 * description ： TODO:类的作用
 * date : 2025/3/6 11:12
 */
/**
 * text: 一级菜单内容
 * TYPE: 类型为一级菜单还是二级菜单
 * childList: 子任务列表
 * isExpand: 是否为展开状态
 */
data class TaskInfo(val text: String, val TYPE: Int, val childList: MutableList<ChildInfo>, var isExpand: Boolean)
