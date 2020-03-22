package com.chukun.datastruct.linkedlist;

import com.chukun.datastruct.enums.OrderEnum;

/**
 * @author chukun
 * 双向链表的基本操作
 */
public class DoubleLinkedListOperator {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        DoubleHeroNode heroNode01 = new DoubleHeroNode("01","宋江","及时雨");
//        DoubleHeroNode heroNode02 = new DoubleHeroNode("02","卢俊义","玉麒麟");
//        DoubleHeroNode heroNode03 = new DoubleHeroNode("03","吴用","智多星");
//        DoubleHeroNode heroNode04 = new DoubleHeroNode("04","公孙胜","入云龙");
//        doubleLinkedList.addHeroNode(heroNode01);
//        doubleLinkedList.addHeroNode(heroNode02);
//        doubleLinkedList.addHeroNode(heroNode03);
//        doubleLinkedList.addHeroNode(heroNode04);
//        System.out.println("======================添加之后=======================");
//        doubleLinkedList.listHeroNodes();
//        System.out.println("======================修改节点=======================");
//        DoubleHeroNode updateHeroNode02 = new DoubleHeroNode("02","卢俊义~~","玉麒麟~~");
//        doubleLinkedList.updateHeroNode(updateHeroNode02);
//        doubleLinkedList.listHeroNodes();
//        System.out.println("======================删除节点=======================");
//        doubleLinkedList.delHeroNode("01");
//        doubleLinkedList.delHeroNode("02");
//        doubleLinkedList.delHeroNode("03");
//        doubleLinkedList.delHeroNode("04");
//        doubleLinkedList.listHeroNodes();
        System.out.println("======================排序插入节点=======================");
        DoubleHeroNode newHeroNode01 = new DoubleHeroNode("01","宋江","及时雨");
        DoubleHeroNode newHeroNode04 = new DoubleHeroNode("04","公孙胜","入云龙");
        DoubleHeroNode newHeroNode03 = new DoubleHeroNode("03","吴用","智多星");
        DoubleHeroNode newHeroNode02 = new DoubleHeroNode("02","卢俊义","玉麒麟");
        doubleLinkedList.addHeroNodeByOrder(newHeroNode01,OrderEnum.ASC);
        doubleLinkedList.addHeroNodeByOrder(newHeroNode04,OrderEnum.ASC);
        doubleLinkedList.addHeroNodeByOrder(newHeroNode03,OrderEnum.ASC);
        doubleLinkedList.addHeroNodeByOrder(newHeroNode02,OrderEnum.ASC);
        doubleLinkedList.listHeroNodes();
    }
}

/**
 * 封装单链表的基本操作
 */
class DoubleLinkedList{
    private DoubleHeroNode head;

    public DoubleLinkedList(){
        this.head = new DoubleHeroNode();
    }

    public DoubleHeroNode getHead() {
        return head;
    }

    /**
     * 队尾添加节点
     * @param heroNode
     */
    public void addHeroNode(DoubleHeroNode heroNode){
        boolean isInsert = true;
        DoubleHeroNode tempNode = head;
        while(true){
            if(tempNode.getNextHero()==null){
                break;
            }
            if(tempNode.getNextHero().gethNo().equals(heroNode.gethNo())){
                isInsert = false;
                break;
            }
            tempNode = tempNode.getNextHero();
        }
        if(isInsert) {
            tempNode.setNextHero(heroNode);
            heroNode.setPreHero(tempNode);
        }else{
            System.out.printf("链表中已经存在改编号%d的节点，不能插入",heroNode.gethNo());
        }
    }

    /**
     * 遍历节点
     */
    public void listHeroNodes(){

        DoubleHeroNode tempNode = this.head.getNextHero();
        while(tempNode!=null){
            System.out.println(tempNode);
            tempNode = tempNode.getNextHero();
        }
    }

