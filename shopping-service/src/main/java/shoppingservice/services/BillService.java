package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.Article;
import shoppingservice.enitites.Bill;
import shoppingservice.enitites.enums.Status;
import shoppingservice.repositories.ArticleRepository;
import shoppingservice.repositories.BillRepository;
import shoppingservice.utils.dtos.ArticleForBillDto;
import shoppingservice.utils.dtos.BillDto;
import shoppingservice.utils.dtos.CreateBillDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BillService {
    private BillRepository billRepository;
    private ArticleRepository articleRepository;
    private BagService bagService;
    private AmqpTemplate amqpTemplate;

    /**GET**/
    public List<Bill> getAllBills() {
        return  billRepository.findAll();
    }

    public List<Bill> getAllBillsByCustomerId(Long customerId) {
        return billRepository.findByCustomerId(customerId);
    }

    public List<Bill> getAllPaid(){
        return new ArrayList<>(billRepository.findByStatus(Status.PAID));
    }

    public List<Bill> getAllShipped(){
        return new ArrayList<>(billRepository.findByStatus(Status.SHIPPED));
    }

    public List<Bill> getAllDelivered(){
        return billRepository.findByStatus(Status.DELIVERED);
    }

    /**POST**/
    public Bill createBill(CreateBillDto createBillDto, Long customerId) {
        Bill bill = new Bill();
        Double price = 0.0;
        bill.setStatus(Status.PAID);
        for (String id : createBillDto.getArticleIds()){
            if (articleRepository.findById(Long.valueOf(id)).isPresent()){
                Article article = articleRepository.findById(Long.valueOf(id)).get();
                bill.getArticleList().add(article);
                if (article.getDiscount() == null || article.getDiscount() == 0){
                    price += article.getPrice();
                }else {
                    price += article.getPrice() * (1 - (double) article.getDiscount() /100);
                }
            }
        }
        bill.setAddress(createBillDto.getAddress());
        bill.setCardInfo(createBillDto.getCardInfo());
        bill.setPrice(price);
        bill.setDate(new Date());
        bill.setCustomerId(customerId);
        bagService.removeAllBagArticles(customerId);

        sendBillToQueue(bill);
        return billRepository.save(bill);
    }

    public void setBillStatus(Long billId, Status status) {
        if (billRepository.findById(billId).isPresent()){
            Bill bill = billRepository.findById(billId).get();
            bill.setStatus(status);
            billRepository.save(bill);
        }
    }

    private void sendBillToQueue(Bill bill) {
        BillDto billDto = new BillDto();
        billDto.setUserId(bill.getCustomerId());
        billDto.setDate(bill.getDate().getTime());
        billDto.setPrice(bill.getPrice());
        billDto.setAddress(bill.getAddress());
        billDto.setCardInfo(bill.getCardInfo());
        List<ArticleForBillDto> articleForBillDtoList = new ArrayList<>();
        for (Article article : bill.getArticleList()){
            ArticleForBillDto articleForBillDto = new ArticleForBillDto();
            articleForBillDto.setTitle(article.getTitle());
            articleForBillDto.setPrice(article.getPrice());
            articleForBillDtoList.add(articleForBillDto);
        }
        billDto.setArticleForBillDtos(articleForBillDtoList);
        amqpTemplate.convertAndSend("billQueue", billDto);
    }
}
