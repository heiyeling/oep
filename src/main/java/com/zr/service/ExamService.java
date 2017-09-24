package com.zr.service;

import net.sf.json.JSONObject;

public interface ExamService {
	/**
	 * 根据关键词查询考试
	 * @param key		关键字
	 * @param currentPage	当前页码
	 * @param pageSize		每页行数
	 * @return	考试信息的json对象(total:总数,row:[(examName：考试名,examId:考试id,examTime:考试时间,examState：考试状态)])
	 */
	public JSONObject getExamByKey(String key,int currentPage,int pageSize);

	/**
	 * 根据id[]批量删除考试
	 * @param examIds
	 * @return	删除成功返回true
	 */
	public boolean deleteExamByIds(int[] examIds);

	/**
	 * 新增一门考试
	 * @param examName		考试名
	 * @param examStartTime	考试开始时间
	 * @param examEndTime	考试结束时间
	 * @param examTotal		考试总分
	 * @return		插入成功返回true
	 */
	public boolean insertExam(String examName, String examStartTime, String examEndTime, int examTotal);
}