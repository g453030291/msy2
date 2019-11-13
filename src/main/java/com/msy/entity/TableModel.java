package com.msy.entity;


public class TableModel<T> {

	/** 页数 */
	private Integer page;

	/** 每页条数 */
	private Integer limit;

	/** 排序字段 */
	private String field;

	/** 排序方式asc,desc */
	private String order;

	/** 日期查询字段1 */
	private String query_date1;

	/** 日期查询字段2 */
	private String query_date2;

	private T data;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getQuery_date1() {
		return query_date1;
	}

	public void setQuery_date1(String query_date1) {
		this.query_date1 = query_date1;
	}

	public String getQuery_date2() {
		return query_date2;
	}

	public void setQuery_date2(String query_date2) {
		this.query_date2 = query_date2;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TableModel{" +
				"page=" + page +
				", limit=" + limit +
				", field='" + field + '\'' +
				", order='" + order + '\'' +
				", query_date1='" + query_date1 + '\'' +
				", query_date2='" + query_date2 + '\'' +
				", data=" + data +
				'}';
	}
}
