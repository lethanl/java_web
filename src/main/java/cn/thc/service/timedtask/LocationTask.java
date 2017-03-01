package cn.thc.service.timedtask;

import java.util.TimerTask;

/**
 * Created by thc on 2016/12/21
 */
public class LocationTask extends TimerTask {
    private static boolean isRunning = false;

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("执行了");
            isRunning = false;
        }else {
            System.out.println("执行错误");
        }
    }
}
