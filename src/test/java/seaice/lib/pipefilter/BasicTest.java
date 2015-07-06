package seaice.lib.pipefilter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Basic Test.
 *
 * @author zhb
 */
public class BasicTest {

    List<String> stringList;

    @Before
    public void init() {
        // initialize the string list to be test.
        stringList = new ArrayList<>();
        stringList.add("I am working at Alibaba Group.");
        stringList.add("The Alibaba Group stands at Hangzhou, Zhejiang.");
        stringList.add("Every language comes with a \"Hello, World!\" example.");
        stringList.add("That's it.");
        stringList.add("The only string contains \"abc\"");
    }

    /**
     * Test the list of string. filter all the {@code String} which contains all the 'abc' alphabet.
     */
    @Test
    public void testListString() {
        stringList = Pipe.in(stringList).then((data, context) ->
                data.stream().filter(string -> string.contains("a") && string.contains("b")
                        && string.contains("c")).collect(Collectors.toList())).out();
        Assert.assertTrue(stringList.size() == 1);
    }
}
