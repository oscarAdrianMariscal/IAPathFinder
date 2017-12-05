package com.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeNodeS<T> implements Iterable<TreeNodeS<T>>, Comparable<TreeNodeS<T>>{

	public T data;
	public TreeNodeS<T> parent;
	public List<TreeNodeS<T>> children;
	public boolean abierto;
	public ArrayList<Integer> visitas= new ArrayList<>();
        
        //Costo
        float Costo;
        //Suma de costos
        float GN;
        //Suma de distancias
        float HN;
        //Costo estimado de la solucion
        float FN;
        
	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	private List<TreeNodeS<T>> elementsIndex;

	public TreeNodeS(T data) {
		this.data = data;
		this.children = new LinkedList<TreeNodeS<T>>();
		this.elementsIndex = new LinkedList<TreeNodeS<T>>();
		this.elementsIndex.add(this);
		this.abierto = true;
	}

	public TreeNodeS<T> addChild(T child) {
		TreeNodeS<T> childNode = new TreeNodeS<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	private void registerChildForSearch(TreeNodeS<T> node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public TreeNodeS<T> findTreeNode(Comparable<T> cmp) {
		for (TreeNodeS<T> element : this.elementsIndex) {
			T elData = element.data;
			if (cmp.compareTo(elData) == 0)
				return element;
		}

		return null;
	}
        //OBTENER COSTO ANTERIOR + COSTO ACTUAL
        public void setCosto(float Costo){
            this.Costo = Costo;
        }
        
        public float getCosto()
        {
            return Costo;
        }
        
        //OBTENER COSTO ANTERIOR + COSTO ACTUAL
        public void setGN(float GN){
            this.GN = GN;
        }
        
        public float getGN()
        {
            return GN;
        }
        
        //DISTANCIA ACTUAL A FIN
        public void setHN(float HN)
        {
            this.HN = HN;
        }
        
        public float getHN()
        {
            return HN;
        }

        //OBTENER F(N)
        public void setFN(float FN)
        {
             this.FN = FN;
        }
        
        public float getFN()
        {
            return FN;
        }
        
        public TreeNodeS<T> getPreviousNode() {
                return parent;
        }

	@Override
	public String toString() {
            return data != null ? data.toString() : "[data null]";
	}

    @Override
    public Iterator<TreeNodeS<T>> iterator() {
        TreeNodeIterS<T> iter = new TreeNodeIterS<T>(this);
	return iter;
    }

    @Override
    public int compareTo(TreeNodeS<T> t) {
        if (FN < t.FN) {
                return -1;
            }
            if (FN > t.FN) {
                return 1;
            }
            return 0;
    }
}