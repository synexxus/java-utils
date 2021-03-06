package com.github.hypfvieh.util;

import java.util.List;

import org.junit.Test;

import com.github.hypfvieh.AbstractBaseUtilTest;

/**
 *
 * @author hypfvieh
 */
public class StringUtilTest extends AbstractBaseUtilTest {

    public StringUtilTest() {
    }

    @Test
    public void testSmartSplit() throws Exception {

        String sampleText = "Vorbereitung Quoteverbreiterung falls TG nicht da: Felder EE und DU als TG Indikator eingefügt. "
                + "Schalter 16 eingefügt. Felder EK und EL eingefügt. EK/EL berechnet die MinPerformance Bid/Ask, "
                + "falls TG nicht als Konkurrenz und Schalter16 auf yes. In RicBid/Ask Formeln so angepasst, "
                + "dass falls TG nicht Konkurrenz, Schalter16 an und EK/EL > 0, so wird EK/EL 'Smart Performance' verwendet.";

        List<String> smartStringSplit = StringUtil.smartWordSplit(sampleText, 50);
        assertEquals("Expected 8 lines", 8, smartStringSplit.size());

        for (String line : smartStringSplit) {
            assertTrue("Each line should be no longer than 50", line.length() <= 50);
        }

    }

    @Test
    public void testSmartSplitWrongTokenLengthFix() throws Exception {

        String sampleText = "Disconnect the given session. If --force is used socket will be closed without sending logout message";

        List<String> smartStringSplit = StringUtil.smartWordSplit(sampleText, 29);
        assertEquals("Expected 4 lines", 4, smartStringSplit.size());

        for (String line : smartStringSplit) {
            assertTrue("Each line should be no longer than 29", line.length() <= 29);
        }

    }


    @Test
    public void testLowerCaseFirstChar() throws Exception {
        String tstStr = "TEST";

        assertEquals("tEST", StringUtil.lowerCaseFirstChar(tstStr));
    }

    @Test
    public void testUpperCaseFirstChar() throws Exception {
        String tstStr = "test";

        assertEquals("Test", StringUtil.upperCaseFirstChar(tstStr));
    }

    @Test
    public void testJoin() throws Exception {
        String[] toJoin = {"This", "should", "be", "joined"};

        String joined = StringUtil.join(" ", toJoin);

        assertEquals("This should be joined", joined);
    }

    @Test
    public void testAbbreviate() throws Exception {
        String tstStr = "This should be shortend";

        assertEquals("This should...", StringUtil.abbreviate(tstStr, 14));
    }

    @Test
    public void testConvertCamelToUpperCase() {
        assertNull(StringUtil.convertCamelToUpperCase(null));
        assertEquals("", StringUtil.convertCamelToUpperCase(""));
        assertEquals("  ", StringUtil.convertCamelToUpperCase("  "));
        assertEquals("QUOTE_STATUS_REPORT", StringUtil.convertCamelToUpperCase("QuoteStatusReport"));
        assertEquals("HELLO", StringUtil.convertCamelToUpperCase("hello"));
        assertEquals("MEDIA", StringUtil.convertCamelToUpperCase("MEDIA"));
        assertEquals("ACME_COMPANY", StringUtil.convertCamelToUpperCase("AcmeCompany"));
    }

    @Test
    public void testConvertUpperToCamelCase() {
        assertNull(StringUtil.convertUpperToCamelCase(null));
        assertEquals("", StringUtil.convertUpperToCamelCase(""));
        assertEquals("  ", StringUtil.convertUpperToCamelCase("  "));
        assertEquals("QuoteStatusReport", StringUtil.convertUpperToCamelCase("QUOTE_STATUS_REPORT"));
        assertEquals("Hello", StringUtil.convertUpperToCamelCase("hello"));
        assertEquals("UserResponse", StringUtil.convertUpperToCamelCase("UserResponse"));
    }
}
