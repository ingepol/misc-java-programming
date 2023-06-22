package com.basicstrong.functionalprogramming.section14;

@SuppressWarnings("rawtypes")
public class TreeFun<E extends Comparable<E>> {

  private final E value;
  private final TreeFun<E> left;
  private final TreeFun<E> right;
  public final int size;
  public final int height;

  private static final TreeFun NIL = new TreeFun<>();


  @SuppressWarnings("unchecked")
  private TreeFun() {
    value = null;
    left = NIL;
    right = NIL;
    size = 0;
    height = -1;
  }

  private TreeFun(TreeFun<E> left, E value, TreeFun<E> right) {
    this.left = left;
    this.value = value;
    this.right = right;
    this.size = 1 + this.left.size + this.right.size;
    this.height = 1 + Math.max(this.left.height, this.right.height);
  }

  @SafeVarargs
  public static <E extends Comparable<E>> TreeFun tree(E... es) {
    TreeFun tree = NIL;
    for (E e : es) {
      tree = tree.insert(e);
    }

    return tree;
  }

  private TreeFun<E> insert(E newValue) {
    TreeFun<E> newNode = newValue.compareTo(this.value) > 0 ?
        new TreeFun<>(this.left, this.value, right.insert(newValue))
        : new TreeFun<>(this.left, newValue, this.right);
    newNode = newValue.compareTo(this.value) < 0 ?
        new TreeFun<>(left.insert(newValue), this.value, this.right) : newNode;
    return isEmpty() ? new TreeFun<>(NIL, newValue, NIL) : newNode;
  }

  private boolean isEmpty() {
    return size == 0;
  }

  public String toString() {
    return this.value != null ? String.format(" %s %s %s ", left, value, right) : "";
  }

  public TreeFun<E> remove(E element) {
    if (element.compareTo(this.value) < 0) {
      return new TreeFun<>(this.left.remove(element), this.value, this.right);
    } else if (element.compareTo(this.value) > 0) {
      return new TreeFun<>(this.left, this.value, this.right.remove(element));
    } else {
      //when node is having zero or one child
      if (this.left.isEmpty()) {
        return this.right;
      } else if (this.right.isEmpty()) {
        return this.left;
      }
      //have two child nodes- need to find the node which can replace root
      //either replace it by max node value from left subtree or smallest node from right subtree
      else {
        E max = min(this.right);
        return new TreeFun<>(this.left, max, this.right);
      }
    }
  }

  //function to find minimum node value
  private E min(TreeFun<E> tr) {
    E e = null;
    while (tr.left != null) {
      e = tr.value;
      tr = tr.right;
    }
    return e;
  }

  public boolean isMember(E value) {

    if (this.value != null) {
      if (value.compareTo(this.value) < 0) {
        return left.isMember(value);
      } else if (value.compareTo(this.value) > 0) {
        return right.isMember(value);
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  public E max() {
    return this.right.equals(NIL) ? this.value : this.right.max();
  }
}
