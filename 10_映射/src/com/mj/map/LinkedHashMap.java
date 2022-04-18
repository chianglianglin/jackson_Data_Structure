package com.mj.map;

public class LinkedHashMap<K, V> extends HashMap<K, V> {
	private LinkedNode<K,V> first;
	private LinkedNode<K,V> last;

	@Override
	public void clear() {
		super.clear();
		last = null;
		first = null;
	}

	@Override
	protected void afterRemove(Node<K,V> removeNode){
		LinkedNode<K,V> linkedNode = (LinkedNode<K, V>) removeNode;
		LinkedNode<K,V> prev = linkedNode.prev;
		LinkedNode<K,V> next = linkedNode.next;
		if (prev == null){
			first = next;
		}else{
			prev.next = next;
		}
		if (next == null){
			last = prev;
		}else{
			next.prev = prev;
		}
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		if (visitor == null)return;
		LinkedNode<K,V> node = first;
		while (node != null){
			if (visitor.visit(node.key,node.value))return;
			node = node.next;
		}
	}

	@Override
	protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
		LinkedNode node = new LinkedNode<>(key, value, parent);
		if (first == null){
			first = last = node;
		}else{
			last.next = node;
			node.prev = last;
			last = node;
		}
		return node;
	}

	private static class LinkedNode<K, V> extends Node<K, V> {
		LinkedNode<K,V> prev;
		LinkedNode<K,V> next;
		public LinkedNode(K key, V value, Node<K, V> parent) {
			super(key, value, parent);
		}
	}
}
