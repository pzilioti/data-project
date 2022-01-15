package dev.zilioti.daofab.repository;

import dev.zilioti.daofab.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    @Query(value = "select sum(c.paid_amount) from Child c where c.parent_id = ?1", nativeQuery = true)
    public Integer getSumPaidAmountByParentId(Integer id);

    @Query(value= "select c.id, p.sender, p.receiver, p.total_amount, c.paid_amount from child c, parent p where c.parent_id = p.id order by c.id", nativeQuery = true)
    public List<Map<String, Object>> getChildrenByParent();
}
