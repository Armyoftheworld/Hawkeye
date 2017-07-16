package com.juziwl.commonlibrary.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/1/15
 * @description 线程池
 */

public class ThreadExecutorManager {

    private ExecutorService executorService = null;

    private ThreadExecutorManager(){
        executorService = Executors.newFixedThreadPool(10);
    }

    private static class SingletonHolder {
        private static ThreadExecutorManager instance = new ThreadExecutorManager();
    }

    public static ThreadExecutorManager getInstance(){
        return SingletonHolder.instance;
    }

    public void runInThreadPool(Runnable runnable){
        if(executorService != null && !executorService.isShutdown()){
            executorService.execute(runnable);
        }
    }

}
