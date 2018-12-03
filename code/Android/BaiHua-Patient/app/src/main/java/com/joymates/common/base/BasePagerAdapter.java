package com.joymates.common.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePagerAdapter<T> extends PagerAdapter {
	protected Context mContext;
	protected LayoutInflater mInflater;
	protected List<T> mList = new ArrayList<T>();
	protected List<View> mViewList = new ArrayList<View>();
	
	public BasePagerAdapter(Context context){
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
	}
	
	/**
	 * 设置数据
	 * @param list 加载的数据
	 * @param resId 加载的布局ID
	 */
	public void setData(List<T> list, int resId){
		mList.clear();
		mViewList.clear();
		mList .addAll(list);
		for (int i = 0; i < mList.size(); i++) {
			View lView = mInflater.inflate(resId, null);
			mViewList.add(lView);
		}
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}
	
	public List<T> getList(){
		return mList;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		if (position < mList.size()) {
			container.removeView(mViewList.get(position));
		}
	}
	
//	@Override
//	public int getItemPosition(Object object) {
//		// TODO Auto-generated method stub
//		return super.getItemPosition(object);
//	}
	
	

}
