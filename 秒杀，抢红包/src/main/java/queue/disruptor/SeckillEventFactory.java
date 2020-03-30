package queue.disruptor;

import com.lmax.disruptor.EventFactory;

public class SeckillEventFactory implements EventFactory<SeckillEvent> {

    public SeckillEvent newInstance() {
        return new SeckillEvent();
    }
}
