package seaice.lib.pipefilter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test the transform functionality.
 *
 * @author zhb
 */
public class TransformTest {

    List<Integer[]> integerArrayList;

    @Before
    public void init() {
        integerArrayList = new ArrayList<>();
        Integer[] firstArray = new Integer[]{
                6, 4, 2
        };
        Integer[] secondArray = new Integer[]{
                5, 9, 1
        };
        Integer[] thirdArray = new Integer[]{
                3, 7, 1
        };
        integerArrayList.add(firstArray);
        integerArrayList.add(secondArray);
        integerArrayList.add(thirdArray);
    }

    /**
     * Given a list of integer array, calculate the sum of each array, and then find
     * the biggest of them.
     */
    @Test
    public void testBiggestSum() {
        // The first round: calculate the sum of integer array
        List<Integer[]> firstRound = Pipe.in(integerArrayList).then(integerArrayList -> {
            List<Integer[]> temp = new ArrayList<>();
            for (Integer[] integerArray : integerArrayList) {
                int sum = 0;
                for (Integer value : integerArray) {
                    sum += value;
                }
                temp.add(new Integer[]{sum});
            }
            return temp;
        }).out();
        Assert.assertEquals(firstRound.size(), 3);
        Assert.assertArrayEquals(firstRound.get(0), new Integer[]{12});
        Assert.assertArrayEquals(firstRound.get(1), new Integer[]{15});
        Assert.assertArrayEquals(firstRound.get(2), new Integer[]{11});
        // The second round: fetch the first of integer array
        List<Integer> secondRound = Pipe.in(firstRound).transform(integerArrayList ->
                integerArrayList.stream().map(integerArray ->
                        integerArray[0]).collect(Collectors.toList())).out();
        Assert.assertEquals(secondRound.size(), 3);
        Assert.assertTrue(secondRound.get(0) == 12);
        Assert.assertTrue(secondRound.get(1) == 15);
        Assert.assertTrue(secondRound.get(2) == 11);
        // The third round: find the largest number
        Integer thirdRound = Pipe.in(secondRound).transform(integerList -> {
            int max = integerList.get(0);
            for (int i = 1; i < integerList.size(); ++i) {
                if (integerList.get(i) > max) {
                    max = integerList.get(i);
                }
            }
            return max;
        }).out();
        Assert.assertTrue(thirdRound == 15);
        // The final solution, one way..
        Assert.assertTrue(Pipe.in(integerArrayList).then(integerArrayList -> {
            List<Integer[]> temp = new ArrayList<>();
            for (Integer[] integerArray : integerArrayList) {
                int sum = 0;
                for (Integer value : integerArray) {
                    sum += value;
                }
                temp.add(new Integer[]{sum});
            }
            return temp;
        }).transform(integerArrayList -> integerArrayList.stream().map(array ->
                array[0]).collect(Collectors.toList()))
                .transform(integerList -> {
                            int max = integerList.get(0);
                            for (int i = 1; i < integerList.size(); ++i) {
                                if (integerList.get(i) > max) {
                                    max = integerList.get(i);
                                }
                            }
                            return max;
                        }
                ).out() == 15);
    }
}
