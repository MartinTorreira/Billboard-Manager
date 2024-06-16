package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends PagingAndSortingRepository<Order, Long> {

    Slice<Order> findByUserIdOrderByIdDesc(Long userId, Pageable pageable);
}
