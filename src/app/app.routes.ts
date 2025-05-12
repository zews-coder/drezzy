import { Routes } from '@angular/router';
import { CustomerLoginComponent } from './components/customer/customer-login/customer-login.component';
import { AccessoriesComponent } from './components/customer/men/accessories/accessories.component';
import { ClothingComponent } from './components/customer/men/clothing/clothing.component';
import { HomePageComponent } from './components/customer/men/home-page/home-page.component'; 
import { NavbarComponent } from './components/customer/men/navbar/navbar.component';
import { NewComponent } from './components/customer/men/new/new.component';
import { SaleComponent } from './components/customer/men/sale/sale.component';
import { ShoesComponent } from './components/customer/men/shoes/shoes.component';
import { TrendsComponent } from './components/customer/men/trends/trends.component';
import { SignInComponent } from './components/customer/sign-in/sign-in.component';
import { UserComponent } from './components/customer/men/user/user.component';
import { BagComponent } from './components/customer/men/bag/bag.component';
import { WishlistComponent } from './components/customer/men/wishlist/wishlist.component';
import { PaymentComponent } from './components/customer/payment/payment.component';
import { SingleArticleComponent } from './components/customer/single-article/single-article.component';
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
    { path: '', component: HomePageComponent },
    { path: 'men/accessories', component: AccessoriesComponent },
    { path: 'men/clothing', component: ClothingComponent },
    { path: 'men/navbar', component: NavbarComponent },
    { path: 'men/new', component: NewComponent },
    { path: 'men/sale', component: SaleComponent },
    { path: 'men/shoes', component: ShoesComponent },
    { path: 'men/trends', component: TrendsComponent },
    { path: 'men/wishlist', component: WishlistComponent },
    { path: 'men/bag', component: BagComponent },
    { path: 'men/user', component: UserComponent },
    { path: "customer-login", component: CustomerLoginComponent },
    { path: "customer-signin", component: SignInComponent },
    { path: "article", component: SingleArticleComponent },
    { path: "payment", component: PaymentComponent },
    //VENDOR PAGES
    { path: "vendor-stats", component: StatsComponent },
    { path: "vendor-history", component: VendorHistoryComponent },
    { path: "vendor-shipped", component: VendorShippedComponent },
    { path: "vendor-orders",component: VendorOrdersComponent},
    { path: "vendor-add",component: VendorAddComponent},
    { path: "vendor-articles", component: VendorArticlesComponent},
    { path: "vendor-home",component: VendorHomeComponent},
    { path: "vendor-login",component: VendorLoginComponent},
];
