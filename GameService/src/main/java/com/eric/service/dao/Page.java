package com.eric.service.dao;

import java.util.List;

public class Page<T>{

	private List<T> list;
	private int page;
	private int size;
	private int totalCount;
	private int totalPage;
	
	public Page(){}
	
	public Page(List<T> list,int page,int size,int totalCount){
		this.list = list;
		this.page = page;
		this.size = size;
		this.totalCount = totalCount;
		if(totalCount%size==0){
			this.totalPage = totalCount/size;
		}else{
			this.totalPage = totalCount/size+1;
		}
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
