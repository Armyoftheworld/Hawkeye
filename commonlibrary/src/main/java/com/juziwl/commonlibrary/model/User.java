package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月21日
 * @description 用户实体类
 */
@Entity
public class User implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901488872936236870L;
    @Id(autoincrement = true)
    public Long id;
    //判断是否禁用群聊
    public String classBlocked = "false";
    public String userId = "";
    public String xlId = "";
    public String accessToken = "";
    public String time = "";
    public String userName = "";
    public String fullName = "";
    public String phoneNumber = "";
    public String userImageUrl = "";
    public String provinceName = "";
    public String provinceId = "";
    public String cityName = "";
    public String cityId = "";
    public String districtName = "";
    public String districtId = "";
    public String deviceToken = "";
    public String registered = "";
    public String xxCode = "";
    public String role = "";
    public String studentName = "";
    public String totalRole = "";
    public Integer type = 0;//0 游客  1 家长  2 教师  3 家长教师  4 是教师但没有班级 5 s是学校负责人 6 是班级负责人
    public Integer openType = 0;//0 游客  1 家长  2 教师
    public String totalName = "";
    public String addGlag = "";//添加好友时使用
    public String pinyin = "";//昵称的拼音
    public String friendsTimeNows = "";//更新好友信息要用
    public Integer isChat; //在群聊中是否禁言，禁言为-1，非禁言为1
    public Boolean ischecked = false; //在禁言功能中的选择联系人页面要用
    public Boolean isTeacher = false;
    public String isSchoolOwner = "0";//0代表不是学校负责人 1代表是学校负责人
    public String isClassOwner = "0";//0代表不是班级负责人 1代表是班级负责人
    public String mid = "";
    public String flag = "";
    @Transient
    public ArrayList<Clazz> teacherOwnerInfo = new ArrayList<>();//班级负责人列表
    @Transient
    public ArrayList<Clazz> schoolInfo = new ArrayList<>();//学校负责人列表
    @Transient
    public ArrayList<Clazz> parentInfo = new ArrayList<Clazz>();
    @Transient
    public ArrayList<Clazz> teacherInfo = new ArrayList<Clazz>();
    @Transient
    public ArrayList<Clazz> openClazz = new ArrayList<Clazz>();

    @Generated(hash = 1715106548)
    public User(Long id, String classBlocked, String userId, String xlId, String accessToken,
            String time, String userName, String fullName, String phoneNumber, String userImageUrl,
            String provinceName, String provinceId, String cityName, String cityId, String districtName,
            String districtId, String deviceToken, String registered, String xxCode, String role,
            String studentName, String totalRole, Integer type, Integer openType, String totalName,
            String addGlag, String pinyin, String friendsTimeNows, Integer isChat, Boolean ischecked,
            Boolean isTeacher, String isSchoolOwner, String isClassOwner, String mid, String flag) {
        this.id = id;
        this.classBlocked = classBlocked;
        this.userId = userId;
        this.xlId = xlId;
        this.accessToken = accessToken;
        this.time = time;
        this.userName = userName;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.userImageUrl = userImageUrl;
        this.provinceName = provinceName;
        this.provinceId = provinceId;
        this.cityName = cityName;
        this.cityId = cityId;
        this.districtName = districtName;
        this.districtId = districtId;
        this.deviceToken = deviceToken;
        this.registered = registered;
        this.xxCode = xxCode;
        this.role = role;
        this.studentName = studentName;
        this.totalRole = totalRole;
        this.type = type;
        this.openType = openType;
        this.totalName = totalName;
        this.addGlag = addGlag;
        this.pinyin = pinyin;
        this.friendsTimeNows = friendsTimeNows;
        this.isChat = isChat;
        this.ischecked = ischecked;
        this.isTeacher = isTeacher;
        this.isSchoolOwner = isSchoolOwner;
        this.isClassOwner = isClassOwner;
        this.mid = mid;
        this.flag = flag;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public static User getLoginJson(String content) {
        User user = new User();
        ArrayList<Clazz> parentInfos = new ArrayList<Clazz>();
        ArrayList<Clazz> teacherInfos = new ArrayList<Clazz>();
        ArrayList<Clazz> openClazz = new ArrayList<Clazz>();
        ArrayList<Clazz> teacherOwnerInfo = new ArrayList<>();//班级负责人列表
        ArrayList<Clazz> schoolInfo = new ArrayList<>();//学校负责人列表
        try {
            JSONObject jsonObj = new JSONObject(content);
            user.userId = jsonObj.getString("userId");
            user.accessToken = jsonObj.getString("accessToken");
            user.time = jsonObj.getString("time");
            user.userName = jsonObj.getString("userName");
            user.fullName = jsonObj.getString("fullName");
            user.phoneNumber = jsonObj.getString("phoneNumber");
            user.userImageUrl = jsonObj.getString("userImageUrl");
            user.provinceName = jsonObj.getString("provinceName");
            user.provinceId = jsonObj.getString("provinceId");
            user.cityName = jsonObj.getString("cityName");
            user.cityId = jsonObj.getString("cityId");
            user.districtName = jsonObj.getString("districtName");
            user.districtId = jsonObj.getString("districtId");
            user.deviceToken = jsonObj.getString("deviceToken");
            user.registered = jsonObj.getString("registered");
            user.xxCode = jsonObj.getString("xxCode");
            user.type = jsonObj.getInt("type");
            user.openType = jsonObj.getInt("openType");
            JSONArray arrayparent = jsonObj.getJSONArray("parentInfo");
            for (int i = 0; i < arrayparent.length(); i++) {
                Clazz parentInfo = new Clazz();
                JSONObject arrObj = arrayparent.getJSONObject(i);
                parentInfo.role = arrObj.getString("role");
                parentInfo.schoolId = arrObj.getString("schoolId");
                parentInfo.schoolName = arrObj.getString("schoolName");
                parentInfo.classId = arrObj.getString("classId");
                parentInfo.className = arrObj.getString("className");
                parentInfo.studentId = arrObj.getString("studentId");
                parentInfo.studentName = arrObj.getString("studentName");
                parentInfo.subjectName = arrObj.getString("subjectName");
                parentInfo.address = arrObj.getString("address");
                parentInfo.classBlocked = arrObj.getString("classBlocked");
                parentInfo.sendFun = arrObj.getString("sendFun");
                parentInfo.chatFun = arrObj.getString("chatFun");
                parentInfo.owner = "false";
                parentInfo.isChat = arrObj.getInt("isChat");
                parentInfos.add(parentInfo);
            }
            JSONArray arrayteacher = jsonObj.getJSONArray("teacherInfo");
            for (int i = 0; i < arrayteacher.length(); i++) {
                Clazz teacherInfo = new Clazz();
                JSONObject arrObj = arrayteacher.getJSONObject(i);
                teacherInfo.role = arrObj.getString("role");
                teacherInfo.schoolId = arrObj.getString("schoolId");
                teacherInfo.schoolName = arrObj.getString("schoolName");
                teacherInfo.classId = arrObj.getString("classId");
                teacherInfo.className = arrObj.getString("className");
                teacherInfo.studentId = arrObj.getString("studentId");
                teacherInfo.studentName = arrObj.getString("studentName");
                teacherInfo.subjectName = arrObj.getString("subjectName");
                teacherInfo.address = arrObj.getString("address");
                teacherInfo.classBlocked = arrObj.getString("classBlocked");
                teacherInfo.subjectId = arrObj.getString("subJectId");
                teacherInfo.sendFun = arrObj.getString("sendFun");
                teacherInfo.chatFun = arrObj.getString("chatFun");
                teacherInfo.owner = "false";
                teacherInfo.isChat = arrObj.getInt("isChat");
                teacherInfos.add(teacherInfo);
            }
            JSONArray arrayOpenClazz = jsonObj.optJSONArray("openclassInfo");
            for (int i = 0; i < arrayOpenClazz.length(); i++) {
                Clazz openInfo = new Clazz();
                JSONObject arrObj = arrayOpenClazz.getJSONObject(i);
                openInfo.role = "open";
                openInfo.schoolId = arrObj.getString("schoolId");
                openInfo.schoolName = arrObj.getString("schoolName");
                openInfo.classId = arrObj.getString("classId");
                openInfo.className = arrObj.getString("className");
                openInfo.studentId = arrObj.getString("studentId");
                openInfo.studentName = arrObj.getString("studentName");
                openInfo.subjectName = arrObj.getString("subjectName");
                openInfo.address = arrObj.getString("address");
                openInfo.classBlocked = arrObj.getString("classBlocked");
                openInfo.subjectId = arrObj.getString("subJectId");
                openInfo.owner = arrObj.getBoolean("owner") + "";
                openInfo.classNo = arrObj.getString("classNo");
                openInfo.sendFun = arrObj.getString("sendFun");//屏蔽
                openInfo.chatFun = arrObj.getString("chatFun");//免打扰
                openClazz.add(openInfo);
            }
            user.parentInfo = parentInfos;
            user.teacherInfo = teacherInfos;
            user.openClazz = openClazz;
            JSONArray teacherOwnerInfoArray = jsonObj.optJSONArray("teacherOwnerInfo");
            for (int i = 0; teacherOwnerInfoArray != null && i < teacherOwnerInfoArray.length(); i++) {
                Clazz classOwnerInfo = new Clazz();
                JSONObject owner = teacherOwnerInfoArray.getJSONObject(i);
                classOwnerInfo.classId = owner.getString("classId");
                classOwnerInfo.className = owner.getString("className");
                classOwnerInfo.schoolName = owner.getString("schoolName");
                classOwnerInfo.schoolId = owner.getString("schoolId");
                classOwnerInfo.owner = "class";//代表班级负责人
                teacherOwnerInfo.add(classOwnerInfo);
            }
            user.teacherOwnerInfo = teacherOwnerInfo;
            if (teacherOwnerInfo != null && teacherOwnerInfo.size() > 0) {
                user.isClassOwner = "1";//标记是班级负责人信息
            } else {
                user.isClassOwner = "0";
            }

            JSONArray schoolOwnerInfoArray = jsonObj.optJSONArray("schoolInfo");
            for (int i = 0; schoolOwnerInfoArray != null && i < schoolOwnerInfoArray.length(); i++) {
                Clazz schoolOwnerInfo = new Clazz();
                JSONObject owner = schoolOwnerInfoArray.getJSONObject(i);
                schoolOwnerInfo.schoolName = owner.getString("schoolName");
                schoolOwnerInfo.schoolId = owner.getString("schoolId");
                schoolOwnerInfo.owner = "school";
                schoolInfo.add(schoolOwnerInfo);
            }
            user.schoolInfo = schoolInfo;
            if (schoolInfo != null && schoolInfo.size() > 0) {
                user.isSchoolOwner = "1";
            } else {
                user.isSchoolOwner = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "classBlocked='" + classBlocked + '\'' +
                ", userId='" + userId + '\'' +
                ", xlId='" + xlId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", time='" + time + '\'' +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userImageUrl='" + userImageUrl + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", districtName='" + districtName + '\'' +
                ", districtId='" + districtId + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", registered='" + registered + '\'' +
                ", xxCode='" + xxCode + '\'' +
                ", role='" + role + '\'' +
                ", studentName='" + studentName + '\'' +
                ", totalRole='" + totalRole + '\'' +
                ", type=" + type +
                ", openType=" + openType +
                ", totalName='" + totalName + '\'' +
                ", addGlag='" + addGlag + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", friendsTimeNows='" + friendsTimeNows + '\'' +
                ", isChat=" + isChat +
                ", ischecked=" + ischecked +
                ", isTeacher=" + isTeacher +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassBlocked() {
        return this.classBlocked;
    }

    public void setClassBlocked(String classBlocked) {
        this.classBlocked = classBlocked;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getXlId() {
        return this.xlId;
    }

    public void setXlId(String xlId) {
        this.xlId = xlId;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserImageUrl() {
        return this.userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictId() {
        return this.districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDeviceToken() {
        return this.deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getRegistered() {
        return this.registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getXxCode() {
        return this.xxCode;
    }

    public void setXxCode(String xxCode) {
        this.xxCode = xxCode;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTotalRole() {
        return this.totalRole;
    }

    public void setTotalRole(String totalRole) {
        this.totalRole = totalRole;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOpenType() {
        return this.openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public String getTotalName() {
        return this.totalName;
    }

    public void setTotalName(String totalName) {
        this.totalName = totalName;
    }

    public String getAddGlag() {
        return this.addGlag;
    }

    public void setAddGlag(String addGlag) {
        this.addGlag = addGlag;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFriendsTimeNows() {
        return this.friendsTimeNows;
    }

    public void setFriendsTimeNows(String friendsTimeNows) {
        this.friendsTimeNows = friendsTimeNows;
    }

    public Integer getIsChat() {
        return this.isChat;
    }

    public void setIsChat(Integer isChat) {
        this.isChat = isChat;
    }

    public Boolean getIschecked() {
        return this.ischecked;
    }

    public void setIschecked(Boolean ischecked) {
        this.ischecked = ischecked;
    }

    public Boolean getIsTeacher() {
        return this.isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getIsSchoolOwner() {
        return this.isSchoolOwner;
    }

    public void setIsSchoolOwner(String isSchoolOwner) {
        this.isSchoolOwner = isSchoolOwner;
    }

    public String getIsClassOwner() {
        return this.isClassOwner;
    }

    public void setIsClassOwner(String isClassOwner) {
        this.isClassOwner = isClassOwner;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
