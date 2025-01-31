import { Routes } from '@angular/router';

import { HomePageComponent } from './components/customer/home-page/home-page.component';
import { CustomerLoginComponent } from './components/customer/customer-login/customer-login.component';

import { VendorLoginComponent } from './components/vendor/vendor-login/vendor-login.component';
import { VendorHomeComponent } from './components/vendor/vendor-home/vendor-home.component';
import { VendorArticlesComponent } from './components/vendor/vendor-articles/vendor-articles.component';
import { VendorAddComponent } from './components/vendor/vendor-add/vendor-add.component';
import { VendorOrdersComponent } from './components/vendor/vendor-orders/vendor-orders.component';
import { VendorShippedComponent } from './components/vendor/vendor-shipped/vendor-shipped.component';
import { VendorHistoryComponent } from './components/vendor/vendor-history/vendor-history.component';
import { StatsComponent } from './components/vendor/stats/stats.component';

export const routes: Routes = [
    //CUSTOMER PAGES
    {
        path: "",
        component: HomePageComponent
    },
    {
        path: "customer-login",
        component: CustomerLoginComponent
    },

    //VENDOR PAGES
    {
        path: "vendor-stats",
        component: StatsComponent
    },
    {
        path: "vendor-history",
        component: VendorHistoryComponent
    },
    {
        path: "vendor-shipped",
        component: VendorShippedComponent
    },
    {
        path: "vendor-orders",
        component: VendorOrdersComponent
    },
    {
        path: "vendor-add",
        component: VendorAddComponent
    },
    {
        path: "vendor-articles",
        component: VendorArticlesComponent
    },
    {
        path: "vendor-home",
        component: VendorHomeComponent
    },
    {
        path: "vendor-login",
        component: VendorLoginComponent
    },
];
