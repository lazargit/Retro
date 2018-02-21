package com.shamildev.retro.domain.params;

import java.math.BigDecimal;

/**
 * Created by Shamil Lazar on 09.02.2018.
 */

public class Product {
    // all fields are final: immutable object
    private final String id;
    private final BigDecimal price;
    private final String salesDescription;
    private final Product baseProduct;
    private final boolean approved;

    private Product (Builder builder) {
        // private Constructor can only be called from Builder
        this.id = builder.id;
        this.price = builder.price;
        this.salesDescription = builder.salesDescription;
        this.baseProduct = builder.baseProduct;
        this.approved = builder.approved;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSalesDescription() {
        return salesDescription;
    }

    public Product getBaseProduct() {
        return baseProduct;
    }

    public boolean isApproved() {
        return approved;
    }

    public static class Builder {
        // mandatory parameter
        private final String id;
        private final BigDecimal price;

        // optional
        private String salesDescription = "";
        private Product baseProduct     = null;
        private boolean approved  = true;

        public Builder(String id, BigDecimal price) {
            this.id = id;
            this.price = price;
        }
        public Builder salesDescription(String salesDescription) {
            this.salesDescription = salesDescription;
            return this;
        }
        public Builder baseProduct(Product baseProduct) {
            this.baseProduct = baseProduct;
            return this;
        }
        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }
        public Product build() {
            return new Product(this);
        }
    }
}