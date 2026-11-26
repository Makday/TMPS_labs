package io.github.Makday;

import java.util.Scanner;

import io.github.Makday.cli.Shell;

public class App {
  public static void main(String[] args) {
    new Shell().run(new Scanner(System.in));
  }
}
