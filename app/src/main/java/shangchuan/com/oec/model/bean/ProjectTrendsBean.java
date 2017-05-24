package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/5/19.
 */

public class ProjectTrendsBean {


     private String TheDate;
    private ArrayList<ProjectTrendsListBean> ProjectProcessList;

    public String getTheDate() {
        return TheDate;
    }

    public void setTheDate(String theDate) {
        TheDate = theDate;
    }

    public ArrayList<ProjectTrendsListBean> getProjectProcessList() {
        return ProjectProcessList;
    }

    public void setProjectProcessList(ArrayList<ProjectTrendsListBean> projectProcessList) {
        ProjectProcessList = projectProcessList;
    }
}
