package com.basicstrong.functionalprogramming.section14;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ListDemo {

  public static void main(String[] args) {
    ListFun<Integer> list = ListFun.list(2, 3, 6, 9);
    printList(list);

    ListFun<Integer> newList = list.addElement(56);
    printList(newList);

    ListFun<Integer> removeElementList = newList.removeElement(3);
    printList(removeElementList);

    printList(removeElementList.reverList());

    ListFun<Integer> list1 = ListFun.list(2, 3, 6, 9);
    ListFun<Integer> list2 = ListFun.list(53, 78, 90);

    printList(ListFun.concat(list1, list2));

    List<Integer> l = List.of(2, 5, 8, 7);
    printList(list1.addAllElement(l));
  }

  private static void printList(ListFun<Integer> list) {
    log.info("===========");
    list.forEach(num -> log.info(String.valueOf(num)));
  }
}
