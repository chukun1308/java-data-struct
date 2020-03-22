package com.chukun.datastruct.linkedlist;

/**
 * @author chukun
 * 约瑟夫环问题，单向环形链表解决
 */
public class JosephuPractise {

    public static void main(String[] args) {
       CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
       circleSingleLinkedList.addChild(5);
       circleSingleLinkedList.showChild();

       circleSingleLinkedList.josephuCircle(2,2,5);
    }
}

class CircleSingleLinkedList{

    private Child firstNode = new Child(-1);

    /**
     * 添加小孩
     * @param nums
     */
    public void addChild(int nums){
        if(nums<1){
            System.out.println("输入的创建小孩的数量有误.......");
            return;
        }
        Child currentNode = firstNode;
        for(int i=1;i<=nums;i++){
            Child child = new Child(i);
            if(i==1){
                firstNode = child;
                firstNode.setNextChild(child);
                child.setNextChild(firstNode);
            }else{
                currentNode.setNextChild(child);
                child.setNextChild(firstNode);
            }
            currentNode = child;
        }
    }

    /**
     * 展示小孩信息
     */
    public void showChild(){
        if(firstNode.getNextChild()==null){
            System.out.println("单向的循环链表为空,不能进行遍历操作");
            return;
        }
        Child currentNode = firstNode;
        while(true){
            System.out.println(currentNode);
            if(currentNode.getNextChild()==firstNode){
                break;
            }
            currentNode = currentNode.getNextChild();
        }
    }

    /**
     * 约瑟夫环问题解决
     * @param startNo
     * @param count
     * @param nums
     */
    public void josephuCircle(int startNo,int count,int nums){
        if(firstNode.getNextChild()==null || startNo<1 || startNo>nums){
            System.out.println("输入的参数有误，请检查........");
            return;
        }
        Child helper = firstNode;
        while(true){
            if(firstNode==helper.getNextChild()){
                break;
            }
            helper = helper.getNextChild();
        }
        for(int i=0;i<startNo-1;i++){
            firstNode = firstNode.getNextChild();
            helper = helper.getNextChild();
        }
        while(true){
            if(firstNode==helper){
                break;
            }
            for(int i=0;i<count-1;i++){
                firstNode = firstNode.getNextChild();
                helper = helper.getNextChild();
            }
            System.out.printf("第%d个小朋友出圈\n",firstNode.getChildNo());
            firstNode = firstNode.getNextChild();
            helper.setNextChild(firstNode);
        }
        System.out.printf("最后第%d个小孩出圈",helper.getChildNo());
    }
}

class Child{
    private int childNo;
    private Child nextChild;

    public Child(int childNo){
        this.childNo = childNo;
        this.nextChild = null;
    }
    public int getChildNo() {
        return childNo;
    }

    public void setChildNo(int childNo) {
        this.childNo = childNo;
    }

    public Child getNextChild() {
        return nextChild;
    }

    public void setNextChild(Child nextChild) {
        this.nextChild = nextChild;
    }

    @Override
    public String toString() {
        return "Child{" +
                "childNo=" + childNo +
                '}';
    }
}
