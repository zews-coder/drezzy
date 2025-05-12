import { Component, OnInit } from '@angular/core';
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";
import { HttpClient } from '@angular/common/http';
import { NgxChartsModule } from '@swimlane/ngx-charts'

@Component({
  selector: 'app-stats',
  imports: [VendorHomeComponent, NgxChartsModule],
  templateUrl: './stats.component.html',
  styleUrl: './stats.component.css'
})
export class StatsComponent implements OnInit{
  earnings: number = 0;
  
  itemsSoldData: any[] = [];
  ordersData: any[] = [];

  view: [number, number] = [500, 300]; // Chart size

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchEarnings();
    this.fetchItemsSold();
    this.fetchOrdersByStatus();
  }

  fetchEarnings() {
    this.http.get<number>('http://localhost:9090/api/v1/stats/earnings').subscribe(
      (data) => (this.earnings = data),
      (error) => console.error('Error fetching earnings', error)
    );
  }

  fetchItemsSold() {
    this.http.get<any>('http://localhost:9090/api/v1/stats/itemsSold').subscribe(
      (data) => {
        this.itemsSoldData = [
          { name: 'Clothes', value: data.clothesSold },
          { name: 'Shoes', value: data.shoesSold }
        ];
      },
      (error) => console.error('Error fetching items sold', error)
    );
  }

  fetchOrdersByStatus() {
    this.http.get<any>('http://localhost:9090/api/v1/stats/billsByStats').subscribe(
      (data) => {
        this.ordersData = [
          { name: 'PAID', value: data.ordersPaid },
          { name: 'SHIPPED', value: data.ordersShipped },
          { name: 'DELIVERED', value: data.ordersDelivered }
        ];
      },
      (error) => console.error('Error fetching orders by status', error)
    );
  }
}
