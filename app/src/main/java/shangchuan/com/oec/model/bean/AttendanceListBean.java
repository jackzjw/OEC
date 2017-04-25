package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/25.
 */

public class AttendanceListBean {

    private int  Id;
    private int  TenantId;
    private int UserID ;
    private int  ArriveStatus;
    private int  LeaveStatus;
    private String  AttendanceDate ;
    private String   ArriveTime;
    private String LeaveTime  ;
    private int IsVacation;

    public int getIsVacation() {
        return IsVacation;
    }

    public void setIsVacation(int isVacation) {
        IsVacation = isVacation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTenantId() {
        return TenantId;
    }

    public void setTenantId(int tenantId) {
        TenantId = tenantId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getArriveStatus() {
        return ArriveStatus;
    }

    public void setArriveStatus(int arriveStatus) {
        ArriveStatus = arriveStatus;
    }

    public int getLeaveStatus() {
        return LeaveStatus;
    }

    public void setLeaveStatus(int leaveStatus) {
        LeaveStatus = leaveStatus;
    }

    public String getAttendanceDate() {
        return AttendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        AttendanceDate = attendanceDate;
    }

    public String getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(String arriveTime) {
        ArriveTime = arriveTime;
    }

    public String getLeaveTime() {
        return LeaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        LeaveTime = leaveTime;
    }
}