    /**
     * 更新节点
     * @param heroNode
     */
    public void updateHeroNode(DoubleHeroNode heroNode){
        if(this.head.getNextHero()==null){
            System.out.println("该链表数据为空，不能更新");
            return;
        }
        DoubleHeroNode tempNode = this.head.getNextHero();
        boolean isUpdate = true;
        while(true){
            if(tempNode==null){
                isUpdate = false;
                break;
            }
            if(tempNode.gethNo().equals(heroNode.gethNo())){
                break;
            }
            tempNode = tempNode.getNextHero();
        }
        if(isUpdate){
            tempNode.sethNo(heroNode.gethNo());
            tempNode.sethName(heroNode.gethName());
            tempNode.sethNickName(heroNode.gethNickName());
        }else{
            System.out.printf("该链表中没有编号%d的节点,不能更新",heroNode.gethNo());
        }
    }

    /**
     * 根据编号删除节点
     * @param hNo
     */
    public void delHeroNode(String hNo){
        boolean isDel = true;
        DoubleHeroNode tempNode = this.head.getNextHero();
        if(tempNode==null){
            System.out.println("链表为空,不能执行删除操作.........");
            return;
        }
        while(true){
            if(tempNode.gethNo().equals(hNo)){
                break;
            }
            tempNode = tempNode.getNextHero();
        }
        if(isDel){
            tempNode.getPreHero().setNextHero(tempNode.getNextHero());
            if(tempNode.getNextHero()!=null){
                tempNode.getNextHero().setPreHero(tempNode.getPreHero());
            }
        }else{
            System.out.printf("该链表中没有编号%d的节点,不能删除",hNo);
        }
    }

    /**
     * @param heroNode
     * @param order
     */
    public void addHeroNodeByOrder(DoubleHeroNode heroNode, OrderEnum order){
        boolean isInsert = true;
        DoubleHeroNode tempNode = this.head;
        while(true){
            if(tempNode.getNextHero()==null){
                break;
            }
            if(tempNode.getNextHero().gethNo().equals(heroNode.gethNo())){
                isInsert = false;
                break;
            }
            if(OrderEnum.ASC.getCode()==order.getCode()){
                //升序
                if(tempNode.getNextHero().gethNo().compareTo(heroNode.gethNo())>0){
                    break;
                }
            }else{
                //降序
                if(tempNode.getNextHero().gethNo().compareTo(heroNode.gethNo())<0){
                    break;
                }
            }
            tempNode = tempNode.getNextHero();
        }
        if(isInsert){
            /**
             *  这步很关键，不然会出错
             *  若要插入的节点正好在两个节点之间的话，
             *  不执行这步操作，就会使后面的节点断开，被丢弃
             */
            heroNode.setNextHero(tempNode.getNextHero());
            if(tempNode.getNextHero()!=null) {
                tempNode.getNextHero().setPreHero(heroNode);
            }
            tempNode.setNextHero(heroNode);
            heroNode.setPreHero(tempNode);
        }else{
            System.out.printf("链表中已经存在改编号%d的节点，不能插入",heroNode.gethNo());
        }
    }

}

/**
 * 定义单链表的数据结构
 */
class DoubleHeroNode{
    private String hNo;
    private String hName;
    private String hNickName;
    private DoubleHeroNode nextHero;
    private DoubleHeroNode preHero;

    public DoubleHeroNode() {
        this.nextHero = null;
    }

    public DoubleHeroNode(String hNo, String hName, String hNickName){
        this.hNo = hNo;
        this.hName = hName;
        this.hNickName = hNickName;
        this.nextHero = null;
        this.preHero = null;
    }

    public String gethNo() {
        return hNo;
    }

    public void sethNo(String hNo) {
        this.hNo = hNo;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethNickName() {
        return hNickName;
    }

    public void sethNickName(String hNickName) {
        this.hNickName = hNickName;
    }

    public DoubleHeroNode getNextHero() {
        return nextHero;
    }

    public void setNextHero(DoubleHeroNode nextHero) {
        this.nextHero = nextHero;
    }

    public DoubleHeroNode getPreHero() {
        return preHero;
    }

    public void setPreHero(DoubleHeroNode preHero) {
        this.preHero = preHero;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "hNo='" + hNo + '\'' +
                ", hName='" + hName + '\'' +
                ", hNickName='" + hNickName + '\'' +
                '}';
    }
}
