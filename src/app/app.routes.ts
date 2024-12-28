import { Routes } from '@angular/router';
import { AppComponent } from './components/app/app.component';
import { HomePageComponent } from './components/customer/home-page/home-page.component';
import { CustomerLoginComponent } from './components/customer/customer-login/customer-login.component';
import { VendorLoginComponent } from './components/vendor/vendor-login/vendor-login.component';

export const routes: Routes = [
    {
        path: "",
        component: HomePageComponent
    },
    {
        path: "customer-login",
        component: CustomerLoginComponent
    },
    {
        path: "vendor-login",
        component: VendorLoginComponent
    }
];
