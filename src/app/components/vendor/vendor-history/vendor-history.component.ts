import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";
import { DateFormatPipe } from '../../../pipes/date-format.pipe'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Bill } from '../../../models/Vendor';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-vendor-history',
  imports: [VendorHomeComponent, CommonModule, DateFormatPipe],
  templateUrl: './vendor-history.component.html',
  styleUrl: './vendor-history.component.css'
})
export class VendorHistoryComponent implements OnInit{
  bills: Bill[] = [];
  selectedBill: Bill | null = null;

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    this.fetchBills();
  }

  private getAuthHeaders() {
    const token = this.authService.getJwt();
      return new HttpHeaders({
       'Authorization': token ? `Bearer ${token}` : '',
     });
  }

  fetchBills(): void {
    const headers = this.getAuthHeaders();
    this.http.get<Bill[]>('http://localhost:9090/api/v1/bills/getAllDelivered', { headers }).subscribe(
      (data) => {
        this.bills = data;
      },
      (error) => {
        console.error('Error fetching bills: history');
      }
    );
  }  

  selectBill(bill: Bill): void {
    this.selectedBill = bill;
  }
}
