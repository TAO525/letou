package com.tao.utils.export;

import java.util.ArrayList;
import java.util.List;

public abstract class CSVExport<T, E> {

	/**
	 * 描述：获取头部信息<br/>
	 */
	public abstract String[] getHeaders();

	/**
	 * 描述：根据当前页和页大小获取内容<br/>
	 */
	public abstract List<T> getPageContents(E e, int start, int pagesize);

	/**
	 * 描述：总条数
	 */
	public abstract long getTotal(E e);

	public abstract Object getValue(int index, T t);

	/**
	 * 描述：获取内容
	 */
	public List<T> getContents(E e) {
		int pagesize = 5000;
		long total = getTotal(e);
		int page = (int) (total / pagesize);
		if (total % pagesize != 0) {
			page++;
		}

		List<T> datas = new ArrayList<T>();
		for (int i = 0; i < page; i++) {
			List<T> data = getPageContents(e, i * pagesize, pagesize);
			System.out.println("i" + i + "******" + data.size());
			datas.addAll(data);

		}
		return datas;
	}

	public String createFile(E e) {
		String[] headers = getHeaders();

		List<T> contents = getContents(e);
		StringBuffer str = new StringBuffer();
		int len = headers.length;
		// 添加头部信息
		for (int i = 0; i < len; i++) {
			str.append(headers[i]);
			if (i != (len - 1)) {
				str.append(",");
			} else {
				str.append("\r\n");
			}
		}
		// 添加内容信息
		for (T t : contents) {
			for (int i = 0; i < len; i++) {
				String value = String.valueOf(getValue(i, t));
				value = filterValue(value);
				str.append(value);
				if (i != (len - 1)) {
					str.append(",");
				} else {
					str.append("\r\n");
				}
			}
		}
		return str.toString();
	}

	public String filterValue(String value) {
		if (value == null || "null".equals(value)) {
			return "";
		}
		return value.replace(",", "，").replaceAll("\r", "").replaceAll("\n", "");

	}
}
