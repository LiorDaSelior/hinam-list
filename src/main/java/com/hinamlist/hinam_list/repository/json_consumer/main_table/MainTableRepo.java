package com.hinamlist.hinam_list.repository.json_consumer.main_table;

import com.hinamlist.hinam_list.model.json_consumer.main_table.MainTableProduct;
import com.hinamlist.hinam_list.model.json_consumer.main_table.StoreNumberBarcodeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MainTableRepo extends JpaRepository<MainTableProduct, Integer> {
}
