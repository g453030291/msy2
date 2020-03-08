package com.msy.entity.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WxPageUtil<T> {

	/**
	 * 返回的数据
	 */
	private T data;
	/**
	 * 当前页数
	 */
	private Integer num;

	/**
	 * 每页条数
	 */
	private Integer size;

	/**
	 * 数据的总条数
	 */
	private Integer totalSize;
}
