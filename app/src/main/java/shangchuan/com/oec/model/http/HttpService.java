package shangchuan.com.oec.model.http;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import shangchuan.com.oec.model.bean.CharactersTokenBean;
import shangchuan.com.oec.model.bean.ClientDetailsBasicBean;
import shangchuan.com.oec.model.bean.ContactListBean;
import shangchuan.com.oec.model.bean.CustomerListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.LoginInfoBean;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.model.bean.OaTypeBean;
import shangchuan.com.oec.model.bean.OrgBasicBean;
import shangchuan.com.oec.model.bean.OrganizeInfoBean;
import shangchuan.com.oec.model.bean.UserInfoBean;
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
                                                              @Field("OrderFlag") int flag,@Field("OrderTitle") String orderTitle,@Field("OrderContent") String content,@Field("Handlers") int[] handler,@Field("AttFileName") String[] fileName,@Field("token") String token );
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

    //获取客户列表
    @FormUrlEncoded
    @POST("Customer/customer_list")
    Observable<HttpDataResult<OaBasicItemBean<CustomerListBean>>> getClientLst(@Field("liveType") String type,@Field("token") String token,@Field("Page") int page);

    //获取客户详情
    @FormUrlEncoded
    @POST("Customer/customer_info")
    Observable<HttpDataResult<ClientDetailsBasicBean>> getClientDetails(@Field("Id") int id,@Field("token") String token);
   //新增客户
    @FormUrlEncoded
    @POST("Customer/customer_save")
    Observable<HttpDataResult<WoSuccessBean>> addClient(@FieldMap HashMap<String,Object> map);
    //客户联系人详情
    @FormUrlEncoded
    @POST("Customer/customer_contact_info")
    Observable<HttpDataResult<CustomerListBean>> getClientDetails(@Field("Id") int id);
    //客户联系人列表
    @FormUrlEncoded
    @POST("Customer/customer_contact_list")
    Observable<HttpDataResult<List<ContactListBean>>> clientContactList(@Field("CustomerId") int id,@Field("token") String token);
    //新增客户联系人
    @FormUrlEncoded
    @POST("Customer/customer_contact_save")
    Observable<HttpDataResult<WoSuccessBean>> addContactSuccess(@FieldMap HashMap<String,Object> map);
    //删除联系人
    @FormUrlEncoded
    @POST("Customer/customer_contact_del")
    Observable<HttpDataResult<WoSuccessBean>> deleteContact(@Field("Id") int id,@Field("token") String token);
    //保存联系人修改结果
    @FormUrlEncoded
    @POST("Customer/customer_contact_edit_save")
    Observable<HttpDataResult<WoSuccessBean>> saveContactResult(@FieldMap HashMap<String,Object> map);
    //用户列表
    @FormUrlEncoded
    @POST("User/user_list")
    Observable<HttpDataResult<OaBasicItemBean<UserInfoBean>>> getUserList(@Field("Keyword") String keyword,@Field("Page") int page,@Field("token") String token);
     //工单处理人，工单审批人
    @FormUrlEncoded
    @POST("Common/user_list_bygroup")
    Observable<HttpDataResult<OrgBasicBean>> getGroupList(@Field("token") String token);
     //工单详情
  //  @FormUrlEncoded
   // @POST("WO/wo_info")
    //新增通用申请
    @FormUrlEncoded
    @POST("OA/oa_save")
    Observable<HttpDataResult<WoSuccessBean>> submitCommonApply(@FieldMap HashMap<String,Object> map,@Field("token") String token);
    //新增申请
    @FormUrlEncoded
    @POST("OA/oa_save")
    Observable<HttpDataResult<WoSuccessBean>> submitApply(@FieldMap HashMap<String,Object> map,@Field("token") String token);



}
