package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fd.entities.CollectedOrder;

@Repository
public interface CollectedOrderRepository extends JpaRepository<CollectedOrder, Long> {

	Optional<CollectedOrder> findByOrderId(long orderId);

	Optional<List<CollectedOrder>> findByDeliveryBoyId(long deliveryBoyId);

	@Query(value = "SELECT * FROM COLLECTED_ORDER WHERE DELIVERY_BOY_ID = :deliveryBoyId AND IS_DELIVERED = 'false'", nativeQuery = true)
	Optional<CollectedOrder> findByDeliveryBoyIdAndNotDelivered(long deliveryBoyId);
	
	@Query(value = "SELECT * FROM COLLECTED_ORDER WHERE DELIVERY_BOY_ID = :deliveryBoyId AND IS_DELIVERED = 'true'", nativeQuery = true)
	Optional<List<CollectedOrder>> findByDeliveryBoyIdAndDelivered(long deliveryBoyId);
	
	Optional<List<CollectedOrder>> findAllByisDelivered(String status);
}
