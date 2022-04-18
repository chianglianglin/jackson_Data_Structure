package com.mj.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class HashMap<K,V> implements Map<K,V>{
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private  int size;
    private Node<K,V>[] table;
    private static final int DEFAULT_CAPACITY = 1<<4;


    public HashMap(){
        table = new Node[DEFAULT_CAPACITY];
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) return;
        size= 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null){
            root = createNode(key,value,null);
            table[index] = root;
            size++;
            fixAfterPut(root);
            return null;
        }
        //添加新的節點 根節點不為空
        Node<K,V> parent = root;
        Node<K,V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = k1 == null?0:key.hashCode();
        Node<K,V> result = null;
        boolean searched = false;//是否已經搜索過
        do{
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2){
                cmp = 1;
            }else if (h1 < h2){
                cmp = -1;
            } else if (Objects.equals(k1,k2)){
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) !=0){
                // >0 <0  ==0 把compareTo cmp == 0排除 不能因為cmp == 0就覆蓋值
//                cmp = ((Comparable) k1).compareTo(k2);
            } else if (searched){
                //searched == true 掃描過了
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }else {
                //還沒有掃描,然後再根據內存地址大小決定左右
                if ((node.left != null && (result = node(node.left,k1)) != null)
                        || (node.right != null && (result = node(node.left,k1)) != null)){
                    //已經存在這個key
                    node = result;
                    cmp = 0;
                }else{
                    //不存在這個key
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }

            }
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }while (node != null );
        
        Node<K,V> newNode = createNode(key,value,parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        //新增節點後處理
        fixAfterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K,V> node = node(key);
        return node != null? node.value : null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;
        Queue<Node<K,V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null)continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if (Objects.equals(value,node.value))return true;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) return ;
        Queue<Node<K,V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null)continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if (visitor.visit(node.key,node.value))return ;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
    }


    protected V remove(Node<K,V> node){
        if (node == null) return null;
        Node<K, V> oldNode = node;
        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;
            // 删除后继节点
            // 这里是因为后面必然会删除node节点
            // 所以直接将后继节点赋给node,在后面将它删除
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;

        int index = index(node);
        if (replacement != null) { // node是度为1的节点
            // 核心：用子节点替代原节点的位置
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理，BST中无需处理，为 AVL树 和 B树提供可覆盖的方法
            fixAfterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;

            // 删除节点之后的处理，BST中无需处理，为 AVL树 和 B树提供可覆盖的方法
            fixAfterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) { // 是左子树
                node.parent.left = null;
            } else { // node == node.parent.right // 是右子树
                node.parent.right = null;
            }

            // 删除节点之后的处理，BST中无需处理，为 AVL树 和 B树提供可覆盖的方法
            fixAfterRemove(node);
        }
        afterRemove(node);
        return oldNode.value;
    }
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<K, V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }
    private Node<K,V> node(K key){
        Node<K,V> root = table[index(key)];
        return root == null ? null : node(root,key );
    }

    private Node<K,V> node(Node<K,V> node,K k1){
        int h1 = k1 == null?0:k1.hashCode();
        //儲存查找結果
        Node<K,V> result = null;
        int cmp = 0;
        while (node != null){
            K k2 = node.key;
            int h2 = node.hash;
            //先比較哈希值
            if(h1 > h2){
                node = node.right;
            }else if (h1 < h2){
                node = node.left;
            }else if(Objects.equals(k1,k2)){
                return node;
            }else if(k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) !=0){
                node = cmp > 0 ? node.right : node.left;
            }else if (node.right != null && (result = node(node.right,k1)) != null) {//哈希值相等,不具備可比較性,也不equals
                return result;
            } else {//只能往左邊找
                node = node.left;
            }
//            }else if (node.left != null && (result = node(node.left,k1)) != null){
//                return result;
//            }else{
//                return null;
//            }

        }
        return null;

    }

    private int compare(K k1, K k2,int h1,int h2) {
            //比較哈希值
        int result = h1 - h2;
        if (result != 0)return result;
        if (Objects.equals(k1,k2))return 0;

        //哈希值相同,但是不equals
        //比較類名
        if (k1 != null && k2 != null
              && k1.getClass() == k2.getClass()
                && k1 instanceof Comparable){
            //同一種類型並且具備可比較性
            if (k1 instanceof Comparable) return ((Comparable) k1).compareTo(k2);
        }
        //同一種類型,但是不具備可比較性
        //k1不為null,k2為null
        //k1為null,k2不為null

        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    private void fixAfterPut(Node<K,V> node){
        Node<K, V> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            fixAfterPut(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            table[index(grand)] = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }
    private void fixAfterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private int index(K key){
        if (key == null)return 0;
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & (table.length - 1);
    }
    private int index(Node<K, V> node) {
        return node.hash & (table.length - 1);
    }
    private int hash(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    protected Node<K,V> createNode(K key, V value, Node<K, V> parent){
        return new Node<>(key, value, parent);
    }
    protected void afterRemove(Node<K,V> removeNode){}

    protected static class Node<K,V>{
        int hash;
        K key;
        V value;
        boolean color=RED;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;

        public Node(K key, V value, Node<K, V> parent) {
            this.hash = key == null? 0:key.hashCode();
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
        public boolean isLeaf() { // 是否为叶子结点
            return left == null && right == null;
        }

        public boolean hasTwoChildren() { // 是否有两个子节点
            return left != null && right != null;
        }

        public boolean isLeftChild() { // 是否为左节点
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() { // 是否为右节点
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() { // 返回兄弟节点
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }
}
