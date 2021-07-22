package techrovers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import techrovers.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

	List<Invoice> findByUserId(Long userId);
	
	@Query("SELECT inv from Invoice inv where inv.id  in (SELECT indtl.invoiceId from InvoiceDeatils indtl where indtl.itemId= :itemId)")
	List<Invoice> getInvoiceFromItem(@Param("itemId") Long itemId);
}
