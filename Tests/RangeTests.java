package jfree.RangeTest;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Test;

public class RangeTests {
	
	private Range exampleRange;
	private Range sampleRange;
	
	
	@Test
	public void testCorrectLowerBound1() {
		sampleRange = new Range(-200.87, 200.87);
		assertEquals("The lower bound for the range (-200.87, 200.87) should be -200.87", -200.87, sampleRange.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testCorrectLowerBound2() {
		sampleRange = new Range(-5.69, 20.92);
		assertEquals("The lower bound for the range (-5.69, 20.92) should be -5.69", -5.69, sampleRange.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testCorrectLowerBound3() {
		sampleRange = new Range(-90.99, -3.90);
		assertEquals("The lower bound for the range (-90.99, -3.90) should be -90.99", -90.99, sampleRange.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testCorrectLowerBound4() {
		sampleRange = new Range(0.00, 3.89);
		assertEquals("The lower bound for the range (0.00, 3.89) should be 0.00", 0.00, sampleRange.getLowerBound(), .000000001d);
	}
	
	
	@Test
	public void testCorrectUpperBound1() {
		sampleRange = new Range(-200.87, 200.87);
		assertEquals("The upper bound for the range (-200.87, 200.87) should be 200.87", 200.87, sampleRange.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testCorrectUpperBound2() {
		sampleRange = new Range(-5.69, 20.92);
		assertEquals("The upper bound for the range (-5.69, 20.92) should be 20.92", 20.92, sampleRange.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testCorrectUpperBound3() {
		sampleRange = new Range(-90.99, -3.90);
		assertEquals("The upper bound for the range (-90.99, -3.90) should be -3.90", -3.90, sampleRange.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testCorrectUpperBound4() {
		sampleRange = new Range(0.00, 3.89);
		assertEquals("The upper bound for the range (0.00, 3.89) should be 3.89", 3.89, sampleRange.getUpperBound(), .000000001d);
	}
	
	/**
	 * Tests getLength method where the ranges lower and upper bounds are negative
	 */
	@Test
	public void testNegativeUpperAndLowerBounds() {
		exampleRange = new Range(-100.01, -34.23);
		assertEquals("The length of the range (-100.01, -34.23) should be 65.78", 65.78, 
				exampleRange.getLength(), .000000001d);
	}
	
	/**
	 * Tests getLength method where the ranges lower bound is negative and the upper bound is positive
	 */
	@Test
	public void testNegativeLowerBoundPositiveUpperBound() {
		exampleRange = new Range(-100.01, 15.1);
		assertEquals("The length of the range (-100.01, 15.1) should be 115.11", 115.11, 
				exampleRange.getLength(), .000000001d);
	}
	
	/**
	 * Tests getLength method where the ranges lower and upper bounds are positive
	 */
	@Test
	public void testPositiveUpperBoundAndLowerBounds() {
		exampleRange = new Range(10.01, 15.1);
		assertEquals("The length of the range (10.01, 15.1) should be 5.09", 5.09, 
				exampleRange.getLength(), .000000001d);
	}
	
	/**
	 * Tests getLength method where the ranges lower and upper bounds are equal
	 */
	@Test
	public void testUpperBoundEqualsLowerBound() {
		exampleRange = new Range(1, 1);
		assertEquals("The length of the range (1, 1) should be 0", 0, 
				exampleRange.getLength(), .000000001d);
	}
	
	@Test
	public void testInvalidInput() {
		
		
		boolean thrown = false;
		
		try {
			exampleRange = new Range(1, -1);
			exampleRange.getLength();
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
		
	}
	
	@Test
	public void testCentralValue() {
		exampleRange = new Range(-1, 1);
		
		assertEquals("Central value should be 0", 0, exampleRange.getCentralValue(), .000000001d);
		
	}
	
	@Test
	public void testCentralValueNotNaN() {
		exampleRange = new Range(-1, 1);
		
		assertFalse("Central value should be 0", Double.isNaN(exampleRange.getCentralValue()));
	}
	
	@Test
	public void testCentralValuePositive() {
		exampleRange = new Range(1, 2);
		
		assertEquals("Central value should be 1.5", 1.5, exampleRange.getCentralValue(), .000000001d);
	}
	
	@Test
	public void testCentralValuePositive2() {
		exampleRange = new Range(1, 2);
		double actual = exampleRange.getCentralValue();
		actual = exampleRange.getCentralValue();
		assertEquals("Central value should be 1.5", 1.5, actual, .000000001d);
	}
	
	@Test
	public void testIntersects() {
		exampleRange = new Range(-1, 1);
		Range range2 = new Range(0, 2);
		
		assertTrue(exampleRange.intersects(range2));
		
	}
	
	@Test
	public void testIntersectsDoesntChangeBounds() {
		
		Range range1 = new Range(0, 2);
		Range range2 = new Range(1, 3);
		
		range1.intersects(range2);
		range1.intersects(range2);
		
		boolean actual = false;
		
		if (range1.getUpperBound() == 2 && 
			range1.getLowerBound() == 0 && 
			range2.getLowerBound() == 1 && 
			range2.getUpperBound() == 3) 
		{
			actual = true;
		}
		
		assertTrue("insert changed  the values of the bounds", actual);
	}
	
	@Test
	public void testIntersectsDoesntChangeBounds2() {
		
		Range range1 = new Range(0, 2);
		
		range1.intersects(1, 3);
		
		boolean actual = false;
		
		if (range1.getUpperBound() == 2 && 
			range1.getLowerBound() == 0)
		{
			actual = true;
		}
		
		assertTrue("insert changed  the values of the bounds", actual);
	}
	
	@Test
	public void testDoesntIntersect() {
		exampleRange = new Range(-1, 1);
		Range range2 = new Range(2, 3);
		
		assertFalse(exampleRange.intersects(range2));
	}
	
	@Test
	public void testDoesntIntersectBelow() {
		exampleRange = new Range(-1, 1);
		Range range2 = new Range(-4, -2);
		
		assertFalse(exampleRange.intersects(range2));
	}
	
	@Test
	public void testToString() {
		
		exampleRange = new Range(-1, 1);
		assertEquals("Range[-1.0,1.0]", exampleRange.toString());
	}
	
	@Test
	public void testToStringDoesntChangeBounds() {
		
		exampleRange = new Range(-1, 1);
		exampleRange.toString();
		assertEquals("Range[-1.0,1.0]", exampleRange.toString());
		assertEquals(-1, exampleRange.getLowerBound(), 0.00001d);
		assertEquals(1, exampleRange.getUpperBound(), 0.00001d);
		
		
	}
	
	@Test
	public void testIsNaNRange() {
		exampleRange = new Range(Double.NaN, Double.NaN);
		assertTrue(exampleRange.isNaNRange());
	}
	
	@Test
	public void testIsNotNaNRange() {
		exampleRange = new Range(-1, 1);
		assertFalse(exampleRange.isNaNRange());
	}
	
	@Test
	public void inputBelowLowerBoundShouldBeFalse() {
		exampleRange = new Range(-10.23, 25.02);
		assertFalse("The value -10.24 should not be within (-10.23, 25.02)", exampleRange.contains(-10.24));
	}
	
	/**
	 * Tests the contains method where the input is the lower bound of the range,
	 * which should return true
	 */
	@Test
	public void inputAtLowerBoundShouldBeTrue() {
		exampleRange = new Range(-10.23, 25.02);
		assertTrue("The value -10.23 should be within (-10.23, 25.02)", exampleRange.contains(-10.23));
	}
	
	/**
	 * Tests the contains method where the input is just above the lower bound of the range,
	 * which should return true
	 */
	@Test
	public void inputAboveLowerBoundShouldBeTrue() {
		exampleRange = new Range(-10.23, 25.02);
		assertTrue("The value -10.22 should be within (-10.23, 25.02)", exampleRange.contains(-10.22));
	}
	
	/**
	 * Tests the contains method where the input is a nominal value in the range,
	 * which should return true
	 */
	@Test
	public void inputOfNominalValueShouldBeTrue() {
		exampleRange = new Range(-10.23, 25.02);
		assertTrue("The value 0.06 should be within (-10.23, 25.02)", exampleRange.contains(0.06));
	}
	
	/**
	 * Tests the contains method where the input is just below the upper bound of the range,
	 * which should return true
	 */
	@Test
	public void inputBelowUpperBoundShouldBeTrue() {
		exampleRange = new Range(-10.23, 25.02);
		assertTrue("The value 25.01 should be within (-10.23, 25.02)", exampleRange.contains(25.01));
	}
	
	/**
	 * Tests the contains method where the input is the upper bound of the range,
	 * which should return true
	 */
	@Test
	public void inputAtUpperBoundShouldBeTrue() {
		exampleRange = new Range(-10.23, 25.02);
		assertTrue("The value 25.02 should be within (-10.23, 25.02)", exampleRange.contains(25.02));
	}
	
	@Test
	public void testContainsRangeIsSingleValue1() {
		exampleRange = new Range(1, 1);
		assertFalse("The value 25.02 should be within (-10.23, 25.02)", exampleRange.contains(25.02));
	}
	
	@Test
	public void testContainsRangeIsSingleValue2() {
		exampleRange = new Range(1, 1);
		assertTrue("The value 25.02 should be within (-10.23, 25.02)", exampleRange.contains(1));
	}
	
	@Test
	public void testContainsRangeIsSingleValue3() {
		exampleRange = new Range(1, 1);
		assertFalse("The value 25.02 should be within (-10.23, 25.02)", exampleRange.contains(-1));
	}
	
	
	/**
	 * Tests the contains method where the input is just above the upper bound of the range,
	 * which should return false
	 */
	@Test
	public void inputAboveUpperBoundShouldBeFalse() {
		exampleRange = new Range(-10.23, 25.02);
		assertFalse("The value 25.03 should not be within (-10.23, 25.02)", exampleRange.contains(25.03));
	}
	
	  @Test
	    public void inputBelowLowerBoundShouldReturnLowerBound( ) {
	    	exampleRange = new Range(-10.23, 25.02);
	    	assertEquals("The value within the range (-10.23, 25.02) that is closest to -10.25 should be -10.23", -10.23, 
	    			exampleRange.constrain(-10.25), .000000001d);
	    }
	    
	    /**
	     * Tests the constrain method where the input value is the lower bound, which should return 
	     * the lower bound of the range
	     */
	    @Test
	    public void inputOfLowerBoundShouldReturnLowerBound() {
	    	exampleRange = new Range(-10.23, 25.02);
	    	assertEquals("The value within the range (-10.23, 25.02) that is closest to -10.23 should be -10.23", -10.23, 
	    			exampleRange.constrain(-10.23), .000000001d);
	    }
	    
	    /**
	     * Tests the constrain method where the input value is just above the lower bound, which should
	     * return the input value
	     */
	    @Test
	    public void inputAboveLowerBoundShouldReturnInputValue() {
	    	exampleRange = new Range(-10.23, 25.02);
	    	assertEquals("The value within the range (-10.23, 25.02) that is closest to -10.20 should be -10.20", -10.20, 
	    			exampleRange.constrain(-10.20), .000000001d);
	    }
	    
	    /**
	     * Tests the constrain method where the input value is a nominal value, which should
	     * return the input value
	     */
	    @Test
	    public void inputOfNominalValueShouldReturnInputValue() {
	    	exampleRange = new Range(-10.23, 25.02);
	    	assertEquals("The value within the range (-10.23, 25.02) that is closest to 0.05 should be 0.05", 0.05, 
	    			exampleRange.constrain(0.05), .000000001d);
	    }
	    
	    /**
	     * Tests the constrain method where the input value is just below the upper bound, which should
	     * return the input value
	     */
	    @Test
	    public void inputBelowUpperBoundShouldReturnInputValue() {
	    	exampleRange = new Range(-10.23, 25.02);
	    	assertEquals("The value within the range (-10.23, 25.02) that is closest to 25.01 should be 25.01", 25.01, 
	    			exampleRange.constrain(25.01), .000000001d);
	    }
	    
	    /**
	     * Tests the constrain method where the input value is the upper bound, which should
	     * return the upper bound of the range
	     */
	    @Test
	    public void inputAtUpperBoundShouldReturnUpperBound() {
	    	exampleRange = new Range(-10.23, 25.02);
	    	assertEquals("The value within the range (-10.23, 25.02) that is closest to 25.02 should be 25.02", 25.02, 
	    			exampleRange.constrain(25.02), .000000001d);
	    }
	    
	    /**
	     * Tests the constrain method where the input value is just above the upper bound, which should
	     * return the upper bound of the range
	     */
	    @Test
	    public void inputAboveUpperBoundShouldReturnUpperBound() {
	    	exampleRange = new Range(-10.23, 25.02);
	    	assertEquals("The value within the range (-10.23, 25.02) that is closest to 25.03 should be 25.02", 25.02, 
	    			exampleRange.constrain(25.03), .000000001d);
	    }
	    
	    @Test
	    public void testCombine() {
	    	exampleRange = new Range(1, 2);
	    	Range range2 = new Range(3, 4);
	    	
	    	Range combined = Range.combine(exampleRange, range2);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(4.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    
	    @Test
	    public void testCombineNull() {
	    	exampleRange = new Range(1, 2);
	    	Range combined = Range.combine(exampleRange, null);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(2.0, combined.getUpperBound(), .000000001d);
	    	
	    	
	    }
	    
	    @Test
	    public void testCombineNull2() {
	    	exampleRange = new Range(1, 2);
	    	Range combined = Range.combine(null, exampleRange);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(2.0, combined.getUpperBound(), .000000001d);
	    	
	    	
	    }
	    
	    @Test
	    public void testCombineNaN() {
	    	exampleRange = new Range(Double.NaN, 1);
	    	Range range2 = new Range(1, Double.NaN);
	    	
	    	Range combined = Range.combineIgnoringNaN(exampleRange, range2);
	    	
	    	assertFalse(combined.isNaNRange());
	    }
		
		@Test
	    public void testCombineNaN2() {
	    	exampleRange = new Range(Double.NaN, 1);
	    	Range range2 = new Range(1, Double.NaN);
	    	
	    	Range combined = Range.combineIgnoringNaN(range2, exampleRange);
	    	
	    	assertFalse(combined.isNaNRange());
	    }
	    
	    @Test
	    public void testCombineIgnoringNaN() {
	    	exampleRange = new Range(1, 2);
	    	Range range2 = new Range(3, 4);
	    	
	    	Range combined = Range.combineIgnoringNaN(exampleRange, range2);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(4.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testCombineIgnoringNaNUpperNull() {
	    	exampleRange = new Range(1, 2);
	    	Range combined = Range.combineIgnoringNaN(null, exampleRange);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(2.0, combined.getUpperBound(), .000000001d);
	    	
	    }
	    
	    @Test
	    public void testCombineIgnoringNaNLowerNull() {
	    	exampleRange = new Range(1, 2);
	    	Range combined = Range.combineIgnoringNaN(exampleRange, null);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(2.0, combined.getUpperBound(), .000000001d);
	    	
	    }
	    
	    @Test
	    public void testCombineIgnoringNaNLowerNullUpperNaN() {
	    	exampleRange = new Range(Double.NaN, Double.NaN);
	    	Range combined = Range.combineIgnoringNaN(exampleRange, null);
	    	
	    	assertEquals(null, combined);
	    	
	    }
	    
	    @Test
	    public void testCombineIgnoringNaNLowerNaNUpperNull() {
	    	exampleRange = new Range(Double.NaN, Double.NaN);
	    	Range combined = Range.combineIgnoringNaN(null, exampleRange);
	    	
	    	assertEquals(null, combined);
	    	
	    }
	    
	    @Test
	    public void testCombineDoesntChangeBounds() {
	    	exampleRange = new Range(2, 3);
	    	Range range2 = new Range(4, 5);
	    	Range combined = Range.combineIgnoringNaN(range2, exampleRange);
	    	
	    	assertEquals(2, exampleRange.getLowerBound(), 0.0001d);
	    	assertEquals(3, exampleRange.getUpperBound(), 0.0001d);
	    	assertEquals(4, range2.getLowerBound(), 0.0001d);
	    	assertEquals(5, range2.getUpperBound(), 0.0001d);
	    }
	    
	    @Test
	    public void testCombineIgnoringNaNLowerNaNUpperNaN() {
	    	exampleRange = new Range(Double.NaN, Double.NaN);
	    	Range range2 = new Range(Double.NaN, Double.NaN);
	    	
	    	Range combined = Range.combineIgnoringNaN(exampleRange, range2);
	    	
	    	assertEquals(null, combined);
	    	
	    }
	    
	    @Test
	    public void testExpandToIncludeNullRange() {
	    	Range combined = Range.expandToInclude(null, 1);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(1.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testExpandToIncludeValueLessThanRange() {
	    	exampleRange = new Range(2, 3);
	    	Range combined = Range.expandToInclude(exampleRange, 1);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(3.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testExpandToIncludeValueMoreThanRange() {
	    	exampleRange = new Range(2, 3);
	    	Range combined = Range.expandToInclude(exampleRange, 4);
	    	
	    	assertEquals(2.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(4.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testExpandToIncludeValueEqualRange() {
	    	exampleRange = new Range(2, 3);
	    	Range combined = Range.expandToInclude(exampleRange, 3);
	    	
	    	assertEquals(2.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(3.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testShift() {
	    	exampleRange = new Range(1, 2);
	    	Range combined = Range.shift(exampleRange, 1);
	    	
	    	assertEquals(2.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(3.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testShiftNotNull() {
	    	boolean passed = false;
	    	try {
	    		Range combined = Range.shift(null, 1);
	    	} catch (IllegalArgumentException e) {
	    		passed = true;
	    	}
	    	
	    	assertTrue(passed);
	    }
	    
	    @Test
	    public void testShiftZeroCrossing() {
	    	exampleRange = new Range(-2, -1);
	    	Range combined = Range.shift(exampleRange, 3, true);
	    	
	    	assertEquals(1.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(2.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testShiftZeroCrossingDoesntChangeBounds() {
	    	exampleRange = new Range(-2, -1);
	    	Range combined = Range.shift(exampleRange, 3, true);
	    	combined = Range.shift(exampleRange, 3, true);
	    	
	    	assertEquals(-2, exampleRange.getLowerBound(), .000000001d);
	    	assertEquals(-1, exampleRange.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testShiftNegativeValue() {
	    	exampleRange = new Range(-2, -1);
	    	Range combined = Range.shift(exampleRange, -1, false);
	    	
	    	assertEquals(-3.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(-2.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testShiftZeroValue() {
	    	exampleRange = new Range(-2, 0);
	    	Range combined = Range.shift(exampleRange, -1, false);
	    	
	    	assertEquals(-3.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(-1.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testExpand() {
	    	exampleRange = new Range(1, 2);
	    	Range combined = Range.expand(exampleRange, 1, 1);
	    	
	    	assertEquals(0.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(3.0, combined.getUpperBound(), .000000001d);
	    	
	    }
	    
	    @Test
	    public void testExpandLowerGreaterThanUpper() {
	    	exampleRange = new Range(1, 2);
	    	
	    	Range combined = Range.expand(exampleRange, -10, 0);
	    	
	    	assertEquals(6.5, combined.getLowerBound(), .000000001d);
	    	assertEquals(6.5, combined.getUpperBound(), .000000001d);
	    	
	    }
	    
	    @Test
	    public void testScale() {
	    	exampleRange = new Range(1, 2);
	    	Range combined = Range.scale(exampleRange, 2);
	    	
	    	assertEquals(2.0, combined.getLowerBound(), .000000001d);
	    	assertEquals(4.0, combined.getUpperBound(), .000000001d);
	    }
	    
	    @Test
	    public void testScaleBadArg() {
	    	exampleRange = new Range(1, 2);
	    	
	    	boolean passed = false;
	    	
	    	try {
	    		Range combined = Range.scale(exampleRange, -1);
	    	} catch (IllegalArgumentException e) {
	    		passed = true;
	    	}
	    	
	    	assertTrue("Method didnt check for lllegal arg wasnt", passed);
	    }
	    
	    @Test
	    public void testEqualsNotRangeObj() {
	    	Double obj = 1.1;
	    	exampleRange = new Range(1, 1);
	    	exampleRange.equals(obj);
	    	assertFalse(exampleRange.equals(obj));
	    }
	    
	    @Test
	    public void testEqualsUpperDoesntMatch() {
	    	exampleRange = new Range(1, 2);
	    	Range range2 = new Range(1, 3);
	    	exampleRange.equals(range2);
	    	assertFalse(exampleRange.equals(range2));
	    }
	    
	    @Test
	    public void testEqualsLowerDoesntMatch() {
	    	exampleRange = new Range(1, 2);
	    	Range range2 = new Range(2, 3);
	    	exampleRange.equals(range2);
	    	assertFalse(exampleRange.equals(range2));
	    }
	    
	    
	    @Test
	    public void testEqualsRangeMatches() {
	    	exampleRange = new Range(1, 2);
	    	Range range2 = new Range(1, 2);
	    	exampleRange.equals(range2);
	    	assertTrue(exampleRange.equals(range2));
	    }
	    @Test
	    public void testHashCodeReturnsInt() {
	    	exampleRange = new Range(1, 1);
	    	assertTrue(Integer.class.isInstance(exampleRange.hashCode()));
	    }
	    
	    @Test
	    public void testHashReturnsCorrectHash() {
	    	exampleRange = new Range(2, 3);
	    	
	    	int result;
	        long temp;
	        temp = Double.doubleToLongBits(2);
	        result = (int) (temp ^ (temp >>> 32));
	        temp = Double.doubleToLongBits(3);
	        result = 29 * result + (int) (temp ^ (temp >>> 32));
	        
	        exampleRange.hashCode();
	        
	        assertEquals(result, exampleRange.hashCode());
	        assertEquals(2, exampleRange.getLowerBound(), 0.0001d);
	        assertEquals(3, exampleRange.getUpperBound(), 0.0001d);
	    }
	    
	    @Test
	    public void testHashDoesntChangeBounds() {
	    	exampleRange = new Range(1, 2);
	    	
	    	exampleRange.hashCode();
	    	exampleRange.hashCode();
	    	
	    	boolean passed = false;
	    	if (exampleRange.getLowerBound() == 1 && exampleRange.getUpperBound() == 2) {
	    		passed = true;
	    	}
	        
	        assertTrue(passed);
	    }
	    
	    @Test
	    public void testConstructorDoesntChangeBounds() {
	    	Range range = new Range (2, 3);
	    	assertEquals(2, range.getLowerBound(), 0.0001d);
	    	assertEquals(3, range.getUpperBound(), 0.0001d);
	    }
	    
	    @Test
	    public void testConstructorBoundsAreCorrect() {
	    	Range range = new Range (2, 3);
	    	assertEquals(2, range.getLowerBound(), 0.0001d);
	    	assertEquals(3, range.getUpperBound(), 0.0001d);
	    }
	    
	    @Test
	    public void testConstuctorErrorMessage() {
	    	
	    	
	    	String expected = "Range(double, double): require lower (3.0) <= upper (2.0).";
	    	String actual = "";
	    	
	    	try {
	    		Range range = new Range(3, 2);
	    	} catch (IllegalArgumentException e) {
	    		actual = e.getMessage();
	    	}
	    	
	    	assertEquals(expected, actual);
	    }
	    
}
