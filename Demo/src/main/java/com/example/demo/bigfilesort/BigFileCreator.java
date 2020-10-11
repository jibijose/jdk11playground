package com.example.demo.bigfilesort;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BigFileCreator {

  private static int KEY_LENGTH = 16;
  private static int GB_SIZE = 1;

  public static void main(String args[]) {
    BigFileCreator bigFileCreator = new BigFileCreator();
    bigFileCreator.createBigFile();
  }

  private void createBigFile() {
    try (FileWriter fileWriter = new FileWriter("bigfile.txt")) {
      try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
        IntStream.range(0, GB_SIZE * 1024 * 1024 * 1024 / KEY_LENGTH / 2)
            .forEach(
                index -> {
                  String randKey = getRandomString(KEY_LENGTH - 1);
                  printWriter.println(randKey + "," + randKey);
                });
      }
    } catch (IOException ioException) {
      log.error("File write error", ioException);
    }
  }

  private String getRandomString(int length) {
    String AlphaNumericString =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int index = (int) (AlphaNumericString.length() * Math.random());
      sb.append(AlphaNumericString.charAt(index));
    }

    return sb.toString();
  }
}
