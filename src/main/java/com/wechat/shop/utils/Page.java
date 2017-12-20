package com.wechat.shop.utils;

import java.util.List;
import java.util.Map;

import com.wechat.shop.common.Constants;

public class Page {
	// 当前页
	private Integer pageNum = 1;
	// 查询开始条数
	private Integer pageBeginNum;
	// 查询条数
	private Integer pageSize = Constants.PAGESIZE_10;
	// 数据总条数
	private Integer pageTotalCount;
	// 数据总页数
	private Integer pageTotalNum;
	// 数据集合
	private List<Map<String, Object>> page;

	public Page() {
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageBeginNum() {
		setPageBeginNum((getPageNum() - 1) * getPageSize());
		return pageBeginNum;
	}

	public void setPageBeginNum(Integer pageBeginNum) {
		this.pageBeginNum = pageBeginNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(Integer pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public Integer getPageTotalNum() {
		if (getPageTotalCount() != null) {
			Integer totalNum = getPageTotalCount() % getPageSize();
			totalNum = totalNum > 0 ? getPageTotalCount() / getPageSize() + 1 : getPageTotalCount() / getPageSize();
			setPageTotalNum(totalNum);
		}
		return pageTotalNum;
	}

	public void setPageTotalNum(Integer pageTotalNum) {
		this.pageTotalNum = pageTotalNum;
	}

	public List<Map<String, Object>> getPage() {
		return page;
	}

	public void setPage(List<Map<String, Object>> page) {
		this.page = page;
	}

}
