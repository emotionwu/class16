import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RBTree <K extends Comparable <K>,V>{

    //静态的不可以修改的
    private static final boolean RED=true;
    private static final boolean BLACK=true;

    public  class Node{

        public K key;
        public V value;
        public Node left,right;
        public boolean color;

        public Node(K key,V value){
            this.key=key;
            this.value=value;
            left=null;
            right=null;
            this.color=RED;
        }
    }

    private Node  root;
    private int size;

    public RBTree(){
        root=null;
        size=0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public boolean  isRed(Node node){
        if(node==null)
            return BLACK;
        return node.color;
    }

    //添加元素后两节点情形
    private Node leftRotate(Node node){
        Node x=node.right;

        node.right=x.left;
        x.left=node;
        x.color= node.color;
        node.color=RED;

        return x;
    }

    private Node rightRotate(Node node){
        Node x=node.left;

        node.left=x.right;
        x.right=node;
        x.color=node.color;
        node.color=RED;

        return x;
    }
    //add后三节点情形
    private void flipColors(Node node){

        node.color=RED;
        node.left.color=BLACK;
        node.right.color=BLACK;
    }

    public void addd(K key,V value){

        root =addd(root,key,value);
        root.color=BLACK;
    }
    private Node addd(Node node,K key,V value ){

        if(node==null){
            size++;
            return new Node(key,value);
        }

        if(key.compareTo(node.key)<0)
            node.left=addd(node.left,key,value);
        else if(key.compareTo(node.key)>0)
            node.right=addd(node.right,key,value);
        else
            node.value=value;

        if(isRed(node.right)&&!isRed(node.left))//右节点为红色，左节点为黑色或无左节点
            node=leftRotate(node);

        if(isRed(node.left)&&isRed(node.left.left))//左节点为红色且左节点的左节点为红色
            node=rightRotate(node);

        if(isRed(node.left)&&isRed(node.right))//左节点右节点都为红色
            flipColors(node);

        return node;
    }


}
