import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { VendorArticles } from '../../../models/Vendor'
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";
import { DateFormatPipe } from '../../../pipes/date-format.pipe'
import { FormsModule } from '@angular/forms'; // <-- Import FormsModule

@Component({
  selector: 'app-vendor-articles',
  templateUrl: './vendor-articles.component.html',
  styleUrls: ['./vendor-articles.component.css'],
  imports: [CommonModule, VendorHomeComponent, HttpClientModule, DateFormatPipe, FormsModule],
})
export class VendorArticlesComponent implements OnInit{
  articles: VendorArticles[] = [];
  discountInputs: { [key: string]: number } = {}; // Use string keys

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
   this.fetchVendorArticles();
  }

  private getAuthHeaders() {
      const token = sessionStorage.getItem('token');
      return new HttpHeaders({
        'Authorization': token ? `Bearer ${token}` : '',
      });
    }

  fetchVendorArticles() {
    const url = 'http://localhost:9090/api/v1/articles/getAllArticles';

    this.http.get<VendorArticles[]>(url).subscribe({
      
      next: (data) => {
        this.articles = data;
      },
      error: (error) => {
        console.error('Error fetching articles:', error);
      },
    });
  }

  changeVisibility(article: any) {
    const url = `http://localhost:9090/api/v1/articles/visibility/${article.id}`;
  
    this.http.delete(url, { headers: this.getAuthHeaders() }).subscribe(
      () => {
        // Toggle visibility on success
        article.visible = !article.visible;
      },
      (error) => {
        console.error('Error changing visibility', error);
      }
    );
  }

  updateDiscount(article: any, discount: number) {
    if (discount == null || discount < 0 || discount > 100) {
      console.error('Invalid discount value');
      return;
    }
  
    const url = `http://localhost:9090/api/v1/articles/discount/${article.id}?discount=${discount}`;
    const headers = this.getAuthHeaders();
  
    this.http.put(url, {}, { headers }).subscribe(
      (response) => {
        console.log('Discount updated successfully', response);
  
        // ✅ Update article.discount with new value
        article.discount = discount;
  
        // ✅ Clear the discount input after success
        delete this.discountInputs[article.id.toString()]; // Convert to string
      },
      (error) => {
        console.error('Error updating discount', error);
      }
    );
  }
  
  
}
