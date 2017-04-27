package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.UserInfoBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.UserListContract;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.HanziToPinyin;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.PinyinComparator;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/12.
 */

public class UserListPresent extends RxPresent<UserListContract.View> implements UserListContract.Present {
   private RetrofitHelper mHelper;
    private int currentPage=1;
    private List<UserInfoBean> mTotalList;
    @Inject
    public UserListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }




    @Override
    public void getUserList() {
        currentPage=1;
        Subscription subscription = mHelper.getApiSevice().getUserList("", currentPage++, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<UserInfoBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<UserInfoBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<UserInfoBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<UserInfoBean> bean) {
                           LogUtil.i(bean.getItems().toString());
                              mTotalList=setFirstLetter(bean.getItems());
                              mView.showContent(mTotalList);
                    }
                });
           add(subscription);
    }
   private List<UserInfoBean> setFirstLetter(List<UserInfoBean> bean){
       for(UserInfoBean item:bean){
           if(!CommonUtil.isNull(item.getUserNickName().toString().trim())) {
               ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(item.getUserNickName());
               item.setSortLetter(tokens.get(0).target.substring(0, 1).toUpperCase());
           }
       }
       Collections.sort(bean,new PinyinComparator());
       return bean;
   }
    @Override
    public void getMoreUser() {
        Subscription subscription = mHelper.getApiSevice().getUserList("", currentPage++,SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<UserInfoBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<UserInfoBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<UserInfoBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<UserInfoBean> bean) {
                        mTotalList.addAll(setFirstLetter(bean.getItems()));
                        mView.showMoreContent(mTotalList,mTotalList.size(),mTotalList.size()+bean.getSize());
                    }
                });
        add(subscription);
    }
}
