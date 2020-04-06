package util;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class TextProcessorUtilTest {

    static final String TEST_FILE_PATH = "src/test/resources/testFile.txt";

    @Test
    public void noVowelWordsReturnOneSyllableTest() throws IOException {
        setTextFile("try");
        int[] expectedResult = new int[]{1};
        int[] actualResult = TextProcessorUtil.getSyllableCounts(TEST_FILE_PATH);
        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void twoSyllableWordTest() throws IOException {
        setTextFile("balloon");
        int[] expectedResult = new int[]{2};
        int[] actualResult = TextProcessorUtil.getSyllableCounts(TEST_FILE_PATH);
        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void threeSyllableWordTest() throws IOException {
        setTextFile("ballooning");
        int[] expectedResult = new int[]{3};
        int[] actualResult = TextProcessorUtil.getSyllableCounts(TEST_FILE_PATH);
        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void processMultipleWordsTest() throws IOException {
        setTextFile("This is a complete sentence written to one line.");
        int[] expectedResult = new int[]{1,1,1,3,3,2,1,2,2};
        int[] actualResult = TextProcessorUtil.getSyllableCounts(TEST_FILE_PATH);
        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void processMultipleLinesTest() throws IOException {
        setTextFile("This is a sentence written\nacross\nmultiple\nlines.");
        int[] expectedResult = new int[]{1,1,1,3,2,2,3,2};
        int[] actualResult = TextProcessorUtil.getSyllableCounts(TEST_FILE_PATH);
        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    public void setTextFile(String text) {
        try {
            FileWriter fileWriter = new FileWriter(TEST_FILE_PATH);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}


