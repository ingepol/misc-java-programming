package com.basicstrong.functionalprogramming.section7.designpatterns;

import com.basicstrong.functionalprogramming.section7.Stock;
import com.basicstrong.functionalprogramming.section7.StockFilters;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StrategyPattern {

  public static void main(String[] args) {
    /*
      Is used when we have multiple solution or algorithms to solve a specific task 
      and client decides the specific implementation which is to be used at runtime.
     */

    List<Stock> stockList = List.of(
        Stock.builder().symbol("AAPL").price(318.65).units(10).build(),
        Stock.builder().symbol("MSFT").price(166.86).units(45).build(),
        Stock.builder().symbol("Google").price(99).units(12.5).build(),
        Stock.builder().symbol("AMZ").price(1866.74).units(45).build(),
        Stock.builder().symbol("GOOGL").price(1480.20).units(3.5).build(),
        Stock.builder().symbol("AAPL").price(318.65).units(8).build(),
        Stock.builder().symbol("AMZ").price(1866.74).units(9).build()
    );

    /*
    StockFilters.bySymbol(stockList, "AMZ").forEach(stock -> log.info(stock.toString()));    
    log.info("---------------------");
    StockFilters.byPriceAbove(stockList, 300).forEach(stock -> log.info(stock.toString()));        
     */
    StockFilters.filter(stockList, stock -> stock.getSymbol().equals("AMZ"))
        .forEach(stock -> log.info(stock.toString()));
  }
}
