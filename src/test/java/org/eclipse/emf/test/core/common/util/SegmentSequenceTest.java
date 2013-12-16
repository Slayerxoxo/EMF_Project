/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.common.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.eclipse.emf.common.util.InterningSet;
import org.eclipse.emf.common.util.SegmentSequence;
import org.junit.Assert;
import org.junit.Test;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;


public class SegmentSequenceTest //extends TestCase
{
 /* public SegmentSequenceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("SegmentSequenceTest");
    suite.addTest(new SegmentSequenceTest("test"));
    return suite;
  }*/
  
  @Test
  public void test()
  {
    {
      SegmentSequence s1 = SegmentSequence.create("/", "");
      Assert.assertEquals(1, s1.segmentCount());
      Assert.assertSame("", s1.firstSegment());
      Assert.assertSame("", s1.lastSegment());
      Assert.assertSame("", s1.segment(0));
      Assert.assertEquals("", s1.toString());
      Assert.assertEquals("/", s1.delimiter());

      SegmentSequence s2 = SegmentSequence.create("/", "");
      Assert.assertSame(s1, s2);
      
      SegmentSequence s3 = SegmentSequence.create("/", new String[] { "" });
      Assert.assertSame(s2, s3);
      
      SegmentSequence s4 = SegmentSequence.create("/", new String[0]);
      Assert.assertNotSame(s3, s4);
      Assert.assertEquals(0, s4.segmentCount());
      Assert.assertEquals("", s4.toString());
      
      SegmentSequence s5 = SegmentSequence.create("/", new String[0]);
      Assert.assertSame(s4, s5);
      
      SegmentSequence s6 = SegmentSequence.create("/", new String[0]);
      Assert.assertSame(s5, s6);
      
      SegmentSequence s7 = SegmentSequence.create("/");
      Assert.assertSame(s6, s7);
      
      SegmentSequence s8 = SegmentSequence.create("/").append("");
      Assert.assertSame(s3, s8);
    }
    {
      SegmentSequence s1 = SegmentSequence.create("", "");
      Assert.assertEquals(0, s1.segmentCount());
      Assert.assertEquals(null, s1.firstSegment());
      Assert.assertEquals(null, s1.lastSegment());
      Assert.assertEquals("", s1.toString());
      Assert.assertSame("", s1.delimiter());

      SegmentSequence s2 = SegmentSequence.create("", "");
      Assert.assertSame(s1, s2);
      
      SegmentSequence s3 = SegmentSequence.create("", new String[] { "" });
      Assert.assertSame(s2, s3);
      
      SegmentSequence s4 = SegmentSequence.create("", new String[0]);
      Assert.assertSame(s3, s4);
      
      SegmentSequence s5 = SegmentSequence.create("", new String[0]);
      Assert.assertSame(s4, s5);
      
      SegmentSequence s6 = SegmentSequence.create("", new String[0]);
      Assert.assertSame(s5, s6);
      
      SegmentSequence s7 = SegmentSequence.create("");
      Assert.assertSame(s6, s7);

      SegmentSequence s8 = SegmentSequence.create("").append("");
      Assert.assertSame(s7, s8);

      SegmentSequence s9 = SegmentSequence.create("", "", "");
      Assert.assertSame(s8, s9);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "/");
      Assert.assertEquals(2, s1.segmentCount());
      Assert.assertEquals("/", s1.toString());
      Assert.assertEquals("/", s1.delimiter());

      SegmentSequence s2 = SegmentSequence.create("/", "/");
      Assert.assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("/", new String[] { "", ""});
      Assert.assertSame(s2, s3);

      SegmentSequence s4 = SegmentSequence.create("/", "", "");
      Assert.assertSame(s3, s4);

      SegmentSequence s5 = SegmentSequence.create("/").append("").append("");
      Assert.assertSame(s4, s5);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "a");
      Assert.assertEquals(1, s1.segmentCount());
      Assert.assertEquals("a", s1.toString());
      
      SegmentSequence s2 = SegmentSequence.create("/", "a");
      Assert.assertSame(s1, s2);
      
