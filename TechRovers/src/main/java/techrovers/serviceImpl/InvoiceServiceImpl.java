package techrovers.serviceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import techrovers.entity.Invoice;
import techrovers.entity.InvoiceDeatils;
import techrovers.entity.Item;
import techrovers.entity.UserMst;
import techrovers.model.CustomException;
import techrovers.repository.InvoiceRepository;
import techrovers.service.InvoiceService;
import techrovers.service.ItemService;
import techrovers.service.UserService;
import techrovers.util.Constant;
import techrovers.util.Utility;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private ItemService itemService;
	
	@Override
	public List<Invoice> getAllInvoiceDetails() {
		// TODO Auto-generated method stub
		return invoiceRepository.findAll();
	}

	@Override
	public List<Invoice> getInvoiceDetailsByUser(Long userId) throws Exception {
		// TODO Auto-generated method stub
		UserMst user = userService.getUserById(userId);
		if(Objects.isNull(user)) {
			throw new CustomException(Constant.CUSTOM_ERROR_CODE, "No user found");
		}
		if(user.getRole().getType() == Constant.ADMIN_ROLE) {
			return getAllInvoiceDetails();
		}else {
			return invoiceRepository.findByUserId(userId);
		}
	}

	@Override
	public void saveInvoiceInformation(List<InvoiceDeatils> invoiceDetails, Long userId) throws Exception {
		List<Item> itemList = null;
		if(!invoiceDetails.isEmpty()) {
			Invoice invoice = new Invoice();
			invoice.setInvoiceNumber("invoice_"+Utility.generateRandomLong());
			invoice.setDate(new Date());
			invoice.setUserId(userId);
			invoiceRepository.save(invoice);
			
			itemList = itemService.getItems(invoiceDetails.stream().map(element -> element.getItemId()).distinct().collect(Collectors.toList()));
			
			invoiceDetails.forEach(element -> {
				element.setInvoiceId(invoice.getId());
			});
			invoice.setInvoiceDetails(invoiceDetails);
			invoice.setGstAmount(calculateGST(invoiceDetails, itemList));
		}
		
	}
	
	private Double calculateGST(List<InvoiceDeatils> invoiceDetails, List<Item> itemDetails) {
		Double gstAmount = 0.00;
		Item item = null;
		for (InvoiceDeatils invoiceDeatils : invoiceDetails) {
			item = itemDetails.stream().filter(x -> Objects.equals(x.getId(), invoiceDeatils.getItemId())).findFirst().orElseGet(null);
			if(Objects.nonNull(item)) {
				double gstValue = item.getTotalPrice() * (Double.valueOf(item.getGst()) / 100);
				System.out.println(Double.valueOf(item.getGst()) / 100);
				double totalAmount = item.getTotalPrice() * invoiceDeatils.getQuantity();
				gstAmount += (totalAmount + (gstValue * invoiceDeatils.getQuantity()));
			}
		}
		
		return gstAmount;
	}

	@Override
	public List<Invoice> getInvoiceDetailsByItem(Long itemId) throws Exception {
		// TODO Auto-generated method stub
		return invoiceRepository.getInvoiceFromItem(itemId);
	}
	
	

}
