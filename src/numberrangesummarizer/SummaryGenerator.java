package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class SummaryGenerator implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        // using a TreeSet to store unique numbers in a sorted order
        TreeSet<Integer> numbers = new TreeSet<>();

        // splitting the input string by commas to produce a String numbers list
        String[] numbersList = input.split(",");

        int currentNumber;
        for (String number : numbersList) {
            try {
                // parse each String number to an integer while accounting for potential
                // whitespace
                currentNumber = Integer.parseInt(number.trim());

                numbers.add(currentNumber);
            } catch (NumberFormatException e) {
                // terminating if a number is invalid
                throw new IllegalArgumentException(number + " is not a valid number.");
            }
        }
        return numbers;
    }

    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> sortedNumbers = new ArrayList<>(input);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sortedNumbers.size(); i++) {
            int start = sortedNumbers.get(i);
            int end = start;

            /*
             * check if index is within bounds
             * accounting for potential ranges by checking if the next value is an increment
             * of 1 from the current end
             */
            while (i + 1 < sortedNumbers.size() && sortedNumbers.get(i + 1) == end + 1) {
                // update end and increment i
                end = sortedNumbers.get(++i);
            }

            // no range
            if (start == end) {
                result.append(start);
            } else {
                // formatting range
                result.append(start).append("-").append(end);
            }

            // add commas for all but the last number or range
            if (i < sortedNumbers.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}
