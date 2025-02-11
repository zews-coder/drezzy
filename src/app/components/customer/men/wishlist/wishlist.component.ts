import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { SingleArticle } from '../../../../models/Vendor';
import { AuthService } from '../../../../services/auth.service';

@Component({
  selector: 'app-wishlist',
  imports: [NavbarComponent, CommonModule],
  templateUrl: './wishlist.component.html',
  styleUrl: './wishlist.component.css'
})
export class WishlistComponent implements OnInit{
  articles: SingleArticle[] = [];

  constructor(private http: HttpClient, private authService: AuthService) {}
  
  ngOnInit(): void {
    this.fetchWishlist();
  }

  private getAuthHeaders() {
    const token = this.authService.getJwt();
      return new HttpHeaders({
       'Authorization': token ? `Bearer ${token}` : '',
     });
  }

  fetchWishlist(){
    const url = `http://localhost:9090/api/v1/user/wishlist`;

    this.http.get<SingleArticle[]>(url, { headers: this.getAuthHeaders() }).subscribe({
      next: (response) => {
        this.articles = response;
      },
      error: (err) => {
        console.error("Error fetching wishlist", err);
      },
    })
  }

  removeFromWishlist(id: number){
    const url = `http://localhost:9090/api/v1/user/wishlist/${id}`;

    this.http.delete(url, { headers: this.getAuthHeaders() }).subscribe(
      () => {
        this.articles = this.articles.filter(article => article.id !== id);
        alert("Uspesno uklonjen sa liste zelja");
      },
      (error) => {
        console.error('Error deleting article on wishlist', error);
      }
    );
  }

  addToBag(id: number){
    const url = `http://localhost:9090/api/v1/user/bag/${id}`;

    this.http.post(url, {}, { headers: this.getAuthHeaders() }).subscribe(
      () => {
        alert("Uspesno dodat u korpu");
      },
      (error) => {
        console.error('Error adding article on wishlist', error);
      }
    );
  }
}
