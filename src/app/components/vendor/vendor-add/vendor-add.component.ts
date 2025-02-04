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

  selectedFileShoes: File | null = null;
  selectedFileClothes: File | null = null;

  size_clothes = ["S", "M", "L", "XL"];
  subtype_shoes = ["BOOTS", "SNEAKERS", "SPORT", "BUSINESS", "LOAFERS"];
  subtype_clothes = ["TSHIRTSnPOLOS", "SHIRTS", "SWEATSHIRTSnHOODIES", "TROUSERS", "JEANS", "JACKETSnCOATS", "TRACKSUITS", "UNDERWEARnSOCKS"];
  sexes = ['MEN', 'WOMEN', 'KIDS'];

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.vendorFormShoes = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
      size: ['', Validators.required],
      subtype_shoes: ['', Validators.required],
      sex: ['', Validators.required]
    });

    this.vendorFormClothes = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
      size: ['', Validators.required],
      subtype_clothes: ['', Validators.required],
      sex: ['', Validators.required]
    });
  }

  private getAuthHeaders() {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': token ? `Bearer ${token}` : '',
    });
  }

  onFileChangeShoes(event: any) {
    if (event.target.files.length > 0) {
      this.selectedFileShoes = event.target.files[0];
    }
  }

  onFileChangeClothes(event: any) {
    if (event.target.files.length > 0) {
      this.selectedFileClothes = event.target.files[0];
    }
  }

  onSubmitShoes() {
    const headers = this.getAuthHeaders();
  
    // Prepare the DTO as JSON
    const shoesDto = {
      title: this.vendorFormShoes.get('title')?.value || '',
      description: this.vendorFormShoes.get('description')?.value || '',
      price: this.vendorFormShoes.get('price')?.value || '',
      size: this.vendorFormShoes.get('size')?.value || '',
      subtype_shoes: this.vendorFormShoes.get('subtype_shoes')?.value || '',
      sex: this.vendorFormShoes.get('sex')?.value || ''
    };
  
    const formData = new FormData();
    formData.append('createShoesDto', new Blob([JSON.stringify(shoesDto)], { type: 'application/json' })); // Convert DTO to Blob
    if (this.selectedFileShoes) {
      formData.append('image', this.selectedFileShoes);
    }
  
    this.http.post('http://localhost:9090/api/v1/shoes/add', formData, { headers }).subscribe(
      response => console.log('Shoes data submitted successfully', response),
      error => console.error('Error submitting shoes data', error)
    );
  }

  onSubmitClothes() {
    const headers = this.getAuthHeaders();
  
    // Prepare the DTO as JSON
    const clothesDto = {
      title: this.vendorFormClothes.get('title')?.value || '',
      description: this.vendorFormClothes.get('description')?.value || '',
      price: this.vendorFormClothes.get('price')?.value || '',
      size: this.vendorFormClothes.get('size')?.value || '',
      subtype_clothes: this.vendorFormClothes.get('subtype_clothes')?.value || '',
      sex: this.vendorFormClothes.get('sex')?.value || ''
    };
  
    const formData = new FormData();
    formData.append('createClothesDto', new Blob([JSON.stringify(clothesDto)], { type: 'application/json' })); // Convert DTO to Blob
    if (this.selectedFileClothes) {
      formData.append('image', this.selectedFileClothes);
    }
  
    this.http.post('http://localhost:9090/api/v1/clothes/add', formData, { headers }).subscribe(
      response => console.log('Clothes data submitted successfully', response),
      error => console.error('Error submitting clothes data', error)
    );
  }
}
