import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";
import { Bill, VendorArticles } from '../../../models/Vendor'

@Component({
  selector: 'app-vendor-orders',
  imports: [VendorHomeComponent, CommonModule],
  templateUrl: './vendor-orders.component.html',
  styleUrl: './vendor-orders.component.css'
})
export class VendorOrdersComponent implements OnInit{
  bills: Bill[] = [];
  selectedBill: Bill | null = null;
  
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchBills();
  }

  private getAuthHeaders() {
    if (typeof window !== 'undefined') {
      const token = sessionStorage.getItem('token');
      return new HttpHeaders({
        'Authorization': token ? `Bearer ${token}` : '',
      });
    }
    return new HttpHeaders();
  }

  // Fetching bills from the provided URL
  fetchBills(): void {
    const headers = this.getAuthHeaders();
    this.http.get<Bill[]>('http://localhost:9090/api/v1/bills/getAllPaid', { headers }).subscribe(
      (data) => {
        this.bills = data;
      },
      (error) => {
        console.error('Error fetching bills:', error);
      }
    );
  }

  // Function to set the bill status
  setStatus(bill: Bill, status: string): void {
    console.log(`Setting status of Bill ID ${bill.id} to ${status}`);
    bill.status = status;
  }

  // Set selectedBill to the clicked bill
  selectBill(bill: Bill): void {
    this.selectedBill = bill;  // Set the clicked bill as selectedBill
  }
}
