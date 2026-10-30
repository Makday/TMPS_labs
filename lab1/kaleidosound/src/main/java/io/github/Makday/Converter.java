package io.github.Makday;

import java.io.IOException;

interface Converter {
  void convert(String input, String output) throws IOException, InterruptedException;
}
