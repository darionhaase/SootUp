/** @author: Hasitha Rajapakse */
package de.upb.swt.soot.test.java.bytecode.minimaltestsuite.java6;

import categories.Java8Test;
import de.upb.swt.soot.core.signatures.MethodSignature;
import de.upb.swt.soot.test.java.bytecode.minimaltestsuite.MinimalBytecodeTestSuiteBase;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.experimental.categories.Category;

@Category(Java8Test.class)
public class AssertStatementTest extends MinimalBytecodeTestSuiteBase {
  @Override
  public MethodSignature getMethodSignature() {
    return identifierFactory.getMethodSignature(
        "assertStatement", getDeclaredClassSignature(), "void", Collections.emptyList());
  }

  @Override
  public List<String> expectedBodyStmts() {
    return Stream.of(
            "l0 := @this: AssertStatement",
            "$stack1 = <AssertStatement: Z $assertionsDisabled>",
            "if $stack1 != 0 goto label1",
            "if \"\" != null goto label1",
            "$stack2 = new java/lang/AssertionError",
            "specialinvoke $stack2.<java.lang.AssertionError: void <init>()>()",
            "throw $stack2",
            "label1:",
            "return")
        .collect(Collectors.toList());
  }
}
