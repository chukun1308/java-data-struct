package com.chukun.datastruct.bstree;

/**
  *@auther : chukun
  *@description : 二叉排序树的基本操作
  *@create : 2019/7/14 1:19
  *@copyright www.hualala.com
  */
public class BinarySearchTreeOperator {

    public static void main(String[] args) {

    }
}

class BinarySearchTree{

    private BinarySearchTreeNode root = null;

    /**
     * 添加节点
     * @param value
     */
    public void addNode(int value){
        if(root==null){
            root = new BinarySearchTreeNode(value);
        }else{
            root.addNode(value);
        }
    }

    public void infixOrder(){
        if(root==null){
            System.out.println("该二叉树为空，不能遍历");
            return;
        }
        root.infixOrder();
    }

    /**
     * 删除节点
     * @param value
     */
    public void delNode(int value){
        if(root==null){
            throw  new IllegalArgumentException("该树不存在该节点，不能删除");
        }
        root.delNode(root,value);
    }
}

class BinarySearchTreeNode{
    int value;
    BinarySearchTreeNode leftNode;
    BinarySearchTreeNode rightNode;

    public BinarySearchTreeNode(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinarySearchTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinarySearchTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinarySearchTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinarySearchTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "BinarySearchTreeNode{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点
     * @param value
     */
    public void addNode(int value){
        if(value<this.value){
            //想左递归添加
            if(this.leftNode==null){
                this.leftNode = new BinarySearchTreeNode(value);
            }else{
                this.leftNode.addNode(value);
            }
        }else{
            //向右递归添加节点
            if(this.rightNode==null){
                this.rightNode = new BinarySearchTreeNode(value);
            }else{
                this.rightNode.addNode(value);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if(this.getLeftNode()!=null){
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        if(this.getRightNode()!=null){
            this.rightNode.infixOrder();
        }
    }

    /**
     * 查找节点
     * @param value
     * @return
     */
    public BinarySearchTreeNode searchNode(int value){
        BinarySearchTreeNode resultNode = null;
        if(this.getValue()==value){
            return this;
        } else if(value<this.getValue()){
            //向左遍历查找
            if(this.leftNode!=null){
                resultNode = this.leftNode.searchNode(value);
            }
            if(resultNode!=null){
                return resultNode;
            }
        }else{
            //向有遍历查找
            if(this.rightNode!=null){
                resultNode = this.rightNode.searchNode(value);
            }
            return resultNode;
        }
        return null;
    }

    /**
     * 查找父节点
     * @param value
     * @return
     */
    public BinarySearchTreeNode searchParentNode(int value){
        if((this.leftNode!=null && this.leftNode.value==value)
                ||(this.rightNode!=null && this.rightNode.value==value)){
            return this;
        }else if(this.leftNode!=null && this.leftNode.value>value){
            return this.leftNode.searchParentNode(value);
        }else if(this.rightNode!=null && this.rightNode.value<value){
            return this.rightNode.searchParentNode(value);
        }else{
            return null;
        }
    }

    /**
     * 查找当前节点的右边节点的最小节点
     * @param currentNode
     * @return
     */
    public BinarySearchTreeNode searchRightMinNode(BinarySearchTreeNode currentNode){
        BinarySearchTreeNode tempNode = currentNode;
        while(tempNode.leftNode!=null){
            tempNode = tempNode.leftNode;
        }
        return tempNode;
    }
    /**
     * 查找当前节点的左边节点的最大节点
     * @param currentNode
     * @return
     */
    public BinarySearchTreeNode searchLeftMaxNode(BinarySearchTreeNode currentNode){
        BinarySearchTreeNode tempNode = currentNode;
        while(tempNode.rightNode!=null){
            tempNode = tempNode.rightNode;
        }
        return tempNode;
    }

    /**
     * 删除节点
     *  第一种情况:
     * 删除叶子节点 (比如：2, 5, 9, 12)
     * 思路
     * (1) 需求先去找到要删除的结点  targetNode
     * (2)  找到targetNode 的 父结点 parent
     * (3)  确定 targetNode 是 parent的左子结点 还是右子结点
     * (4)  根据前面的情况来对应删除
     * 左子结点 parent.left = null
     * 右子结点 parent.right = null;
     * 第二种情况: 删除只有一颗子树的节点 比如 1
     * 思路
     * (1) 需求先去找到要删除的结点  targetNode
     * (2)  找到targetNode 的 父结点 parent
     * (3) 确定targetNode 的子结点是左子结点还是右子结点
     * (4) targetNode 是 parent 的左子结点还是右子结点
     * (5) 如果targetNode 有左子结点
     * 5. 1 如果 targetNode 是 parent 的左子结点
     * parent.left = targetNode.left;
     * 5.2  如果 targetNode 是 parent 的右子结点
     * parent.right = targetNode.left;
     * (6) 如果targetNode 有右子结点
     * 6.1 如果 targetNode 是 parent 的左子结点
     * parent.left = targetNode.right;
     * 6.2 如果 targetNode 是 parent 的右子结点
     * parent.right = targetNode.right
     *
     *
     * 情况三 ： 删除有两颗子树的节点. (比如：7, 3，10 )
     * 思路
     * (1) 需求先去找到要删除的结点  targetNode
     * (2)  找到targetNode 的 父结点 parent
     * (3)  从targetNode 的右子树找到最小的结点
     * (4) 用一个临时变量，将 最小结点的值保存 temp = 11
     * (5)  删除该最小结点
     * (6)  targetNode.value = temp
     * @param root
     * @param value
     */
    public void delNode(BinarySearchTreeNode root,int value){
        BinarySearchTreeNode parentNode = searchParentNode(value);
        //第一种情况,删除的是叶子结点
        if(this.value==value &&(this.leftNode==null && this.rightNode==null)){
            //判断叶子结点是该父节点的左节点，还是有节点
            if(parentNode!=null && parentNode.leftNode.value==this.value){
                //是左节点
                parentNode.leftNode = null;
            }
            if(parentNode!=null && parentNode.rightNode.value==this.value){
                //右子节点
                parentNode.rightNode = null;
            }
        }else if(this.value==value &&(this.leftNode!=null && this.rightNode!=null)){
            //左右节点都存在
            BinarySearchTreeNode currentNode = searchRightMinNode(this);
            this.value = currentNode.value;
            currentNode = null;
        }else {
            //只有一个节点
            if(parentNode!=null){
                if(parentNode.leftNode!=null) {
                    if (this.value == value && parentNode.leftNode.value == value && this.leftNode != null) {
                        parentNode.leftNode = this.leftNode;
                    }
                    if(this.value==value && parentNode.leftNode.value==value && this.rightNode!=null){
                        parentNode.leftNode = this.rightNode;
                    }
                }
                if(parentNode.rightNode!=null){
                    if (this.value == value && parentNode.rightNode.value == value && this.leftNode != null) {
                        parentNode.rightNode = this.leftNode;
                    }
                    if(this.value==value && parentNode.rightNode.value==value && this.rightNode!=null){
                        parentNode.rightNode = this.rightNode;
                    }
                }
            }else{
                if(this.leftNode!=null){
                    root = this.leftNode;
                }
                if(this.rightNode!=null){
                    root = this.rightNode;
                }
            }
        }
    }

}
