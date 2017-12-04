package com.tree;

import java.util.Iterator;

public class TreeNodeIterS<T> implements Iterator<TreeNodeS<T>> {

	enum ProcessStages {
		ProcessParent, ProcessChildCurNode, ProcessChildSubNode
	}

	private TreeNodeS<T> treeNode;

	public TreeNodeIterS(TreeNodeS<T> treeNode) {
		this.treeNode = treeNode;
		this.doNext = ProcessStages.ProcessParent;
		this.childrenCurNodeIter = treeNode.children.iterator();
	}

	private ProcessStages doNext;
	private TreeNodeS<T> next;
	private Iterator<TreeNodeS<T>> childrenCurNodeIter;
	private Iterator<TreeNodeS<T>> childrenSubNodeIter;

	@Override
	public boolean hasNext() {

		if (this.doNext == ProcessStages.ProcessParent) {
			this.next = this.treeNode;
			this.doNext = ProcessStages.ProcessChildCurNode;
			return true;
		}

		if (this.doNext == ProcessStages.ProcessChildCurNode) {
			if (childrenCurNodeIter.hasNext()) {
				TreeNodeS<T> childDirect = childrenCurNodeIter.next();
				childrenSubNodeIter = childDirect.iterator();
				this.doNext = ProcessStages.ProcessChildSubNode;
				return hasNext();
			}

			else {
				this.doNext = null;
				return false;
			}
		}
		
		if (this.doNext == ProcessStages.ProcessChildSubNode) {
			if (childrenSubNodeIter.hasNext()) {
				this.next = childrenSubNodeIter.next();
				return true;
			}
			else {
				this.next = null;
				this.doNext = ProcessStages.ProcessChildCurNode;
				return hasNext();
			}
		}

		return false;
	}

	@Override
	public TreeNodeS<T> next() {
		return this.next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}