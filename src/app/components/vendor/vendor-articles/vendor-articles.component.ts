import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { VendorArticles } from '../../../models/Vendor'
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";

@Component({
  selector: 'app-vendor-articles',
  templateUrl: './vendor-articles.component.html',
  styleUrls: ['./vendor-articles.component.css'],
  imports: [CommonModule, VendorHomeComponent, HttpClientModule],
})
export class VendorArticlesComponent implements OnInit{
  articles: VendorArticles[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
   this.fetchVendorArticles();
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
}
