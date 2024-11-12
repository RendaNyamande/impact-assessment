package numberrangesummarizer;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SummaryGeneratorTest {
    private NumberRangeSummarizer summarizer;

    @BeforeEach
    void setUp() {
        summarizer = new SummaryGenerator();
    }

    @Test
    void testCollectWithValidInput() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);

        Collection<Integer> result = summarizer.collect(input);

        assertEquals(expected, result, "The collection should match the expected sequence of numbers.");
    }

    @Test
    void testCollectWithInvalidInput() {
        String input = "1,3,a,7,8,b,13";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect(input);
        });

        assertTrue(exception.getMessage().contains("is not a valid number."),
                "Exception message should contain 'is not a valid number.'");
    }

    @Test
    void testSummarizeCollectionWithSequentialRanges() {
        Collection<Integer> input = Arrays.asList(1, 2, 3, 5, 7, 8, 9, 10, 12);
        String expected = "1-3, 5, 7-10, 12";

        String result = summarizer.summarizeCollection(input);

        assertEquals(expected, result, "The summarized output should match the expected range format.");
    }

    @Test
    void testSummarizeCollectionWithNoRanges() {
        Collection<Integer> input = Arrays.asList(1, 3, 5, 7, 9);
        String expected = "1, 3, 5, 7, 9";

        String result = summarizer.summarizeCollection(input);

        assertEquals(expected, result, "Each number should be displayed without ranges.");
    }

    @Test
    void testSummarizeCollectionWithSingleNumber() {
        Collection<Integer> input = Arrays.asList(42);
        String expected = "42";

        String result = summarizer.summarizeCollection(input);

        assertEquals(expected, result, "A single number should be returned without any range notation.");
    }

    @Test
    void testSummarizeCollectionWithEmptyInput() {
        Collection<Integer> input = Arrays.asList();
        String expected = "";

        String result = summarizer.summarizeCollection(input);

        assertEquals(expected, result, "Empty input should result in an empty string.");
    }
}
