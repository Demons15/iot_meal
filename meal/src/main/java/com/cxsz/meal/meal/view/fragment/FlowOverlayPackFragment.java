package com.cxsz.meal.meal.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cxsz.meal.R;
import com.cxsz.meal.R2;
import com.cxsz.meal.meal.view.activity.MealDetailsActivity;
import com.cxsz.meal.meal.view.adapter.FlowOverlayPacketRecycleAdapter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import common.base.BaseFragment;
import common.constant.KeyConstants;
import common.model.MealGoodsBean;
import common.model.ModuleHelper;

public class FlowOverlayPackFragment extends BaseFragment {
    @BindView(R2.id.flow_overlay_pack_list)
    RecyclerView flowOverlayPackList;

    @Override
    protected int getLayoutId() {
        return R.layout.flow_overlay_pack_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        final List<MealGoodsBean.MealGoodsBodyBean> mealGoodsBodyBeanList = ModuleHelper.getInstance().getFlowMealGoodsList();
        if (mealGoodsBodyBeanList != null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
            flowOverlayPackList.setLayoutManager(gridLayoutManager);
            FlowOverlayPacketRecycleAdapter flowOverlayPacketRecycleAdapter = new FlowOverlayPacketRecycleAdapter(getActivity(), mealGoodsBodyBeanList);
            flowOverlayPackList.setAdapter(flowOverlayPacketRecycleAdapter);
            flowOverlayPacketRecycleAdapter.setItemClickListener(new FlowOverlayPacketRecycleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                    ToastUtil.show(getActivity(), "您点击的是流量叠加包:" + mealGoodsBodyBeanList.get(position).getGoodsName());
                    Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
                    intent.putExtra(KeyConstants.PACKET_INFO, mealGoodsBodyBeanList.get(position));
                    startActivity(intent);
                    getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            });
        }
    }

    @Override
    protected void lazyFetchData() {

    }

    public static FlowOverlayPackFragment getInstance() {
        FlowOverlayPackFragment childInfoFragment = new FlowOverlayPackFragment();
        return childInfoFragment;
    }

}
