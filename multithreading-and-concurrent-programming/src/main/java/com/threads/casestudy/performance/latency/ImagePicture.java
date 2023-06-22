package com.threads.casestudy.performance.latency;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImagePicture {

  public static final String SOURCE_FILE = "./resources/img/many-flowers.jpg";
  public static final String DESTINATION_FILE = "./out/many-flowers.jpg";


  public static void main(final String[] args) throws IOException {

    final BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
    final BufferedImage resultImage = new BufferedImage(originalImage.getWidth(),
        originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    final long start = System.currentTimeMillis();
    //recolorSingleThreaded(originalImage, resultImage);
    final int numOfThreads = 1;
    recolorMultiThread(originalImage, resultImage, numOfThreads);
    final long duration = System.currentTimeMillis() - start;

    final File output = new File(DESTINATION_FILE);
    ImageIO.write(resultImage, "jpg", output);
    log.info("Duration: {}", duration);

  }

  public static void recolorMultiThread(final BufferedImage originalImage,
      final BufferedImage resultImage,
      final int numOfThread) {
    final List<Thread> threads = new ArrayList<>();
    final int width = originalImage.getWidth();
    final int height = originalImage.getHeight() / numOfThread;

    for (int i = 0; i < numOfThread; i++) {
      final int threadMultiplier = i;
      final Thread thread = new Thread(() -> {
        final int leftCorner = 0;
        final int topCorner = height * threadMultiplier;
        recolorImage(originalImage, resultImage, leftCorner, topCorner, width, height);
      });
      threads.add(thread);
    }

    for (final Thread thread : threads) {
      thread.start();
    }
    for (final Thread thread : threads) {
      try {
        thread.join();
      } catch (final InterruptedException e) {
        thread.interrupt();
      }
    }

  }

  public static void recolorSingleThreaded(final BufferedImage original,
      final BufferedImage result) {
    recolorImage(original, result, 0, 0, original.getWidth(), original.getHeight());
  }

  public static void recolorImage(final BufferedImage originalImage,
      final BufferedImage resultImage,
      final int leftCorner, final int topCorner, final int width, final int height) {
    for (int x = leftCorner; x < leftCorner + width && x < originalImage.getWidth(); x++) {
      for (int y = topCorner; y < topCorner + height && y < originalImage.getHeight(); y++) {
        recolorPixel(originalImage, resultImage, x, y);
      }
    }
  }

  public static void recolorPixel(final BufferedImage originalImage,
      final BufferedImage resultImage, final int x,
      final int y) {
    final int rgb = originalImage.getRGB(x, y);

    final int red = getRed(rgb);
    final int green = getGreen(rgb);
    final int blue = getBlue(rgb);

    final int newRed;
    final int newGreen;
    final int newBlue;

    if (isShadeOfGray(red, green, blue)) {
      newRed = Math.min(255, red + 10);
      newGreen = Math.max(0, green - 80);
      newBlue = Math.max(0, blue - 20);
    } else {
      newRed = red;
      newGreen = green;
      newBlue = blue;
    }
    final int newRGB = createRGBFromColors(newRed, newGreen, newBlue);
    setRGB(resultImage, x, y, newRGB);
  }

  public static void setRGB(final BufferedImage image, final int x, final int y, final int rgb) {
    image.getRaster().setDataElements(x, y, image.getColorModel().getDataElements(rgb, null));
  }

  public static boolean isShadeOfGray(final int red, final int green, final int blue) {
    return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30;
  }

  public static int createRGBFromColors(final int red, final int green, final int blue) {
    int rgb = 0;

    rgb |= blue;
    rgb |= green << 8;
    rgb |= red << 16;

    rgb |= 0xFF000000;
    return rgb;
  }

  public static int getRed(final int rgb) {
    return (rgb & 0x00FF0000) >> 16;
  }

  public static int getGreen(final int rgb) {
    return (rgb & 0x0000FF00) >> 8;
  }

  public static int getBlue(final int rgb) {
    return rgb & 0x000000FF;
  }

}
