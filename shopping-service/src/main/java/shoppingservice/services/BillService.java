package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.Article;
import shoppingservice.enitites.Bill;
import shoppingservice.enitites.enums.Status;
import shoppingservice.repositories.ArticleRepository;
import shoppingservice.repositories.BillRepository;
import shoppingservice.utils.dtos.CreateBillDto;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BillService {
    private BillRepository billRepository;
    private ArticleRepository articleRepository;

    public List<Bill> getAllBills() {
        return  billRepository.findAll();
    }

    public List<Bill> getAllBillsByCustomerId(Long customerId) {
        return billRepository.findByCustomerId(customerId);
    }

    public List<Bill> getAllPaid(){
        return billRepository.findByStatus(Status.PAID);
    }

    public List<Bill> getAllFinished(){
        return billRepository.findByStatus(Status.DELIVERED);
    }

    public Bill createBill(CreateBillDto createBillDto, Long customerId) {
        Bill bill = new Bill();
        Double price = 0.0;
        bill.setStatus(Status.PAID);
        for (Long id : createBillDto.getArticleIds()){
            System.out.println("USAO: " + id);
            if (articleRepository.findById(id).isPresent()){
                Article article = articleRepository.findById(id).get();
                bill.getArticleList().add(article);
                price += article.getPrice();
                System.out.println("PRONADJEN");
            }
        }
        bill.setPrice(price);
        bill.setDate(new Date());
        bill.setCustomerId(customerId);

        return billRepository.save(bill);
    }

    public void setBillStatus(Long billId, Status status) {
        billRepository.findById(billId).get().setStatus(status);
    }
}
