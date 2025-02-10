import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { CardViewComponent } from '../../card-view/card-view.component';
import { VendorArticles } from '../../../../models/Vendor';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-new',
  imports: [CommonModule, CardViewComponent, NavbarComponent],
  templateUrl: './new.component.html',
  styleUrl: './new.component.css'
})
export class NewComponent implements OnInit {
  articles: VendorArticles[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchArticles();
  }

  fetchArticles(){
    const url = "http://localhost:9090/api/v1/men/newest"

    this.http.get<VendorArticles[]>(url).subscribe({
      next: (data) => {
        this.articles = data;
      },
      error: (error) => {
        console.error('Error fetching articles:', error);
      },
    })
  }
}
