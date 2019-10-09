/** @author: Hasitha Rajapakse */
package de.upb.swt.soot.test.java.sourcecode.minimaltestsuite.java6;

import categories.Java8Test;
import de.upb.swt.soot.core.signatures.MethodSignature;
import de.upb.swt.soot.test.java.sourcecode.minimaltestsuite.MinimalTestSuiteBase;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Java8Test.class)
public class InitializeMultidimensionalArraysTest extends MinimalTestSuiteBase {

  private String methodName;
  private String methodSignature;
  private List<String> jimpleLines;

  @Test
  public void defaultTest() {
    HashMap<String, HashMap<String, Object>> methodList = setValues();
    Set<String> methodListKeys = methodList.keySet();

    for (String methodListKey : methodListKeys) {
      methodName = methodListKey;
      HashMap<String, Object> mv = methodList.get(methodListKey);
      methodSignature = (String) mv.get("methodSignature");
      jimpleLines = (List<String>) mv.get("jimpleLines");

      test(getJimpleLines(), getMethodSignature());
    }
  }

  @Override
  public MethodSignature getMethodSignature() {
    return identifierFactory.getMethodSignature(
        methodName, getDeclaredClassSignature(), methodSignature, Collections.emptyList());
  }

  @Override
  public List<String> getJimpleLines() {
    return jimpleLines;
  }

  public HashMap<String, HashMap<String, Object>> setValues() {
    HashMap<String, HashMap<String, Object>> methodList = new HashMap<>();
    HashMap<String, Object> methodValues = new HashMap<>();

    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (int[][])[3]",
                "$r2 = newarray (int[])[3]",
                "$r2[0] = 1",
                "$r2[1] = 2",
                "$r2[2] = 3",
                "$r1[0] = $r2",
                "$r3 = newarray (int[])[2]",
                "$r3[0] = 5",
                "$r3[1] = 6",
                "$r1[1] = $r3",
                "$r4 = newarray (int[])[3]",
                "$r4[0] = 7",
                "$r4[1] = 8",
                "$r4[2] = 9",
                "$r1[2] = $r4",
                "return")
            .collect(Collectors.toList()));
    methodList.put("intArrays", methodValues);

    methodValues = new HashMap<>();
    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (byte[][])[2]",
                "$r2 = newarray (byte[])[2]",
                "$r2[0] = 4",
                "$r2[1] = 5",
                "$r1[0] = $r2",
                "$r3 = newarray (byte[])[1]",
                "$r3[0] = 2",
                "$r1[1] = $r3",
                "return")
            .collect(Collectors.toList()));
    methodList.put("byteArrays", methodValues);

    methodValues = new HashMap<>();
    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (short[][])[2]",
                "$r2 = newarray (short[])[3]",
                "$r2[0] = 10",
                "$r2[1] = 20",
                "$r2[2] = 30",
                "$r1[0] = $r2",
                "$r3 = newarray (short[])[1]",
                "$r3[0] = 40",
                "$r1[1] = $r3",
                "return")
            .collect(Collectors.toList()));
    methodList.put("shortArrays", methodValues);

    methodValues = new HashMap<>();
    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (long[][])[3]",
                "$r2 = newarray (long[])[2]",
                "$r2[0] = 547087L",
                "$r2[1] = 654786L",
                "$r1[0] = $r2",
                "$r3 = newarray (long[])[3]",
                "$r3[0] = 547287L",
                "$r3[1] = 864645L",
                "$r3[2] = 6533786L",
                "$r1[1] = $r3",
                "$r4 = newarray (long[])[2]",
                "$r4[0] = 34565L",
                "$r4[1] = 234L",
                "$r1[2] = $r4",
                "return")
            .collect(Collectors.toList()));
    methodList.put("longArrays", methodValues);

    methodValues = new HashMap<>();
    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (float[][])[2]",
                "$r2 = newarray (float[])[2]",
                "$r2[0] = 3.14F",
                "$r2[1] = 5.46F",
                "$r1[0] = $r2",
                "$r3 = newarray (float[])[2]",
                "$r3[0] = 2.987F",
                "$r3[1] = 4.87F",
                "$r1[1] = $r3",
                "return")
            .collect(Collectors.toList()));
    methodList.put("floatArrays", methodValues);

    methodValues = new HashMap<>();
    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (double[][])[3]",
                "$r2 = newarray (double[])[2]",
                "$r2[0] = 6.765414",
                "$r2[1] = 9.676565646",
                "$r1[0] = $r2",
                "$r3 = newarray (double[])[1]",
                "$r3[0] = 45.345435",
                "$r1[1] = $r3",
                "$r4 = newarray (double[])[2]",
                "$r4[0] = 3.5656",
                "$r4[1] = 68.234234",
                "$r1[2] = $r4",
                "return")
            .collect(Collectors.toList()));
    methodList.put("doubleArrays", methodValues);

    methodValues = new HashMap<>();
    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (boolean[][])[2]",
                "$r2 = newarray (boolean[])[2]",
                "$r2[0] = 1",
                "$r2[1] = 0",
                "$r1[0] = $r2",
                "$r3 = newarray (boolean[])[1]",
                "$r3[0] = 1",
                "$r1[1] = $r3",
                "return")
            .collect(Collectors.toList()));
    methodList.put("booleanArrays", methodValues);

    methodValues = new HashMap<>();
    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (char[][])[3]",
                "$r2 = newarray (char[])[3]",
                "$r2[0] = 65",
                "$r2[1] = 98",
                "$r2[2] = 38",
                "$r1[0] = $r2",
                "$r3 = newarray (char[])[2]",
                "$r3[0] = 99",
                "$r3[1] = 36",
                "$r1[1] = $r3",
                "$r4 = newarray (char[])[2]",
                "$r4[0] = 50",
                "$r4[1] = 71",
                "$r1[2] = $r4",
                "return")
            .collect(Collectors.toCollection(ArrayList::new)));
    methodList.put("charArrays", methodValues);

    methodValues = new HashMap<>();
    methodValues.put("methodSignature", "void");
    methodValues.put(
        "jimpleLines",
        Stream.of(
                "r0 := @this: InitializeMultidimensionalArrays",
                "$r1 = newarray (java.lang.String[][])[2]",
                "$r2 = newarray (java.lang.String[])[1]",
                "$r2[0] = \"Hello World\"",
                "$r1[0] = $r2",
                "$r3 = newarray (java.lang.String[])[2]",
                "$r3[0] = \"Greetings\"",
                "$r3[1] = \"Welcome\"",
                "$r1[1] = $r3",
                "return")
            .collect(Collectors.toCollection(ArrayList::new)));
    methodList.put("stringArrays", methodValues);

    return methodList;
  }
}
