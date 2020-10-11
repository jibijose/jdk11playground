package com.example.demo.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BrowserUrlSearchTest {

  @Test
  public void testFirstLetterMatching() {
    BrowserUrlSearch browserUrlSearch = constructBrowserUrlSearch();

    assertEquals(4, browserUrlSearch.fetchMatches("").size());
    assertEquals(2, browserUrlSearch.fetchMatches("").get("abcd"));
    assertEquals(1, browserUrlSearch.fetchMatches("").get("abcc"));

    assertEquals(3, browserUrlSearch.fetchMatches("a").size());
    assertEquals(2, browserUrlSearch.fetchMatches("a").get("abcd"));

    assertEquals(2, browserUrlSearch.fetchMatches("ab").size());
    assertEquals(2, browserUrlSearch.fetchMatches("ab").get("abcd"));
    assertEquals(2, browserUrlSearch.fetchMatches("abc").size());
    assertEquals(2, browserUrlSearch.fetchMatches("abc").get("abcd"));
    assertEquals(1, browserUrlSearch.fetchMatches("abcd").size());
    assertEquals(2, browserUrlSearch.fetchMatches("abcd").get("abcd"));
  }

  private BrowserUrlSearch constructBrowserUrlSearch() {
    BrowserUrlSearch browserUrlSearch = new BrowserUrlSearch();

    browserUrlSearch.addUrl("abcd");
    browserUrlSearch.addUrl("abcc");
    browserUrlSearch.addUrl("bbcd");
    browserUrlSearch.addUrl("aacd");
    browserUrlSearch.addUrl("abcd");

    return browserUrlSearch;
  }
}
