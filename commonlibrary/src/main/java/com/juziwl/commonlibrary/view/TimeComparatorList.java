//package com.juziwl.commonlibrary.view;
//
//
//import com.juziwl.commonlibrary.model.HistoryOffline;
//
//import java.io.Serializable;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Comparator;
//import java.util.Date;
//
//public class TimeComparatorList implements Comparator<HistoryOffline>, Serializable {
//
//    @Override
//    public int compare(HistoryOffline historyOffline1, HistoryOffline historyOffline2) {
//        // TODO Auto-generated method stub
//        Date d1 = null;
//        Date d2 = null;
//        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        try {
//            if (historyOffline1 != null && historyOffline2 != null) {
//                d1 = s.parse(historyOffline1.P_SaveTime);
//                d2 = s.parse(historyOffline2.P_SaveTime);
//            } else {
//                return -1;
//            }
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        if (d2 != null && d2.after(d1)) {
//            return -1;
//        } else {
//            return 1;
//        }
//    }
//}
