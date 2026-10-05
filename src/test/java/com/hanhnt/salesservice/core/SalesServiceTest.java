/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hanhnt.salesservice.core;

/**
 *
 * @author tango
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SalesServiceTest {

    private SalesService service;

    @BeforeEach
    public void setUp() {
        service = new SalesService();
    }

    @Test
    public void testCalculateSubtotal_NormalCase() {
        Product p = new Product("P01", "Keyboard", 500, 3);
        assertEquals(1500, service.calculateSubtotal(p), 0.001);
    }

    @Test
    public void testCalculateSubtotal_QuantityOne() {
        Product p = new Product("P02", "Mouse", 200, 1);
        assertEquals(200, service.calculateSubtotal(p), 0.001);
    }

    @Test
    public void testCalculateSubtotal_DecimalPrice() {
        Product p = new Product("P03", "Cable", 99.99, 2);
        assertEquals(199.98, service.calculateSubtotal(p), 0.001);
    }

    @Test
    public void testCalculateDiscount_Boundary_999_99_ZeroPercent() {
        assertEquals(0, service.calculateDiscount(999.99), 0.001);
    }

    @Test
    public void testCalculateDiscount_Boundary_1000_FivePercent() {
        assertEquals(50, service.calculateDiscount(1000), 0.001);
    }

    @Test
    public void testCalculateDiscount_Boundary_4999_99_FivePercent() {
        assertEquals(249.9995, service.calculateDiscount(4999.99), 0.001);
    }

    @Test
    public void testCalculateDiscount_Boundary_5000_TenPercent() {
        assertEquals(500, service.calculateDiscount(5000), 0.001);
    }

    @Test
    public void testCalculateDiscount_Boundary_9999_99_TenPercent() {
        assertEquals(999.999, service.calculateDiscount(9999.99), 0.001);
    }

    @Test
    public void testCalculateDiscount_Boundary_10000_FifteenPercent() {
        assertEquals(1500, service.calculateDiscount(10000), 0.001);
    }

    @Test
    public void testCalculateShippingFee_BelowThreshold() {
        assertEquals(50, service.calculateShippingFee(1999), 0.001);
    }

    @Test
    public void testCalculateShippingFee_AtThreshold_FreeShipping() {
        assertEquals(0, service.calculateShippingFee(2000), 0.001);
    }

    @Test
    public void testCalculateShippingFee_AboveThreshold() {
        assertEquals(0, service.calculateShippingFee(5000), 0.001);
    }
    
    @Test
    public void testCalculateTotal_SilverTier() {
        Product p = new Product("P04", "Headset", 500, 3);
        assertEquals(1475, service.calculateTotal(p), 0.001);
    }

    @Test
    public void testCalculateTotal_VipTier() {
        Product p = new Product("P05", "Laptop", 1000, 10);
        assertEquals(8500, service.calculateTotal(p), 0.001);
    }
    
    @Test
    public void testClassifyCustomer_Regular() {
        assertEquals("REGULAR", service.classifyCustomer(500));
    }

    @Test
    public void testClassifyCustomer_Silver() {
        assertEquals("SILVER", service.classifyCustomer(3000));
    }

    @Test
    public void testClassifyCustomer_Gold() {
        assertEquals("GOLD", service.classifyCustomer(7000));
    }

    @Test
    public void testClassifyCustomer_Vip_AtBoundary() {
        assertEquals("VIP", service.classifyCustomer(10000));
    }

    @Test
    public void testCalculateSubtotal_NullProduct_ThrowsException() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.calculateSubtotal(null)
        );
        assertEquals("Product cannot be null", exception.getMessage());
    }

    @Test
    public void testCalculateDiscount_NegativeSubtotal_ThrowsException() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.calculateDiscount(-100)
        );
        assertEquals("Subtotal cannot be negative", exception.getMessage());
    }

    @Test
    public void testCalculateShippingFee_NegativeSubtotal_ThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.calculateShippingFee(-1)
        );
    }
    
    @ParameterizedTest
    @CsvSource({
        "999.99, 0",
        "1000, 50",
        "4999.99, 249.9995",
        "5000, 500",
        "9999.99, 999.999",
        "10000, 1500"
    })
    public void testCalculateDiscount_Parameterized(double subtotal, double expected) {
        assertEquals(expected, service.calculateDiscount(subtotal), 0.001);
    }
}
