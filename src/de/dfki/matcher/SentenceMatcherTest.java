package de.dfki.matcher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by alvaro on 6/6/17.
 */
class SentenceMatcherTest {

    private String toMatchSentence;
    private LinkedList<String> options;

    @BeforeEach
    public void setUp(){
        options = new LinkedList<>();
    }
    @Test
    public void
    match_ExactSentenceToMatch_MatchTrueAndOptionMatched(){
        toMatchSentence = "We treat the aspect of face as basic wants, which every member knows every member";
        SentenceMatcher matcher = makeMatcher();
        matcher.match("We treat the aspect of face as basic wants, which every member knows every member");
        boolean isMatchFound = matcher.hasMatch();
        assertTrue(isMatchFound);
        assertEquals(toMatchSentence, matcher.getMatch());
    }

    @Test public void
    match_SentenceWithMissingWords_MatchTrueAndOptionMatched(){
        toMatchSentence = "We treat the aspect of face as basic wants, which every member knows every member";
        SentenceMatcher matcher = makeMatcher();
        matcher.match("We treat the aspect of face which every member knows every member");
        boolean isMatchFound = matcher.hasMatch();
        assertTrue(isMatchFound);
        assertEquals(toMatchSentence, matcher.getMatch());
    }

    @Test public void
    match_ShorterSentenceIsRightMatchButLongerSenteceHasMoreCommonWords_ShorterSentces(){
        toMatchSentence = "We treat the aspect of face as basic wants, which every member knows every member";
        String expected = "We treat the aspect of face as basic wants ";
        options.add(expected);
        SentenceMatcher matcher = makeMatcher();
        matcher.match("We treat the aspect of every");
        boolean isMatchFound = matcher.hasMatch();
        assertTrue(isMatchFound);
        assertEquals(expected, matcher.getMatch());
    }

    @Test public void
    hasMatch_SentenceWithWithSmallMatchingCount_False(){
        toMatchSentence = "We treat the aspect of face as basic wants, which every member knows every member";
        SentenceMatcher matcher = makeMatcher();
        matcher.match("aspect treat member ");
        boolean isMatchFound = matcher.hasMatch();
        assertFalse(isMatchFound);
    }

    @Test public void
    hasMatch_SentenceWithSameWordsButDifferentOrder_False(){
        toMatchSentence = "We treat the aspect of face as basic wants, which every member knows every member";
        SentenceMatcher matcher = makeMatcher();
        matcher.match("aspect treat member face member knows wants every");
        boolean isMatchFound = matcher.hasMatch();
        assertFalse(isMatchFound);
    }

    private SentenceMatcher makeMatcher() {

        options.add(toMatchSentence);
        options.add("Negative face, with its derivative politeness of non-imposition, is familiar");
        options.add("We treat as basic wants, which every member");
        options.add("Negative face, with its");
        options.add("politeness its with  face, familiar  Negative");
        return new SentenceMatcher(options);
    }
}