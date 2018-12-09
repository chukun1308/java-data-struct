package com.chukun.datastruct.tree;

/**
 * AVLTree 的基本操作
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class AVLNode{
        public K key;
        public V value;
        public AVLNode leftNode,rightNode;
        public int height;

        public AVLNode(K key,V value){
            this.key = key;
            this.value=value;
            leftNode=null;
            rightNode=null;
            height=1;
        }
    }

    private AVLNode root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    // 获得节点node的高度
    public int getHeight(AVLNode node){
        if(node==null){
            return 0;
        }
        return node.height;
    }

    //获取节点的平衡因子
    public int getBalanceFactor(AVLNode node){
        if(node==null){
            return 0;
        }
        return getHeight(node.leftNode)-getHeight(node.rightNode);
    }

    public void add(K key,V value){
        root = add(root,key,value);
    }

    /**
     * AVL树添加节点的情况
     * @param node
     * @param key
     * @param value
     * @return
     */
    private AVLNode add(AVLNode node,K key,V value){
        if(node==null){
            size++;
            return new AVLNode(key,value);
        }
        if(key.compareTo(node.key)<0){
            node.leftNode = add(node.leftNode,key,value);
        }else if(key.compareTo(node.key)>0){
            node.rightNode = add(node.rightNode,key,value);
        }else {
            node.value = value;
        }

        //更新height
        node.height = Math.max(getHeight(node.leftNode),getHeight(node.rightNode))+1;

        //获取当前节点的平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if(Math.abs(balanceFactor)>1){
//            //破坏AVL树的性质了
//        }
        //LL 单一左子树的情况
        if(balanceFactor>1 && getBalanceFactor(node.leftNode)>=0){
            //需要右旋操作
            return rightRotate(node);
        }
        //RR 单一右子树的情况
        if(balanceFactor<-1 && getBalanceFactor(node.rightNode)<=0){
            //需要左旋操作
            return leftRotate(node);
        }
        //LR 左子树的右子树情况
        if(balanceFactor>1 && getBalanceFactor(node.leftNode)<0){
            //先把右子树作左旋操作
            node.leftNode= leftRotate(node.leftNode);
            //再把x作右旋操作
            return rightRotate(node);
        }
        //RL 右子树的左子树情况
        if(balanceFactor<-1 && getBalanceFactor(node.rightNode)>0){
            //先把左子树作右旋操作
            node.rightNode = rightRotate(node.rightNode);
            //再把x作右旋操作
            return leftRotate(node);
        }
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        AVLNode node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private AVLNode remove(AVLNode node,K key){
        if(node==null){
            return null;
        }
        AVLNode retNode;
        if( key.compareTo(node.key) < 0 ){
            node.leftNode = remove(node.leftNode , key);
            // return node;
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.rightNode = remove(node.rightNode, key);
            // return node;
            retNode = node;
        } else{   // key.compareTo(node.key) == 0
            // 待删除节点左子树为空的情况
            if(node.leftNode == null){
                AVLNode rightNode = node.rightNode;
                node.rightNode = null;
                size --;
                // return rightNode;
                retNode = rightNode;
            }
            // 待删除节点右子树为空的情况
            else if(node.rightNode == null){
                AVLNode leftNode = node.leftNode;
                node.leftNode = null;
                size --;
                // return leftNode;
                retNode = leftNode;
            }
            // 待删除节点左右子树均不为空的情况
            else{
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                AVLNode successor = minimum(node.rightNode);
                //successor.right = removeMin(node.right);
                successor.rightNode = remove(node.rightNode, successor.key);
                successor.leftNode = node.leftNode;

                node.leftNode = node.rightNode = null;

                // return successor;
                retNode = successor;
            }
        }
        if(retNode==null){
            return null;
        }

        //更新height
        retNode.height = Math.max(getHeight(retNode.leftNode),getHeight(retNode.rightNode))+1;

        //获取当前节点的平衡因子
        int balanceFactor = getBalanceFactor(retNode);
//        if(Math.abs(balanceFactor)>1){
//            //破坏AVL树的性质了
//        }
        //LL 单一左子树的情况
        if(balanceFactor>1 && getBalanceFactor(retNode.leftNode)>=0){
            //需要右旋操作
            retNode =  rightRotate(retNode);
        }
        //RR 单一右子树的情况
        if(balanceFactor<-1 && getBalanceFactor(retNode.rightNode)<=0){
            //需要左旋操作
            retNode =  leftRotate(retNode);
        }
        //LR 左子树的右子树情况
        if(balanceFactor>1 && getBalanceFactor(retNode.leftNode)<0){
            //先把右子树作左旋操作
            retNode.leftNode= leftRotate(retNode.leftNode);
            //再把x作右旋操作
            retNode =  rightRotate(retNode);
        }
        //RL 右子树的左子树情况
        if(balanceFactor<-1 && getBalanceFactor(retNode.rightNode)>0){
            //先把左子树作右旋操作
            retNode.rightNode = rightRotate(retNode.rightNode);
            //再把x作右旋操作
            retNode =  leftRotate(retNode);
        }
        return retNode;

    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private AVLNode minimum(AVLNode node){
        if(node.leftNode == null)
            return node;
        return minimum(node.leftNode);
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private AVLNode getNode(AVLNode node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.leftNode, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.rightNode, key);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private AVLNode rightRotate(AVLNode y){
        AVLNode x = y.leftNode;
        AVLNode T3 = x.rightNode;
        //右旋操作
        x.rightNode=y;
        y.leftNode=T3;
        //更新height的值，先更新y的height 在更新x的height
        y.height = Math.max(getHeight(y.leftNode),getHeight(y.rightNode))+1;
        x.height = Math.max(getHeight(x.leftNode),getHeight(x.rightNode))+1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private AVLNode leftRotate(AVLNode y){
        AVLNode x = y.rightNode;
        AVLNode T2 = x.leftNode;

        //左旋操作
        x.leftNode = y;
        y.rightNode=T2;

        //更新height的值
        // 更新height
        y.height = Math.max(getHeight(y.leftNode), getHeight(y.rightNode)) + 1;
        x.height = Math.max(getHeight(x.leftNode), getHeight(x.rightNode)) + 1;
        return x;
    }
}
