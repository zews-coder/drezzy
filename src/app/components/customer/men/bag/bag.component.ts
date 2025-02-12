import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { SingleArticle } from '../../../../models/Vendor';
import { AuthService } from '../../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bag',
  imports: [NavbarComponent, CommonModule],
  templateUrl: './bag.component.html',
  styleUrl: './bag.component.css'
})
export class BagComponent implements OnInit{
  articles: SingleArticle[] = [];

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) {}
  
  ngOnInit(): void {
    if(this.authService.getJwt() == ''){
      this.router.navigate(['/customer-login']);
    }else{
      this.fetchBag();
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
}
