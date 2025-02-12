import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { SingleArticle } from '../../../../models/Vendor';
import { AuthService } from '../../../../services/auth.service';
import { Router } from '@angular/router';
import { SignUpDTO } from '../../../../models/User';
import { BillRequest } from '../../../../models/User';

@Component({
  selector: 'app-bag',
  imports: [NavbarComponent, CommonModule, FormsModule],
  templateUrl: './bag.component.html',
  styleUrl: './bag.component.css'
})
export class BagComponent implements OnInit{
  articles: SingleArticle[] = [];
  @ViewChild('paymentSection') paymentSection!: ElementRef;
  articleIds: string[] = [];
  user: SignUpDTO = {
      username: '',
      email: '',
      password: '',
      firstName: '',
      lastName: '',
      address: {
        street: '',
        city: '',
        state: '',
        zip: '',
        phone: ''
      },
      cardInfo: {
        cardHolder: '',
        cardNumber: '',
        expiryDate: '',
        cvv: ''
      }
    };

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) {}
  
  ngOnInit(): void {
    if(this.authService.getJwt() == ''){
      this.router.navigate(['/customer-login']);
    }else{
      this.fetchBag();
      this.fetchUserData();
    }
  }

  private getAuthHeaders() {
    const token = this.authService.getJwt();
      return new HttpHeaders({
       'Authorization': token ? `Bearer ${token}` : '',
     });
  }

  fetchBag(){
    const url = `http://localhost:9090/api/v1/user/bag`;

    this.http.get<SingleArticle[]>(url, { headers: this.getAuthHeaders() }).subscribe({
      next: (response) => {
        this.articles = response;
      },
      error: (err) => {
        console.error("Error fetching bag", err);
      },
    })
  }

  removeFromBag(id: number){
    const url = `http://localhost:9090/api/v1/user/bag/${id}`;

    this.http.delete(url, { headers: this.getAuthHeaders() }).subscribe(
      () => {
        this.articles = this.articles.filter(article => article.id !== id);
        alert("Uspesno uklonjen iz korpe");
      },
      (error) => {
        console.error('Error deleting article from bag', error);
      }
    );
  }

  getTotalPrice(): number {
    return this.articles.reduce((total, article) => {
      const discountedPrice = (article?.price ?? 0) * (1 - (article?.discount ?? 0) / 100);
      return total + discountedPrice;
    }, 0);
  }

  proceedToPayment(): void {
    if (this.paymentSection) {
      this.paymentSection.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }

  makeBill(): void {
    // Extract article IDs
    const articleIds = this.articles.map(article => article.id.toString());

    // Create request payload
    const billRequest: BillRequest = {
      articleIds: articleIds,
      address: this.user.address,
      cardInfo: this.user.cardInfo
    };

    // Send POST request
    this.http.post('http://localhost:9090/api/v1/bills/add',  billRequest, { headers: this.getAuthHeaders() })
      .subscribe({
        next: (response) => {
          console.log('Bill created successfully', response);
          alert('Order placed successfully!');
          this.router.navigate(['/']);
        },
        error: (error) => {
          console.error('Error creating bill:', error);
          alert('Failed to process order. Please try again.');
        }
      });
  }

  fetchUserData(): void {
    this.http.get<SignUpDTO>('http://localhost:8080/api/v1/customer/getOne', { headers: this.getAuthHeaders() }).subscribe({
      next: (data) => {
        this.user = data;
      },
      error: (err) => {
        console.error('Error fetching user data:', err);
      }
    });
  }

  isFormValid(): boolean {
    const { address, cardInfo } = this.user;
    
    // Check if all required fields are filled
    const allFieldsFilled =
      (address.street && address.city && address.state && address.zip && address.phone &&
      cardInfo.cardHolder && cardInfo.cardNumber && cardInfo.expiryDate && cardInfo.cvv) ? true : false;
    
    // Check card number (16 digits)
    const isCardNumberValid = /^\d{16}$/.test(cardInfo.cardNumber);
    
    // Check CVV (3 digits)
    const isCvvValid = /^\d{3}$/.test(cardInfo.cvv);
    
    // Check expiry date (5 characters in MM/YY format)
    const isExpiryDateValid = /^\d{2}\/\d{2}$/.test(cardInfo.expiryDate);
    
    return allFieldsFilled && isCardNumberValid && isCvvValid && isExpiryDateValid;
  }
}
