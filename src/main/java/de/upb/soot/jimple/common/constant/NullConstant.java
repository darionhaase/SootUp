/* Soot - a J*va Optimization Framework
 * Copyright (C) 1997-1999 Raja Vallee-Rai
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */

package de.upb.soot.jimple.common.constant;

import de.upb.soot.jimple.common.type.NullType;
import de.upb.soot.jimple.common.type.Type;
import de.upb.soot.jimple.visitor.IConstantVisitor;
import de.upb.soot.jimple.visitor.IVisitor;
import de.upb.soot.signatures.NullTypeSignature;

import java.util.Comparator;

public class NullConstant extends Constant {
  /**
   * 
   */
  private static final long serialVersionUID = 8286431855238615958L;
  private static NullConstant nullconstant;

  private NullConstant() {
  }

  public static NullConstant getInstance() {
    if (nullconstant == null) {
      nullconstant = new NullConstant();
    }
    return nullconstant;
  }

  @Override
  public boolean equals(Object c) {
    return c == nullconstant;
  }

  @Override
  public int hashCode() {
    return 982;
  }

  @Override
  public Type getType() {
    return NullType.getInstance();
  }

  @Override
  public void accept(IVisitor sw) {
    ((IConstantVisitor) sw).caseNullConstant(this);
  }

  @Override
  public String toString() {
    return NullTypeSignature.NULL_TYPE_SIGNATURE.toString();
  }

  @Override
  public boolean equivTo(Object o, Comparator<? extends Object> comparator) {
    // TODO Auto-generated method stub
    return false;
  }
}