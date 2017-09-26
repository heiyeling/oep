package com.zr.dao;

import java.util.List;

import com.zr.model.Exam;

public interface ExamDao {
	/**
	 * 根据关键词实现对exam的分页查找
	 * @param key		关键词
	 * @param start		起始位置
	 * @param pageSize	该页记录行数
	 * @param examList	存exam的链表
	 * @return
	 */
	int getExamByKey(String key, int start, int pageSize,List<Exam> examList);

	/**
	 * 批量删除考试
	 * @param examIds	考试id数组
	 * @return			删除成功返回true
	 */
	boolean deleteByIds(int[] examIds);

	/**
	 * 新增一门考试记录
	 * @param exam	考试实体:主键自增,状态默认为false
	 * @return		插入成功返回true
	 */
	boolean insert(Exam exam);
	
}
