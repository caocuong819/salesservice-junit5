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
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testCreateProduct_ValidInput() {
        Product p = new Product("P01", "Keyboard", 500, 3);
        assertEquals("P01", p.getProductId());
        assertEquals("Keyboard", p.getProductName());
        assertEquals(500, p.getPrice(), 0.001);
        assertEquals(3, p.getQuantity());
    }

    @Test
    public void testCreateProduct_NullProductId_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(null, "Keyboard", 500, 3));
    }

    @Test
    public void testCreateProduct_EmptyProductId_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("", "Keyboard", 500, 3));
    }

    @Test
    public void testCreateProduct_NullProductName_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P01", null, 500, 3));
    }

    @Test
    public void testCreateProduct_EmptyProductName_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P01", "", 500, 3));
    }

    @Test
    public void testCreateProduct_ZeroPrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P01", "Keyboard", 0, 3));
    }

    @Test
    public void testCreateProduct_NegativePrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P01", "Keyboard", -100, 3));
    }

    @Test
    public void testCreateProduct_ZeroQuantity_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P01", "Keyboard", 500, 0));
    }

    @Test
    public void testCreateProduct_NegativeQuantity_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("P01", "Keyboard", 500, -1));
    }
}