package com.basicstrong.functionalprogramming.section14;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.function.Consumer;

public abstract class ListFun<T> {

  public abstract T head();

  public abstract ListFun<T> tail();

  public abstract boolean isEmpty();

  @SuppressWarnings("rawtypes")
  public static final ListFun NIL = new Nil();


  private static class Nil<T> extends ListFun<T> {

    @Override
    public T head() {
      return null;
    }

    @Override
    public ListFun<T> tail() {
      return null;
    }

    @Override
    public boolean isEmpty() {
      return true;
    }
  }

  @AllArgsConstructor
  private static class Const<T> extends ListFun<T> {

    private final T head;
    private final ListFun<T> tail;

    @Override
    public T head() {
      return head;
    }

    @Override
    public ListFun<T> tail() {
      return tail;
    }

    @Override
    public boolean isEmpty() {
      return length() == 0;
    }
  }

  public int length() {
    int l = 0;
    ListFun<T> temp = this;
    while (!temp.equals(NIL)) {
      l++;
      temp = temp.tail();
    }
    return l;
  }

  public static <T> ListFun<T> list() {
    return NIL;
  }

  public static <T> ListFun<T> list(T... t) {
    ListFun<T> temp = list();
    for (int i = t.length - 1; i >= 0; i--) {
      temp = new Const<>(t[i], temp);
    }
    return temp;
  }

  public ListFun<T> addElement(T e) {
    return new Const<>(e, this);
  }

  public void forEach(Consumer<? super T> action) {
    T current = this.head();
    ListFun<T> temp = this;
    for (int i = 0; i < length(); i++) {
      action.accept(current);
      temp = temp.tail();
      current = temp.head();
    }
  }

  public ListFun<T> removeElement(T ele) {
    if (this.length() == 0) {
      return this;
    } else if (ele.equals(this.head())) {
      return tail();
    } else {
      ListFun<T> newTail = tail().removeElement(ele);
      return new Const<>(head(), newTail);
    }
  }

  public ListFun<T> reverList() {
    T current = this.head();
    ListFun<T> list = list();
    ListFun<T> temp = this;
    while (!temp.equals(NIL)) {
      list = list.addElement(current);
      temp = temp.tail();
      current = temp.head();
    }
    return list;
  }

  public static <T> ListFun<T> concat(ListFun<T> list1, ListFun<T> list2) {
    return list1.isEmpty() ? list2 : new Const<>(list1.head(), concat(list1.tail(), list2));
  }

  public ListFun<T> addAllElement(final Collection<? extends T> list) {
    ListFun<T> result = this;
    for (T t : list) {
      result = result.addElement(t);
    }
    return result;
  }

}
