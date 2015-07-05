PipeFilter
==========

The `pipe-filter pattern` in action.

### Sample

Given a list of strings, find all the strings which contains `a`, `b` and `c` characters.

```java
// A list of string...
stringList = Pipe.in(stringList).then(data ->
        data.stream().filter(string -> string.contains("a") && string.contains("b")
                && string.contains("c")).collect(Collectors.toList())).out();
Assert.assertTrue(stringList.size() == 1);
```
