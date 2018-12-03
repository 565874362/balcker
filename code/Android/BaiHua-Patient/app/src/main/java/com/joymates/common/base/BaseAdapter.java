package com.joymates.common.base;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
	protected Context mContext;
	protected LayoutInflater mInflater;
	protected List<T> mList = new ArrayList<>();
	protected String from;

	public BaseAdapter(Context context) {
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	public BaseAdapter(Context context, List<T> list) {
		this(context);
		if (list != null) {
			this.mList = list;
		}
	}

	public BaseAdapter(Context context, List<T> list, String from) {
		this(context);
		if (list != null) {
			this.mList = list;
		}
		if (from != "") {
			this.from = from;
		}
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		if (position > mList.size() - 1) {
			return null;
		}
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 获取列表数据
	 * 
	 * @return
	 */
	public List<T> getList() {
		return mList;
	}

	/**
	 * 重新设置数据
	 * 
	 * @param list
	 */
	public void resetData(List<T> list) {
		mList.clear();
		mList.addAll(list);
		notifyDataSetChanged();
	}

	/**
	 * 将数据追加到列表顶部
	 * 
	 * @param list
	 */
	public void appendToListTop(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(0, list);
		notifyDataSetChanged();
	}

	/**
	 * 将数据追加到列表的底部
	 * 
	 * @param list
	 */
	public void appendToListBottom(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(list);
		notifyDataSetChanged();
	}

}
