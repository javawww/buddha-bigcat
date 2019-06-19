package com.buddha.component.base.pubdef;

public enum WhereNullToDo {

	/// <summary>
    /// 不做处理
    /// </summary>
    None,
    /// <summary>
    /// 判断是否空值
    /// </summary>
    IsNull,
    /// <summary>
    /// 判断是否空值
    /// </summary>
    IsNotNull,
    /// <summary>
    /// 否定条件，查询将无值.
    /// </summary>
    SayNo
}
