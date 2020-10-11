package com.example.demo.ds;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserUrlSearch {

  private Map<String, Map<String, Integer>> masterEntries = new HashMap<>();

  public BrowserUrlSearch() {
    masterEntries.put("", new HashMap<>());
  }

  public void addUrl(String url) {
    int currentCount = masterEntries.get("").get(url) == null ? 0 : masterEntries.get("").get(url);
    masterEntries.get("").put(url, currentCount + 1);
    for (int i = 0; i < url.length(); i++) {
      String pushString = url.substring(0, i + 1);
      if (masterEntries.get(pushString) == null) {
        Map<String, Integer> newMap = new HashMap<>();
        masterEntries.put(pushString, newMap);
      }
      int currentPushCount =
          masterEntries.get(pushString).get(url) == null
              ? 0
              : masterEntries.get(pushString).get(url);
      masterEntries.get(pushString).put(url, currentPushCount + 1);
    }
  }

  public Map<String, Integer> fetchMatches(String partUrl) {
    return masterEntries.get(partUrl);
  }
}
