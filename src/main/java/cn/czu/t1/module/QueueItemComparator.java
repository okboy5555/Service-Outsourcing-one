package cn.czu.t1.module;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class QueueItemComparator implements Comparator<QueueItem>{
    @Override
    public int compare(QueueItem o1, QueueItem o2) {
        return o2.getCustomer().getLevel() - o1.getCustomer().getLevel();
    }

    @Override
    public Comparator<QueueItem> reversed() {
        return null;
    }

    @Override
    public Comparator<QueueItem> thenComparing(Comparator<? super QueueItem> other) {
        return null;
    }

    @Override
    public <U> Comparator<QueueItem> thenComparing(Function<? super QueueItem, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<QueueItem> thenComparing(Function<? super QueueItem, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<QueueItem> thenComparingInt(ToIntFunction<? super QueueItem> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<QueueItem> thenComparingLong(ToLongFunction<? super QueueItem> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<QueueItem> thenComparingDouble(ToDoubleFunction<? super QueueItem> keyExtractor) {
        return null;
    }
}
