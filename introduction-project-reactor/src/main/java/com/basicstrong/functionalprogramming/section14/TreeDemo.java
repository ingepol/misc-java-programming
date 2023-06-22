package com.basicstrong.functionalprogramming.section14;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TreeDemo {

  public static void main(String[] args) {

    TreeFun<Integer> t = TreeFun.tree(23, 5, 76, 10, 3, 45);
    TreeFun<Integer> tree = t.remove(76);
    log.info(tree.toString());

    log.info(String.valueOf(tree.isMember(50)));
    log.info(tree.max().toString());


  }

}