package io.github.Makday.settings;

import java.util.ArrayList;
import java.util.List;

public final class ConversionSettings {
  private final Integer bitrateKbps;
  private final Integer sampleRate;
  private final Integer channels;

  private ConversionSettings(Builder b) {
    this.bitrateKbps = b.bitrateKbps;
    this.sampleRate = b.sampleRate;
    this.channels = b.channels;
  }

  public List<String> toFfmpegArgs() {
    List<String> args = new ArrayList<>();
    if (bitrateKbps != null) {
      args.add("-b:a");
      args.add(bitrateKbps + "k");
    }
    if (sampleRate != null) {
      args.add("-ar");
      args.add(String.valueOf(sampleRate));
    }
    if (channels != null) {
      args.add("-ac");
      args.add(String.valueOf(channels));
    }
    return args;
  }

  public static class Builder {
    private Integer bitrateKbps;
    private Integer sampleRate;
    private Integer channels;

    public Builder bitrate(int kbps) {
      this.bitrateKbps = kbps;
      return this;
    }

    public Builder sampleRate(int hz) {
      this.sampleRate = hz;
      return this;
    }

    public Builder channels(int count) {
      this.channels = count;
      return this;
    }

    public ConversionSettings build() {
      return new ConversionSettings(this);
    }
  }
}
