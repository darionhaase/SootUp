/** @author: Hasitha Rajapakse */
package de.upb.swt.soot.test.java.sourcecode.minimaltestsuite.java6;

import categories.Java8Test;
import de.upb.swt.soot.core.model.SootMethod;
import de.upb.swt.soot.core.signatures.MethodSignature;
import de.upb.swt.soot.test.java.sourcecode.minimaltestsuite.MinimalSourceTestSuiteBase;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Java8Test.class)
public class ForLoopTest extends MinimalSourceTestSuiteBase {

  @Override
  public MethodSignature getMethodSignature() {
    return identifierFactory.getMethodSignature(
        "forLoop", getDeclaredClassSignature(), "void", Collections.emptyList());
  }

  /**  <pre>    public void forLoop(){
   * int j = 10;
   * int num = 0;
   * for(int i=0; i<j; i++){
   * num++;
   * }
   * <pre>*/
  @Override
  public List<String> expectedBodyStmts() {
    return Stream.of(
            "r0 := @this: ForLoop",
            "$i0 = 10",
            "$i1 = 0",
            "$i2 = 0",
            "label1:",
            "$z0 = $i2 < $i0",
            "if $z0 == 0 goto label2",
            "$i3 = $i1",
            "$i4 = $i1 + 1",
            "$i1 = $i4",
            "$i5 = $i2",
            "$i6 = $i2 + 1",
            "$i2 = $i6",
            "goto label1",
            "label2:",
            "return")
        .collect(Collectors.toList());
  }

  @Test
  public void test() {
    SootMethod method = loadMethod(getMethodSignature());
    assertJimpleStmts(method, expectedBodyStmts());
  }
}
