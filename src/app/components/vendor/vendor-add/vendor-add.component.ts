import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { VendorHomeComponent } from '../vendor-home/vendor-home.component';

@Component({
  selector: 'app-vendor-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, VendorHomeComponent], // Add CommonModule here
  templateUrl: './vendor-add.component.html',
  styleUrls: ['./vendor-add.component.css'],
})
export class VendorAddComponent {
  vendorFormShoes: FormGroup;
  vendorFormClothes: FormGroup;

  size_clothes = ["S", "M", "L", "XL"]
  subtype_shoes = ["BOOTS","SNEAKERS","SPORT","BUSINESS","LOAFERS"];
  subtype_clothes = ["TSHIRTSnPOLOS", "SHIRTS", "SWEATSHIRTSnHOODIES","TROUSERS","JEANS","JACKETSnCOATS","TRACKSUITS","UNDERWEARnSOCKS"];
  sexes = ['MEN', 'WOMEN', 'KIDS'];

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.vendorFormShoes = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]], // Price with up to 2 decimals
      size: ['', Validators.required],
      subtype_shoes: ['', Validators.required],
      sex: ['', Validators.required],
    });

    this.vendorFormClothes = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]], // Price with up to 2 decimals
      size: ['', Validators.required],
      subtype_clothes: ['', Validators.required],
      sex: ['', Validators.required],
    });
  }

  private getAuthHeaders() {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': token ? `Bearer ${token}` : '',
    });
  }

  onSubmitShoes() {
    if (this.vendorFormShoes.valid) {
      const headers = this.getAuthHeaders();
      this.http.post('http://localhost:9090/api/v1/shoes/add', this.vendorFormShoes.value, { headers }).subscribe(
        (response) => {
          console.log('Shoes data submitted successfully', response);
        },
        (error) => {
          this.vendorFormShoes.reset();
          console.error('Error submitting shoes data', error);
        }
      );
    } else {
      console.error('Shoes form is invalid');
    }
  }

  onSubmitClothes() {
    if (this.vendorFormClothes.valid) {
      const headers = this.getAuthHeaders();
      this.http.post('http://localhost:9090/api/v1/clothes/add', this.vendorFormClothes.value, { headers }).subscribe(
        (response) => {
          console.log('Clothes data submitted successfully', response);
          this.vendorFormClothes.reset();
        },
        (error) => {
          console.error('Error submitting clothes data', error);
        }
      );
    } else {
      console.error('Clothes form is invalid');
    }
  }
}

