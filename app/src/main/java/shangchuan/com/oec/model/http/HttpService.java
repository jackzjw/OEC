package shangchuan.com.oec.model.http;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import shangchuan.com.oec.model.bean.AttendanceListBean;
import shangchuan.com.oec.model.bean.CharactersTokenBean;
import shangchuan.com.oec.model.bean.ClassListBean;
import shangchuan.com.oec.model.bean.ClientDetailsBasicBean;
import shangchuan.com.oec.model.bean.CommentResultBean;
import shangchuan.com.oec.model.bean.ContactListBean;
import shangchuan.com.oec.model.bean.CustomerListBean;
import shangchuan.com.oec.model.bean.GroupBasicBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.LoginInfoBean;
import shangchuan.com.oec.model.bean.MyInfoBean;
import shangchuan.com.oec.model.bean.NewsClassifyBean;
import shangchuan.com.oec.model.bean.NewsDetailsBean;
import shangchuan.com.oec.model.bean.NewsListBean;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.model.bean.OaTypeBean;
import shangchuan.com.oec.model.bean.OrgBasicBean;
import shangchuan.com.oec.model.bean.OrganizeInfoBean;
import shangchuan.com.oec.model.bean.ProjectDocumentBean;
import shangchuan.com.oec.model.bean.ProjectListBean;
import shangchuan.com.oec.model.bean.ProjectProcessBlockList;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.ResultListBean;
import shangchuan.com.oec.model.bean.RoleListBean;
import shangchuan.com.oec.model.bean.TanentInfoBean;
import shangchuan.com.oec.model.bean.TaskDetailsBean;
import shangchuan.com.oec.model.bean.TaskListBean;
import shangchuan.com.oec.model.bean.TrendsListBean;
import shangchuan.com.oec.model.bean.UserInfoBean;
import shangchuan.com.oec.model.bean.WoClassBasicBean;
import shangchuan.com.oec.model.bean.WoDetailBean;
import shangchuan.com.oec.model.bean.WoListBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.bean.WorkReportDetailsBean;
import shangchuan.com.oec.model.bean.WorkReportListBean;

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
    Observable<HttpDataResult<CharactersTokenBean>> enterTenant(@Field("UserId") int userId, @Field("TenantId") int tenantId,@Field("CurrentLoginId")int loginId, @Field("token") String token);
    //办公申请类型
    @FormUrlEncoded
    @POST("OA/oa_class")
    Observable<HttpDataResult<List<OaTypeBean>>> getApplyTypes(@Field("token") String token);
   //办公申请列表
    @FormUrlEncoded
    @POST("OA/oa_list")
    Observable<HttpDataResult<OaBasicItemBean<OaItemBean>>>  getOaResult(@Field("ClassId") int id, @Field("token") String token, @Field("Page") int page);
    //办公申请关键字搜索
    @FormUrlEncoded
    @POST("OA/oa_list")
    Observable<HttpDataResult<OaBasicItemBean<OaItemBean>>>  getOaKeyWord(@Field("SearchKey") String keyword, @Field("token") String token, @Field("Page") int page);

 //办公详情
    @FormUrlEncoded
    @POST("OA/oa_info")
    Observable<HttpDataResult<OaDetailsBean>>   getOaDetailsResult(@Field("Id") int id,@Field("token") String token);
    //创建工单
    @FormUrlEncoded
    @POST("WO/wo_save")
    Observable<HttpDataResult<WoSuccessBean>> submitWorkOrder(@FieldMap HashMap<String,Object> map );
    // 修改工单
    @FormUrlEncoded
    @POST("WO/wo_edit_save")
    Observable<HttpDataResult<WoSuccessBean>> modifyWorkOrder(@Field("Id") int id, @Field("ClassIdB") int classId,
                                                              @Field("OrderFlag") int flag,@Field("OrderTitle") String orderTitle,@Field("OrderContent") String content,@Field("Handlers") int[] handler,@Field("AttFileName") String fileName );
    //删除工单
    @FormUrlEncoded
    @POST("WO/wo_del")
    Observable<HttpDataResult<WoSuccessBean>> delWorkOrder(@Field("Id") int id,@Field("token") String token);
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
    getWoList(@Field("ClassIdA") String aid,@Field("ClassIdB") String bid,
              @Field("OrderStatus") String ostatus,@Field("Status") String status,@Field("Page") int page,@Field("token") String token);
     //获取工单关键字列表
     @FormUrlEncoded
     @POST("WO/wo_list")
   Observable<HttpDataResult<OaBasicItemBean<WoListBean>>> getWoKeyWord(@Field("KeyWord") String keyword,@Field("Page") int page,@Field("token") String token);
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
    @FormUrlEncoded
    @POST("WO/wo_info")
    Observable<HttpDataResult<WoDetailBean>> getWoDetails(@Field("OrderId") int id,@Field("token") String token);
    //新增通用申请
    @FormUrlEncoded
    @POST("OA/oa_save")
    Observable<HttpDataResult<WoSuccessBean>> submitCommonApply(@FieldMap HashMap<String,Object> map,@Field("token") String token);
    //新增申请
    @FormUrlEncoded
    @POST("OA/oa_save")
    Observable<HttpDataResult<WoSuccessBean>> submitApply(@FieldMap HashMap<String,Object> map,@Field("token") String token);
     //新增周报、日报
     @FormUrlEncoded
     @POST("OA/report_save")
     Observable<HttpDataResult<WoSuccessBean>> addWorkReport(@FieldMap HashMap<String,Object> map,@Field("token") String token);
     //周报、日报详情
      @FormUrlEncoded
      @POST("OA/report_info")
      Observable<HttpDataResult<WorkReportDetailsBean>> getWorkReportDeatails(@Field("Id") int id,@Field("SelectType") int type,@Field("token") String token);
      //周报、日报列表
      @FormUrlEncoded
      @POST("OA/report_list")
      Observable<HttpDataResult<OaBasicItemBean<WorkReportListBean>>> getWorkReportList(@Field("SelectType") int type,@Field("Page") int page,@Field("token") String token);
      //获取周报、日报关键字列表
      @FormUrlEncoded
      @POST("OA/report_list")
      Observable<HttpDataResult<OaBasicItemBean<WorkReportListBean>>> getWRKeyWord(@Field("KeyWord") String keyword,@Field("Page") int page,@Field("token") String token);

       //工作报告删除
       @FormUrlEncoded
       @POST("OA/report_del")
       Observable<HttpDataResult<WoSuccessBean>> delWR(@Field("Id") int id,@Field("token") String token);
       //添加客户（含联系人）
      @FormUrlEncoded
      @POST("Customer/customer_save_all")
       Observable<HttpDataResult<WoSuccessBean>> addClientContact(@FieldMap HashMap<String,Object> hashMap, @Body RequestBody jsonbody,@Field("token") String token);
       //审批列表
       @FormUrlEncoded
       @POST("OA/approval_list")
       Observable<HttpDataResult<OaBasicItemBean<OaItemBean>>> getApproveList(@Field("ClassId") int classid,@Field("IsAudit") int isAudit,@Field("Page") int page,@Field("token" ) String token);
       //公告列表
       @FormUrlEncoded
       @POST("Index/news_list")
       Observable<HttpDataResult<OaBasicItemBean<NewsListBean>>>   getNewsList(@Field("Page") int page,@Field("Size") int size,@Field("token") String token);
       //最新动态列表
       @FormUrlEncoded
       @POST("Index/job_list")
       Observable<HttpDataResult<OaBasicItemBean<TrendsListBean>>> getTrendsList(@Field("JobType") String type,@Field("KeyWord") String keyword,@Field("Page") int page,@Field("Size") String size,@Field("token") String token);
       //工单处理结果
       @FormUrlEncoded
       @POST("WO/wo_deal")
       Observable<HttpDataResult<WoSuccessBean>> woDealResult(@Field("ProcessResult") int result,@Field("OrderId") int orderId,@Field("Remark") String remark,@Field("Id") String id,@Field("token") String token);
       //OA审核处理结果
       @FormUrlEncoded
       @POST("OA/oa_check")
       Observable<HttpDataResult<WoSuccessBean>> oaDealResult(@Field("OrderId") int orderId,@Field("ProcessResult") int resultId,@Field("Remark") String remark,@Field("ToUserId") String toUserId,@Field("token") String token);
       //工作报告审核处理结果
       @FormUrlEncoded
       @POST("OA/report_audit")
       Observable<HttpDataResult<WoSuccessBean>>  wrDealResult(@Field("OrderId") int orderId,@Field("ProcessResult") int resultId,@Field("Remark") String remark,@Field("ToUserId") String toUserId,@Field("token") String token);
       //考勤列表
       @FormUrlEncoded
       @POST("OA/attendance_list")
       Observable<HttpDataResult<OaBasicItemBean<AttendanceListBean>>> getAttendanceList(@Field("AttendanceDate") String date,@Field("Size") int size,@Field("token") String token);
      //公告类型
       @FormUrlEncoded
       @POST("index/news_class")
       Observable<HttpDataResult<List<NewsClassifyBean>>> getNewsClassify(@Field("token") String token);
       //公告分类列表
       @FormUrlEncoded
       @POST("index/news_list")
       Observable<HttpDataResult<OaBasicItemBean<NewsListBean>>>   getNewsLists(@Field("ClassId") String id,@Field("ReadStatus") int status,@Field("Page") int page,@Field("KeyWord") String keyword,@Field("token") String token);
       //公告详情
       @FormUrlEncoded
       @POST("index/news_info")
       Observable<HttpDataResult<NewsDetailsBean>>  getNewsDetails(@Field("NewsId") int id,@Field("token") String token);
        //新增用户
        @FormUrlEncoded
        @POST("User/user_save")
        Observable<HttpDataResult<WoSuccessBean>> addUser(@FieldMap HashMap<String,Object> hashMap,@Field("token") String token);
         //角色列表
       @FormUrlEncoded
       @POST("Role/RoleList")
        Observable<HttpDataResult<OaBasicItemBean<RoleListBean>>> getRoleList(@Field("token") String token);
       //组织架构列表
      @FormUrlEncoded
      @POST("Organization/group_list")
      Observable<HttpDataResult<GroupBasicBean>>  getOrGroupList(@Field("token") String token);
      //添加组织架构
      @FormUrlEncoded
      @POST("Organization/group_save")
       Observable<HttpDataResult<WoSuccessBean>> addGroupNode(@Field("PId")int pid,@Field("GroupName") String name,@Field("Remark") String remark,@Field("token") String token);
       //修改密码
       @FormUrlEncoded
       @POST("Common/user_pwd_edit")
       Observable<HttpDataResult<WoSuccessBean>> modifyPwd(@Field("OldMD5UserPassword") String oldPwd,@Field("NewMD5UserPassword") String newPwd,@Field("token") String token);
       //用户详情
       @FormUrlEncoded
       @POST("User/user_info")
       Observable<HttpDataResult<MyInfoBean>> userInfoDetail(@Field("Id") int id,@Field("token") String token);
       //修改用户详情
        @FormUrlEncoded
        @POST("User/user_edit_save")
         Observable<HttpDataResult<WoSuccessBean>> modifyUserInfo(@FieldMap HashMap<String,Object> hashMap,@Field("token") String token);
       //项目列表
         @FormUrlEncoded
         @POST("Project/project_list")
         Observable<HttpDataResult<ResultListBean<ProjectListBean>>> projectList(@Field("ProStatus") int status, @Field("token") String token);
          //我的任务
          @FormUrlEncoded
          @POST("Project/project_mytask")
          Observable<HttpDataResult<ResultBean<OaBasicItemBean<TaskListBean>>>> getTaskList(@Field("TaskStatus") int status,@Field("ProId") int id,@Field("UserId") String userid,@Field("Size") int size,@Field("token") String token);
          //删除Oa
          @FormUrlEncoded
          @POST("OA/oa_del")
          Observable<HttpDataResult<WoSuccessBean>> deleteOa(@Field("Id") int id,@Field("token") String token);
          //机构信息
          @FormUrlEncoded
          @POST("Tenant/tenant_info")
           Observable<HttpDataResult<ResultBean<TanentInfoBean>>> getTanentInfo(@Field("token") String token);
          //任务详情
          @FormUrlEncoded
          @POST("Project/project_task_info")
          Observable<HttpDataResult<ResultBean<TaskDetailsBean>>>  getTaskDetails(@Field("TId") String id,@Field("token") String token);
          //任务添加评论
           @FormUrlEncoded
           @POST("Project/project_task_sendMsg")
           Observable<HttpDataResult<ResultBean<CommentResultBean>>> submitRemark(@Field("TaskId") String taskId,@Field("PId") String pid,@Field("ToUserId") String toUserId,@Field("Remark") String remark,@Field("token") String token);
           //项目任务的添加
            @FormUrlEncoded
            @POST("Project/project_task_save")
            Observable<HttpDataResult<ResultBean<WoSuccessBean>>> addProjectTask(@FieldMap HashMap<String,String> hashMap);
            //项目任务分类的添加
            @FormUrlEncoded
             @POST("Project/project_taskclass_add")
            Observable<HttpDataResult<ResultBean<WoSuccessBean>>> addClassTask(@Field("ProjectId") String id,@Field("ClassName") String className,@Field("OrderBy") String orderBy,@Field("ClassDesc")String desc,@Field("token")String token);
            //项目成员
             @FormUrlEncoded
             @POST("Project/project_member")
              Observable<HttpDataResult<ResultBean<List<UserInfoBean>>>> projectMemberList(@Field("ProjectId") String projectId,@Field("token") String token);
              //项目列表
              @FormUrlEncoded
              @POST("Project/project_task_list")
              Observable<HttpDataResult<ResultListBean<ClassListBean>>>  getProjectTaskList(@Field("ProjectId") String id,@Field("token") String token);
              //完成任务
              @FormUrlEncoded
             @POST("Project/project_task_done")
              Observable<HttpDataResult<ResultBean<WoSuccessBean>>>   finishTask(@Field("TaskId") String taskid,@Field("token") String token);
              //项目动态
               @FormUrlEncoded
               @POST("Project/project_news")
               Observable<HttpDataResult<ResultBean<ProjectProcessBlockList>>> getProjectTrends(@Field("DayCount") String dayCount,@Field("Id") String id,@Field("token") String token);
               //项目文档列表
                @FormUrlEncoded
                 @POST("Project/project_document_list")
                Observable<HttpDataResult<ResultBean<OaBasicItemBean<ProjectDocumentBean>>>>  projectDocumentList(@Field("Id") String project,@Field("ClassId") String  classid,@Field("Size") int size,@Field("token") String token);


}
