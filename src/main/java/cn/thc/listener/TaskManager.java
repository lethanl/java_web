package cn.thc.listener;

import cn.thc.service.timedtask.LocationTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * Created by thc on 2016/12/21
 */
//web下定时任务
// 参考：http://www.programgo.com/article/53522943605/
public class TaskManager implements ServletContextListener {

    //每天的毫秒数
    public static final long PERIOD_DAY = 24*60*60*1000;
    //一周的毫秒数
    public static final long PERIOD_WEEK = PERIOD_DAY*7;
    //无延迟
    public static final long DELAY = 0;
    //定时器
    private Timer timer;

    /**
     * 在Web应用启动时初始化任务
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //定义定时器
        timer = new Timer(true);
        timer.schedule(new LocationTask(),DELAY,30000);
    }

    /**
     * 在Web应用结束时停止任务
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        timer.cancel();
    }
}
