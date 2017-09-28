package cn.czu.t1.controler;

import cn.czu.t1.module.QueueItem;
import cn.czu.t1.module.QueueItemComparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 队列控制管理器
 */
public final class QueueController {
    private static PriorityBlockingQueue<QueueItem> queueItemQueue = new PriorityBlockingQueue<>(200, new QueueItemComparator());
    public static void put(QueueItem item){
        if(!queueItemQueue.contains(item)){
            queueItemQueue.put(item);
        }
    }

    public static QueueItem poll(){
        return queueItemQueue.poll();
    }

    public static int getIndex(QueueItem item){
//        return queueItemQueue.
        return 0;
    }

}
