package seaice.lib.pipefilter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seaice.lib.builder.Builder;

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
        Integer[] firstArray = Builder.array(Integer.class).size(3).after(6).after(4).after(2).val();
        Integer[] secondArray = Builder.array(Integer.class).size(3).after(5).after(9).after(1).val();
        Integer[] thirdArray = Builder.array(Integer.class).size(3).after(3).after(7).after(1).val();
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
        List<Integer[]> firstRound = Pipe.in(integerArrayList).then((integerArrayList, context) -> {
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
        List<Integer> secondRound = Pipe.in(firstRound).transform((integerArrayList, context) ->
                integerArrayList.stream().map(integerArray ->
                        integerArray[0]).collect(Collectors.toList())).out();
        Assert.assertEquals(secondRound.size(), 3);
        Assert.assertTrue(secondRound.get(0) == 12);
        Assert.assertTrue(secondRound.get(1) == 15);
        Assert.assertTrue(secondRound.get(2) == 11);
        // The third round: find the largest number
        Integer thirdRound = Pipe.in(secondRound).transform((integerList, context) -> {
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
        Assert.assertTrue(Pipe.in(integerArrayList).then((integerArrayList, context) -> {
            List<Integer[]> temp = new ArrayList<>();
            for (Integer[] integerArray : integerArrayList) {
                int sum = 0;
                for (Integer value : integerArray) {
                    sum += value;
                }
                temp.add(new Integer[]{sum});
            }
            return temp;
        }).transform((integerArrayList, context) -> integerArrayList.stream().map(array ->
                array[0]).collect(Collectors.toList()))
                .transform((integerList, context) -> {
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
