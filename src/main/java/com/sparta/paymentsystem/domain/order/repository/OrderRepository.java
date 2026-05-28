package com.sparta.paymentsystem.domain.order.repository;

import com.sparta.paymentsystem.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 내 주문 목록 조회 (최신순)
    // - LEFT JOIN FETCH orderItems: 목록 카드에 "상품명 외 N건" 등을 표시해야 하므로 N+1 방지용
    // - DISTINCT : 컬렉션 fetch join은 root(Order)가 orderItem 수만큼 중복 엔티티 제거
    // - LEFT JOIN : 아이템이 하나도 없는 주문이 있더라도 목록에서 누락되지 않도록
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.member.id = :memberId ORDER BY o.createdAt DESC")
    List<Order> findByMemberIdOrderByCreatedAtDesc(@Param("memberId") Long memberId);

    // 주문 단건 상세 조회 : orderId만으로 조회
    // - LEFT JOIN FETCH orderItems: 목록 카드에 "상품명 외 N건" 등을 표시해야 하므로 N+1 방지용
    // - DISTINCT : 컬렉션 fetch join은 root(Order)가 orderItem 수만큼 중복 엔티티 제거
    // - LEFT JOIN : 아이템이 하나도 없는 주문이 있더라도 목록에서 누락되지 않도록
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.id = :orderId")
    Optional<Order> findByIdWithOrderItems(@Param("orderId") Long orderId);

}
