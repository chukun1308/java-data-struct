package com.chukun.datastruct.linkedlist;

import com.chukun.data.structure.enums.OrderEnum;
import java.util.Optional;
import java.util.Stack;

/**
 * 单链表的面试题
 */
public class SingleLinkedListExamination {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        System.out.println("======================排序插入节点=======================");
        HeroNode newHeroNode01 = new HeroNode("01","宋江","及时雨");
        HeroNode newHeroNode04 = new HeroNode("04","公孙胜","入云龙");
        HeroNode newHeroNode03 = new HeroNode("03","吴用","智多星");
        HeroNode newHeroNode02 = new HeroNode("02","卢俊义","玉麒麟");
        singleLinkedList.addHeroNodeByOrder(newHeroNode01, OrderEnum.ASC);
        singleLinkedList.addHeroNodeByOrder(newHeroNode04,OrderEnum.ASC);
        singleLinkedList.addHeroNodeByOrder(newHeroNode03,OrderEnum.ASC);
        singleLinkedList.addHeroNodeByOrder(newHeroNode02,OrderEnum.ASC);
        singleLinkedList.listHeroNodes();
        System.out.println("=====================获取链表有效节点的个数====================");
        System.out.println(getSingleLinkedListLength(singleLinkedList.getHead()));
        System.out.println("======================获取节点倒数第几个节点====================");
        System.out.println(getIndexNodeByReverseIndex(singleLinkedList.getHead(),1));

        System.out.println("========================逆序单链表============================");
        reverseSinglelinkedList(singleLinkedList.getHead());
        singleLinkedList.listHeroNodes();

        System.out.println("=========================逆序打印链表=========================");
        reversePrintSingleLinkedList(singleLinkedList.getHead());

    }


    /**
     * 获取单链表有效节点的长度
     * @param head
     * @return
     */
    public static int getSingleLinkedListLength(HeroNode head){

        /**
         * 单链表为空
         */
        if(head.getNextHero()==null){
            return 0;
        }
        HeroNode tempNode = head.getNextHero();
        int length = 0;
        while(tempNode!=null){
            length++;
            tempNode = tempNode.getNextHero();
        }
        return length;
    }

    /**
     * 获取单链表倒数第几个节点
     * @param head
     * @param index
     * @return
     */
    public static Optional<HeroNode> getIndexNodeByReverseIndex(HeroNode head, int index){
        if(head.getNextHero()==null){
            return Optional.empty();
        }
        int length = getSingleLinkedListLength(head);
        //检验index的合法性
        if(index<=0 || index>length){
            return Optional.empty();
        }

        int searchIndex = length-index;
        HeroNode tempNode = head.getNextHero();
        for(int i=0;i<searchIndex;i++){
            tempNode = tempNode.getNextHero();
        }
        return Optional.of(tempNode);
    }

    /**
     * 反转链表
     * @param head
     */
    public static void reverseSinglelinkedList(HeroNode head){
        //单链表为空，或者只有一个节点，不需要反转
        if(head.getNextHero()==null || head.getNextHero().getNextHero()==null){
            return;
        }
        //记录当前节点
        HeroNode currentNode = head.getNextHero();
        //记录当前节点的下一个节点
        HeroNode nextNode = null;
        //创建反转节点的头节点
        HeroNode reverseHeadNode = new HeroNode();
        while(currentNode!=null){
            nextNode = currentNode.getNextHero();
            currentNode.setNextHero(reverseHeadNode.getNextHero());
            reverseHeadNode.setNextHero(currentNode);
            currentNode = nextNode;
        }
        //原链表与现链表连接起来
        head.setNextHero(reverseHeadNode.getNextHero());
    }

    /**
     * 逆序打印单链表,借助栈这种数据结构
     * @param head
     */
    public static void reversePrintSingleLinkedList(HeroNode head){
        if(head.getNextHero()==null){
            return;
        }
        //保存单链表的栈
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode tempNode = head.getNextHero();
        //将链表压入栈
        while(tempNode!=null){
            heroNodeStack.push(tempNode);
            //后移节点
            tempNode = tempNode.getNextHero();
        }
        while(heroNodeStack.size()>0){
            HeroNode heroNode = heroNodeStack.pop();
            System.out.println(heroNode);
        }
    }

}
