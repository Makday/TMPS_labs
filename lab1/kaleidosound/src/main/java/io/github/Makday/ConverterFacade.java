package io.github.Makday;

class ConverterFacade {
  private final Converter converter = new FfmpegConverter();

  public void convert(String input, String output) {
    try {
      converter.convert(input, output);
      System.out.println("Done: " + output);
    } catch (Exception e) {
      System.err.println("Conversion failed: " + e.getMessage());
    }
  }
}
