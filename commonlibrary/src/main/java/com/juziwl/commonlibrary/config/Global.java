package com.juziwl.commonlibrary.config;

import android.app.Application;
import android.os.Environment;

import com.juziwl.commonlibrary.model.Clazz;
import com.juziwl.commonlibrary.model.HisMsg;
import com.juziwl.commonlibrary.model.User;

import java.util.HashMap;

/**
 * Created by ztn on
 * 全局数据存放处 广播action 消息 url地址等等
 */
public class Global {
    public static Application application;//在application里面赋值
    public static String uid = "uid", accessToken = "token";

    //小米推送AppId和AppKey
    public static final String AppId = "2882303761517511762";
    public static final String AppKey = "5561751149762";

    //小米推送证书ID
    public static final long XMBussId = 2181;
    //华为推送证书ID
    public static final long HWBussId = 2180;

    public static final String ENCODING = "UTF-8";
    public static final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/orange/";

////        正式库
    public static String BoBoApi = "https://api.imexue.com/";
    public static String UrlApi = "https://api.imexue.com/";
//////     测试库
//    public static String BoBoApi = "https://test.juziwl.com/exiaoxplatf/";
//    public static String UrlApi = "https://test.juziwl.com/";
    public static String webBaseUrl = "https://m.imexue.com";
    public static String baseURL = UrlApi.contains("test.juziwl.com") ? "https://test.juziwl.com" : "https://m.imexue.com";
    public static String ABOUTXIAOXIN = webBaseUrl + "/data/file/public/source/html/guanyu.html";
    public static String requestURL = UrlApi + "UploadServer/phone/upload";
    public static String requestURL1 = BoBoApi + "v1/upload";
    //报平安基地址
    public static final String REPORT_SAFETY = UrlApi + "api/v2/";
    public static String EschoolUrl = "https://v.imexue.com/view/wap/index.html?";
    //    public static String IniUrl = "http://juziwl.com/file/xiaoxin.cnf";
    public static String XXClause = webBaseUrl + "/data/file/termofservice-exue.html";
    public static final String APP_ID = "wxc0c00b4b0d24d50b";
    //    public static final String APPID = "wx3c9b90e0350de1b5";//测试用的
    public static Integer msg_Count = 0;//聊天记录的未读条数总数
    public static int onlinecount = 0;//在线学堂未读条数总数
    public static int gongzaicount = 0;//公仔未读条数总数
    public static int count = 0;//未处理的好友条数
    public static int count2 = 0;//未处理的好友条数
    public static HashMap<String, Integer> UpdateMsg = new HashMap<String, Integer>();//聊天记录的未读条数
    public static HashMap<String, HisMsg> hismsg = new HashMap<String, HisMsg>();//聊天记录
    public static HashMap<String, User> contacts = new HashMap<String, User>();//联系人
    public static HashMap<String, Clazz> cHashMap = new HashMap<String, Clazz>();//所有班级
    public static long date = 0;
    public static final String UPDATE_HEADER = "com.csbjstx.service.updateheader";
    public static String fid = "";
    public static final String ATTENDANCECANCEL = "com.csbjstx.service.attendancecancel";
    public static final String XX_NOTICECANCEL = "com.csbjstx.service.xxnoticecancel";
    public static final String SCHOOLCANCEL = "com.csbjstx.service.schoolcancel";
    public static int loginid = 0;
    public static int classInfor = 0;
    public static int homemsg = 0;
    public static int homeGroupmsg = 0;
    public static int homeGongZaimsg = 0;
    public static int homeworkFlag = 0;

    //公仔绑定的广播
//    public static final String GZ_BANGDING = "com.exiaoxin.service.HASBINDDEVICE";
//    public static final String GZ_NOBANGDING = "com.exiaoxin.service.HASUNBINDDEVICE";

