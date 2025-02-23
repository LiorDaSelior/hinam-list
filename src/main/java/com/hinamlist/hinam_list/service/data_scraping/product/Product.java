package com.hinamlist.hinam_list.service.data_scraping.product;

public record Product(int retailId, String barcode, int categoryId, String name, boolean isInKg) {
}
