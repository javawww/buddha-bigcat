package com.buddha.component.base.model;

import java.util.List;

/**
 * Created by tsq on 2019-02-18.
 */
public class JRTreeNode<T> {
    private String text;
    private String id;
    private boolean expanded;
    private T currNode;
    private List<JRTreeNode<T>> children;
    public List<JRTreeNode<T>> getChildren() {
        return children;
    }
    public void setChildren(List<JRTreeNode<T>> childNodes) {
        this.children = childNodes;
    }

    public T getCurrNode() {
        return currNode;
    }
    public void setCurrNode(T currNode) {
        this.currNode = currNode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLeaf() {
        return this.children==null || this.children.size()<=0;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
