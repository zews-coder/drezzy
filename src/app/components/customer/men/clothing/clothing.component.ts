import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from "../navbar/navbar.component";
import { CardViewComponent } from '../../card-view/card-view.component';
import { VendorArticles } from '../../../../models/Vendor';

@Component({
  selector: 'app-clothing',
  imports: [NavbarComponent, CommonModule, CardViewComponent],
  templateUrl: './clothing.component.html',
  styleUrl: './clothing.component.css'
})
export class ClothingComponent implements OnInit{
  articles: VendorArticles[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchArticles("TSHIRTSnPOLOS");
  }

  fetchArticles(subtype: string){
    const url = "http://localhost:9090/api/v1/men/clothes?subtype="+subtype;

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
