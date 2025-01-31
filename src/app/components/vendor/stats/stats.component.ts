import { Component } from '@angular/core';
import { VendorHomeComponent } from "../vendor-home/vendor-home.component";

@Component({
  selector: 'app-stats',
  imports: [VendorHomeComponent],
  templateUrl: './stats.component.html',
  styleUrl: './stats.component.css'
})
export class StatsComponent {

}
