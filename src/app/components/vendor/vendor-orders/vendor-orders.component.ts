import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";
import { Bill } from '../../../models/Vendor'
import { DateFormatPipe } from '../../../pipes/date-format.pipe'

@Component({
  selector: 'app-vendor-orders',
  imports: [VendorHomeComponent, CommonModule, DateFormatPipe],
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
      const token = localStorage.getItem('token');
      return new HttpHeaders({
        'Authorization': token ? `Bearer ${token}` : '',
      });
    }
    return new HttpHeaders();
  }

  fetchBills(): void {
    const headers = this.getAuthHeaders();
    this.http.get<Bill[]>('http://localhost:9090/api/v1/bills/getAllPaid', { headers }).subscribe(
      (data) => {
        this.bills = data;
      },
      (error) => {
        console.error('Error fetching bills: paid')
      }
    );
  }

  setStatus(bill: any, newStatus: string): void {
    const url = 'http://localhost:9090/api/v1/bills/changeStatus';
    const dto = {
      billId: bill.id,
      status: newStatus
    };

    this.http.put(url, dto).subscribe({
      
      next: (response) => {
        this.bills = this.bills.filter(b => b.id !== bill.id);
      },
      error: (error) => {
        console.error('Error updating status:', error);
      }
    });
  }

  selectBill(bill: Bill): void {
    this.selectedBill = bill; 
  }
}
