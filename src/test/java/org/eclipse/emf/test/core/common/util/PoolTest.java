/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.common.util;


import org.eclipse.emf.common.util.Pool;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;


public class PoolTest //extends TestCase
{
  /*public PoolTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("PoolTest");
    suite.addTest(new PoolTest("testWeakness"));
    return suite;
  }*/

  @Test
  public void testWeakness()
  {
    Pool<String> set = new Pool<String>();
    {
      String foo = new String("foo");
      set.add(foo);
      Assert.assertTrue(set.contains("foo"));
      Assert.assertEquals(1, set.size());
      foo = null;
    }
    System.gc();
    Assert.assertFalse(set.contains("foo"));
    try
    {
      // If the garbage collector a chance to enqueue the stale entries.
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      // Expected.
    }
    Assert.assertEquals(0, set.size());
  }
}
  