import { Component } from '@angular/core';
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";

@Component({
  selector: 'app-vendor-orders',
  imports: [VendorHomeComponent],
  templateUrl: './vendor-orders.component.html',
  styleUrl: './vendor-orders.component.css'
})
export class VendorOrdersComponent {

}
