package com.chukun.datastruct.linkedlist;

import com.chukun.data.structure.enums.OrderEnum;

/**
 * @author chukun
 * 单链表的基本操作
 */
public class SingleLinkedListOperator {

    public static void main(String[] args) {
          SingleLinkedList singleLinkedList = new SingleLinkedList();
//        HeroNode heroNode01 = new HeroNode("01","宋江","及时雨");
//        HeroNode heroNode02 = new HeroNode("02","卢俊义","玉麒麟");
//        HeroNode heroNode03 = new HeroNode("03","吴用","智多星");
//        HeroNode heroNode04 = new HeroNode("04","公孙胜","入云龙");
//        singleLinkedList.addHeroNode(heroNode01);
//        singleLinkedList.addHeroNode(heroNode02);
//        singleLinkedList.addHeroNode(heroNode03);
//        singleLinkedList.addHeroNode(heroNode04);
//        System.out.println("======================添加之后=======================");
//        singleLinkedList.listHeroNodes();
//        System.out.println("======================修改节点=======================");
//        HeroNode updateHeroNode02 = new HeroNode("02","卢俊义~~","玉麒麟~~");
//        singleLinkedList.updateHeroNode(updateHeroNode02);
//        singleLinkedList.listHeroNodes();
//        System.out.println("======================删除节点=======================");
//        singleLinkedList.delHeroNode("01");
//        singleLinkedList.delHeroNode("02");
//        singleLinkedList.delHeroNode("03");
//        singleLinkedList.delHeroNode("04");
//        singleLinkedList.listHeroNodes();
        System.out.println("======================排序插入节点=======================");
        HeroNode newHeroNode01 = new HeroNode("01","宋江","及时雨");
        HeroNode newHeroNode04 = new HeroNode("04","公孙胜","入云龙");
        HeroNode newHeroNode03 = new HeroNode("03","吴用","智多星");
        HeroNode newHeroNode02 = new HeroNode("02","卢俊义","玉麒麟");
        singleLinkedList.addHeroNodeByOrder(newHeroNode01,OrderEnum.ASC);
        singleLinkedList.addHeroNodeByOrder(newHeroNode04,OrderEnum.ASC);
        singleLinkedList.addHeroNodeByOrder(newHeroNode03,OrderEnum.ASC);
        singleLinkedList.addHeroNodeByOrder(newHeroNode02,OrderEnum.ASC);
        singleLinkedList.listHeroNodes();
    }
}

/**
 * 封装单链表的基本操作
 */
class SingleLinkedList{
    private HeroNode head;

    public SingleLinkedList(){
        this.head = new HeroNode();
    }

    public HeroNode getHead() {
        return head;
    }

    /**
     * 队尾添加节点
     * @param heroNode
     */
    public void addHeroNode(HeroNode heroNode){
        boolean isInsert = true;
        HeroNode tempNode = head;
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
        }else{
            System.out.printf("链表中已经存在改编号%d的节点，不能插入",heroNode.gethNo());
        }
    }

    /**
     * 遍历节点
     */
    public void listHeroNodes(){

        HeroNode tempNode = this.head.getNextHero();
        while(tempNode!=null){
            System.out.println(tempNode);
            tempNode = tempNode.getNextHero();
        }
    }

    /**
     * 更新节点
     * @param heroNode
     */
    public void updateHeroNode(HeroNode heroNode){
        if(this.head.getNextHero()==null){
            System.out.println("该链表数据为空，不能更新");
            return;
        }
        HeroNode tempNode = this.head.getNextHero();
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
        HeroNode tempNode = this.head;
        while(true){
            if(tempNode.getNextHero()==null){
                isDel = false;
                break;
            }
            if(tempNode.getNextHero().gethNo().equals(hNo)){
                break;
            }
            tempNode = tempNode.getNextHero();
        }
        if(isDel){
            tempNode.setNextHero(tempNode.getNextHero().getNextHero());
        }else{
            System.out.printf("该链表中没有编号%d的节点,不能删除",hNo);
        }
    }

    /**
     * @param heroNode
     * @param order
     */
    public void addHeroNodeByOrder(HeroNode heroNode, OrderEnum order){
        boolean isInsert = true;
        HeroNode tempNode = this.head;
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
            tempNode.setNextHero(heroNode);
        }else{
            System.out.printf("链表中已经存在改编号%d的节点，不能插入",heroNode.gethNo());
        }
    }

}

/**
 * 定义单链表的数据结构
 */
class HeroNode{
    private String hNo;
    private String hName;
    private String hNickName;
    private HeroNode nextHero;

    public HeroNode() {
        this.nextHero = null;
    }

    public HeroNode(String hNo, String hName, String hNickName){
        this.hNo = hNo;
        this.hName = hName;
        this.hNickName = hNickName;
        this.nextHero = null;
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

    public HeroNode getNextHero() {
        return nextHero;
    }

    public void setNextHero(HeroNode nextHero) {
        this.nextHero = nextHero;
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
