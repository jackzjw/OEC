package shangchuan.com.oec.model.http;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import rx.Observable;
import shangchuan.com.oec.model.bean.CharactersTokenBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.LoginInfoBean;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.model.bean.OaTypeBean;
import shangchuan.com.oec.model.bean.OrganizeInfoBean;
import shangchuan.com.oec.model.bean.WoClassBasicBean;
import shangchuan.com.oec.model.bean.WoListBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;

/**
 * Created by sg280 on 2017/3/6.
 */

public interface HttpService {
    //登录
    @FormUrlEncoded
    @POST("Home/Login")
    Observable <HttpDataResult<LoginInfoBean>> login(@Field("Tel") String tel, @Field("Pws") String pwd);
    //选择机构列表
    @FormUrlEncoded
    @POST("HOME/tenant_list")
    Observable<HttpDataResult<List<OrganizeInfoBean>>> getTenantList(@Field("UserId") int userId,@Field("token") String token);
     //进入机构
    @FormUrlEncoded
    @POST("HOME/tenant_choose")
    Observable<HttpDataResult<CharactersTokenBean>> enterTenant(@Field("UserId") int userId, @Field("TenantId") int tenantId, @Field("token") String token);
    //办公申请类型
    @FormUrlEncoded
    @POST("OA/oa_class")
    Observable<HttpDataResult<List<OaTypeBean>>> getApplyTypes(@Field("token") String token);
   //办公申请列表
    @FormUrlEncoded
    @POST("OA/oa_list")
    Observable<HttpDataResult<OaBasicItemBean<OaItemBean>>>  getOaResult(@Field("ClassId") int id, @Field("token") String token, @Field("Page") int page);
    //办公详情
    @FormUrlEncoded
    @POST("OA/oa_info")
    Observable<HttpDataResult<OaDetailsBean>>   getOaDetailsResult(@Field("Id") int id,@Field("token") String token);
    //创建工单
    @FormUrlEncoded
    @POST("WO/wo_save")
    Observable<HttpDataResult<WoSuccessBean>> submitWorkOrder(@Field("ClassIdB") int id,
                                                              @Field("OrderFlag") int flag,@Field("OrderTitle") String orderTitle,@Field("OrderContent") String content,@Field("Handlers") int[] handler,@Field("AttFileName") String[] fileName );
    // 修改工单
    @FormUrlEncoded
    @POST("WO/wo_edit_save")
    Observable<HttpDataResult<WoSuccessBean>> modifyWorkOrder(@Field("Id") int id, @Field("ClassIdB") int classId,
                                                              @Field("OrderFlag") int flag,@Field("OrderTitle") String orderTitle,@Field("OrderContent") String content,@Field("Handlers") int[] handler,@Field("AttFileName") String[] fileName );
    //删除工单
    @FormUrlEncoded
    @POST("WO/wo_del")
    Observable<HttpDataResult<WoSuccessBean>> delWorkOrder(@Field("Id") int id);
    //保存处理结果
    @FormUrlEncoded
    @POST("WO/wo_deal")
    Observable<HttpDataResult<WoSuccessBean>> dealWorkOrder(@Field("ProcessResult") int result ,@Field("OrderId") int orderId,@Field("Remark") String remark,@Field("Id") int id);
    //获取工单类型
    @FormUrlEncoded
    @POST("WO/wo_class")
    Observable<HttpDataResult<WoClassBasicBean>> getWoType(@Field("token") String token);
    //获取工单列表
    @FormUrlEncoded
    @POST("WO/wo_list")
    Observable<HttpDataResult<OaBasicItemBean<WoListBean>>>
    getWoList(@Field("ClassIdA") int aid,@Field("ClassIdB") int bid,
              @Field("OrderStatus") int ostatus,@Field("Status") int status,@Field("Page") int page,@Field("token") String token);





}
