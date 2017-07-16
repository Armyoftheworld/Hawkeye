package com.juziwl.commonlibrary.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.juziwl.commonlibrary.model.Area;
import com.juziwl.commonlibrary.model.Article;
import com.juziwl.commonlibrary.model.CircleTopicClass;
import com.juziwl.commonlibrary.model.ClassDynamic;
import com.juziwl.commonlibrary.model.ClassDynInfo;
import com.juziwl.commonlibrary.model.Clazz;
import com.juziwl.commonlibrary.model.ClazzStartEndTime;
import com.juziwl.commonlibrary.model.CourseFile;
import com.juziwl.commonlibrary.model.HisMsg;
import com.juziwl.commonlibrary.model.Msg;
import com.juziwl.commonlibrary.model.NewTime;
import com.juziwl.commonlibrary.model.OnlineSchool;
import com.juziwl.commonlibrary.model.ProvinceCityDistinct;
import com.juziwl.commonlibrary.model.SearchHistory;
import com.juziwl.commonlibrary.model.ServiceItem;
import com.juziwl.commonlibrary.model.User;
import com.juziwl.commonlibrary.model.WeiDuMsg;

import com.juziwl.commonlibrary.dao.AreaDao;
import com.juziwl.commonlibrary.dao.ArticleDao;
import com.juziwl.commonlibrary.dao.CircleTopicClassDao;
import com.juziwl.commonlibrary.dao.ClassDynamicDao;
import com.juziwl.commonlibrary.dao.ClassDynInfoDao;
import com.juziwl.commonlibrary.dao.ClazzDao;
import com.juziwl.commonlibrary.dao.ClazzStartEndTimeDao;
import com.juziwl.commonlibrary.dao.CourseFileDao;
import com.juziwl.commonlibrary.dao.HisMsgDao;
import com.juziwl.commonlibrary.dao.MsgDao;
import com.juziwl.commonlibrary.dao.NewTimeDao;
import com.juziwl.commonlibrary.dao.OnlineSchoolDao;
import com.juziwl.commonlibrary.dao.ProvinceCityDistinctDao;
import com.juziwl.commonlibrary.dao.SearchHistoryDao;
import com.juziwl.commonlibrary.dao.ServiceItemDao;
import com.juziwl.commonlibrary.dao.UserDao;
import com.juziwl.commonlibrary.dao.WeiDuMsgDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig areaDaoConfig;
    private final DaoConfig articleDaoConfig;
    private final DaoConfig circleTopicClassDaoConfig;
    private final DaoConfig classDynamicDaoConfig;
    private final DaoConfig classDynInfoDaoConfig;
    private final DaoConfig clazzDaoConfig;
    private final DaoConfig clazzStartEndTimeDaoConfig;
    private final DaoConfig courseFileDaoConfig;
    private final DaoConfig hisMsgDaoConfig;
    private final DaoConfig msgDaoConfig;
    private final DaoConfig newTimeDaoConfig;
    private final DaoConfig onlineSchoolDaoConfig;
    private final DaoConfig provinceCityDistinctDaoConfig;
    private final DaoConfig searchHistoryDaoConfig;
    private final DaoConfig serviceItemDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig weiDuMsgDaoConfig;

    private final AreaDao areaDao;
    private final ArticleDao articleDao;
    private final CircleTopicClassDao circleTopicClassDao;
    private final ClassDynamicDao classDynamicDao;
    private final ClassDynInfoDao classDynInfoDao;
    private final ClazzDao clazzDao;
    private final ClazzStartEndTimeDao clazzStartEndTimeDao;
    private final CourseFileDao courseFileDao;
    private final HisMsgDao hisMsgDao;
    private final MsgDao msgDao;
    private final NewTimeDao newTimeDao;
    private final OnlineSchoolDao onlineSchoolDao;
    private final ProvinceCityDistinctDao provinceCityDistinctDao;
    private final SearchHistoryDao searchHistoryDao;
    private final ServiceItemDao serviceItemDao;
    private final UserDao userDao;
    private final WeiDuMsgDao weiDuMsgDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        areaDaoConfig = daoConfigMap.get(AreaDao.class).clone();
        areaDaoConfig.initIdentityScope(type);

        articleDaoConfig = daoConfigMap.get(ArticleDao.class).clone();
        articleDaoConfig.initIdentityScope(type);

        circleTopicClassDaoConfig = daoConfigMap.get(CircleTopicClassDao.class).clone();
        circleTopicClassDaoConfig.initIdentityScope(type);

        classDynamicDaoConfig = daoConfigMap.get(ClassDynamicDao.class).clone();
        classDynamicDaoConfig.initIdentityScope(type);

        classDynInfoDaoConfig = daoConfigMap.get(ClassDynInfoDao.class).clone();
        classDynInfoDaoConfig.initIdentityScope(type);

        clazzDaoConfig = daoConfigMap.get(ClazzDao.class).clone();
        clazzDaoConfig.initIdentityScope(type);

        clazzStartEndTimeDaoConfig = daoConfigMap.get(ClazzStartEndTimeDao.class).clone();
        clazzStartEndTimeDaoConfig.initIdentityScope(type);

        courseFileDaoConfig = daoConfigMap.get(CourseFileDao.class).clone();
        courseFileDaoConfig.initIdentityScope(type);

        hisMsgDaoConfig = daoConfigMap.get(HisMsgDao.class).clone();
        hisMsgDaoConfig.initIdentityScope(type);

        msgDaoConfig = daoConfigMap.get(MsgDao.class).clone();
        msgDaoConfig.initIdentityScope(type);

        newTimeDaoConfig = daoConfigMap.get(NewTimeDao.class).clone();
        newTimeDaoConfig.initIdentityScope(type);

        onlineSchoolDaoConfig = daoConfigMap.get(OnlineSchoolDao.class).clone();
        onlineSchoolDaoConfig.initIdentityScope(type);

        provinceCityDistinctDaoConfig = daoConfigMap.get(ProvinceCityDistinctDao.class).clone();
        provinceCityDistinctDaoConfig.initIdentityScope(type);

        searchHistoryDaoConfig = daoConfigMap.get(SearchHistoryDao.class).clone();
        searchHistoryDaoConfig.initIdentityScope(type);

        serviceItemDaoConfig = daoConfigMap.get(ServiceItemDao.class).clone();
        serviceItemDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        weiDuMsgDaoConfig = daoConfigMap.get(WeiDuMsgDao.class).clone();
        weiDuMsgDaoConfig.initIdentityScope(type);

        areaDao = new AreaDao(areaDaoConfig, this);
        articleDao = new ArticleDao(articleDaoConfig, this);
        circleTopicClassDao = new CircleTopicClassDao(circleTopicClassDaoConfig, this);
        classDynamicDao = new ClassDynamicDao(classDynamicDaoConfig, this);
        classDynInfoDao = new ClassDynInfoDao(classDynInfoDaoConfig, this);
        clazzDao = new ClazzDao(clazzDaoConfig, this);
        clazzStartEndTimeDao = new ClazzStartEndTimeDao(clazzStartEndTimeDaoConfig, this);
        courseFileDao = new CourseFileDao(courseFileDaoConfig, this);
        hisMsgDao = new HisMsgDao(hisMsgDaoConfig, this);
        msgDao = new MsgDao(msgDaoConfig, this);
        newTimeDao = new NewTimeDao(newTimeDaoConfig, this);
        onlineSchoolDao = new OnlineSchoolDao(onlineSchoolDaoConfig, this);
        provinceCityDistinctDao = new ProvinceCityDistinctDao(provinceCityDistinctDaoConfig, this);
        searchHistoryDao = new SearchHistoryDao(searchHistoryDaoConfig, this);
        serviceItemDao = new ServiceItemDao(serviceItemDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        weiDuMsgDao = new WeiDuMsgDao(weiDuMsgDaoConfig, this);

        registerDao(Area.class, areaDao);
        registerDao(Article.class, articleDao);
        registerDao(CircleTopicClass.class, circleTopicClassDao);
        registerDao(ClassDynamic.class, classDynamicDao);
        registerDao(ClassDynInfo.class, classDynInfoDao);
        registerDao(Clazz.class, clazzDao);
        registerDao(ClazzStartEndTime.class, clazzStartEndTimeDao);
        registerDao(CourseFile.class, courseFileDao);
        registerDao(HisMsg.class, hisMsgDao);
        registerDao(Msg.class, msgDao);
        registerDao(NewTime.class, newTimeDao);
        registerDao(OnlineSchool.class, onlineSchoolDao);
        registerDao(ProvinceCityDistinct.class, provinceCityDistinctDao);
        registerDao(SearchHistory.class, searchHistoryDao);
        registerDao(ServiceItem.class, serviceItemDao);
        registerDao(User.class, userDao);
        registerDao(WeiDuMsg.class, weiDuMsgDao);
    }
    
    public void clear() {
        areaDaoConfig.clearIdentityScope();
        articleDaoConfig.clearIdentityScope();
        circleTopicClassDaoConfig.clearIdentityScope();
        classDynamicDaoConfig.clearIdentityScope();
        classDynInfoDaoConfig.clearIdentityScope();
        clazzDaoConfig.clearIdentityScope();
        clazzStartEndTimeDaoConfig.clearIdentityScope();
        courseFileDaoConfig.clearIdentityScope();
        hisMsgDaoConfig.clearIdentityScope();
        msgDaoConfig.clearIdentityScope();
        newTimeDaoConfig.clearIdentityScope();
        onlineSchoolDaoConfig.clearIdentityScope();
        provinceCityDistinctDaoConfig.clearIdentityScope();
        searchHistoryDaoConfig.clearIdentityScope();
        serviceItemDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        weiDuMsgDaoConfig.clearIdentityScope();
    }

    public AreaDao getAreaDao() {
        return areaDao;
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public CircleTopicClassDao getCircleTopicClassDao() {
        return circleTopicClassDao;
    }

    public ClassDynamicDao getClassDynamicDao() {
        return classDynamicDao;
    }

    public ClassDynInfoDao getClassDynInfoDao() {
        return classDynInfoDao;
    }

    public ClazzDao getClazzDao() {
        return clazzDao;
    }

    public ClazzStartEndTimeDao getClazzStartEndTimeDao() {
        return clazzStartEndTimeDao;
    }

    public CourseFileDao getCourseFileDao() {
        return courseFileDao;
    }

    public HisMsgDao getHisMsgDao() {
        return hisMsgDao;
    }

    public MsgDao getMsgDao() {
        return msgDao;
    }

    public NewTimeDao getNewTimeDao() {
        return newTimeDao;
    }

    public OnlineSchoolDao getOnlineSchoolDao() {
        return onlineSchoolDao;
    }

    public ProvinceCityDistinctDao getProvinceCityDistinctDao() {
        return provinceCityDistinctDao;
    }

    public SearchHistoryDao getSearchHistoryDao() {
        return searchHistoryDao;
    }

    public ServiceItemDao getServiceItemDao() {
        return serviceItemDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public WeiDuMsgDao getWeiDuMsgDao() {
        return weiDuMsgDao;
    }

}