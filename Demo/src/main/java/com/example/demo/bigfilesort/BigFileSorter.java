package com.example.demo.bigfilesort;

import static java.lang.String.format;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BigFileSorter {

  private static int KEY_LENGTH = 16;
  private static int GB_SIZE = 20;
  private static int FILES_COUNT = GB_SIZE * 10;
  private static int PROGRESS_GROUPS = 1000000;
  private static String FILE_PAD_FORMAT = "%04d";

  public static void main(String args[]) {
    BigFileSorter bigFileSorter = new BigFileSorter();
    // bigFileSorter.createBigFile();
    bigFileSorter.divideBigFile();
    // bigFileSorter.sortSmallFiles();
    // bigFileSorter.combineSmallFiles();
  }

  private void combineSmallFiles() {
    FileInputStream[] fileInputStreams = new FileInputStream[FILES_COUNT];
    Scanner[] scanners = new Scanner[FILES_COUNT];
    String[] lines = new String[FILES_COUNT];
    initializeSmallfileReaders(fileInputStreams, scanners);

    IntStream.range(0, FILES_COUNT)
        .forEach(
            index -> {
              if (scanners[index].hasNextLine()) {
                lines[index] = scanners[index].nextLine();
              } else {
                lines[index] = null;
              }
            });

    try (FileWriter fileWriter = new FileWriter("bigfile_sorted.txt")) {
      try (PrintWriter printWriter = new PrintWriter(fileWriter)) {

        int indexSmallest = findSmallest(lines);
        log.debug("IndexSmallest is {}", indexSmallest);
        while (indexSmallest >= 0) {
          printWriter.println(lines[indexSmallest]);
          if (scanners[indexSmallest].hasNextLine()) {
            lines[indexSmallest] = scanners[indexSmallest].nextLine();
          } else {
            lines[indexSmallest] = null;
          }
          indexSmallest = findSmallest(lines);
          log.debug("IndexSmallest is {}", indexSmallest);
        }
      }
    } catch (IOException ioException) {
      log.error("Big sorted file write error", ioException);
    }
  }

  private int findSmallest(String[] lines) {
    AtomicInteger indexSmallest;
    int linesCount = lines.length;
    OptionalInt optionalFirstIndex =
        IntStream.range(0, linesCount).filter(index -> lines[index] != null).findFirst();
    int indexOfComma;
    if (optionalFirstIndex.isEmpty()) {
      return -1;
    } else {
      indexSmallest = new AtomicInteger(optionalFirstIndex.getAsInt());
      indexOfComma = lines[indexSmallest.get()].indexOf(',');
    }

    IntStream.range((indexSmallest.get() + 1), linesCount)
        .forEach(
            index -> {
              if (lines[index] != null) {
                String existingSmall = lines[indexSmallest.get()].substring(0, indexOfComma);
                String tryNextString = lines[index].substring(0, indexOfComma);
                if (existingSmall.compareTo(tryNextString) > 0) {
                  indexSmallest.set(index);
                }
              }
            });
    return indexSmallest.get();
  }

  private void sortSmallFiles() {
    IntStream.range(0, FILES_COUNT)
        .forEach(
            index -> {
              String fileName = "temp/smallfile_" + format(FILE_PAD_FORMAT, index) + ".txt";
              String fileNameSorted = "temp/smallfile_sorted_" + format(FILE_PAD_FORMAT, index) + ".txt";
              try (FileInputStream inputStream = new FileInputStream(fileName)) {
                try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
                  Map<String, String> fileMap = new TreeMap<>();
                  int numOfLinesRead = 0;
                  while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    numOfLinesRead++;
                    int indexOfComma = line.indexOf(',');
                    fileMap.put(
                        line.substring(0, indexOfComma),
                        line.substring(indexOfComma + 1, line.length()));
                    if (numOfLinesRead % PROGRESS_GROUPS == 0) {
                      log.debug("Mapped {} lines from {}", numOfLinesRead, fileName);
                    }
                  }
                  if (scanner.ioException() != null) {
                    throw scanner.ioException();
                  }

                  try (FileWriter fileWriter = new FileWriter(fileNameSorted)) {
                    try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                      fileMap.keySet().stream()
                          .forEach(
                              key -> {
                                printWriter.println(key + "," + fileMap.get(key));
                              });
                    }
                  }
                  log.debug("File written {} with size {}", fileName, fileMap.size());
                }
              } catch (IOException ioException) {
                log.error("Small file sorting error", ioException);
              }
            });
  }

  private void divideBigFile() {
    int lineCount = 0;
    FileWriter[] fileWriters = new FileWriter[FILES_COUNT];
    PrintWriter[] printWriters = new PrintWriter[FILES_COUNT];
    try (FileInputStream inputStream = new FileInputStream("bigfile.txt")) {
      try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
        initializeSmallfileWriters(fileWriters, printWriters);
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          lineCount++;
          printWriters[lineCount % FILES_COUNT].println(line);
        }
        if (scanner.ioException() != null) {
          throw scanner.ioException();
        }
      }
    } catch (IOException ioException) {
      log.error("Small file write error", ioException);
    } finally {
      Arrays.stream(printWriters).forEach(printWriter -> printWriter.close());
      Arrays.stream(fileWriters)
          .forEach(
              fileWriter -> {
                try {
                  fileWriter.close();
                } catch (IOException ioException) {
                  log.error("File write close error", ioException);
                }
              });
    }
  }

  private void initializeSmallfileReaders(FileInputStream[] fileInputStreams, Scanner[] scanners) {
    AtomicInteger indexFileReader = new AtomicInteger(0);
    Arrays.stream(fileInputStreams)
        .forEach(
            fileInputStream -> {
              try {
                int indexFile = indexFileReader.getAndIncrement();
                fileInputStreams[indexFile] =
                    new FileInputStream("temp/smallfile_sorted_" + format(FILE_PAD_FORMAT, indexFile) + ".txt");
                scanners[indexFile] = new Scanner(fileInputStreams[indexFile]);
              } catch (IOException ioException) {
                log.error("File reader creation error", ioException);
              }
            });
  }

  private void initializeSmallfileWriters(FileWriter[] fileWriters, PrintWriter[] printWriters) {
    AtomicInteger indexFileWriter = new AtomicInteger(0);
    Arrays.stream(fileWriters)
        .forEach(
            fileWriter -> {
              try {
                int indexFile = indexFileWriter.getAndIncrement();
                fileWriters[indexFile] = new FileWriter("temp/smallfile_" + format(FILE_PAD_FORMAT, indexFile) + ".txt");
                printWriters[indexFile] = new PrintWriter(fileWriters[indexFile]);
              } catch (IOException ioException) {
                log.error("File writer creation error", ioException);
              }
            });
  }

  private void createBigFile() {
    try (FileWriter fileWriter = new FileWriter("bigfile.txt")) {
      try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
        BigInteger kiloBigInteger = BigInteger.valueOf(1024);
        BigInteger upperLimitBigInteger =
            BigInteger.valueOf(GB_SIZE)
                .multiply(kiloBigInteger)
                .multiply(kiloBigInteger)
                .multiply(kiloBigInteger)
                .divide(BigInteger.valueOf(KEY_LENGTH))
                .divide(BigInteger.valueOf(2));
        for (BigInteger bi = upperLimitBigInteger;
            bi.compareTo(BigInteger.ZERO) > 0;
            bi = bi.subtract(BigInteger.ONE)) {
          String randKey = getRandomString(KEY_LENGTH - 1);
          printWriter.println(randKey + "," + randKey);
        }
      }
    } catch (IOException ioException) {
      log.error("File write error", ioException);
    }
    log.debug("Created bigfile");
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
