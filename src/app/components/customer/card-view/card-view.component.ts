import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card-view',
  imports: [CommonModule],
  templateUrl: './card-view.component.html',
  styleUrl: './card-view.component.css'
})

export class CardViewComponent {
  @Input() id!: number;
  @Input() imageUrl!: string;
  @Input() title!: string;
  @Input() price!: number;

  constructor(private router: Router){}

  viewArticle(){
  localStorage.setItem('article', this.id.toString());
  this.router.navigate(['/article']);
 }
}

