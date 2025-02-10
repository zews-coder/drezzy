import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { NavbarComponent } from "../men/navbar/navbar.component";
import { SingleArticle } from "../../../models/Vendor";

@Component({
  selector: 'app-single-article',
  imports: [NavbarComponent],
  templateUrl: './single-article.component.html',
  styleUrl: './single-article.component.css'
})
export class SingleArticleComponent implements OnInit{
  article: SingleArticle | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    const id = localStorage.getItem('article');
    if (id) {
      this.fetchArticle(id);
    }else{
      alert("NE RADI SINGLE VIEW")
    }
  }

  fetchArticle(id: string) {
    const url = `http://localhost:9090/api/v1/men/article/${id}`;
  
    this.http.get<SingleArticle>(url).subscribe({
      next: (response) => {
        this.article = response; // Store the article if the first request succeeds
        console.log("ID je: " + id)
      },
      error: (err) => {
        console.error("First request failed, trying second URL...", err);
      },
    });
  }
  
}
