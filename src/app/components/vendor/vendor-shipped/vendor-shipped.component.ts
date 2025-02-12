import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";
import { Bill } from '../../../models/Vendor'
import { DateFormatPipe } from '../../../pipes/date-format.pipe'

@Component({
  selector: 'app-vendor-shipped',
  imports: [VendorHomeComponent, CommonModule, DateFormatPipe],
  templateUrl: './vendor-shipped.component.html',
  styleUrl: './vendor-shipped.component.css'
})
export class VendorShippedComponent implements OnInit{
  billsShipped: Bill [] = [];
  selectedBill: Bill | null = null;

  constructor(private http: HttpClient) {}

  private getAuthHeaders() {
    if (typeof window !== 'undefined') {
      const token = localStorage.getItem('token');
      return new HttpHeaders({
        'Authorization': token ? `Bearer ${token}` : '',
      });
    }
    return new HttpHeaders();
  }

  ngOnInit(): void {
    this.fetchBillsShipped();
  }

  fetchBillsShipped(): void {
    const headers = this.getAuthHeaders();
    this.http.get<Bill[]>('http://localhost:9090/api/v1/bills/getAllShipped', { headers }).subscribe(
      (data) => {
        this.billsShipped = data;
      },
      (error) => {
        console.error('Error fetching bills: shipped');
      }
    );
  }

  selectBill(bill: Bill): void {
    this.selectedBill = bill;  // Set the clicked bill as selectedBill
  }

  setStatus(bill: any, newStatus: string): void {
    const url = 'http://localhost:9090/api/v1/bills/changeStatus';
    const dto = {
      billId: bill.id,
      status: newStatus
    };

    this.http.put(url, dto).subscribe({
      
      next: (response) => {
        this.billsShipped = this.billsShipped.filter(b => b.id !== bill.id);
      },
      error: (error) => {
        console.error('Error updating status: vendor=shipped');
      }
    });
  }
}