    public static final String MIDDLEREDPOINT = "com.exiaoxin.service.middleredpoint";
    public static final String MSG_COMPLETED = "com.exiaoxin.service.msg";
    public static final String TAKEING_MSG_COMPLETED = "com.exiaoxin.service.takeing.msg";
    public static final String GROUP_TAKEING_MSG_COMPLETED = "com.exiaoxin.service.group.takeing.msg";
    public static final String GONGZAI_TAKEING_MSG_COMPLETED = "com.exiaoxin.service.gongzai.takeing.msg";
    public static final String LOGIN = "com.exiaoxin.service.login";
    public static final String LOGINSUCCESS = "com.exiaoxin.service.loginsuccess";
    public static final String LOGINFAIL = "com.exiaoxin.service.loginfail";
    public static final String CLEARMSG = "com.exiaoxin.service.clearmsg";
    public static final String UPDATEMSG = "com.exiaoxin.service.updatemsg";
    public static final String ADDFRIENDFRI = "com.exiaoxin.service.addFri";
    public static final String ONLINENUM = "com.exiaoxin.service.onlinenum";
    public static final String ADDFRIENDQ = "com.exiaoxin.service.add";
    public static final String NOCLAZZ = "com.exiaoxin.service.noclazz";
    public static final String BANCLAZZ = "com.exiaoxin.service.banclazz";
    public static final String TAKE_CHONGLIAN = "com.exiaoxin.service.take.chonglian";
    public static final String TAKE_SHIBAI = "com.exiaoxin.service.take.shibai";
    public static final String TAKE_OK = "com.exiaoxin.service.take.ok";
    public static final int TAKE_MSG = 1;
    public static final int TAKEING_MSG = 2;
    public static final int ADDFRIEND = 4;
    public static final int ADDFRIENDAG = 5;
    public static final int TAKEING_CHONGLIAN = 6;
    public static final int TAKEING_SHIBAI = 7;
    public static final int TAKEING_OK = 8;

    //宝宝直播页面删除
    public static final String BABYDELETE = "com.juzi.exiaoxin.babydelete";
    //服务页面要用
    public static final String SERVICE = "com.juzi.exiaoxin.service";
    public static final String CLEARDELETE = "com.juzi.exiaoxin.cleardeleteicon";
    public static final String SHOWNEWINFO = "com.juzi.exiaoxin.shownewinfo";
    public static final String CLICKNEWINFO = "com.juzi.exiaoxin.clicknewinfo";
    public static final String ALLRED = "com.juzi.exiaoxin.allred";
    public static final String XX_AD = "com.juzi.exiaoxin.xx_id";
    public static final String UPDATEPANEL = "com.juzi.exiaoxin.updatepanel";
    public static final String reportHTML = UrlApi.contains("test") ? UrlApi + "data/platform1/static/single/report.html" : webBaseUrl + "/data/platform/static/single/report.html";
    public static final String ARTICLEDELETEHTML = UrlApi + "data/platform/static/single/notexist.html";
    public static final String jieshaoUrl = webBaseUrl + "/data/file/public/source/html/Features_new.html";
    public static final String helpUrl = webBaseUrl + "/data/file/public/source/help/";
    public static final String ANTILOSTCANCEL = "com.csbjstx.service.antilostcancel";
    //悬赏答题界面广播
    public static final String HASITEMDELETE = "com.juzi.exiaoxin.hasItemDelete";
    //防拐防丢发布丢失与线索广播
    public static final String HasPublishLost = "com.juzi.exiaoxin.hasPublishLost";
    public static final String HasPublishClue = "com.juzi.exiaoxin.hasPublishClue";
    //红包要用到
    public static int ShareFlag = 0;
    public static String topicID = "";
    //统一图片压缩宽高
    public static final int imgWidth = 960;
    public static final int imgHeight = 1280;
    //语音存放路径
    public static final String VOICEPATH = filePath + "voice/";

    //视频存放路径
    public static final String VIDEOPATH = filePath + "video/";

    //图片保存
    public static final String SAVEPICTURE = filePath + "savepictures/";

    //选图片缓存的图片
    public static final String imgPath = Global.filePath + "pickImgCache/";

    public static final String THETHIRDURL = UrlApi + "api/v2/openlogin/";
    public static String DELETECLASSID = "";

    public static final String ASK_HIDE_RED_POINT = "com.juzi.exiaoxin.askhideredpoint";
    public static int VIEWTYPE = 0;

    public static boolean hasRedPoint = false;
    public static boolean hasPaySuccess = false;

