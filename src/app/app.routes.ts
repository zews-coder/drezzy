import { Routes } from '@angular/router';
import { AppComponent } from './components/app/app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { CustomerLoginComponent } from './components/customer-login/customer-login.component';

export const routes: Routes = [
    {
        path: "",
        component: HomePageComponent
    },
    {
        path: "customer-login",
        component: CustomerLoginComponent
    }
];
