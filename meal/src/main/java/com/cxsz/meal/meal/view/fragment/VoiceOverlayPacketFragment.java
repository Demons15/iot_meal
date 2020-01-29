package com.cxsz.meal.meal.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.cxsz.meal.R;
import com.cxsz.meal.R2;
import com.cxsz.meal.meal.view.activity.MealDetailsActivity;
import com.cxsz.meal.meal.view.adapter.VoiceOverlayPacketRecycleAdapter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import common.base.BaseFragment;
import common.constant.KeyConstants;
import common.model.MealGoodsBean;
import common.model.ModuleHelper;

public class VoiceOverlayPacketFragment extends BaseFragment {
    @BindView(R2.id.voice_overlay_pack_list)
    RecyclerView voiceOverlayPackList;

    @Override
    protected int getLayoutId() {
        return R.layout.voice_overlay_pack_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        final List<MealGoodsBean.MealGoodsBodyBean> mealGoodsBodyBeanList = ModuleHelper.getInstance().getVoiceMealGoodsList();
        if (mealGoodsBodyBeanList != null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
            voiceOverlayPackList.setLayoutManager(gridLayoutManager);
            VoiceOverlayPacketRecycleAdapter voiceOverlayPacketRecycleAdapter = new VoiceOverlayPacketRecycleAdapter(getActivity(), mealGoodsBodyBeanList);
            voiceOverlayPackList.setAdapter(voiceOverlayPacketRecycleAdapter);
            voiceOverlayPacketRecycleAdapter.setItemClickListener(new VoiceOverlayPacketRecycleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                    ToastUtil.show(getActivity(), "您点击的是语音叠加包:" + mealGoodsBodyBeanList.get(position).getGoodsName());
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

    public static VoiceOverlayPacketFragment getInstance() {
        VoiceOverlayPacketFragment childInfoFragment = new VoiceOverlayPacketFragment();
        return childInfoFragment;
    }

}