      SegmentSequence s3 = SegmentSequence.create("/").append("a");
      Assert.assertSame(s2, s3);
      
      SegmentSequence s4 = SegmentSequence.create("/").append("a").append("a");
      Assert.assertEquals(2, s4.segmentCount());
      Assert.assertSame(s4.segment(0), s4.segment(1));
    }


    {
      SegmentSequence s1 = SegmentSequence.create("/", "/a");
      Assert.assertEquals(2, s1.segmentCount());
      Assert.assertEquals("/a", s1.toString());

      SegmentSequence s2 = SegmentSequence.create("/", "/a");
      Assert.assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("/").append("/a");
      Assert.assertSame(s2, s3);

      SegmentSequence s4 = SegmentSequence.create("/", "").append("a");
      Assert.assertSame(s3, s4);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("", "a");
      Assert.assertEquals(1, s1.segmentCount());
      Assert.assertEquals("a", s1.toString());
      
      SegmentSequence s2 = SegmentSequence.create("", "a");
      Assert.assertSame(s1, s2);
      
      SegmentSequence s3 = SegmentSequence.create("").append("a");
      Assert.assertSame(s2, s3);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("", "/a");
      Assert.assertEquals(1, s1.segmentCount());
      Assert.assertEquals("/a", s1.toString());

      SegmentSequence s2 = SegmentSequence.create("", "/a");
      Assert.assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("").append("/a");
      Assert.assertSame(s2, s3);

      SegmentSequence s4 = SegmentSequence.create("", "/").append("a");
      Assert.assertNotSame(s3, s4);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "/a/b");
      Assert.assertEquals(3, s1.segmentCount());
      Assert.assertEquals("/a/b", s1.toString());

      SegmentSequence s2 = SegmentSequence.create("/", "").append("a").append("b");
      Assert.assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("/", "", "a", "b");
      Assert.assertSame(s2, s3);

      SegmentSequence s4 = SegmentSequence.create("/", "/a").append("b");
      Assert.assertSame(s3, s4);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "a", "b", "c");
      Assert.assertEquals("a/b/c", s1.toString());

      SegmentSequence s2 = SegmentSequence.create("/", "a", "b", "c", "d");
      Assert.assertEquals("a/b/c/d", s2.toString());
      
      Assert.assertSame(s1.delimiter(), s2.delimiter());
      Assert.assertSame(s1.segment(0), s2.segment(0));
      Assert.assertSame(s1.segment(1), s2.segment(1));
      Assert.assertSame(s1.segment(2), s2.segment(2));
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "x", "y", "z");
      SegmentSequence s2 = SegmentSequence.create("/").append("x/y/z");
      Assert.assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("/").append("x", "y", "z");
      Assert.assertSame(s2, s3);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("");
      SegmentSequence s2 = SegmentSequence.create("/");
      Assert.assertSame(getSegments(s1), getSegments(s2));

      SegmentSequence s3 = SegmentSequence.create("", "a");
      SegmentSequence s4 = SegmentSequence.create("/").append("a");
      Assert.assertSame(getSegments(s3), getSegments(s4));
      
      SegmentSequence s5 = SegmentSequence.create("", "b");
      SegmentSequence s6 = SegmentSequence.create("/", "b");
      Assert.assertSame(getSegments(s5), getSegments(s6));

      SegmentSequence s7 = s5.append("c");
      SegmentSequence s8 = s6.append("c");
      Assert.assertSame(getSegments(s7), getSegments(s8));
      
      SegmentSequence s9 = s7.append(SegmentSequence.create("", "d", "e"));
      SegmentSequence s10 = s8.append(SegmentSequence.create("/", "d/e"));
      Assert.assertEquals("bcde", s9.toString());
      Assert.assertEquals("b/c/d/e", s10.toString());
      Assert.assertSame(getSegments(s9), getSegments(s10));
    }
  }
  
  private static final Field SEGMENT_SEQUENCE_SEGMENTS;
  static
  {
    Field field = null;
    try
    {
      field = SegmentSequence.class.getDeclaredField("segments");
      field.setAccessible(true);
    }
    catch (Throwable throwable)
    {
      // Ignore
    }
    SEGMENT_SEQUENCE_SEGMENTS = field;
  }

  private String[] getSegments(SegmentSequence segmentSequence)
  {
    try
    {
      return (String[])SEGMENT_SEQUENCE_SEGMENTS.get(segmentSequence);
    }
    catch (Throwable throwable)
    {
      Assert.fail("No access to segments");
      return null;
    }
  }
  
  public static void main(String[] args)
  {
    int count = 1000000;
    String[] data = new String[count];
    // String[] dataCopy = new String[count];
    Random random = new Random(0);
    Set<String> allStrings = new HashSet<String>();
    int stringSize = 5;
    for (int i = 0; i < count; ++i)
    {
      char[] characters = new char[stringSize];
      for (int j = 0; j < stringSize; ++j)
      {
        characters[j] = (char)(((0x7FFFFFFF & random.nextInt()) % 26) + 'a');
      }
      allStrings.add(data[i] = new String(characters));
    }

    int segmentCount = 1000000;
    Set<String> segments = new HashSet<String>();
    String[] segmentData = new String[segmentCount];
    SegmentSequence[] segmentDataCopy = new SegmentSequence[segmentCount];
    int segmentSize = 4;
    for (int i = 0; i < segmentCount; ++i)
    {
      StringBuilder builder = new StringBuilder();
      for (int j = 0; j < segmentSize; ++j)
      {
        builder.append("/");
        builder.append(data[((0x7FFFFFFF & random.nextInt()) % count)]);
      }
      segments.add(segmentData[i] = builder.toString());
    }

    int repeat = 20;
    System.err.println("Creating Segments");
    for (int i = 0; i < repeat; ++i)
    {
      System.gc();
      {
        long start = System.currentTimeMillis();
        for (int j = 0; j < segmentCount; ++j)
        {
          String value = segmentData[j];
          segmentDataCopy[j] = SegmentSequence.create("/", value);
        }
        long end = System.currentTimeMillis();
        System.out.println("Pool      {" + POOL.size() + "} elapsed time: " + (end - start));
        if (new HashSet<SegmentSequence>(POOL).size() != POOL.size())
        {
          System.err.println("###" + new HashSet<SegmentSequence>(POOL).size() + " <=>" + POOL.size());
          System.err.println("###" + new HashSet<SegmentSequence>(POOL).size() + " <=>" + POOL.size());
          // POOL.dump();
          System.err.println("###" + new HashSet<SegmentSequence>(POOL));
        }
      }
      System.gc();
      {
        long start = System.currentTimeMillis();
        Set<SegmentSequence> set = new HashSet<SegmentSequence>();
        for (int j = 0; j < segmentCount; ++j)
        {
          SegmentSequence value = segmentDataCopy[j];
          set.add(value);
        }
        long end = System.currentTimeMillis();
        System.out.println("Set       {" + POOL.size() + "} elapsed time: " + (end - start));
      }
      System.gc();
      {
        String[][] arrays = new String[segmentCount][];
        long start = System.currentTimeMillis();
        for (int j = 0; j < segmentCount; ++j)
        {
          String value = segmentData[j];
          arrays[j] = value.split("/");
        }
        long end = System.currentTimeMillis();
        System.out.println("String spl{" + POOL.size() + "} elapsed time: " + (end - start));
      }
    }
  }
  
  private static  final InterningSet<SegmentSequence> POOL;

  static
  {
    InterningSet<SegmentSequence> segmentSequencePool = null;
    try
    {
      @SuppressWarnings("unchecked")
      Class<InterningSet<String>> segmentSequenceClass = (Class<InterningSet<String>>)Class.forName("org.eclipse.emf.common.util.SegmentSequence");
      Field pool = segmentSequenceClass.getDeclaredField("POOL");
      pool.setAccessible(true);
      @SuppressWarnings("unchecked")
      InterningSet<SegmentSequence> result = (InterningSet<SegmentSequence>)pool.get(null);
      segmentSequencePool = result;
    }
    catch (Throwable throwable)
    {
      Assert.fail();
    }
    POOL = segmentSequencePool;
  }
}
