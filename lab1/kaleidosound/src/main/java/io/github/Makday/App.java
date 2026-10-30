package io.github.Makday;

public class App {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Usage: java Main <input> <output>");
      return;
    }
    new ConverterFacade().convert(args[0], args[1]);
  }
}
