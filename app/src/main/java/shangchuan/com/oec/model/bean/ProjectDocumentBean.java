package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/5/19.
 */

public class ProjectDocumentBean {
    private  String  Id;
    private  String  ProjectId;
    private  String  DocumentTitle;
    private  String  CreateUserId;
    private  String CreateTime ;
    private  String CreateUserName ;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getDocumentTitle() {
        return DocumentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        DocumentTitle = documentTitle;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String createUserId) {
        CreateUserId = createUserId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }
}
