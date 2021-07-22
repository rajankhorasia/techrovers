package techrovers.service;

import java.util.List;

import techrovers.entity.Invoice;
import techrovers.entity.InvoiceDeatils;

public interface InvoiceService {
	
	List<Invoice> getAllInvoiceDetails();
	
	List<Invoice> getInvoiceDetailsByUser(Long userId) throws Exception;
	
	List<Invoice> getInvoiceDetailsByItem(Long itemId) throws Exception;
	
	void saveInvoiceInformation(List<InvoiceDeatils> invoiceDetails, Long userId) throws Exception;
	
}
