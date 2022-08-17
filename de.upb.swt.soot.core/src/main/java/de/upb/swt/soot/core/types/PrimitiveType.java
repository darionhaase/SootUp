package de.upb.swt.soot.core.types;

/*-
 * #%L
 * Soot
 * %%
 * Copyright (C) 2019-2020 Christian Brüggemann, Markus Schmidt
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import de.upb.swt.soot.core.jimple.visitor.TypeVisitor;
import javax.annotation.Nonnull;

/** Represents Java's primitive types. */
public abstract class PrimitiveType extends Type {

  /**
   * Signatures of primitive types are unique and should not be created from the outside, thus the
   * constructor is private.
   *
   * @param name the primitive's name
   */
  private PrimitiveType(@Nonnull String name) {
    this.name = name;
  }

  @Nonnull private final String name;

  /**
   * Gets the primitive type's name.
   *
   * @return The value to get.
   */
  @Nonnull
  public String getName() {
    return name;
  }

  @Override
  @Nonnull
  public String toString() {
    return name;
  }

  public static boolean isIntLikeType(Type t) {
    return t == PrimitiveType.IntType.getInstance()
        || t == PrimitiveType.ByteType.getInstance()
        || t == PrimitiveType.ShortType.getInstance()
        || t == PrimitiveType.CharType.getInstance()
        || t == PrimitiveType.BooleanType.getInstance();
  }

  @Nonnull
  public static ByteType getByte() {
    return ByteType.getInstance();
  }

  @Nonnull
  public static ShortType getShort() {
    return ShortType.getInstance();
  }

  @Nonnull
  public static IntType getInt() {
    return IntType.getInstance();
  }

  @Nonnull
  public static LongType getLong() {
    return LongType.getInstance();
  }

  @Nonnull
  public static FloatType getFloat() {
    return FloatType.getInstance();
  }

  @Nonnull
  public static DoubleType getDouble() {
    return DoubleType.getInstance();
  }

  @Nonnull
  public static CharType getChar() {
    return CharType.getInstance();
  }

  @Nonnull
  public static BooleanType getBoolean() {
    return BooleanType.getInstance();
  }

  @Nonnull
  public static Integer1Type getInteger1() {
    return Integer1Type.getInstance();
  }

  @Nonnull
  public static Integer127Type getInteger127() {
    return Integer127Type.getInstance();
  }

  @Nonnull
  public static Integer32767Type getInteger32767() {
    return Integer32767Type.getInstance();
  }

  public static class ByteType extends PrimitiveType implements IntegerType {
    private static final ByteType INSTANCE = new ByteType();

    private ByteType() {
      super("byte");
    }

    public static ByteType getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      v.caseByteType();
    }
  }

  public static class ShortType extends PrimitiveType implements IntegerType {
    private static final ShortType INSTANCE = new ShortType();

    private ShortType() {
      super("short");
    }

    public static ShortType getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      v.caseShortType();
    }
  }

  public static class IntType extends PrimitiveType implements IntegerType {
    private static final IntType INSTANCE = new IntType();

    public IntType() {
      super("int");
    }

    public static IntType getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      v.caseIntType();
    }
  }

  public static class DoubleType extends PrimitiveType {
    private static final DoubleType INSTANCE = new DoubleType();

    private DoubleType() {
      super("double");
    }

    public static DoubleType getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      v.caseDoubleType();
    }
  }

  public static class LongType extends PrimitiveType {
    private static final LongType INSTANCE = new LongType();

    private LongType() {
      super("long");
    }

    public static LongType getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      v.caseLongType();
    }
  }

  public static class FloatType extends PrimitiveType {
    private static final FloatType INSTANCE = new FloatType();

    private FloatType() {
      super("float");
    }

    public static FloatType getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      v.caseFloatType();
    }
  }

  public static class CharType extends PrimitiveType implements IntegerType {
    private static final CharType INSTANCE = new CharType();

    private CharType() {
      super("char");
    }

    public static CharType getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      v.caseCharType();
    }
  }

  public static class BooleanType extends PrimitiveType implements IntegerType {
    private static final BooleanType INSTANCE = new BooleanType();

    private BooleanType() {
      super("boolean");
    }

    public static BooleanType getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      v.caseBooleanType();
    }
  }

  /**
   * This type is intermediate type and used for determining the ancestor of an integer type. see:
   * AugmentHierarchy;
   */
  public static class Integer1Type extends PrimitiveType implements IntegerType {
    private static final Integer1Type INSTANCE = new Integer1Type();

    private Integer1Type() {
      super("integer1");
    }

    public static Integer1Type getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      // todo: case for Integer1Type
    }
  }

  /** This type is intermediate type and used for determining the ancestor of an integer type */
  public static class Integer127Type extends PrimitiveType implements IntegerType {
    private static final Integer127Type INSTANCE = new Integer127Type();

    private Integer127Type() {
      super("integer127");
    }

    public static Integer127Type getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      // todo: case for Integer127Type
    }
  }

  /** This type is intermediate type and used for determining the ancestor of an integer type */
  public static class Integer32767Type extends PrimitiveType implements IntegerType {
    private static final Integer32767Type INSTANCE = new Integer32767Type();

    private Integer32767Type() {
      super("integer32767");
    }

    public static Integer32767Type getInstance() {
      return INSTANCE;
    }

    @Override
    public void accept(@Nonnull TypeVisitor v) {
      // todo: case for Integer32767Type
    }
  }
}
