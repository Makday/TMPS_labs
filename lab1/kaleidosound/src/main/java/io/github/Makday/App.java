package io.github.Makday;

import io.github.Makday.settings.ConversionSettings;

public class App {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: java App <input> <output> [bitrateKbps] [sampleRate] [channels]");
      return;
    }
    ConversionSettings.Builder builder = new ConversionSettings.Builder();
    if (args.length > 2)
      builder.bitrate(Integer.parseInt(args[2]));
    if (args.length > 3)
      builder.sampleRate(Integer.parseInt(args[3]));
    if (args.length > 4)
      builder.channels(Integer.parseInt(args[4]));

    new ConverterFacade().convert(args[0], args[1], builder.build());
  }
}
