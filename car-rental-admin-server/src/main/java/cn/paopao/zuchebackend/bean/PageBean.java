package cn.paopao.zuchebackend.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分页信息封装类
 */
@Component
public class PageBean<T> {
	
	private int pageNum = 1;//当前页码
	
	private int pageSize = 10;//每页显示数量，默认十条
	
	private long total = 0;//总数量
	
	private long totalPage=0;//总页数

	private int showPageSize = 5;//显示在页面可快速跳转的页码个数
	
	private List<T> content;

	private List<Integer> currentShowPage = new ArrayList<Integer>();

	public List<Integer> getCurrentShowPage() {
		//首先当前页往前显示currentShowPage页
		for(int i = pageNum - 1;i > 0; i--){
			currentShowPage.add(i);
			if(i <= (pageNum - showPageSize)){
				break;
			}
		}
		//接下来当前页往后显示currentShowPage页
		for(int i = pageNum;i <= totalPage; i++){
			currentShowPage.add(i);
			if(i >= totalPage){
				break;
			}
			if(i >= (showPageSize + pageNum)){
				break;
			}
		}
		Collections.sort(currentShowPage);
		return currentShowPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getShowPageSize() {
		return showPageSize;
	}

	public void setShowPageSize(int showPageSize) {
		this.showPageSize = showPageSize;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public void setCurrentShowPage(List<Integer> currentShowPage) {
		this.currentShowPage = currentShowPage;
	}

	@Override
	public String toString() {
		return "PageBean{" +
				"pageNum=" + pageNum +
				", pageSize=" + pageSize +
				", total=" + total +
				", totalPage=" + totalPage +
				", showPageSize=" + showPageSize +
				", content=" + content +
				", currentShowPage=" + currentShowPage +
				'}';
	}
}
