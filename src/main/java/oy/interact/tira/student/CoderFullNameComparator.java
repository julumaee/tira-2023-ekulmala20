package oy.interact.tira.student;

import java.util.Comparator;

import oy.interact.tira.model.Coder;

public class CoderFullNameComparator implements Comparator <Coder> {

    @Override
    public int compare(Coder o1, Coder o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }
}
