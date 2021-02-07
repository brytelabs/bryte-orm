package org.brytelabs.orm.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.brytelabs.orm.core.operations.ConjunctionOperation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkedConjunction {
    private Expression left;
    private Expression right;
    private ConjunctionOperation operation;
    private LinkedConjunction next;
    private ConjunctionOperation nextOperation;

    public LinkedConjunction(Expression left) {
        this.left = left;
    }

    public boolean hasNext() {
        return next != null;
    }

    public LinkedConjunction getNext() {
        if (!hasNext()) {
            return this;
        }

        LinkedConjunction temp = next;
        while(temp != null) {
            if (temp.next == null) break;
            temp = temp.next;
        }
        return temp;
    }
}
