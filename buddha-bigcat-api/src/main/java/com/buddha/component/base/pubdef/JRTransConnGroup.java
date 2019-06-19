package com.buddha.component.base.pubdef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JRTransConnGroup {
    public JRTransConnGroup()
    {
        transConns = new ArrayList<JRTransConnection>();
        currSelectNode = -1;
    }

    public int getCurrSelectNode() {
        return currSelectNode;
    }

    public void setCurrSelectNode(int currSelectNode) {
        this.currSelectNode = currSelectNode;
    }

    public List<JRTransConnection> getTransConns() {
        return transConns;
    }
    private int currSelectNode;
    private List<JRTransConnection> transConns;
}