    //我的收藏页面广播
    public static String ADDANDDELETECOLLECTION = "com.juziwl.xiaoxin.addanddeletecollection";

    //小小q相关信息
//    public static String xxqId = "";  //xxqid
//    public static String token = "";//xxq token
//    public static boolean isFirstConnect = true;//xxq token
//    public static final String XXQ = "http://domain.gz.1251007304.clb.myqcloud.com/qrconnecter/";
//    public static String PLAYSONG = "com.juzi.exiaoxin.playsong";
////    public static HashMap<String, Song> playSong = new HashMap<>();
////    public static boolean songstatue = false;
//    public static XXQ xxqClass = new XXQ();//xxq群
//    public static boolean isbuffer = false;
//    public static boolean isStop = false;
//    public static final String XXQREPORT = "com.juzi.exiaoxin.xxqreport";
//    public static final String XXQONLINE = "com.juzi.exiaoxin.xxqonline";
//    public static final String XXQNETCONN = "com.juzi.exiaoxin.xxqnetconn";

    //积分规则
    public static final String SCORERULE = webBaseUrl + "/data/file/public/source/help/mymodular/specific/integralrule.html";

    //意见反馈
    public static final String FEEDBACK = webBaseUrl + "/data/file/public/source/help/customerService/";

    //圈子相关配置

    public final static String host = "api.juziwl.cn";
  //  public static String UrlApiCircle = "http://api.juziwl.cn/";
    public static final String CIRCLE_NEAR = "com.csbjstx.service.circlenear";
    public static final String CIRCLE_HOT = "com.csbjstx.service.circlehot";

 //   public static String qzuserId="212c8fd0-656c-4bd3-85c0-17e54ac9c747";
//    public static String qztoken="AAAAAJEMo0SqG7Kf_Bl2_Eo4-ApHC710mq8q5f3LMF5lOea-qLjF-2ZmgnAfZh7XMx4hxWYSrxNfPhYtdp1VhHHXbqb5ZoiohyGA8Z_7ZUPgHIDW6HQ6RTksQWInuctt7uUjUHcseG7OPsw7R-WooInyOSA";
    public final static String contentType = "application/json;charset=utf-8";
    public static final String encoding = "UTF-8";

    public static final String CIRCLE_MYCREATE = "com.csbjstx.service.circlemycreate";
    public static final String CIRCLE_MYCARES = "com.csbjstx.service.circlemycares";
    public static final String CIRCLE_MYCREATETOPIC = "com.csbjstx.service.circlemycreatetopic";
    public static final String CIRCLE_TOPIC = "com.csbjstx.service.circletopic";
    public static final  String  QZBASEURL = "http://juziwl.cn";
    public static final String tongzhuoUrl = "http://reg3.tongzhuo100.com/?";
    public static final String tongzhuoActivation = "http://reg2.tongzhuo100.com/";
    public static final String tongzhuoRegister = "http://reg1.tongzhuo100.com/";
    public static final String tongzhuoQuestionList = "http://ask.tongzhuo100.com/MyProxiaoxin.php";
    public static final String tongzhuoSubmit = "http://ask.tongzhuo100.com/ask_xiaoxin_submit.php";
    public static final String KEY = "NESTORANGES2012BJHKLOMGPHSS&%1990GLEMJCPAN&%718LIUYGUDSXPAN";
    public static final String tigasePwd = "JHVFshqh876gd729bdYFG1";
    public static final String tongzhuoPwd = "19fa08049cd6f04136bece707b9fb72e";
    public static final String tongzhuoFree = "http://reg4.tongzhuo100.com/index2.php";


    public static final String CIRCLE_SEARCH = "com.csbjstx.service.circleserach";
    public static final String CIRCLE_SEARCHCIRCLE = "com.csbjstx.service.circlesearchcircle";
    public static final String UPDATECIRCLE = "com.csbjstx.service.common_circle";
    public static final String CIRCLE_MYCARESTOPIC = "com.csbjstx.service.circlemycarestopic";

    public static final String XXCIRCLE = "http://test.juziwl.com/data/file/group/topic/serviceAgreement.html";

    public static boolean isDNSOpen = false;
}
