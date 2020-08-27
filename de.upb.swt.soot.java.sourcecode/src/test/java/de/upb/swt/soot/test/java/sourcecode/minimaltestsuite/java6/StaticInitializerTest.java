package de.upb.swt.soot.test.java.sourcecode.minimaltestsuite.java6;

import static org.junit.Assert.assertTrue;

import de.upb.swt.soot.core.model.SootClass;
import de.upb.swt.soot.core.model.SootMethod;
import de.upb.swt.soot.core.signatures.MethodSignature;
import de.upb.swt.soot.test.java.sourcecode.minimaltestsuite.MinimalSourceTestSuiteBase;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/** @author Kaustubh Kelkar */
public class StaticInitializerTest extends MinimalSourceTestSuiteBase {
  public MethodSignature getMethodSignature() {
    return identifierFactory.getMethodSignature(
        "methodStaticInitializer", getDeclaredClassSignature(), "void", Collections.emptyList());
  }

  public MethodSignature getStaticMethodSignature() {
    return identifierFactory.getMethodSignature(
        "<clinit>", getDeclaredClassSignature(), "void", Collections.emptyList());
  }

  @Test
  public void test() {
    SootMethod method = loadMethod(getMethodSignature());
    assertJimpleStmts(method, expectedBodyStmts());
    SootClass clazz = loadClass(getDeclaredClassSignature());
    assertTrue(
        clazz.getFields().stream()
            .anyMatch(sootField -> sootField.getName().equals("i") && sootField.isStatic()));

    final SootMethod staticMethod = loadMethod(getStaticMethodSignature());
    assertTrue(staticMethod.isStatic());
    assertJimpleStmts(staticMethod, expectedBodyStmtsOfClinit());
  }

  /**
   *
   *
   * <pre>
   * static int i=5;
   *
   *     static{
   *         if(i>4)
   *         {
   *             i=4;
   *         }
   *     }
   *    </pre>
   */
  public List<String> expectedBodyStmtsOfClinit() {
    return Stream.of(
            "<StaticInitializer: int i> = 5",
            "$i0 = <StaticInitializer: int i>",
            "$z0 = $i0 > 4",
            "if $z0 == 0 goto label1",
            "<StaticInitializer: int i> = 4",
            "goto label1",
            "label1:",
            "return")
        .collect(Collectors.toList());
  }

  /**
   *
   *
   * <pre>
   *     static void methodStaticInitializer(){
   * System.out.println(i);
   * }
   * </pre>
   */
  @Override
  public List<String> expectedBodyStmts() {
    return Stream.of(
            "$r0 = <java.lang.System: java.io.PrintStream out>",
            "$i0 = <StaticInitializer: int i>",
            "virtualinvoke $r0.<java.io.PrintStream: void println(int)>($i0)",
            "return")
        .collect(Collectors.toList());
  }
}
