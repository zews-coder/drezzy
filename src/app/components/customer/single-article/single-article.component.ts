import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
import { NavbarComponent } from "../men/navbar/navbar.component";
import { SingleArticle } from "../../../models/Vendor";
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-single-article',
  imports: [NavbarComponent, CommonModule],
  templateUrl: './single-article.component.html',
  styleUrl: './single-article.component.css'
})
export class SingleArticleComponent implements OnInit{
  article: SingleArticle | null = null;

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    const id = localStorage.getItem('article');
    if (id) {
      this.fetchArticle(id);
    }else{
      alert("NE RADI SINGLE VIEW")
    }
  }

  private getAuthHeaders() {
      const token = this.authService.getJwt();
      return new HttpHeaders({
        'Authorization': token ? `Bearer ${token}` : '',
      });
  }

  fetchArticle(id: string) {
    const url = `http://localhost:9090/api/v1/men/article/${id}`;
  
    this.http.get<SingleArticle>(url).subscribe({
      next: (response) => {
        this.article = response;
        console.log("ID je: " + id)
      },
      error: (err) => {
        console.error("First request failed, trying second URL...", err);
      },
    });
  }

  addToWishList(id: number){
    if(id == 0){
      return;
    }
    const url = `http://localhost:9090/api/v1/user/wishlist/${id}`;

    this.http.post(url, {}, { headers: this.getAuthHeaders() }).subscribe(
      () => {
        alert("Uspesno dodat na listu zelja");
      },
      (error) => {
        console.error('Error adding article on wishlist', error);
      }
    );
  }

  addToBag(id: number){
    if(id == 0){
      return;
    }
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
