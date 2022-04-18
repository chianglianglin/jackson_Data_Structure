package com.mj.tree;

import com.mj.printer.BinaryTrees;

import java.util.Random;

public class TestMorris {
	private static class MorrisTree extends BinarySearchTree<Integer> {
		public void inorder() {
			Node<Integer> node = root;
			while(node != null){
				if (node.left != null){
					//找前驅節點
					Node<Integer> pred = node.left;
					while (pred.right != null && pred.right != node){
						pred = pred.right;
					}
					//第一次找到前驅節點後把right復植node
					if (pred.right == null){
						pred.right = node;
						node = node.left;
					}else{
						//第二次發現pred.right != null
						//打印後復值為null 為了恢復整個架構
						System.out.print(node.element + " ");
						pred.right = null;
						node = node.right;
					}
				}else{
					System.out.print(node.element + " ");
					node = node.right;
				}
			}
		}


	}
//	private static class MorrisTree extends BinarySearchTree<Integer>{
//		public void inorder(){
//				inorder(root);
//		}
//
//		private void inorder(Node<Integer> root){
//			if (root == null)return;
//			inorder(root.left);
//			System.out.print(root.element + "  ");
//			inorder(root.right);
//		}
//	}

	public static void main(String[] args) {
		MorrisTree tree = new MorrisTree();
		for (int i = 0; i < 10; i++) {
			tree.add(new Random().nextInt(100));
		}
		BinaryTrees.println(tree);
		System.out.println("-------------------");
		tree.inorder();
		System.out.println("-------------------");
		BinaryTrees.println(tree);
	}
}
