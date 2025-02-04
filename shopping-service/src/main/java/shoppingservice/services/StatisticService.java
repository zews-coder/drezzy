package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.Article;
import shoppingservice.enitites.ArticleClothes;
import shoppingservice.enitites.ArticleShoes;
import shoppingservice.enitites.Bill;
import shoppingservice.enitites.enums.Status;
import shoppingservice.repositories.BillRepository;
import shoppingservice.utils.dtos.ClothesSoldDto;
import shoppingservice.utils.dtos.ItemsSoldDto;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticService {
    private BillRepository billRepository;

    public ClothesSoldDto billsByStatus(){
        int ordersPaid = billRepository.countByStatus(Status.PAID);
        int ordersShipped = billRepository.countByStatus(Status.SHIPPED);
        int ordersDelivered = billRepository.countByStatus(Status.DELIVERED);

        return new ClothesSoldDto(ordersPaid, ordersShipped, ordersDelivered);
    }

    public double earnings(){
        List<Bill> allBills = billRepository.findAll();
        double profit = 0;
        for (Bill bill : allBills) {
            profit += bill.getPrice();
        }
        return profit;
    }

    public ItemsSoldDto itemsSold(){
        List<Bill> allBills = billRepository.findAll();

        int clothesSold = 0;
        int shoesSold = 0;
        int articlesSold = 0;

        for (Bill bill : allBills) {
            articlesSold += bill.getArticleList().size();
            for (Article article : bill.getArticleList()){
                if (article instanceof ArticleClothes){
                    clothesSold++;
                    continue;
                }
                if (article instanceof ArticleShoes){
                    shoesSold++;
                }
            }
        }

        return new ItemsSoldDto(clothesSold, shoesSold, articlesSold);
    }
}
