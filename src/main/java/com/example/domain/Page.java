package com.example.domain;

public class Page {
	
	/** 現在のページ */
	private Integer nowPage;
	/** 前のページ */
	private Integer prevPage;
	/** 次のページ */
	private Integer nextPage;
	
	public Integer getNowPage() {
		return nowPage;
	}
	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}
	public Integer getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	
	@Override
	public String toString() {
		return "Page [nowPage=" + nowPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}

}
