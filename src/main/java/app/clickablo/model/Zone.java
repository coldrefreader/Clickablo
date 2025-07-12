package app.clickablo.model;

import lombok.Data;

import java.util.List;

@Data
public class Zone {

    private final String name;
    private final List<String> encounterOrder;

    private int nextIndex = 0;

    public boolean hasNext() {
        return nextIndex < encounterOrder.size();
    }

    public String peekNextTemplate() {
        return encounterOrder.get(nextIndex);
    }

    public void advance() {
        if (hasNext()) {
            nextIndex++;
        }
    }
}
