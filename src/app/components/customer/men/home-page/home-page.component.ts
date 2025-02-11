import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  imports: [ NavbarComponent ],
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],

})
export class HomePageComponent {
  constructor(private router: Router) {}

  shopNow() {
    this.router.navigate(['/men/new']);
  }

  viewCategory(category: string) {
    this.router.navigate(['/shop'], { queryParams: { category } });
  }
}

